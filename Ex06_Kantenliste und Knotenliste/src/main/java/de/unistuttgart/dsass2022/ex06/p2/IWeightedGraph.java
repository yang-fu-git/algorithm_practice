package de.unistuttgart.dsass2022.ex06.p2;

import java.util.ArrayList;
import java.util.Iterator;

public interface IWeightedGraph<N,E> extends Iterable<IEdge<E>> {

	/**
	 * Returns the number of edges in the weighted graph.
	 * 
	 * @return the number of edges in the weighted graph
	 */
	public int numberOfEdges();

	/**
	 * Returns the number of vertices in the weighted graph.
	 * 
	 * @return the number of vertices in the weighted graph
	 */
	public int numberOfNodes();

	/**
	 * Returns an iterator of all edges in the weighted graph.
	 * 
	 * @return an iterator that iterates through all edges in the weighted graph.
	 */
	public Iterator<IEdge<E>> edgeIterator();

	/**
	 * Returns an iterator for all outgoing edges of the current node
	 * 
	 * @param src	the node id, for which to get outgoing edges
	 * @return iterator for all outgoing edges of node source.
	 */
	public Iterator<IEdge<E>> outgoingEdges(int src);

	@Override
	default Iterator<IEdge<E>> iterator() {
		return edgeIterator();
	}
	
	/**
	 * This method should read an edge list and create a graph object from it.
	 * 
	 * Note that, since we cannot define any id's for the nodes, the nodes are
	 * indexed by the natural numbers including zero.
	 * 
	 * setEdgeList can only be called on an empty graph object
	 * @param list
	 * @throws UnsupportedOperationException if the graph object already has nodes or edges
	 */
	public void createFromEdgeList(ArrayList<Integer> list) throws UnsupportedOperationException;
	
	
	/**
	 * This method shall convert the graph object into an edge list.
	 * 
	 * Since the graph object allows for arbitrary node ids, nodes, that are not adjacent to another node, cannot be
	 * represented by its id.
	 * @return
	 */
	public ArrayList<Integer> toEdgeList();
	
	/**
	 * This method should read a node list and create a graph object from it.
	 * 
	 * Note that, since we cannot define any id's for the nodes, the nodes are
	 * indexed by the natural numbers including zero.
	 * 
	 * setNodeList can only be called on an empty graph object
	 * @param list
	 * @throws UnsupportedOperationException if the graph object already has nodes or edges
	 */
	public void createFromNodeList(ArrayList<Integer> list) throws UnsupportedOperationException;
	
	/**
	 * This method shall convert the graph object into a node list.
	 * 
	 * Since the graph object allows for arbitrary node ids, nodes, that are not adjacent to another node, cannot be
	 * represented by its id.
	 * @return
	 */
	public ArrayList<Integer> toNodeList();
}
