//randomization idea: low prob of making the opposite choice when choosing slow or fast? (normal uniform suffices)????
//test lpt against each other (perhaps also against randomization idea described one line above) in hopes of finding a better bound???
//analysis against preemptive opt?
//LPTs against GREEDYs?
//bound second error of bad partitioning???
//berman no improvement for 2 machine classes case? proof by gegenbsp?

import java.util.*;
import java.util.function.*;

public class MA {

    static int jobs, machines, slowMachines, speed;
    static int[] sigma;
    static ArrayDeque<Integer>[] cfg;

    static void printSeparator() {
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
    }

    static int computeMakespan() {
        int result = Integer.MIN_VALUE;
        for (int j = 0; j < machines; j++) {
            int load = 0;
            for (int k : cfg[j]) {
                load += k * (j < slowMachines ? speed : 1);
            }
            result = Math.max(result, load);
        }
        return result;
    }

    static void initializeCfg() {
        cfg = new ArrayDeque[machines];
        for (int j = 0; j < machines; j++) {
            cfg[j] = new ArrayDeque();
        }
    }

    static int[] lpt() {
        int[] a = Arrays.copyOf(sigma, jobs);
        Arrays.sort(a);
        for (int i = 0; i < jobs / 2; i++) {
            int tmp = a[i];
            a[i] = a[jobs - 1 - i];
            a[jobs - 1 - i] = tmp;
        }
        return a;
    }

    static int greedy(int[] jobSize, int job, int range, int[] load) {
        int min = Integer.MAX_VALUE, machine = 0;
        for (int i = range < 2 ? 0 : slowMachines; i < (range > 0 ? machines : slowMachines); i++) {
            int tmp = (i < slowMachines ? speed : 1) * (load[i] + jobSize[job]);
            if (tmp < min) {
                min = tmp;
                machine = i;
            }
        }
        return machine;
    }

    static int greedy(int[] jobSize) {
        initializeCfg();
        int[] load = new int[machines];
        for (int i = 0; i < jobs; i++) {
            int machine = greedy(jobSize, i, 1, load);
            load[machine] += jobSize[i];
            cfg[machine].add(jobSize[i]);
        }
        /*System.out.println("LPT = " + computeMakespan() + ". Corresponding schedule:");        
        System.out.println();
        printCfg(cfg);*/
        return computeMakespan();
    }

    static int greedyLoad(int[] jobSize) {
        initializeCfg();
        int[] load = new int[machines];
        int loadSlow = 0, loadFast = 0;
        for (int i = 0; i < jobs; i++) {
            int machine = greedy(jobSize, i, (loadFast + jobSize[i]) * slowMachines <= (loadSlow + jobSize[i]) * speed * (machines - slowMachines) ? 2 : 0, load);
            if (machine < slowMachines) {
                loadSlow += jobSize[i];
            } else {
                loadFast += jobSize[i];
            }
            load[machine] += jobSize[i];
            cfg[machine].add(jobSize[i]);
        }
        return computeMakespan();
    }

    static int bestFitGreedy(int[] jobSize) {
        initializeCfg();
        int[] load = new int[machines];
        int cMax = 0;
        for (int i = 0; i < jobs; i++) {
            int machine = -1, loadTmp = Integer.MIN_VALUE;
            for (int j = 0; j < machines; j++) {
                int tmp = (j < slowMachines ? speed : 1) * (load[j] + jobSize[i]);
                if (tmp < cMax && tmp > loadTmp) {
                    machine = j;
                }
            }
            if (machine < 0) {
                machine = greedy(jobSize, i, 1, load);
            }
            load[machine] += jobSize[i];
            cfg[machine].add(jobSize[i]);
            cMax = Math.max(cMax, (machine < slowMachines ? speed : 1) * load[machine]);
        }
        return cMax;
    }

