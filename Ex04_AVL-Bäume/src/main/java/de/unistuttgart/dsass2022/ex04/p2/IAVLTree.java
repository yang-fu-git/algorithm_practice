package de.unistuttgart.dsass2022.ex04.p2;


/**
 * 
 *
 * @param <T>
 * 
 * This Interface provides the neccessary methods your AVL-Tree should have.
 */
public interface IAVLTree<T extends Comparable<T>> {

	/**
	 * Inserts a node with the given element into the AVL-Tree. If the balance-property
	 * of the tree gets violated, the neccessary rotations should be done to reestablish
	 * the balance.
	 * 
	 * @param t the element to be inserted
	 */
	public void insert(T t);

	/**
	 * Returns the (true) root node of the binary search tree.
	 * 
	 * @return root node of the tree
	 */
	public IAVLNode<T> getRootNode();

}
