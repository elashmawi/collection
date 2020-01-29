
public class _21 {

    static int sumPropDiv(int n) {
        int result = 1;
        double sqrt = Math.sqrt(n);
        if (sqrt == (int) sqrt) {
            result += sqrt;
        }
        for (int i = 2; i < sqrt; i++) {
            if (n % i == 0) {
                result += i + n / i;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        boolean[] amicable = new boolean[10000];
        int result = 0, tmp;
        for (int i = 3; i < amicable.length; i++) {
            if (!amicable[i]) {
                tmp = sumPropDiv(i);
                if (tmp != i) {
                    if (sumPropDiv(tmp) == i) {
                        amicable[i] = true;
                        if (tmp < amicable.length) {
                            amicable[tmp] = true;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < amicable.length; i++) {
            if (amicable[i]) {
                System.out.println(i);
                result += i;
            }
        }
        System.out.println(result);
    }
}
