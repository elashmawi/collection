
public class _622 {

    public static void main(String[] args) {
        int s = 60;
        //Observation: A riffle shuffle is a permutation with i -> (2i) mod (n-1) (with i starting at 0, rather than 1)
        //=> 2^s mod (n-1) = 1
        //<=> (2^s - 1) mod (n-1) = 0
        //=> Candidates for n-1 are the divisors of 2^s - 1
        long[] candidates = NumberTheory.divisors(NumberTheory.trialDivision((long) Math.pow(2, s) - 1), 1);
        long result = 0;
        for (int i = 1; i < candidates.length; i++) {
            long tmp = 2 % candidates[i];
            long order = 1;
            while (tmp != 1) {
                tmp = (2 * tmp) % candidates[i];
                order++;
            }
            if (order == s) {
                result += candidates[i] + 1;
            }
        }
        System.out.println(result);
    }
}
