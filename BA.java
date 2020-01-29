
import java.io.*;
import java.util.*;

//0,0,0,0 discrete and continuous examples
//+1 offset in or out of minmax?
//use offset variable?
//discrete && i == 0 ? "<=b}},0]" : "<b}},0]"
//check possibilities of codeauslagerung in methods for preventing redundancy
//check commented-out code
//split discrete into <,>,= for visibility in visualization
//indeterminate replacement still after piecewise?
//look at all mathematica functions used and reevaluate usefulness (minimalism)
//PlotRange->All,PlotRangePadding->None?
//sortierung aller methoden
//test everything
class Task {

    int successor, predecessors;
    boolean scheduled, finished, offset;
    StringBuilder elapsed;
}

class StrategyTree {

    Integer node;
    TreeMap<Integer, StrategyTree> next = new TreeMap(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    });

    StrategyTree(Integer i) {
        this.node = i;
    }

    StrategyTree get(LinkedList<Integer> finished) {
        StrategyTree t = this;
        for (int i : finished) {
            if (t == null) {
                return null;
            }
            t = t.next.get(i);
        }
        return t;
    }

    public StrategyTree deepCopy() {
        StrategyTree clone = new StrategyTree(this.node);
        if (this.next != null) {
            clone.next = new TreeMap(this.next.comparator());
            Set<Map.Entry<Integer, StrategyTree>> nextTmp = this.next.entrySet();
            for (Map.Entry<Integer, StrategyTree> e : nextTmp) {
                clone.next.put(e.getKey(), e.getValue().deepCopy());
            }
        }
        return clone;
    }
}

public class BA {

