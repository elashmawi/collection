
public class _40 {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder(1000000);
        int i = 1;
        while (sb.length() <= 1000000) {
            sb.append(i);
            i++;
        }System.out.println((sb.charAt(0)-48)*(sb.charAt(9)-48)*(sb.charAt(99)-48)*(sb.charAt(999)-48)*(sb.charAt(9999)-48)*(sb.charAt(99999)-48)*(sb.charAt(999999)-48));
    }
}
