package de.unistuttgart.dsass2022.ex03.p5;

import java.util.*;
//START SOLUTION


public class BinarySearchTree<T extends Comparable<T>> implements IBinarySearchTreeIterable<T> {

	private volatile IBinaryTreeNode<T> root;

	public BinarySearchTree() {
		this.root = null;
	}

	@Override
	public void insert(T t) {
		this.root = this.insert(this.root, t);
	}

	private IBinaryTreeNode<T> insert(IBinaryTreeNode<T> node, T t) {
		if (node == null) {
			IBinaryTreeNode<T> newNode = new BinaryTreeNode<>();
			newNode.setValue(t);
			return newNode;
		}
		if (t.compareTo(node.getValue()) < 0) {
			node.setLeftChild(this.insert(node.getLeftChild(), t));
		} else if (t.compareTo(node.getValue()) > 0) {
			node.setRightChild(this.insert(node.getRightChild(), t));
		}
		return node;
	}

	@Override
	public IBinaryTreeNode<T> getRootNode() {
		return this.root;
	}

	@Override
	public boolean isFull() {

		return false;
	}

	@Override
	public Iterator<T> iterator(TreeTraversalType traversalType) {
		switch(traversalType){
			case PREORDER:
				return new preorder<>(root);
			case INORDER:
				return new inorder<>(root);
			case POSTORDER:
				return new postorder<>(root);
			case LEVELORDER:
				return null;
		}

		return null;
	}

	static class inorder<T extends Comparable<T>> implements Iterator<T> {
		private int index;
		private List<T> mList;

		public inorder(IBinaryTreeNode<T> root) {
			index = 0;
			mList = new ArrayList<>();
			inorderTraversal(root, mList);
		}

		public T next() {
			return mList.get(index++);
		}

		public boolean hasNext() {
			return index < mList.size();
		}

		// Get the result of inorder traversal
		private void inorderTraversal(IBinaryTreeNode<T> root, List<T> mList) {
			if (root == null)
				return;
			inorderTraversal(root.getLeftChild(), mList);
			mList.add(root.getValue());
			inorderTraversal(root.getRightChild(), mList);
		}
	}
	static class preorder<T extends Comparable<T>> implements Iterator<T> {
		private int index;
		private List<T> mList;

		public preorder(IBinaryTreeNode<T> root) {
			index = 0;
			mList = new ArrayList<>();
			preorderTraversal(root, mList);
		}

		public T next() {
			return mList.get(index++);
		}

		public boolean hasNext() {
			return index < mList.size();
		}

		// Get the result of inorder traversal
		private void preorderTraversal(IBinaryTreeNode<T> root, List<T> mList) {
			if (root == null)
				return;
			mList.add(root.getValue());
			preorderTraversal(root.getLeftChild(), mList);
			preorderTraversal(root.getRightChild(), mList);
		}
	}
	static class postorder<T extends Comparable<T>> implements Iterator<T> {
		private int index;
		private List<T> mList;

		public postorder(IBinaryTreeNode<T> root) {
			index = 0;
			mList = new ArrayList<>();
			postorderTraversal(root, mList);
		}

		public T next() {
			return mList.get(index++);
		}

		public boolean hasNext() {
			return index < mList.size();
		}

		// Get the result of inorder traversal
		private void postorderTraversal(IBinaryTreeNode<T> root, List<T> mList) {
			if (root == null)
				return;
			postorderTraversal(root.getLeftChild(), mList);
			postorderTraversal(root.getRightChild(), mList);
			mList.add(root.getValue());

		}
	}
	static class levelorder<T extends Comparable<T>> implements Iterator<T> {
		private int index;
		private List<T> mList;

		public levelorder(IBinaryTreeNode<T> root) {
			index = 0;
			mList = new ArrayList<>();
			levelorderTraversal(root, mList);
		}

		public T next() {
			return mList.get(index++);
		}

		public boolean hasNext() {
			return index < mList.size();
		}

		// Get the result of inorder traversal
		private void levelorderTraversal(IBinaryTreeNode<T> root, List<T> mList) {
			if (root == null)
				return;
			Queue<IBinaryTreeNode<T>> queue = new LinkedList<>();
			queue.add(root);
			while (!queue.isEmpty()) {
                int cnt = queue.size();
                for (int i = 0; i < cnt; i++) {
                    IBinaryTreeNode<T> node = queue.poll();
                    mList.add(node.getValue());
                    if (node.getLeftChild() != null) {
                        queue.add(node.getLeftChild());
                    }
                    if (node.getRightChild() != null) {
                        queue.add(node.getRightChild());
                    }
                }
			}
		}
	}


}
