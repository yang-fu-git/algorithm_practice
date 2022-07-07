package de.unistuttgart.dsass2022.ex04.p2;

public interface IAVLNode<T extends Comparable<T>> {

	/**
	 * Sets the value of the node.
	 * 
	 * @param val value of the node
	 */
	public void setValue(T val);

	/**
	 * Returns the value of the node.
	 * 
	 * @return value of the node
	 */
	public T getValue();

	/**
	 * Sets the left child of the node.
	 * 
	 * @param left left child
	 */
	public void setLeftChild(IAVLNode<T> left);

	/**
	 * Returns the left child of the node.
	 * 
	 * @return left child of the node
	 */
	public IAVLNode<T> getLeftChild();

	/**
	 * Sets the right child of the node.
	 * 
	 * @param right right child of the node
	 */
	public void setRightChild(IAVLNode<T> right);

	/**
	 * Returns the right child of the node.
	 * 
	 * @return right child of the node
	 */
	public IAVLNode<T> getRightChild();
	
	/**
	 * Sets the AVL-Balance value of the node 
	 * @param bal balance of the node
	 */
	public void setBalance(int bal);
	
	/**
	 * Returns the AVL-Balance value of the node
	 * @return balance of the node
	 */
	public int getBalance();

}
