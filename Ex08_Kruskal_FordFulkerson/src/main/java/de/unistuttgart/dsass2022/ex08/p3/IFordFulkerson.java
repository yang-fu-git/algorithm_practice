package de.unistuttgart.dsass2022.ex08.p3;

import java.util.ArrayList;

public interface IFordFulkerson {
	
	/**
	 * This method calculates the MaxFlow value for a graph given as an adjacency matrix.
	 * graph is not altered during the calculations
	 * 
	 * @param graph the graph as an adjacency matrix, i.e. graph has to be rectangular and is not altered by the calculations
	 * @param s the source
	 * @param t the sink
	 * @return the max flow value of the graph
	 * @throws IllegalArgumentException if @param graph is not rectangular
	 */
	public int calculate(ArrayList<ArrayList<Integer>> graph, int s, int t) throws IllegalArgumentException;
	
	
	/**
	 * This method returns the max flow network
	 * 
	 * @return the flow for which the max flow got calculated
	 */
	public ArrayList<ArrayList<Integer>> flow();

}
