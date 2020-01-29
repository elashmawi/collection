
import java.util.*;

public class _68 {

    public static void main(String[] args) {
        Integer[] config;
        String tmp;
        TreeSet<Integer> conditions;
        int minIndex;
        LinkedList<String> strings = new LinkedList();
        for (int sum = 6; sum < 28; sum++) {
            for (int i = 11111; i < 100000; i++) {
                tmp = Integer.toString(i);
                config = new Integer[10];
                for (int j = 0; j < tmp.length(); j++) {
                    config[j + 5] = tmp.charAt(j) - 48;
                }
                for (int j = 0; j < 5; j++) {
                    config[j] = sum - config[j + 5] - config[5 + (j + 1) % 5];
                }
                conditions = new TreeSet(Arrays.asList(config));
                if (conditions.size() == 10 && conditions.first() > 0 && conditions.last() < 11) {
                    minIndex = 0;
                    for (int k = 1; k < 5; k++) {
                        if (config[k] < config[minIndex]) {
                            minIndex = k;
                        }
                    }
                    tmp = "";
                    for (int k = 0; k < 5; k++) {
                        tmp += config[(minIndex + k) % 5] + "" + config[(minIndex + k) % 5 + 5] + "" + config[5 + ((minIndex + k) % 5 + 1) % 5];
                    }
                    strings.add(tmp);
                }
            }
        }
        Collections.sort(strings);
        System.out.println(strings.peekLast());
    }
}
