package de.unistuttgart.dsass2022.ex04.p2;


import javax.swing.*;

public class AVLTree<T extends Comparable<T>> implements IAVLTree<T> {

    private volatile IAVLNode<T> root;
    boolean rebalance = false;


    public AVLTree() {

    }

    public AVLTree(IAVLNode<T> root) {
        this.root = root;
    }

    @Override
    public void insert(T t) {
        if (root == null) {
            root=new AVLNode<>();
            root.setValue(t);
        } else {
            root = insertNode(root, t);
        }
    }

    private IAVLNode<T> insertNode(IAVLNode<T> node, T t) {
        IAVLNode<T> tmp=node;
        if (node.getValue().compareTo(t) == 0) {
            rebalance = false;
        } else if (node.getValue().compareTo(t) < 0) {
            if (node.getRightChild() != null) {
                //recursive insert to right
                node.setRightChild(insertNode(node.getRightChild(), t));
                if ( rebalance) {
                    switch (node.getBalance()) {
                        case -1:
                            if (node.getRightChild().getBalance() == -1) {
                                tmp = rotateLeft(node);
                                tmp.getLeftChild().setBalance(0);
                            } else {//double rotation
                                int b = node.getRightChild().getLeftChild().getBalance();
                                node.setRightChild(rotateRight(node.getRightChild()));
                                tmp = rotateLeft(node);
                                tmp.getRightChild().setBalance((b == 1) ? -1 : 0);
                                tmp.getLeftChild().setBalance((b == -1) ? 1 : 0);

                            }
                            tmp.setBalance(0);
                            rebalance = false;
                            break;
                        case 0:
                            node.setBalance(-1);
                            break;
                        case 1:
                            node.setBalance(0);
                            rebalance = false;
                    }
                }

            } else {
                IAVLNode<T> newNode = new AVLNode<>();
                newNode.setValue(t);
                node.setRightChild(newNode);
                node.setBalance(node.getBalance() - 1);
                rebalance = (node.getBalance() <= -1);

            }
            // insert to left
        } else if (node.getValue().compareTo(t) > 0) {
            if (node.getLeftChild() != null) {

                node.setLeftChild(insertNode(node.getLeftChild(),t));
                if (rebalance) {
                    switch (node.getBalance()) {
                        case 1:
                            if (node.getLeftChild().getBalance() == 1) {
                                tmp = rotateRight(node);
                                tmp.getRightChild().setBalance(0);
                            } else {//double rotation
                                int b = node.getLeftChild().getRightChild().getBalance();
                                node.setLeftChild(rotateLeft(node.getLeftChild()));
                                tmp = rotateRight(node);
                                tmp.getRightChild().setBalance((b == 1) ? -1 : 0);
                                tmp.getLeftChild().setBalance((b == -1) ? 1 : 0);

                            }
                            tmp.setBalance(0);
                            rebalance = false;
                            break;
                        case 0:
                            node.setBalance(1);
                            break;
                        case -1:
                            node.setBalance(0);
                            rebalance = false;
                    }
                }


            } else {
                IAVLNode<T> newNode = new AVLNode<>();
                newNode.setValue(t);
                node.setLeftChild(newNode);
                node.setBalance(node.getBalance() + 1);
                rebalance = (node.getBalance() >= 1);

            }
        }
        return tmp;
    }

    public IAVLNode<T> rotateLeft(IAVLNode<T> node) {
        IAVLNode<T> tmp = node.getRightChild();
        node.setRightChild((node.getRightChild().getLeftChild()));
        tmp.setLeftChild(node);
        return tmp;
    }

    public IAVLNode<T> rotateRight(IAVLNode node) {
        IAVLNode<T> tmp = node.getLeftChild();
        node.setLeftChild(node.getLeftChild().getRightChild());
        tmp.setRightChild(node);
        return tmp;
    }

    @Override
    public IAVLNode<T> getRootNode() {
        return this.root;
    }

}
