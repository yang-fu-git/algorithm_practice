package de.unistuttgart.dsass2022.ex04.p2;

public class AVLNode<T extends Comparable<T>> implements IAVLNode<T> {
	private T val;
	private IAVLNode<T> left;
	private IAVLNode<T> right;
	private int bal;

	@Override
	public void setValue(T val) {
		this.val=val;
	}

	@Override
	public T getValue() {
		return val;
	}

	@Override
	public void setLeftChild(IAVLNode<T> left) {
		this.left=left;
	}

	@Override
	public IAVLNode<T> getLeftChild() {
		return this.left;
	}
	
	@Override
	public void setRightChild(IAVLNode<T> right) {
		this.right=right;
	}

	@Override
	public IAVLNode<T> getRightChild() {
		return this.right;
	}

	@Override
	public void setBalance(int bal) {
		this.bal=bal;
	}

	@Override
	public int getBalance() {
		return bal;
	}

}
