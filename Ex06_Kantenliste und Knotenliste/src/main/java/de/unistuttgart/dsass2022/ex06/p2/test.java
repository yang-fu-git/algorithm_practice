package de.unistuttgart.dsass2022.ex06.p2;

import java.lang.reflect.Array;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {

    public static void main(String[] args){
        ArrayList<Integer> nodeList = new ArrayList<Integer>(Arrays.asList(4,5,0,1,0,2,1,2,1,3,2,3));
        WeightedGraph<Integer, Edge<Integer>> Graph = new WeightedGraph<>();
        Graph.createFromEdgeList(nodeList);
        for(Integer key :Graph.getAdjacencyList().keySet()){
            System.out.println(key);
            for(IEdge<Edge<Integer>> edge:Graph.getAdjacencyList().get(key)){
                System.out.println(edge.getSource());
                System.out.println(edge.getDestination()) ;
            }
        }







    }



}