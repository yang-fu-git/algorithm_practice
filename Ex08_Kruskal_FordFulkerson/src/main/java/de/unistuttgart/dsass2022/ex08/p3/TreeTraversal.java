package de.unistuttgart.dsass2022.ex08.p3;

import java.util.ArrayList;
import java.util.Arrays;

public class TreeTraversal {


    /**
     * This method traverses the tree using depth first search. To eliminate any ambiguity choosing the next node,
     * the node with the smallest index is visited next.
     *
     * @param weights adjacency matrix defining the graph. Since only you are using this method
     *                calculating the ford_fulkerson algorithm, you can expect weights to be rectangular
     * @param s       the id of the node to start the dfs on
     * @return array with predecessors. I.e. pi[5] = 2 means, that node 2 is predecessor of node 5.
     */

    public static int[] dfs(ArrayList<ArrayList<Integer>> weights, int s) throws IllegalArgumentException {
        int[] predecessor = new int[weights.size()];
        Arrays.fill(predecessor, -1);
        dfs(weights, s, predecessor);
        return predecessor;
    }

    private static void dfs(ArrayList<ArrayList<Integer>> weights, int s, int[] predecessor) {
        for (int next = 0;next<weights.get(s).size();next++) {
            if(predecessor[next]!=-1){
                continue;
            }
            if (weights.get(s).get(next) > 0) {
                predecessor[next] = s;
                dfs(weights, next, predecessor);
            }
        }
    }

}
