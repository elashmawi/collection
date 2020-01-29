/*return full rCounter(s,S) functionality?
static double[] rCounter(int[][] list, char[] sigma, int s) {
        int i = 1;
        for (int[] j : list) {
            j[0] = i;
            i++;
        }
        double[] cost = new double[2];
        int[] counters = new int[list.length];
        for (i = 0; i < Math.pow(s, list.length); i++) {
            int j = 0;
            for (int[] k : list) {
                k[1] = counters[j];
                j++;
            }
            for (j = 0; j < sigma.length; j++) {
                cost[0] += list[sigma[j] - 97][0];
                list[sigma[j] - 97][1] = mod(list[sigma[j] - 97][1] - 1, s);
                if (list[sigma[j] - 97][1] == s - 1) {
                    cost[1] += list[sigma[j] - 97][0] - 1;
                    for (int[] k : list) {
                        if (k[0] < list[sigma[j] - 97][0]) {
                            k[0]++;
                        }
                    }
                    list[sigma[j] - 97][0] = 1;
                }
            }
            inc(counters, s, 0);
        }
        cost[0] /= Math.pow(s, list.length);
        cost[1] /= Math.pow(s, list.length);
        return cost;
    }
*/

//implement dCounter(s,S)?

//rage3 3ala dCounter(s,{s-1})
//generate() (both iterative and recursive): max list length 11 (generate with '12' causes out of memory on ssh)
//dCounter(s,{s-1}): compute min over all s? YES :( ... NO SADFACE :) just till length of sigma - 2
//result in opt treemap vs hashmap (vs arraylist?)
//Character[] to Integer[] (int[] whenever possible) (remove -97)

/*
https://www.geeksforgeeks.org/count-inversions-in-an-array-set-2-using-self-balancing-bst/
https://stackoverflow.com/questions/337664/counting-inversions-in-an-array/23201616#23201616
https://stackoverflow.com/questions/337664/counting-inversions-in-an-array/47845960#47845960
https://stackoverflow.com/questions/337664/counting-inversions-in-an-array/16056139#16056139
https://stackoverflow.com/questions/337664/counting-inversions-in-an-array/33505678#33505678
*/

import java.util.*;

public class IDP {

    static void inc(int[] a, int radix, int i) {
        if (i < a.length) {
            a[a.length - 1 - i] = (a[a.length - 1 - i] + 1) % radix;
            if (a[a.length - 1 - i] == 0) {
                inc(a, radix, i + 1);
            }
        }
    }

    static int mod(int a, int b) {
        return (a % b + b) % b;
    }

    static int fac(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    static void swap(Character[] a, int i, int j) {
        char tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    static ArrayList generate(Character[] a) {
        ArrayList perms = new ArrayList(fac(a.length));
        int[] c = new int[a.length];
        perms.add(Arrays.copyOf(a, a.length));
        int i = 0;
        while (i < a.length) {
            if (c[i] < i) {
                if (i % 2 == 0) {
                    swap(a, 0, i);
                } else {
                    swap(a, c[i], i);
                }
                perms.add(Arrays.copyOf(a, a.length));
                c[i]++;
                i = 0;
            } else {
                c[i] = 0;
                i++;
            }
        }
        return perms;
    }

    static int inversions(Character[] perm1, Character[] perm2) {
        int result = 0;
        int[] tmp = new int[perm1.length];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = Arrays.asList(perm1).indexOf(perm2[i]);
        }
        for (int i = 0; i < tmp.length; i++) {
            for (int j = i + 1; j < tmp.length; j++) {
                if (tmp[i] > tmp[j]) {
                    result++;
                }
            }
        }
        return result;
    }

    static Integer[][] opt(char[] sigma, ArrayList<Character[]> perms) {
        TreeSet<Integer[]> result = new TreeSet(new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return o1[0].equals(o2[0]) ? o1[1].compareTo(o2[1]) : o1[0].compareTo(o2[0]);
            }
        });
        int[] configs = new int[sigma.length];
        for (long i = 0; i < Math.pow(perms.size(), sigma.length); i++) {
            Integer[] tmp = {sigma.length, inversions(perms.get(0), perms.get(configs[0]))};
            for (int j = 0; j < sigma.length; j++) {
                tmp[0] += Arrays.asList(perms.get(configs[j])).indexOf(sigma[j]);
            }
            for (int j = 0; j < configs.length - 1; j++) {
                tmp[1] += inversions(perms.get(configs[j]), perms.get(configs[j + 1]));
            }
            boolean add = true;
            for (Integer[] j : result.toArray(new Integer[result.size()][])) {
                if ((tmp[1] >= j[1] && tmp[0] + tmp[1] >= j[0] + j[1])) {
                    add = false;
                } else if ((tmp[1] <= j[1] && tmp[0] + tmp[1] < j[0] + j[1])) {
                    result.remove(j);
                }
            }
            if (add) {
                result.add(tmp);
            }
            inc(configs, perms.size(), 0);
            if (i % Math.ceil(Math.pow(perms.size(), sigma.length) / 100) == 0) {
                System.out.println((int) (i * 100 / Math.pow(perms.size(), sigma.length)) + "%");
            }
        }
        return result.toArray(new Integer[result.size()][]);
    }

    static double[] dCounter(int[][] list, char[] sigma, int s) {
        int i = 1;
        for (int[] j : list) {
            j[0] = i;
            i++;
        }
        double[] cost = new double[2];
        for (int[] j : list) {
            j[1] = 0;
        }
        for (i = 0; i < sigma.length; i++) {
            cost[0] += list[sigma[i] - 97][0];
            list[sigma[i] - 97][1] = mod(list[sigma[i] - 97][1] - 1, s);
            if (list[sigma[i] - 97][1] == s - 1) {
                cost[1] += list[sigma[i] - 97][0] - 1;
                for (int[] k : list) {
                    if (k[0] < list[sigma[i] - 97][0]) {
                        k[0]++;
                    }
                }
                list[sigma[i] - 97][0] = 1;
            }
        }
        return cost;
    }    

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.print("List size: ");
        int n = scn.nextInt();
        int[][] list = new int[n][2];
        scn.nextLine();
        System.out.print("Sigma: ");
        char[] sigma = scn.nextLine().toCharArray();
        System.out.print("OPT? ");
        boolean opt = scn.nextLine().equalsIgnoreCase("yes");
        long t = System.nanoTime();
        if (!opt) {
            System.out.print("s = ");
            System.out.println(Arrays.toString(dCounter(list, sigma, scn.nextInt())));
        } else {
            Character[] asd = new Character[n];
            for (int i = 0; i < n; i++) {
                asd[i] = (char) (i + 97);
            }
            ArrayList perms = generate(asd);
            System.out.println(Arrays.deepToString(opt(sigma, perms)));
        }
        System.out.println((System.nanoTime() - t) / 1000000000d);
        scn.close();
    }
}
