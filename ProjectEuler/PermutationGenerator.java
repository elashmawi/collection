
import java.util.*;

public class PermutationGenerator {

    static TreeSet<Comparable> base = new TreeSet(new Comparator<Comparable>() {
        @Override
        public int compare(Comparable o1, Comparable o2) {
            return o1.compareTo(o2);
        }
    });
    static ArrayDeque<Comparable> acc = new ArrayDeque();
    static ArrayDeque<Comparable[]> permutations = new ArrayDeque();

    static void generate() {
        if (base.isEmpty()) {
            permutations.add(acc.toArray(new Comparable[acc.size()]));
        } else {
            for (Comparable i : base.toArray(new Comparable[base.size()])) {
                acc.add(i);
                base.remove(i);
                generate();
                base.add(i);
                acc.pollLast();
            }
        }
    }
}

