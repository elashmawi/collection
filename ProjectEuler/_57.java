
import java.math.BigInteger;

class Rational {

    BigInteger numerator, denominator;

    Rational(BigInteger numerator, BigInteger denominator) {
        BigInteger gcd = numerator.gcd(denominator);
        this.numerator = numerator.divide(gcd);
        this.denominator = denominator.divide(gcd);
    }

    Rational add(Rational r) {
        BigInteger numerator = this.numerator.multiply(r.denominator).add(r.numerator.multiply(this.denominator));
        BigInteger denominator = this.denominator.multiply(r.denominator);
        BigInteger gcd = numerator.gcd(denominator);
        numerator = numerator.divide(gcd);
        denominator = denominator.divide(gcd);
        return new Rational(numerator, denominator);
    }

    Rational divide(Rational r) {
        BigInteger numerator = this.numerator.multiply(r.denominator);
        BigInteger denominator = this.denominator.multiply(r.numerator);
        BigInteger gcd = numerator.gcd(denominator);
        numerator = numerator.divide(gcd);
        denominator = denominator.divide(gcd);
        return new Rational(numerator, denominator);
    }

    public String toString() {
        return this.numerator + "/" + this.denominator;
    }
}

public class _57 {

    public static void main(String[] args) {
        int result = 0;
        Rational one = new Rational(BigInteger.ONE, BigInteger.ONE), tmp = one;
        for (int i = 0; i < 1000; i++) {
            tmp = one.add(one.divide(one.add(tmp)));
            if (tmp.numerator.toString().length() > tmp.denominator.toString().length()) {
                result++;
            }
        }
        System.out.println(result);
    }
}
