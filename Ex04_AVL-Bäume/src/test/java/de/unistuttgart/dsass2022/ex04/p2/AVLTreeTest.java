package de.unistuttgart.dsass2022.ex04.p2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import de.unistuttgart.dsass2022.ex04.p2.AVLNode;
import de.unistuttgart.dsass2022.ex04.p2.AVLTree;
import de.unistuttgart.dsass2022.ex04.p2.IAVLNode;

public class AVLTreeTest {
    public static void main(String[] args) {

        AVLTree<Integer> avlTree = new AVLTree<>();
        avlTree.insert(3);
        avlTree.insert(2);
        avlTree.insert(1);
        avlTree.insert(4);
        avlTree.insert(5);
        avlTree.insert(6);


        ArrayList<IAVLNode> list = new ArrayList<>();
        list.add(avlTree.getRootNode());


        while (!list.isEmpty()) {
            for (int i = list.size(); i > 0; i--) {
                IAVLNode curNode = list.remove(0);
                if(curNode==null){
                    System.out.print(null +"\t");
                    continue;
                }
                System.out.print(curNode.getValue() + "\t");
                list.add(curNode.getLeftChild());
                list.add(curNode.getRightChild());


            }
            System.out.println();


        }


    }

}
