package de.unistuttgart.dsass2022.ex08.p1;


import javax.swing.*;
import java.util.*;


public class MinimalSpanningTree {
    private static class UnionFind {
        int[] parent;
        int[] size;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i]=1;
            }
        }

        public void union(int p, int q) {
            int rootP=find(p);
            int rootQ=find(q);
            if(rootQ==rootP){
                return;
            }
            if (size[rootP] > size[rootQ]){
                parent[rootQ]=rootP;
                size[rootP]+=size[rootQ];
            }else {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }
        }

        public int find(int p) {
            while (parent[p] != p) {
                parent[p]= parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }
    }

    /**
     * This method calculates the minimal spanning tree using the kruskal algorithm.
     *
     * @param graph the graph object to calculate the MST for
     * @return a set of edges, which belong to the MST of the given graph
     */
    public static Set<IEdge> kruskal(IWeightedGraph graph) {
        PriorityQueue<IEdge> edgeQueue = new PriorityQueue<>((i, j) -> (int) (i.getWeight() - j.getWeight()));
        for (Iterator<IEdge> it = graph.edgeIterator(); it.hasNext(); ) {
            IEdge edge = it.next();
            edgeQueue.add(edge);
        }
        Map<Long,Integer> nodeIdMap = new HashMap<>();
        int idx = 0;
        for(Iterator<Long> it = graph.nodeIDIterator(); it.hasNext();){
            nodeIdMap.put(it.next(),idx++);
        }
        Set<IEdge> resSet = new HashSet<>();
        UnionFind uf = new UnionFind(graph.numberOfNodes());
        while (!edgeQueue.isEmpty()) {
            IEdge curEdge = edgeQueue.poll();
            int srcId = nodeIdMap.get(curEdge.getSource());
            int desId = nodeIdMap.get(curEdge.getDestination());
            if(uf.connected(srcId, desId)){
                continue;
            }
            uf.union(srcId,desId);
            resSet.add(curEdge);
        }
        return resSet;
    }
}
