#include"EDFhandler.h"
#include<forward_list>
using namespace std;
using namespace Eigen;

class AcquisitionPose {
    Vector3f source, detectorMidPoint, detectorCorner;
public:

    AcquisitionPose(const Vector3f&source, const Vector3f&detectorMidPoint, const Vector3f&detectorCorner) {
        this->source = source;
        this->detectorMidPoint = detectorMidPoint;
        this->detectorCorner = detectorCorner;
    }

    forward_list<pair<Vector3f, Vector3f>>getRays() {
        Vector3f v1(detectorCorner - detectorMidPoint);
        Vector3f v2(v1.cross(source - detectorMidPoint).normalized() * v1.norm());
        Vector3f v3(v2 - v1);
        v3.normalize();
        v3 *= 0.04;
        Vector3f v4(-v2 - v1);
        v4.normalize();
        v4 *= 0.04;
        forward_list<pair<Vector3f, Vector3f>>result;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                result.push_front(pair<Vector3f, Vector3f>(source, detectorCorner + (i + 0.5) * v3 + (j + 0.5) * v4 - source));
            }
        }
        return result;
    }
};

class SystemMatrix {
    Volume vol;
    forward_list<AcquisitionPose>acquisitionPoses;
    float lambda, threshold, noiseFactor, mean, stddev;
public:

    SystemMatrix(const Volume&vol, const forward_list<AcquisitionPose>&acquisitionPoses, const float&lambda, const float&threshold, const float&noiseFactor, const float&mean, const float&stddev) {
        this->vol = vol;
        this->acquisitionPoses = acquisitionPoses;
        this->lambda = lambda;
        this->threshold = threshold;
        this->noiseFactor = noiseFactor;
        this->mean = mean;
        this->stddev = stddev;
    }

    float intersect(const pair<Vector3f, Vector3f>&r) {
        float tmin, tmax, tymin, tymax, tzmin, tzmax;
        float divx = 1 / get<1>(r) (0);
        if (divx >= 0) {
            tmin = (vol.getBoundingBox().min()(0) - get<0>(r) (0)) * divx;
            tmax = (vol.getBoundingBox().max()(0) - get<0>(r) (0)) * divx;
        } else {
            tmin = (vol.getBoundingBox().max()(0) - get<0>(r) (0)) * divx;
            tmax = (vol.getBoundingBox().min()(0) - get<0>(r) (0)) * divx;
        }
        float divy = 1 / get<1>(r) (1);
        if (divy >= 0) {
            tymin = (vol.getBoundingBox().min()(1) - get<0>(r) (1)) * divy;
            tymax = (vol.getBoundingBox().max()(1) - get<0>(r) (1)) * divy;
        } else {
            tymin = (vol.getBoundingBox().max()(1) - get<0>(r) (1)) * divy;
            tymax = (vol.getBoundingBox().min()(1) - get<0>(r) (1)) * divy;
        }
        if ((tmin > tymax) || (tymin > tmax)) {
            return-1;
        }
        if (tymin > tmin) {
            tmin = tymin;
        }
        if (tymax < tmax) {
            tmax = tymax;
        }
        float divz = 1 / get<1>(r) (2);
        if (divz >= 0) {
            tzmin = (vol.getBoundingBox().min()(2) - get<0>(r) (2)) * divz;
            tzmax = (vol.getBoundingBox().max()(2) - get<0>(r) (2)) * divz;
        } else {
            tzmin = (vol.getBoundingBox().max()(2) - get<0>(r) (2)) * divz;
            tzmax = (vol.getBoundingBox().min()(2) - get<0>(r) (2)) * divz;
        }
        if ((tmin > tzmax) || (tzmin > tmax)) {
            return-1;
        }
        return max(tmin, tzmin);
    }
    //from Williams, A., Barrus, S., Morley, R. K., Shirley, P. An Efficient and Robust Ray–Box Intersection Algorithm.

    forward_list<Vector3i>rayTrace(const pair<Vector3f, Vector3f>&r) {
        forward_list<Vector3i>list;
        float t(intersect(r));
        if (t >= 0) {
            Vector3i numVoxels(vol.getNumVoxels());
            Vector3f entryPoint(get<0>(r) + t * get<1>(r));
            int X(min(int(numVoxels(0)), int(entryPoint(0) / vol.getSpacing()(0))));
            int Y(min(int(numVoxels(1)), int(entryPoint(1) / vol.getSpacing()(1))));
            int Z(min(int(numVoxels(2)), int(entryPoint(2) / vol.getSpacing()(2))));
            list.push_front(Vector3i(X, Y, Z));
            int stepX(get<1>(r) (0) / abs(get<1>(r) (0)));
            int stepY(get<1>(r) (1) / abs(get<1>(r) (1)));
            int stepZ(get<1>(r) (2) / abs(get<1>(r) (2)));
            float tDeltaX(abs(vol.getSpacing()(0) / get<1>(r) (0)));
            float tDeltaY(abs(vol.getSpacing()(1) / get<1>(r) (1)));
            float tDeltaZ(abs(vol.getSpacing()(2) / get<1>(r) (2)));
            float tMaxX(tDeltaX);
            float tMaxY(tDeltaY);
            float tMaxZ(tDeltaZ);
            while (1) {
                if (tMaxX < tMaxY) {
                    if (tMaxX < tMaxZ) {
                        X += stepX;
                        if (X > numVoxels(0) - 1 || X < 0) {
                            break;
                        }
                        tMaxX += tDeltaX;
                    } else {
                        Z += stepZ;
                        if (Z > numVoxels(2) - 1 || Z < 0) {
                            break;
                        }
                        tMaxZ += tDeltaZ;
                    }
                } else {
                    if (tMaxY < tMaxZ) {
                        Y += stepY;
                        if (Y > numVoxels(1) - 1 || Y < 0) {
                            break;
                        }
                        tMaxY += tDeltaY;
                    } else {
                        Z += stepZ;
                        if (Z > numVoxels(2) - 1 || Z < 0) {
                            break;
                        }
                        tMaxZ += tDeltaZ;
                    }
                }
                list.push_front(Vector3i(X, Y, Z));
            }
        }
        return list;
    }
    //from Amanatides, J., Woo, A. A Fast Voxel Traversal Algorithm for Ray Tracing.

