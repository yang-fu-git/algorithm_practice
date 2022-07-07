package de.unistuttgart.dsass2022.ex03.p5;

public class BinaryTreeNode<T extends Comparable<T>> implements IBinaryTreeNode<T> {
	private  T val;
	private IBinaryTreeNode<T> left;
	private  IBinaryTreeNode<T> right;
	
	public BinaryTreeNode() {

	}


	@Override
	public void setValue(T val) {
		this.val=val;
	}

	@Override
	public T getValue() {
		return this.val;
	}

	@Override
	public void setLeftChild(IBinaryTreeNode<T> left) {
		this.left=left;

	}

	@Override
	public IBinaryTreeNode<T> getLeftChild() {
		return this.left;
	}

	@Override
	public void setRightChild(IBinaryTreeNode<T> right) {
		this.right=right;

	}

	@Override
	public IBinaryTreeNode<T> getRightChild() {
		return this.right;
	}
}
