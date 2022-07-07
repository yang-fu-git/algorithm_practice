package de.unistuttgart.dsass2022.ex05.p2;
// should have build a new construct for PrefixTreeNode to save a lot of work to create new PrefixTreeNode
public class PrefixTree implements IPrefixTree {
	private PrefixTreeNode root=new PrefixTreeNode();


	@Override
	public void insert(String word) {
		insertNode(root,word);
		
	}
	private IPrefixTreeNode insertNode(IPrefixTreeNode node,String word){
//		if(root==null){
//			PrefixTreeNode newNode= new PrefixTreeNode();
//			newNode.setPrefix(word);
//			node=newNode;
//		}
		if(word.equals(node.getPrefix())){
			node.setNext("",new PrefixTreeNode());
			return node;
		}
		int i=0;
		for(;i<Math.min(word.length(),node.getPrefix().length());i++){
			if(word.charAt(i)!=root.getPrefix().charAt(i)){
				break;
			}
		}
		String prefix=word.substring(0,i);
		if(prefix.equals(node.getPrefix())){
			if(node.getNext().contains(word.charAt(i))){
				if(node.getNextNodes().get(""+word.charAt(i)).getNextNodes()!=null){
					//recursive add the substring to subtree
					return insertNode(node.getNode(""+word.charAt(i)),word.substring(i+1));
				}else{
					// create empty string node to repalce the old leaf and add the rest to its nextNode
					IPrefixTreeNode nodeTmpWord=new PrefixTreeNode();
					nodeTmpWord.setPrefix(word.substring(i+2));
					String nodeTmpFirst=""+node.getNextNodes().get(""+word.charAt(i)).getPrefix().charAt(i+1);
					IPrefixTreeNode nodeTmp=new PrefixTreeNode();
					nodeTmp.setPrefix(node.getNextNodes().get(""+word.charAt(i)).getPrefix().substring(i+2));
					node.getNextNodes().get(""+word.charAt(i)).setPrefix("");
					node.getNextNodes().get(""+word.charAt(i)).setNext(""+word.charAt(i),nodeTmpWord);
					node.getNextNodes().get(""+word.charAt(i)).setNext(nodeTmpFirst,nodeTmp);
					return node;
				}

			}else{
				//create new subtree
				IPrefixTreeNode node1=new PrefixTreeNode();
				node1.setPrefix(word.substring(i+1));
				node.setNext(""+word.charAt(i),node1);
				return node;
			}
		}else{
			//use dummy head to build new root for the tree
			IPrefixTreeNode resNode= new PrefixTreeNode();
			resNode.setPrefix(prefix);
			String  tmpEdge=""+node.getPrefix().charAt(i);
			node.setPrefix(node.getPrefix().substring(i+1));
			resNode.setNext(tmpEdge,node);
			IPrefixTreeNode tmpNode=new PrefixTreeNode();
			tmpNode.setPrefix(word.substring(i+1));
			resNode.setNext(""+word.charAt(i),tmpNode);
			return resNode;

		}




	}

	

	@Override
	public int size() {
		return sizeOfTree(root);
	}
	private  int sizeOfTree(IPrefixTreeNode root){
		int count=0;
		if(root==null){
			return 0;
		}
		if (root.getNext().isEmpty()) {
			return 1;
		}
		else {
			for(String i :root.getNext()){
				count+=sizeOfTree( root.getNextNodes().get(i));
			}
			return count;
		}
	}


}