    int voxelToInt(const Vector3i&voxel) {
        Vector3i numVoxels(vol.getNumVoxels());
        return voxel(0) + voxel(1) * numVoxels(0) + voxel(2) * numVoxels(0) * numVoxels(1);
    }

    forward_list<pair<pair<Vector3f, Vector3f>, float>>forwardProjection() {
        forward_list<pair < pair<Vector3f, Vector3f>, float>>result;
        for (auto acquisitionPose = acquisitionPoses.begin(); acquisitionPose != acquisitionPoses.end(); ++acquisitionPose) {
            forward_list<pair<Vector3f, Vector3f >> rays((*acquisitionPose).getRays());
            for (auto r = rays.begin(); r != rays.end(); ++r) {
                forward_list<Vector3i>voxels(rayTrace(*r));
                float val(0);
                for (auto voxel = voxels.begin(); voxel != voxels.end(); ++voxel) {
                    val += vol.getContent()(voxelToInt(*voxel));
                }
                result.push_front(pair<pair<Vector3f, Vector3f>, float>(*r, val));
            }
        }
        float max(numeric_limits<float>::min());
        float min(numeric_limits<float>::max());
        for (auto rayValuePair = result.begin(); rayValuePair != result.end(); ++rayValuePair) {
            max = std::max(max, get<1>(*rayValuePair));
            min = std::min(min, get<1>(*rayValuePair));
        }
        float maxMinusMin(max - min);
        random_device randomDevice;
        normal_distribution<float>randn(mean, stddev);
        for (auto rayValuePair = result.begin(); rayValuePair != result.end(); ++rayValuePair) {
            get<1>(*rayValuePair) += noiseFactor * maxMinusMin * randn(randomDevice);
        }
        return result;
    };

    VectorXf backProjection() {
        Vector3i numVoxels(vol.getNumVoxels());
        VectorXf result(VectorXf::Zero(numVoxels(0) * numVoxels(1) * numVoxels(2)));
        forward_list<pair < pair<Vector3f, Vector3f>, float>>rayValuePairs(forwardProjection());
        for (auto rayValuePair = rayValuePairs.begin(); rayValuePair != rayValuePairs.end(); ++rayValuePair) {
            forward_list<Vector3i>voxels(rayTrace(get<0>(*rayValuePair)));
            for (auto voxel = voxels.begin(); voxel != voxels.end(); ++voxel) {
                result(voxelToInt(*voxel)) += get<1>(*rayValuePair);
            }
        }
        return result;
    }

    VectorXf operator*(const VectorXf&vector) {
        Vector3i numVoxels(vol.getNumVoxels());
        VectorXf result(VectorXf::Zero(numVoxels(0) * numVoxels(1) * numVoxels(2)));
        for (auto acquisitionPose = acquisitionPoses.begin(); acquisitionPose != acquisitionPoses.end(); ++acquisitionPose) {
            forward_list<pair<Vector3f, Vector3f >> rays((*acquisitionPose).getRays());
            for (auto r = rays.begin(); r != rays.end(); ++r) {
                forward_list<Vector3i>voxels(rayTrace(*r));
                for (auto voxel1 = voxels.begin(); voxel1 != voxels.end(); ++voxel1) {
                    int i(voxelToInt(*voxel1));
                    for (auto voxel2 = voxels.begin(); voxel2 != voxels.end(); ++voxel2) {
                        int j(voxelToInt(*voxel2));
                        result(i) += vector(j);
                    }
                }
            }
        }
        return result + lambda*vector;
    }

    VectorXf conjugateGradient() {
        Vector3i numVoxels(vol.getNumVoxels());
        VectorXf x(VectorXf::Zero(numVoxels(0) * numVoxels(1) * numVoxels(2)));
        VectorXf r(backProjection()- * this*x);
        VectorXf d(r);
        float deltaNew = r.transpose().dot(r);
        while (r.norm() < threshold) {
            VectorXf q(*this*d);
            float alpha(deltaNew / d.transpose().dot(q));
            x += alpha*d;
            r -= alpha*q;
            float deltaOld(deltaNew);
            deltaNew = r.transpose().dot(r);
            d = r + (deltaNew / deltaOld) * d;
        }
        return x;
    }
    //from Shewchuk , J. R. An Introduction to the Conjugate Gradient Method Without the Agonizing Pain.
};
