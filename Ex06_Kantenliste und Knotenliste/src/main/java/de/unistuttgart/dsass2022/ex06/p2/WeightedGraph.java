package de.unistuttgart.dsass2022.ex06.p2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class WeightedGraph<N,E> implements IWeightedGraph<N,E> {

	private int numNodes;
	private int numEdges;



	private HashMap<Integer ,ArrayList<IEdge<E>>> adjacencyList;
	
	/**
	 * Initializes an empty graph without nodes and edges.
	 */
	public WeightedGraph() {
		this.numNodes = 0;
		this.numEdges = 0;
		this.adjacencyList = new HashMap<>();
	}

	public HashMap<Integer, ArrayList<IEdge<E>>> getAdjacencyList() {
		return adjacencyList;
	}
	public void createFromEdgeList(ArrayList<Integer> list) throws UnsupportedOperationException{
		numNodes=list.get(0);
		numEdges=list.size()-2;
		for(int node=0;node<numNodes;node++){
			ArrayList<IEdge<E>> edgeList= new ArrayList<>();
			for(int i=2;i<list.size();){
				if(list.get(i)==node){
					IEdge<E> edge =new Edge<>(list.get(i),list.get(i+1), 0);
					edgeList.add(edge);
					i=i+2;
				}
				break;
			}
			adjacencyList.put(node,edgeList);
		}

		
	}
	
	
	public ArrayList<Integer> toEdgeList(){
		ArrayList<Integer> edgeList= new ArrayList<>();
		edgeList.add(this.numNodes);
		edgeList.add(this.numEdges);
		for (Iterator<IEdge<E>> it = edgeIterator(); it.hasNext(); ) {
			IEdge<E> edge = it.next();
			edgeList.add(edge.getSource());
			edgeList.add(edge.getDestination());
		}
		return edgeList;
		
	}
	
	public void createFromNodeList(ArrayList<Integer> list) throws UnsupportedOperationException{
		numNodes=list.get(0);
		numEdges=list.size()-2;
		for(int node=0;node<numNodes;node++){
			ArrayList<IEdge<E>> edgeList= new ArrayList<>();
			for(int i=2;i<list.size();){
				int edgeNumber=list.get(i);
				while(edgeNumber>=1){
					edgeList.add(new Edge(node,list.get(i+edgeNumber),0));
					edgeNumber--;
				}
				i=i+edgeNumber+1;
			}
			adjacencyList.put(node,edgeList);
		}


	}
	
	public ArrayList<Integer> toNodeList(){
		ArrayList<Integer> nodeList= new ArrayList<>();
		nodeList.add(this.numNodes);
		nodeList.add(this.numEdges);
		for(int src=0;src<numNodes;){
			nodeList.add(0);
			int edgeNum=0;
			for (Iterator<IEdge<E>> it = outgoingEdges(src); it.hasNext(); ) {
				IEdge<E> edge = it.next();
				nodeList.add(edge.getDestination());
				edgeNum++;
			}
			nodeList.set(nodeList.size()-1-edgeNum,edgeNum);
		}
		return nodeList;
	}
	
	@Override
	public int numberOfNodes() {
		return this.numNodes;
	}

	@Override
	public int numberOfEdges() {
		return this.numEdges;
	}
	
	/**
	 * Adds a new node with the specified meta data to this graph.
	 * @param nodeMetaData	meta data of the added node
	 * @return the new node's index
	 */
	public int addNode(int nodeID ,N nodeMetaData){
		this.adjacencyList.put(nodeID, new ArrayList<>(1));
		return this.numNodes++;
	}
	
	/**
	 * Adds a new edge from node index <tt>src</tt> to node index <tt>dst</tt>.
	 * @param src index of the source node
	 * @param dst index of the destination node
	 * @param weight weight of the edge
	 * @return the created edge object.
	 */
	public Edge<E> addEdge(int src, int dst, double weight){
		Edge<E> toAdd = new Edge<>(src, dst, weight);
		this.addEdge(toAdd);
		return toAdd;
	}

	/**
	 * Adds the specified edge to this graph.
	 * @param edge	the edge that should be added
	 */
	public void addEdge(IEdge<E> edge) {
		int source = edge.getSource();
		this.adjacencyList.get(source).add(edge);
		this.numEdges++;
	}
	
	@Override
	public Iterator<IEdge<E>> edgeIterator() {
		ArrayList<IEdge<E>> edgeList = new ArrayList<IEdge<E>>(numEdges);
		for (int i = 0; i < this.numNodes; i++) {
			edgeList.addAll(this.adjacencyList.get(i));
		}
		return edgeList.iterator();
	}

	@Override
	public Iterator<IEdge<E>> outgoingEdges(int src) {
		return this.adjacencyList.get(src).iterator();
	}

}
