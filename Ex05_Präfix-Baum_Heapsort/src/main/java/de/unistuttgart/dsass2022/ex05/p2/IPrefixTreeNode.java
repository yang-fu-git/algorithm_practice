package de.unistuttgart.dsass2022.ex05.p2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Hint:
 * - To manage the strings of the outgoing edges, you could use a HashMap which maps strings to nodes
 */
public interface IPrefixTreeNode {
	public Map<String,IPrefixTreeNode> nextNodes= new HashMap<>();
	
	/**
	 * Sets the prefix string of the node
	 * 
	 * @param prefix 
	 */
	public void setPrefix(String prefix);
	
	/**
	 * Returns the prefix string of the node
	 * 
	 * @return String prefix
	 */
	public String getPrefix();
	
	/**
	 * Returns a set of all strings of the outgoing edges
	 * 
	 * @return set of strings
	 */
	public Set<String> getNext();
	
	/**
	 * Sets the next node
	 *  
	 * @param string outgoing string to the node
	 * @param node node
	 */
	public void setNext(String string, IPrefixTreeNode node);
	
	/**
	 * Returns the node which belongs to a string of a outgoing edge
	 * 
	 * @param string outgoing string
	 * @return the node which belongs to the string
	 */
	public IPrefixTreeNode getNode(String string);
	
	/**
	 * Removes all children of the node
	 */
	public void removeChildren();
	/**
	 * Returns a set of all strings of the outgoing edges  and their nodes
	 *
	 * @return HashMap
	 */
	public  Map<String,IPrefixTreeNode> getNextNodes();
}
