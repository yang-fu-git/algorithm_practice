package de.unistuttgart.dsass2022.ex02.p5;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

public class SimpleListTest {
    public static void main(String[] args){
        ISimpleList<Integer> list =new SimpleList<>();
        for(int i=0;i<5;i++){
            list.prepend(i);
        }
        list.sort();
        for(int i=0;i<5;i++){
            System.out.println(list.getElement(i));
        }
    }

}
