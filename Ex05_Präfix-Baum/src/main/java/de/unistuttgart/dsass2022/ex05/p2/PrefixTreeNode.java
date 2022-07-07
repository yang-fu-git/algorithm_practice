package de.unistuttgart.dsass2022.ex05.p2;

import java.util.HashMap;

import java.util.Map;
import java.util.Set;

public class PrefixTreeNode implements IPrefixTreeNode{
	private String prefix="";
	public Map<String,IPrefixTreeNode> nextNodes = new HashMap<>();
	
	
	public PrefixTreeNode() {
		
	}
	

	@Override
	public void setPrefix(String prefix) {
		this.prefix=prefix;
	}

	@Override
	public String getPrefix() {
		return  this.prefix;
	}

	public  Map<String,IPrefixTreeNode> getNextNodes(){
		return this.nextNodes;
	}
	@Override
	public Set<String> getNext() {
		return nextNodes.keySet();
	}

	@Override
	public void setNext(String string ,IPrefixTreeNode node) {
		nextNodes.put(string,node);
	}


	@Override
	public IPrefixTreeNode getNode(String string) {
		return nextNodes.get(string);
	}


	@Override
	public void removeChildren() {
		nextNodes.clear();
	}
	
	

}