    static InputStream in;
    static OutputStream out;
    static boolean discrete, visualize = false, labels = false, edgeLabels = false;
    static int m = 2, visSize, visCtr, visParamCtr;
    static String strategy, file;
    static Task[] tasks;
    static TreeSet<Integer> ready, scheduled = new TreeSet(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    });
    static LinkedList<Integer> finished = new LinkedList(), lastAdded = new LinkedList();
    static int[] level;
    static LinkedList<LinkedList<Integer>> firstM;
    static LinkedList<Object[]> strategies;
    static LinkedList<StringBuilder[]> results = new LinkedList();
    static StringBuilder visualization, vertexRenderingFunction, edgeRenderingFunction;

    static StringBuilder read() throws IOException {
        StringBuilder s = new StringBuilder();
        int b = in.read();
        while (b != 10) {
            s.append((char) b);
            b = in.read();
        }
        return s;
    }

    static void write(Object o) throws IOException {
        out.write(String.valueOf(o).getBytes());
        out.write(10);
        out.flush();
    }

    static StringBuilder writeRead(Object o) throws IOException {
        write(o);
        return read();
    }

    static int level(int i) {
        if (i == 0) {
            return 0;
        } else if (level[i] == 0) {
            level[i] = level(tasks[i].successor) + 1;
        }
        return level[i];
    }

    static int next() {
        if (strategy.equals("hlf")) {
            return (int) ready.first();
        } else if (strategy.equals("rhlf")) {
            ArrayList<Integer> hl = new ArrayList(tasks.length);
            hl.add((int) ready.first());
            Integer next = ready.higher(hl.get(hl.size() - 1));
            while (next != null && level[hl.get(hl.size() - 1)] == level[next]) {
                hl.add(next);
                next = ready.higher(hl.get(hl.size() - 1));
            }
            return hl.get(new Random().nextInt(hl.size()));
        } else {
            return ((StrategyTree) strategies.peek()[1]).get(finished).node;
        }
    }

    static void schedule(int next) {
        ready.remove(next);
        tasks[next].scheduled = true;
        scheduled.add(next);
        lastAdded.add(next);
    }

    static void revertSchedule() {
        int i = lastAdded.pollLast();
        scheduled.remove(i);
        tasks[i].scheduled = false;
        ready.add(i);
    }

    static void firstM(LinkedList<Integer> scheduledTmp, LinkedList<Integer> readyTmp) {
        if (scheduledTmp.size() == m) {
            firstM.add(new LinkedList(scheduledTmp));
        } else {
            int i = readyTmp.poll();
            scheduledTmp.add(i);
            firstM(scheduledTmp, readyTmp);
            scheduledTmp.pollLast();
            if (m - scheduledTmp.size() <= readyTmp.size()) {
                firstM(scheduledTmp, readyTmp);
            }
            readyTmp.addFirst(i);
        }
    }

    static void buildTrees() {
        LinkedList<Integer> scheduledTmp = new LinkedList(scheduled);
        for (int i : scheduledTmp) {
            scheduled.remove(i);
            tasks[tasks[i].successor].predecessors--;
            if (tasks[tasks[i].successor].predecessors == 0) {
                ready.add(tasks[i].successor);
            }
            LinkedList<Object[]> strategiesTmp = new LinkedList();
            LinkedList<Integer> readyTmp = new LinkedList(ready);
            for (Object[] j : strategies) {
                StrategyTree currentTree = ((StrategyTree) j[1]).get(finished);
                if (currentTree != null && (currentTree.node == null || currentTree.node.intValue() == lastAdded.peekLast().intValue())) {
                    if (currentTree.next == null) {
                        currentTree.next = new TreeMap(new Comparator<Integer>() {
                            @Override
                            public int compare(Integer o1, Integer o2) {
                                return o1 - o2;
                            }
                        });
                    }
                    for (Integer k : readyTmp) {
                        currentTree.next.put(i, new StrategyTree(k));
                        strategiesTmp.add(new Object[]{j[0], ((StrategyTree) j[1]).deepCopy()});
                        currentTree.next.remove(i);
                    }
                    if (readyTmp.isEmpty()) {
                        currentTree.next.put(i, new StrategyTree(null));
                        strategiesTmp.add(j);
                    }
                } else {
                    strategiesTmp.add(j);
                }
            }
            strategies = strategiesTmp;
            finished.add(i);
            for (int j : readyTmp) {
                schedule(j);
                buildTrees();
                revertSchedule();
            }
            if (readyTmp.isEmpty()) {
                buildTrees();
            }
            finished.pollLast();
            ready.remove(tasks[i].successor);
            tasks[tasks[i].successor].predecessors++;
            scheduled.add(i);
        }
    }

    static StringBuilder compute(int predecessor, StringBuilder probability) throws IOException {
        int visCurrent = ++visCtr;
        StringBuilder assumptions = new StringBuilder("Assuming[True");
        LinkedList<Integer> scheduledTmp = new LinkedList(scheduled);
        if ((labels || edgeLabels) && !scheduled.isEmpty()) {
            for (int i : finished) {
                assumptions.append("&&a<=x").append(i).append("&&x").append(i).append("<=b");
                if (discrete) {
                    assumptions.append("&&Element[x").append(i).append(",Integers]");
                }
            }
            for (int i : scheduledTmp) {
                assumptions.append("&&a<=x").append(i).append("&&x").append(i).append("<=b");
                if (discrete) {
                    assumptions.append("&&Element[x").append(i).append(",Integers]");
                }
            }
        }
        assumptions.append(',');
        StringBuilder result = new StringBuilder();
        if (labels) {
            result.append(assumptions).append("FullSimplify[PiecewiseExpand[");
        }
        result.append('0');
        for (int i = 0; i < scheduledTmp.size(); i++) {
            int j = scheduledTmp.poll();
            scheduled.remove(j);
            //result.append('+').append(discrete ? "Sum" : "Integrate").append('[');
            result.append('+').append("Piecewise[{{").append(discrete ? "Sum" : "Integrate").append('[');
            StringBuilder prob = new StringBuilder();
            if (edgeLabels) {
                prob.append(assumptions).append("FullSimplify[PiecewiseExpand[");
            }
            prob.append('1');
            for (int k : scheduledTmp) {
                //prob.append('*').append(discrete ? "Sum" : "Integrate").append("[1/(b-Min[b,Max[a,").append(tasks[k].elapsed).append("]]),{x").append(k).append(",Min[b,Max[a,").append(tasks[k].elapsed).append("+x").append(j).append('-').append(tasks[j].elapsed).append("]],b}]");
                prob.append('*').append("Piecewise[{{").append(discrete ? "Sum" : "Integrate").append("[1/(b-Min[b,Max[a,").append(tasks[k].elapsed).append(discrete ? "]]+1),{x" : "]]),{x").append(k).append(",Min[b,Max[a,").append(tasks[k].elapsed).append("+x").append(j).append('-').append(tasks[j].elapsed).append(discrete ? "]]+1,b}]," : "]],b}],").append(tasks[k].elapsed).append("<b}},0]");
            }
            if (edgeLabels) {
                prob = new StringBuilder(writeRead(prob.append("]]]")).toString().replace("Indeterminate", "0"));
            }
            tasks[tasks[j].successor].predecessors--;
            if (tasks[tasks[j].successor].predecessors == 0) {
                ready.add(tasks[j].successor);
            }
            for (int k : scheduledTmp) {
                tasks[k].elapsed.append("+x").append(j).append('-').append(tasks[j].elapsed).append(')');
                tasks[k].elapsed = new StringBuilder("(").append(tasks[k].elapsed);
                tasks[k].offset = true;
            }
            finished.add(j);
            tasks[j].finished = true;
            boolean empty = ready.isEmpty();
            if (!empty) {
                schedule(next());
            }
            //result.append(prob).append("*(x").append(j).append('-').append(tasks[j].elapsed).append('+').append(compute(visCurrent, prob)).append(")/(b-Min[b,Max[a,").append(tasks[j].elapsed).append("]]),{x").append(j).append(",Min[b,Max[a,").append(tasks[j].elapsed).append("]],b}]");
            result.append(prob).append("*(x").append(j).append('-').append(tasks[j].elapsed).append('+').append(compute(visCurrent, prob)).append(")/(b-Min[b,Max[a,").append(tasks[j].elapsed).append(discrete && !tasks[j].offset ? "]]+1),{x" : "]]),{x").append(j).append(",Min[b,Max[a,").append(tasks[j].elapsed).append("]],b}],").append(tasks[j].elapsed).append(discrete && i == 0 ? "<=b}},0]" : "<b}},0]");
            if (!empty) {
                revertSchedule();
            }
            tasks[j].finished = false;
            finished.pollLast();
            for (int k : scheduledTmp) {
                tasks[k].offset = false;
                tasks[k].elapsed.delete(tasks[k].elapsed.length() - 4 - tasks[j].elapsed.length() - (j == 0 ? 1 : (int) Math.floor(Math.log10(j)) + 1), tasks[k].elapsed.length());
                tasks[k].elapsed.delete(0, 1);
            }
            ready.remove(tasks[j].successor);
            tasks[tasks[j].successor].predecessors++;
            scheduled.add(j);
            scheduledTmp.addLast(j);
        }
        if (labels) {
            result = new StringBuilder(writeRead(result.append("]]]")).toString().replace("Indeterminate", "0"));
        }
        if (visualize && !scheduled.isEmpty()) {
            vertexRenderingFunction.append("{Inset[Labeled[Labeled[TreePlot[{");
            StringBuilder erfTmp = new StringBuilder("},Bottom,0,EdgeRenderingFunction->Function[{").append('u').append(visParamCtr).append(",u").append(visParamCtr + 1).append("},{Piecewise[{");
            StringBuilder vrfTmp = new StringBuilder("},Black],Line[u").append(visParamCtr).append("]}],VertexRenderingFunction->Function[{").append('u').append(visParamCtr + 2).append(",u").append(visParamCtr + 3).append("},{Piecewise[{");
            for (int i = 0; i < tasks.length; i++) {
                if (i > 0) {
                    vertexRenderingFunction.append(i).append("->").append(tasks[i].successor).append(',');
                }
                if (tasks[i].finished) {
                    erfTmp.append("{Transparent,u").append(visParamCtr + 1).append("==={").append(i).append(',').append(tasks[i].successor).append("}},");
                    vrfTmp.append("{Transparent,u").append(visParamCtr + 3).append("==").append(i).append("},");
                } else if (tasks[i].scheduled) {
                    vrfTmp.append("{Green,u").append(visParamCtr + 3).append("==").append(i).append("},");
                }
            }
            vertexRenderingFunction.deleteCharAt(vertexRenderingFunction.length() - 1);
            vrfTmp.deleteCharAt(vrfTmp.length() - 1).append("},Blue],PointSize[Large],Point[u").append(visParamCtr + 2).append("]}]],\"");
            if (labels) {
                vrfTmp.append(result);
            }
            vrfTmp.append("\"],\"\",Background->White],u0],u1==").append(visCurrent).append("},");
            if (predecessor > 0) {
                visualization.append(predecessor).append("->").append(visCurrent).append(',');
                erfTmp.deleteCharAt(erfTmp.length() - 1);
                if (edgeLabels) {
                    edgeRenderingFunction.append("{\"").append(probability).append("\",u3==={").append(predecessor).append(',').append(visCurrent).append("}},");
                }
            }
            vertexRenderingFunction.append(erfTmp).append(vrfTmp);
            visParamCtr += 4;
        }
        return result;
    }

    static void process() throws IOException {
        if (visualize) {
            visCtr = 0;
            visParamCtr = 4;
            visualization = new StringBuilder("Export[\"").append(file).append("\",TreePlot[{");
            vertexRenderingFunction = new StringBuilder("},Top,1,VertexRenderingFunction->Function[{u0,u1},Piecewise[{");
            if (edgeLabels) {
                edgeRenderingFunction = new StringBuilder("}]],EdgeRenderingFunction->Function[{u2,u3},{Line[u2],Inset[Piecewise[{");
            }
        }
        StringBuilder result = compute(visCtr, null);
        if (visualize) {
            visualization.deleteCharAt(visualization.length() - 1).append(vertexRenderingFunction.deleteCharAt(vertexRenderingFunction.length() - 1));
            if (edgeLabels) {
                visualization.append(edgeRenderingFunction.deleteCharAt(edgeRenderingFunction.length() - 1)).append("}],Mean[u2],Background->White]}");
            } else {
                visualization.append("}]");
            }
            visualization.append("],ImageSize->{").append(visSize).append("}]]");
        }
        results.add(new StringBuilder[]{result, visualize ? new StringBuilder(visualization) : null});
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        System.out.print("Discrete? ");
        discrete = scn.next().toLowerCase().equals("yes");
        System.out.print("Strategy (OPT, HLF or rHLF): ");
        strategy = scn.next().toLowerCase();
        System.out.print("In-tree: ");
        String[] inputTmp = scn.next().split(",");
        int[] inTree = new int[inputTmp.length];
        for (int i = 0; i < inputTmp.length; i++) {
            inTree[i] = Integer.parseInt(inputTmp[i]);
        }
        System.out.print("Times elapsed: ");
        inputTmp = scn.next().split(",");
        tasks = new Task[inputTmp.length];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new Task();
            tasks[i].elapsed = new StringBuilder("(").append(inputTmp[i]).append(')');
        }
        if (!strategy.equals("opt")) {
            System.out.print("Visualize? ");
            visualize = scn.next().toLowerCase().equals("yes");
        }
        visualize = visualize || strategy.equals("opt");
        if (visualize) {
            System.out.print("Display intermediate results? ");
            labels = scn.next().toLowerCase().equals("yes");
            System.out.print("Label edges with probabilities? ");
            edgeLabels = scn.next().toLowerCase().equals("yes");
            System.out.print("Output file for visualization: ");
            file = scn.next();
            System.out.print("max(width,height) = ");
            visSize = scn.nextInt();
        }
        long start = System.nanoTime();
        /*Process p = new ProcessBuilder("math", "-noinit", "-noprompt").start();
        in = p.getInputStream();
        out = p.getOutputStream();
        writeRead("$Pre=Quiet");
        writeRead("$RecursionLimit=Infinity");
        writeRead("$IterationLimit=Infinity");
        writeRead("$MaxPiecewiseCases=Infinity");
        writeRead("$MaxRootDegree=Infinity");
        writeRead("$MaxExtraPrecision=Infinity");
        writeRead(new StringBuilder("$Assumptions=0<=a&&a<").append(discrete ? '=' : "").append('b').append(discrete ? "&&Element[a,Integers]&&Element[b,Integers]" : ""));
        writeRead(new StringBuilder("$NewSymbol=($Assumptions=$Assumptions&&Element[ToExpression[#1],").append(discrete ? "Integers" : "Reals").append("])&"));
        for (Task i : tasks) {
            writeRead(i.elapsed);
        }
        write("$NewSymbol=.");*/
        for (int i = 0; i < inTree.length; i++) {
            tasks[i + 1].successor = inTree[i];
            tasks[inTree[i]].predecessors++;
        }
        if (strategy.equals("hlf") || strategy.equals("rhlf")) {
            level = new int[tasks.length];
            for (int i = 1; i < tasks.length; i++) {
                level(i);
            }
        }
        ready = new TreeSet(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return (strategy.equals("hlf") || strategy.equals("rhlf")) && level[o1] != level[o2] ? level[o2] - level[o1] : o1 - o2;
            }
        });
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i].predecessors == 0) {
                ready.add(i);
            }
        }
        if (strategy.equals("opt")) {
            firstM = new LinkedList();
            LinkedList<Integer> readyTmp = new LinkedList(ready);
            if (m < ready.size()) {
                firstM(new LinkedList(), readyTmp);
            } else {
                firstM.add(readyTmp);
            }
            LinkedList<Object[]> strategiesTmp = new LinkedList();
            for (LinkedList<Integer> i : firstM) {
                for (int j : i) {
                    schedule(j);
                }
                strategies = new LinkedList();
                strategies.add(new Object[]{i, new StrategyTree(null)});
                buildTrees();
                strategiesTmp.addAll(strategies);
                for (int j = 0; j < i.size(); j++) {
                    revertSchedule();
                }
            }
            strategies = strategiesTmp;
            while (!strategies.isEmpty()) {
                Object[] i = strategies.peek();
                for (int j : (LinkedList<Integer>) i[0]) {
                    schedule(j);
                }
                process();
                for (int j = 0; j < ((LinkedList<Integer>) i[0]).size(); j++) {
                    revertSchedule();
                }
                strategies.poll();
            }
        } else {
            for (int i = 0; i < m && !ready.isEmpty(); i++) {
                schedule(next());
            }
            process();
        }
        if (!labels) {
            StringBuilder assumptions = new StringBuilder();
            //assumptions.append("Assuming[True");
            assumptions.append(discrete ? "Assuming[True&&0<=a&&a<=b" : "Assuming[True&&0<=a&&a<b");
            for (int i = 0; i < tasks.length; i++) {
                assumptions.append("&&a<=x").append(i).append("&&x").append(i).append("<=b");
                if (discrete) {
                    assumptions.append("&&Element[x").append(i).append(",Integers]");
                }
            }
            /*for (StringBuilder[] i : results) {
                i[0] = writeRead(new StringBuilder(assumptions).append(",FullSimplify[PiecewiseExpand[").append(i[0]).append("]]]"));
            }*/
            for (StringBuilder[] i : results) {
                i[0] = new StringBuilder(assumptions).append(",FullSimplify[PiecewiseExpand[").append(i[0]).append("]]]");
            }
        }
        Collections.sort(results, new Comparator<StringBuilder[]>() {
            @Override
            public int compare(StringBuilder[] o1, StringBuilder[] o2) {
                StringBuilder less = new StringBuilder(), greater = new StringBuilder();
                try {
                    less.append(writeRead(new StringBuilder("FullSimplify[PiecewiseExpand[").append('(').append(o1[0]).append(')').append("<(").append(o2[0]).append(')').append("]]")));
                    greater.append(writeRead(new StringBuilder("FullSimplify[PiecewiseExpand[").append('(').append(o1[0]).append(')').append(">(").append(o2[0]).append(')').append("]]")));
                } catch (Exception e) {
                }
                return less.toString().equals("True") ? -1 : greater.toString().equals("True") ? 1 : 0;
            }
        });
        LinkedList<StringBuilder[]> resultsTmp = new LinkedList();
        resultsTmp.add(results.poll());
        for (StringBuilder[] resultTmp : results) {
            StringBuilder less = writeRead(new StringBuilder("FullSimplify[PiecewiseExpand[").append('(').append(resultsTmp.peekLast()[0]).append(')').append("<(").append(resultTmp[0]).append(')').append("]]"));
            if (less.toString().equals("True")) {
                break;
            }
            resultsTmp.add(resultTmp);
        }
        results = resultsTmp;
        int counter = 1;
        for (StringBuilder[] resultTmp : results) {
            if (!labels) {
                System.out.println(new StringBuilder("Result").append(results.size() == 1 ? "" : new StringBuilder(" ").append(counter)).append(": ").append(resultTmp[0]));
            }
            if (visualize) {
                if (results.size() == 1) {
                    writeRead(resultTmp[1]);
                } else {
                    System.out.println(new StringBuilder("Visualization").append(results.size() == 1 ? "" : new StringBuilder(" ").append(counter)).append(": ").append(resultTmp[1]));
                }
            }
            counter++;
        }
        /*System.out.println(new StringBuilder("Total CPU time used by Mathematica: ").append(writeRead("TimeUsed[]")).append('s'));
        write("Quit[]");
        p.waitFor();*/
        System.out.println(new StringBuilder("Time since last input: ").append((System.nanoTime() - start) / (double) 1000000000).append('s'));
    }
}
