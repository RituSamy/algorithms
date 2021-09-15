package com.company;

/*
* Maintain a queue for all the nodes you still need to visit.
* Start with nodes distance 1 away from source.
* until the queue is empty:
* mark, take off queue, put all adj vertices that haven't been marked on queue.
* */
public class BreadthFirstSearch {
    private Queue<Integer> queue; // queue to maintain all nodes to visit
    private boolean[] marked; // array to maintain if it has been visited
    private int[] edgeTo; // array to maintain paths
    private int[] distTo;
    private final int s;

    public BreadthFirstSearch(Graph G, int s) {
        queue = new Queue<Integer>(); // set up queue and arrays
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        this.s = s;
        bfs(G, s); // search
    }

    public void bfs(Graph G, int s) {
        int dist = 0;
        distTo[s] = dist;
        marked[s] = true;
        queue.enqueue(s); // add the source

        while (!queue.isEmpty()) {
            int v = queue.dequeue(); // examine latest node on queue
            // push all adjacent nodes onto the queue
            for (int w: G.adj(v)) {
                if (!marked[w]) { // only add if it's not marked
                    marked[w] = true; // mark it
                    edgeTo[w] = v; // update edgeTo
                    distTo[w] = distTo[v]+1;
                    queue.enqueue(w); // add it to the queue
                }
            }
        }
    }

    public int distTo(int v) {
        return distTo[v];
    }

    public int edgeTo(int v) { return edgeTo[v]; }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
