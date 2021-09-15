package com.company;

public class NFA {
    private char[] re;
    private Digraph G;
    private int M;

    public NFA(String regexp) {
        re = regexp.toCharArray();
        M = re.length;
        G = buildEpsilonTransitionDigraph();
    }

    public boolean recognizes(String text) {
        // reachable states
        Bag<Integer> states = new Bag<Integer>();
        Queue<Integer> path = new Queue<>();
        // find epsilon transitions from state 0 and add all states to bag
        DirectedDFS dfs = new DirectedDFS(G, 0);
        for (int i = 0; i < G.V(); i++) {
            if (dfs.marked(i)) {
                states.put(i);
                path.enqueue(i);
            }
        }
        for (int i = 0; i < text.length(); i++) { // i = 6 [
            // match transitions
            Bag<Integer> matches = new Bag<Integer>();

            // if any of the reachable states match the text character,
            // put it in matches.
            for (int v: states) {
                // transition: v+1
                if (v < M) {
                    if (re[v] == '[') {
                        int j = v + 1; // first character in set
                        Bag<Character> set = new Bag<Character>();
                        Bag<Character> not = new Bag<Character>();
                        if (re[j] == '^') {
                            j++;
                            while (re[j] != ']') {
                                not.put(re[j]);
                                j++;
                            }
                        } else if (re[j] == '-') {
                            char first = re[j-1];
                            char last = re[j+1];
                            for (char c = first; c <= last; c++) {
                                set.put(c);
                            }
                        } else {
                            while (re[j] != ']') {
                                set.put(re[j]);
                                j++;
                            }
                        }
                        // if the character is in the set and not in not,, skip to end of the ]
                        if (set.contains(text.charAt(i)) || set.contains('.') || !not.contains(text.charAt(i))) matches.put(j);
                    }
                    if (re[v] == text.charAt(i) || re[v] == '.') matches.put(v+1);
                }
            }
            // new states bag for the current text character
            states = new Bag<Integer>();
            // multi-source reachability
            // do a dfs starting from each match vertex to find epsilon transitions
            dfs = new DirectedDFS(G, matches);
            for (int v = 0; v < G.V(); v++) {
                if (dfs.marked(v)) {
                    states.put(v);
                    if (!path.contains(v)) path.enqueue(v);
                }
            }
        }

        if (states.contains(M)) {
            System.out.println("M is an attainable state.");
            for (int v: path) {
                System.out.print(v + " -> ");
            }
            System.out.println();
            return true;
        }
        return false;
    }

    private Digraph buildEpsilonTransitionDigraph() {
        Digraph G = new Digraph(M+1); // shouldn't this be M?
        Stack<Integer> ops = new Stack<Integer>();

        for (int i = 0; i < M; i++) {
            int lp = i;
            if (re[i] == '(' || re[i] == '[' || re[i] == '|') ops.push(i);
            else if (re[i] == ')' || re[i] == ']') {
                int or = ops.pop(); // pop the | or the -
                if (re[or] == '|') {
                    lp = ops.pop(); // pop the (.
                    G.addEdge(lp, or+1); // e-transition from ( to second clause of or statement
                    G.addEdge(or, i); // e-transition from | to )
                } else lp = or; // current state.
            }

            // cycle for *
            if ((i < M-1) && (re[i+1] == '*')) {
                G.addEdge(lp, i+1);
                G.addEdge(i+1, lp);
            }
            // no cycle epsilon transition going to a + because you need a match transition to continue.
            if ((i < M-1) && (re[i+1] == '+')) {
                G.addEdge(i+1, lp);
            }

            // outgoing epsilon transitions for every op except |
            if (re[i] == '(' || re[i] == '*' || re[i] == '+' || re[i] == ')' || re[i] == '[' || re[i] == ']') {
                G.addEdge(i, i+1);
            }
        }
        return G;
    }
}
