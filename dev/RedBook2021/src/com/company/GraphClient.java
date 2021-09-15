package com.company;

public class GraphClient {
    public int degree(Graph G, int v) {
        // degree is number of edges connected to the node
        // equals length of adj[]
        int degree = 0;
        if (v >= G.V()) return -1;
        for (int w: G.adj(v)) {
            degree++;
        }
        return degree;
    }

    public int maxDegree(Graph G) {
        int max = 0;
        for (int v = 0; v < G.V(); v++) {
            int degree = degree(G, v);
            if (degree > max) max = degree;
        }
        return max;
    }

    public double avgDegree(Graph G) {
        // this works because each edge needs to be counted twice for
        // each node on either side of it.
        return 2.0 * G.E() / G.V();
    }

    public int numSelfLoops(Graph G) {
        int count = 0;
        for (int v = 0; v < G.V(); v++) {
            for (int w: G.adj(v)) {
                if (w == v) count++;
            }
        }
        return count / 2; // why this?? how is each edge counted twice
    }

    /* does the graph contain a cycle?
    * a cycle is defined by a sequence of paths that has the same start
    * and end point
    * how would you look for a cycle?
    * iterate through the vertices. for each vertex: look through the
    * adj[] list. mark the first one.
    * if w has not been marked yet, examine the adj[] of w.
    * skip marked vertices. keep going until u find a vertex = to the source
    * a.k.a. the current vertex.
    * this is really slow.
    * */
        /* we need to use recursion...

        for each vertex connected to v, if there is a pathTo() another
        node connected to v, then there is a cycle.

        run a depth first search.
        0: 3, 1, 2
        1: 2, 3
        2: 1, 0
        4: 3

        if there is a path from 3 to 1 or from 3 to 2, there is a cycle.
        dfs(G, 3):
            hasPathTo(1) = true
        // if it was false, then check dfs(3, 2).
        if this is also false, then there is no cycle containing 0.
        move on to 1.
        */
    public boolean hasCycle(Graph G) {
        /*
        * for each vertex v in G:
        *   for each vertex w in adj(v):
        *       run dfs with w as the source.
        *       call hasPathTo() for every other vertex in adj(v).
        *       if it returns true, return true for this whole function.
        *       if none of them return true, move on to the next vertex v.
        * */

        for (int v = 0; v < G.V(); v++) { // 0
            int first = G.adj(v).iterator().next();
//            System.out.println("first element in adj:" + first);
            DepthFirstSearch search = new DepthFirstSearch(G, first);

            for (int w: G.adj(v)) {
                System.out.println("checking if there is a path between " + first + " and " + w);

                if (w != first && search.hasPathTo(w)) {
                    System.out.println("found a path between " + first + " and " + w);
                    return true;
                }
            }
        }
        return false;
    }
}
