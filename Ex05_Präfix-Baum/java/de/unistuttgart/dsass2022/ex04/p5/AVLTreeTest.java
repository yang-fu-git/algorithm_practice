package de.unistuttgart.dsass2022.ex04.p5;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class AVLTreeTest {

	// START SOLUTION
	@Rule
	public Timeout globalTimeout = Timeout.seconds(2); //throws a TestTimedOut exception if a test doesn't terminate within 2 seconds
	

	@Test
	public void testRandomInt() {
		Solution_BinarySearchTree<Integer> bst = new Solution_BinarySearchTree<>();
		for (int i = 0; i < 1000; i++) {
			int random = (int) (Math.random() * 1337);
			bst.insert(random);
			bst.insert_solution(random);
			assertTrue(bst.compareTrees());
		}
	}
	
	@Test
	public void testRandomString() {
		Solution_BinarySearchTree<String> bst = new Solution_BinarySearchTree<>();
		for (int i = 0; i < 1000; i++) {
			//random length
			int length = (int) (Math.random()*5);
			String s = "";
			for (int j = 0; j < length; j++) {
				//append random char
				s += String.valueOf((char)(Math.rint(Math.random()*24+97)));
			}
			
			bst.insert(s);
			bst.insert_solution(s);
			assertTrue(bst.compareTrees());
		}
	}
	
	
	
	
	// solutionclass
	/**
	 * 
	 * Solution Class: It inherits from BinarySearchTree, so all Methods that
	 * should be implemented can be checked with the solution
	 * No Method will be overwritten, so the solution of the student will be checked.
	 *
	 * @param <T> Generic Parameter
	 */
	private static class Solution_BinarySearchTree<T extends Comparable<T>>
			extends AVLTree<T> {
		
		
		
		private volatile IAVLNode<T> s_root;
		
		private boolean s_rebalance;
		
		
		public Solution_BinarySearchTree() {
			this.s_root = new AVLNode<T>();
			
			s_rebalance = false;
		}
		

		
		public void insert_solution(T t) {
			if (this.s_root.getRightChild() == null) {
				this.s_root.setRightChild(new AVLNode<T>());
				this.s_root.getRightChild().setValue(t);
			} else {
				this.s_root.setRightChild(insert_solution(this.s_root.getRightChild(), t));
			}
		}

		private IAVLNode<T> insert_solution(IAVLNode<T> node, T t) {
			
			
			IAVLNode<T> tmp;
			
			
			if (node.getValue().compareTo(t) == 0) {
				s_rebalance = false;
				return node;
			}
			//rechts einfügen
			else if (node.getValue().compareTo(t) < 0) {
				if (node.getRightChild() != null) {
					node.setRightChild(insert_solution(node.getRightChild(),t));
					
					// ggf. Re-balancieren
					if (node != this.s_root && s_rebalance){
						switch (node.getBalance()) {
						case -1:
							if (node.getRightChild().getBalance() == -1) {
								// einfache Rotation nach links
								tmp = rotateLeft(node);
								tmp.getLeftChild().setBalance(0);
								} else {
								// doppelte Rotation (rechts, links)
								int b = node.getRightChild().getLeftChild().getBalance();
								node.setRightChild(rotateRight(node.getRightChild()));
								tmp = rotateLeft(node);
								tmp.getRightChild().setBalance((b == 1) ? -1 : 0);
								tmp.getLeftChild().setBalance((b == -1) ? 1 : 0);
								}
								tmp.setBalance(0);
								s_rebalance = false;
								return tmp;
						case 0:
							node.setBalance(-1);
							return node;
						case 1:
							node.setBalance(0);
							s_rebalance = false;
							return node;
						default:
							return node;
						}
					}
					else {
						return node;
					}
				}
				//Knoten erzeugen
				else {
					IAVLNode<T> newNode = new AVLNode<T>();
					newNode.setValue(t);
					node.setRightChild(newNode);
					//Balance berechnen
					node.setBalance(node.getBalance() - 1);
					s_rebalance = (node.getBalance() <= -1);
					return node;
				}
				
				
				
			}
			//links einfügen
			else {
				if (node.getLeftChild() != null) {
					node.setLeftChild(insert_solution(node.getLeftChild(),t));
					
					// ggf. Re-balancieren
					if (node != this.s_root && s_rebalance){
						switch (node.getBalance ()) {
						case 1:
							if (node.getLeftChild().getBalance() == 1) {
								// einfache Rotation nach links
								tmp = rotateRight(node);
								tmp.getRightChild().setBalance(0);
								} else {
								// doppelte Rotation (rechts, links)
								int b = node.getLeftChild().getRightChild().getBalance();
								node.setLeftChild(rotateLeft(node.getLeftChild()));
								tmp = rotateRight(node);
								tmp.getLeftChild().setBalance((b == 1) ? 1 : 0);
								tmp.getRightChild().setBalance((b == -1) ? -1 : 0);
								}
								tmp.setBalance(0);
								s_rebalance = false;
								return tmp;
						case 0:
							node.setBalance(1);
							return node;
						case -1:
							node.setBalance(0);
							s_rebalance = false;
							return node;
						default:
							return node;
						}
					}
					else {
						return node;
					}
					
					
					
					
					
				}
				//Knoten erzeugen
				else {
					IAVLNode<T> newNode = new AVLNode<T>();
					node.setLeftChild(newNode);
					newNode.setValue(t);
					//Balance berechnen
					node.setBalance(node.getBalance() + 1);
					s_rebalance = (node.getBalance() >= 1);
					return node;
				}
			}
			
		}
		
		
		
		private IAVLNode<T> rotateLeft(IAVLNode<T> node) {
			IAVLNode<T> tmp = node.getRightChild();
			node.setRightChild(node.getRightChild().getLeftChild());
			tmp.setLeftChild(node);
			return tmp;
		}
		
		
		private IAVLNode<T> rotateRight(IAVLNode<T> node) {
			IAVLNode<T> tmp = node.getLeftChild();
			node.setLeftChild(node.getLeftChild().getRightChild());
			tmp.setRightChild(node);
			return tmp;
		}


		/**
		 * Compares the level-Order of the tree and the solution tree
		 * 
		 * @return true if other tree equals solution tree
		 */
		public boolean compareTrees() {
			LevelorderIterator tree = new LevelorderIterator(this.getRootNode().getRightChild());
			LevelorderIterator solutionTree = new LevelorderIterator(
					this.s_root.getRightChild());

			while (tree.hasNext()) {
				if (!solutionTree.hasNext()) {
					return false;
				}
				if (tree.next().compareTo(solutionTree.next()) != 0) {
					return false;
				}
			}
			if (tree.hasNext() != solutionTree.hasNext()) {
				return false;
			}
			return true;
		}

		/**
		 * Levelorder Iterator for a tree
		 *
		 */
		private class LevelorderIterator implements Iterator<T> {

			Queue<IAVLNode<T>> queue = new LinkedList<>();

			/**
			 * Constructor which needs the root node to start the levelorder from.
			 * 
			 * @param root (node where to start the levelorder)
			 */
			public LevelorderIterator(IAVLNode<T> root) {
				if (root != null) {
					this.queue.add(root);
				}
			}

			@Override
			public boolean hasNext() {
				return !this.queue.isEmpty();
			}

			@Override
			public T next() {
				IAVLNode<T> node = this.queue.remove();
				if (node.getLeftChild() != null) {
					this.queue.add(node.getLeftChild());
				}
				if (node.getRightChild() != null) {
					this.queue.add(node.getRightChild());
				}
				return node.getValue();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}

		}
	}
	
	// END SOLUTION
}
