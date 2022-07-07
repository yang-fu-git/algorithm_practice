package de.unistuttgart.dsass2022.ex03.p5;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class BinarySearchTreeTest {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(65);
        tree.insert(13);
        tree.insert(89);
        tree.insert(1);
        tree.insert(21);
        tree.insert(72);
        tree.insert(101);
//        tree.insert(4);
//        tree.insert(21);
//        tree.insert(13);
//        tree.insert(63);
//        tree.insert(34);
//        System.out.println(tree.isFull());
////        Iterator<Integer> it = tree.iterator(TreeTraversalType.PREORDER);
////        while (it.hasNext()){
////            System.out.println(tree.iterator(TreeTraversalType.PREORDER).next());
////        }
        Iterator<Integer> it = tree.iterator(TreeTraversalType.INORDER);
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }

}
