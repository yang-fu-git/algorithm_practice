package de.unistuttgart.dsass2022.ex07.p2;


import java.util.*;


public class ShortestPath implements IShortestPath {

	private final IWeightedGraph graph;
	private final long startNode;
	private Map<Long,IEdge> parent=new HashMap<>();


	/*
	 * syntactic sugar function to conveniently create shortest path objects from
	 * graphs
	 */
	public static ShortestPath calculateFor(IWeightedGraph g, long startNode) {
		return new ShortestPath(g, startNode);
	}

	/**
	 * Initializes the shortest path for weighted graph <tt>graph</tt> from starting
	 * node <tt>startNode</tt>. Calls the dijkstra(graph, startNode) method to
	 * execute the Dijkstra algorithm.
	 * 
	 * @param graph     the weighted graph
	 * @param startNode the starting node
	 */
	public ShortestPath(IWeightedGraph graph, long startNode) {
		this.graph = graph;
		this.startNode = startNode;
		dijkstra(this.graph, this.startNode);
	}
	public void dijkstra(IWeightedGraph graph, long startnode){

		for (Iterator<Node> it = graph.nodeIterator(); it.hasNext(); ) {
			INode node = it.next();
			graph.getNode(node.getID()).setDistance(Double.MAX_VALUE);
		}
		graph.getNode(startnode).setDistance(Double.MAX_VALUE);
		PriorityQueue<INode> queue=new PriorityQueue<>((a,b) -> (int) (a.getDistance()-b.getDistance()));
		while(!queue.isEmpty()){
			INode node= queue.poll();
			for(Iterator<IEdge> it =graph.outgoingEdges(node.getID()); it.hasNext();){
				IEdge edge= it.next();
				if(queue.contains(edge.getDestination())){
					if(node.getDistance()+ edge.getWeight()< graph.getNode(edge.getDestination()).getDistance()){
						graph.getNode(edge.getDestination()).setDistance(node.getDistance()+ edge.getWeight());
						parent.put(edge.getDestination(),edge);
					}
				}
			}
		}

	}
	public double distanceTo(long destination){
		return graph.getNode(destination).getDistance();
	}

	@Override
	public boolean existsPathTo(long destination) {
		return graph.getNode(destination).getDistance()<Double.MAX_VALUE;
	}

	public Iterable<IEdge> pathTo(long destination){
		List<IEdge> path= new ArrayList<>();
		long cur=destination;
		while(cur!=startNode){
			path.add(parent.get(cur));
			cur=parent.get(cur).getSource();
		}

		return path;
	}


}