    static int mean(int[] jobSize) {
        initializeCfg();
        int total = 0;
        for (int i = 0; i < jobs; i++) {
            total += jobSize[i];
        }
        int[] load = new int[machines];
        //int sum = 0;
        for (int i = 0; i < jobs; i++) {
            int machine = -1;
            if (slowMachines < (machines - slowMachines) * speed) {
                machine = greedy(jobSize, i, slowMachines * jobSize[i] * jobs > total * (machines - slowMachines) * speed ? 2 : (slowMachines * jobSize[i] * jobs < total * (machines - slowMachines) * speed ? 0 : 1), load);
            } else {
                machine = greedy(jobSize, i, (machines - slowMachines) * speed * jobSize[i] * jobs > total * slowMachines ? 2 : ((machines - slowMachines) * speed * jobSize[i] * jobs < total * slowMachines ? 0 : 1), load);
            }
            load[machine] += jobSize[i];
            cfg[machine].add(jobSize[i]);
            //sum += jobSize[i];
        }
        return computeMakespan();
    }

    static int mean2(int[] jobSize) {
        initializeCfg();
        int[] load = new int[machines];
        int sum = 0;
        for (int i = 0; i < jobs; i++) {
            int machine = greedy(jobSize, i, jobSize[i] * i >= sum ? 2 : 0, load);
            load[machine] += jobSize[i];
            cfg[machine].add(jobSize[i]);
            sum += jobSize[i];
        }
        return computeMakespan();
    }

    static int mean3(int[] jobSize) {
        initializeCfg();
        int[] load = new int[machines];
        int sum = 0;
        for (int i = 0; i < jobs; i++) {
            int machine = greedy(jobSize, i, jobSize[i] * i > sum ? 2 : (jobSize[i] * i < sum ? 0 : 1), load);
            load[machine] += jobSize[i];
            cfg[machine].add(jobSize[i]);
            sum += jobSize[i];
        }
        return computeMakespan();
    }

    static int pastMean(int[] jobSize) {
        initializeCfg();
        int[] load = new int[machines];
        int sum = 0;
        for (int i = 0; i < jobs; i++) {
            int machine = greedy(jobSize, i, jobSize[i] * (i + 1) > sum ? 2 : 0, load);
            load[machine] += jobSize[i];
            cfg[machine].add(jobSize[i]);
            sum += jobSize[i];
        }
        return computeMakespan();
    }

    static int pastMean2(int[] jobSize) {
        initializeCfg();
        int[] load = new int[machines];
        int sum = 0;
        for (int i = 0; i < jobs; i++) {
            int machine = greedy(jobSize, i, jobSize[i] * (i + 1) >= sum ? 2 : 0, load);
            load[machine] += jobSize[i];
            cfg[machine].add(jobSize[i]);
            sum += jobSize[i];
        }
        return computeMakespan();
    }

    /*static int greedyMax(int[] jobSize) {
        initializeCfg();
        int[] load = new int[machines];
        int slowMax = 0, fastMax = 0;
        for (int i = 0; i < jobs; i++) {
            int slowMachine = lpt(i, 0, load), fastMachine = lpt(i, 2, load);
            if () {
            }
            load[machine] += jobSize[i];
            cfg[machine].add(jobSize[i]);
        }        
    }*/
    //https://en.wikipedia.org/wiki/Inverse_transform_sampling#The_method
    static int uniform(double a, double b) {
        //return (int) Math.round(a + (b - a) * Math.random());
        return (int) a + new Random().nextInt((int) (b - a) + 1);
    }

    static int uQuadratic(double a, double b) {
        return (int) Math.round(((b - a) * Math.cbrt(2 * Math.random() - 1) + a + b) / 2);
    }

    static int arcsine(double a, double b) {
        return (int) Math.round((b - a) * Math.pow(Math.sin(Math.random() * Math.PI / 2), 2) + a);
    }

