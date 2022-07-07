package de.unistuttgart.dsass2022.ex08.p3;

import java.util.ArrayList;
import java.util.Arrays;

import static de.unistuttgart.dsass2022.ex08.p3.TreeTraversal.dfs;

public class FordFulkerson implements IFordFulkerson {

    ArrayList<ArrayList<Integer>> residual;
    ArrayList<ArrayList<Integer>> graph;

    public int calculate(ArrayList<ArrayList<Integer>> graph, int s, int t) {
        this.graph = graph;
        // Initialize residual graph as a copy of graph
        residual = new ArrayList<>();
        for (ArrayList<Integer> weights : graph) {
            residual.add(new ArrayList<>(weights));
        }
        int[] predecessor = dfs(residual, s);
        // Has augmenting path
        while (predecessor[t] != -1) {
            int bottle = Integer.MAX_VALUE;
            // Go along the path we find
            for (int v = t; v != s; v = predecessor[v]) {
                bottle = Math.min(bottle, residual.get(predecessor[v]).get(v));
            }
            // Augment flow
            for (int v = t; v != s; v = predecessor[v]) {
                residual.get(predecessor[v]).set(v, residual.get(predecessor[v]).get(v) - bottle);
                // Add reverse path with capacity bottle
                residual.get(v).set(predecessor[v], residual.get(v).get(predecessor[v]) + bottle);
            }
            predecessor = dfs(residual, s);
        }
        int res = 0;
        for (int i = 0; i < graph.get(s).size(); i++) {
            if (graph.get(s).get(i) > 0) {
                res += graph.get(s).get(i) - residual.get(s).get(i);
            }
        }
        return res;
    }

    @Override
    public ArrayList<ArrayList<Integer>> flow() {
        ArrayList<ArrayList<Integer>> flowGraph = new ArrayList<>();
        for (int i = 0; i < this.graph.size(); i++) {
            flowGraph.add(new ArrayList<>(Arrays.asList(new Integer[graph.get(i).size()])));
        }
        for (int i = 0; i < graph.size(); i++) {
            for (int j = 0; j < graph.get(i).size(); j++) {
                int flowValue = graph.get(i).get(j) > 0 ? graph.get(i).get(j) - residual.get(i).get(j) : 0;
                flowGraph.get(i).set(j, flowValue);
            }
        }
        return flowGraph;
    }

    public static void main(String[] args) {
        // Test FordFulkerson algo
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        //                                      s  a  b  c  d  e  f  g  t
        graph.add(new ArrayList<>(Arrays.asList(0, 12, 0, 10, 0, 0, 0, 0, 0)));
        graph.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 9, 0, 3, 0, 0)));
        graph.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 4, 0, 0, 0, 0)));
        graph.add(new ArrayList<>(Arrays.asList(0, 0, 5, 0, 7, 18, 0, 0, 0)));
        graph.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 7, 3, 0)));
        graph.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 10, 0)));
        graph.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 20)));
        graph.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 5)));
        graph.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0)));
        FordFulkerson ff = new FordFulkerson();
        System.out.println(ff.calculate(graph, 0, 8));
        System.out.println(ff.flow());
    }

}
