import java.util.*;

public class euclideantsp {

    public static double f(int i, double a, double b, double c, double d) {
	return Math.pow(-1, i + 1) * a * Math.pow(c, -2 - i) + b * Math.pow(Math.log(d), i + 1) * Math.pow(d, c);
    }

    public static void main(String[] args) {
	Scanner scn = new Scanner(System.in);
	int n = scn.nextInt();
	double p = scn.nextDouble();
	double s = scn.nextDouble();
	double v = scn.nextDouble();
	double a = s / v;
	double b = n / (1000000000 * p);
	double d = Math.pow(Math.log(n) / Math.log(2), Math.sqrt(2));
	double x_n1 = 0;
	double x_n = 1;
	double f0 = f(0, a, b, x_n, d);
	double f1 = f(1, a, b, x_n, d);
	double f2 = f(2, a, b, x_n, d);
	double f3 = f(3, a, b, x_n, d);
	while (Math.abs(x_n1 - x_n) > 0.0000001) {
	    x_n1 = x_n;
	    x_n = x_n - (6 * f0 * f1 * f1 - 3 * f0 * f0 * f2) / (6 * f1 * f1 * f1 - 6 * f0 * f1 * f2 + f0 * f0 * f3);
	    f0 = f(0, a, b, x_n, d);
	    f1 = f(1, a, b, x_n, d);
	    f2 = f(2, a, b, x_n, d);
	    f3 = f(3, a, b, x_n, d);
	}
	double t = a / x_n + b * Math.pow(d, x_n) + a;
	System.out.println(t + " " + x_n);
    }
}
