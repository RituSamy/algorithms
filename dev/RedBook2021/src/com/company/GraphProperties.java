package com.company;

public class GraphProperties {
    private Graph G;
    private int[] eccs; // ecc of each vertex
    private int radius;
    private boolean[] marked;
    private int girth;
    // exception of G is not connected
    public GraphProperties(Graph G) {
        this.G = G;
        eccs = new int[G.V()];
        for (int i = 0; i < eccs.length; i++) {
            eccs[i] = computeEccentricity(i);
        }
        this.radius = radius();
        marked = new boolean[G.V()];
    }

    public int eccentricity(int v) { return eccs[v]; }

    // length of the shortest path from that vertex to the furthest vertex from v
    private int computeEccentricity(int v) {
        // perform breadth first search from v to every vertex, store distTo[] in an array
        // bfs gives the shortest path each time
        BreadthFirstSearch bfs = new BreadthFirstSearch(G, v);
        int ecc = 0;
        // could use a maxheap
        for (int w = 0; w < G.V(); w++) {
            if (bfs.distTo(w) > ecc) { ecc = bfs.distTo(w); }
        }
        return ecc;
    }

    // maximum eccentricity of any vertex
    public int diameter() {
        int max = eccs[0];
        for (int w = 0; w < G.V(); w++) {
            if (eccs[w] > max) { max = eccs[w]; }
        }
        return max;
    }

    // smallest eccentricity of any vertex
    private int radius() {
        int min = eccs[0];
        for (int w = 0; w < G.V(); w++) {
            if (eccs[w] < min) { min = eccs[w]; }
        }
        radius = min;
        return min;
    }

    // a vertex whose eccentricity = the radius
    public int center() {
        for (int v = 0; v < G.V(); v++) {
            if (eccs[v] == radius) { return v; }
        }
        return -1;
    }

    /*
    * length of shortest cycle
    * if the graph is acyclic, return infinity
    * else:
    * what is the definition of the shortest cycle?
    * the shortest cycle containing s is a shortest path from s to some vertex v
    * plus the edge back from v to s, where that edge is not the one most recently
    * visited. (there has to be another edge before getting to the last one v->s).
    *
    *
    * find shortest path from v->w, then w->x, then
    * run BFS from each vertex to get the shortest path from v to all other vertices w
    * then, iterate through all of those shortest paths and see whether there is an
    * edge connecting the last vertex to the source v.
    * */

    // length of shortest cycle

    // 0, 5, 3

    public int girth() {
//        int minGirth = G.V();
//        for (int v = 0; v < G.V(); v++) {
//            int curr = girth(v, v, v, 0);
//            if (curr < minGirth) minGirth = curr;
//        }
//        return minGirth;

        return girth(0, 0, 0, 0);
    }
    
//    public boolean twoEdgeConnected() {
//        CC cc = new CC(G);
//        int count = cc.numComponents();
//
//        // how do we simulate removing an edge?
//        for (int v = 0; v < G.V(); v++) {
//
//        }
//    }
    
// make this linear!
    public int parallelEdges() {
        int count = 0;
        boolean marked[] = new boolean[G.V()];
        int edgeTo[] = new int[G.V()];

        for (int v = 0; v < G.V(); v++) {
            for (int w: G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                }
                else if (edgeTo[w] == v) {
                    count++;
                }
            }
        }
        return count;
    }

    private int girth(int s, int v, int u, int g) {
        marked[u] = true;
        BreadthFirstSearch bfs = new BreadthFirstSearch(G, v);
        g += bfs.distTo(u);

        // go through adjacent vertices
        for (int w: G.adj(u)) {
            // base case
            if (w != v && w == s) {
                return g++;
            }

            if (!marked[w]) {
                if (w != v) {
                    if (girth(s, u, w, g) < g) g = girth(s, u, w, g);
                }
            }
        }
        return g--;
    }

}