    public static void main(String[] args) {
        ToIntBiFunction[] d = {(ToIntBiFunction<Double, Double>) Program::uniform, (ToIntBiFunction<Double, Double>) Program::uQuadratic, (ToIntBiFunction<Double, Double>) Program::arcsine};
        double max = 1;
        while (true) {
            jobs = uniform(2, 5000);
            machines = d[uniform(0, 2)].applyAsInt(2d, (double) jobs);//if m>n, the same c can be achieved with only the fastest n machines
            slowMachines = d[uniform(1, 2)].applyAsInt(1d, Math.max(1d, machines - 2));
            speed = d[uniform(1, 2)].applyAsInt(2d, Integer.MAX_VALUE / (double) jobs);
            sigma = new int[jobs];
            int r = uniform(1, 2);
            for (int i = 0; i < jobs; i++) {
                sigma[i] = d[r].applyAsInt(1d, Integer.MAX_VALUE / (double) jobs / speed);
            }
            /*double min = Double.MAX_VALUE;
            min = Math.min(min, greedy(lpt()));
            min = Math.min(min, bestFitGreedy(lpt()));
            min = Math.min(min, mean(lpt()));
            min = Math.min(min, mean2(lpt()));
            min = Math.min(min, pastMean(lpt()));
            min = Math.min(min, pastMean2(lpt()));
            double tmp = greedyLoad(lpt()) / min;*///add extra factor of 1/jobs to prevent overflow
            //double tmp = bestFit / greedy;
            double tmp = greedyLoad(lpt()) / greedy(lpt());
            if (tmp > max) {
                max = tmp;
                System.out.println("Job sequence: q * " + Arrays.toString(sigma));
                System.out.println(slowMachines + " machine" + (slowMachines > 1 ? "s" : "") + " with speed 1; " + (machines - slowMachines) + " machine" + (machines - slowMachines > 1 ? "s" : "") + " with speed " + speed);
                System.out.println("c = " + tmp);
                printSeparator();
            }
        }
    }
    /*static int opt;
    static ArrayDeque<Integer> onSlow, onFast;
    static ArrayDeque<ArrayDeque<Integer>[]> OPTs;

    static ArrayDeque<Integer>[] deepCloneCfg() {
        ArrayDeque<Integer>[] result = new ArrayDeque[machines];
        for (int i = 0; i < machines; i++) {
            result[i] = new ArrayDeque();
        }
        for (int i = 0; i < machines; i++) {
            result[i].addAll(cfg[i]);
        }
        return result;
    }

    static void assign(boolean toSlow, int openedBins) {//openedBins is the number of utilized machines in the respective class of machines; reference to bin packing
        ArrayDeque<Integer> toSchedule = toSlow ? onSlow : onFast;
        if (!toSchedule.isEmpty()) {
            int firstMachine = toSlow ? 0 : slowMachines,
                    lastMachine = Math.min(firstMachine + openedBins, (toSlow ? slowMachines : machines) - 1);
            for (int i = firstMachine; i <= lastMachine; i++) {
                cfg[i].add(toSchedule.poll());
                assign(toSlow, openedBins + (i == lastMachine ? 1 : 0));
                toSchedule.addFirst(cfg[i].pollLast());
            }
        } else if (toSlow) {
            assign(false, 0);
        } else {
            int cMax = computeMakespan();
            if (cMax <= opt) {
                if (cMax < opt) {
                    opt = cMax;
                    OPTs = new ArrayDeque();
                }
                OPTs.add(deepCloneCfg());
            }
            //if (cMax < opt) {
            //    opt = cMax;
            //    OPTs = new ArrayDeque();
            //    OPTs.add(deepCloneCfg());
            //}
        }
    }

    static void nonNaiveBruteForce() {
        for (int i = 0; i < Math.pow(2, jobs); i++) {//partition jobs onto slow and fast machines
            String s = Integer.toString(i, 2);
            s = "0".repeat(jobs - s.length()).concat(s);
            onSlow = new ArrayDeque();
            onFast = new ArrayDeque();
            for (int j = 0; j < jobs; j++) {
                (s.charAt(j) < 49 ? onSlow : onFast).add(sigma[j]);
            }
            assign(true, 0);
        }
    }

    static double opt() {
        opt = Integer.MAX_VALUE;
        OPTs = new ArrayDeque();
        initializeCfg();
        nonNaiveBruteForce();
        System.out.println("OPT = " + opt + ". " + OPTs.size() + " possible optimum schedule" + (OPTs.size() > 1 ? "s" : "") + ":");
        System.out.println();
        for (ArrayDeque<Integer>[] i : OPTs) {
            if (!Arrays.equals(i, OPTs.peek())) {
                printSeparator();
            }
            printCfg(i);
        }
        return opt;
    }

    static void printCfg(ArrayDeque<Integer>[] cfg) {
        for (int j = 0; j < slowMachines; j++) {
            System.out.println(cfg[j]);
        }
        System.out.println();
        for (int j = slowMachines; j < machines; j++) {
            System.out.println(cfg[j]);
        }
    }

    static double exponential(double mean) {
        return -mean * Math.log(Math.random());
    }*/
}
