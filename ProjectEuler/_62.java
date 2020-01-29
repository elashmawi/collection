
import java.util.*;

public class _62 {

    static int getNext(int pwr, int prev) {
        char[] prevSorted = Long.toString((long) Math.pow(prev, pwr)).toCharArray();
        Arrays.sort(prevSorted);
        for (int i = prev + 1; Long.toString((long) Math.pow(i, pwr)).length() == prevSorted.length; i++) {
            char[] iSorted = Long.toString((long) Math.pow(i, pwr)).toCharArray();
            Arrays.sort(iSorted);
            if (Arrays.equals(prevSorted, iSorted)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        TreeSet<Integer> done = new TreeSet(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        int pwr = 3, perms = 5, i = 2;
        boolean found = false;
        while (!found) {
            if (!done.contains(i)) {
                int counter = 1, next = i, nextOfNext = getNext(pwr, next);
                done.add(next);
                while (nextOfNext > next) {
                    counter++;
                    next = nextOfNext;
                    nextOfNext = getNext(pwr, next);
                    done.add(next);
                }
                if (counter == perms) {
                    System.out.println((long) Math.pow(i, pwr));
                    found = true;
                }
            }
            i++;
        }
    }
}
