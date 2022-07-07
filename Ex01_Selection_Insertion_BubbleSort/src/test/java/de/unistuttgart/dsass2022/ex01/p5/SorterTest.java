package de.unistuttgart.dsass2022.ex01.p5;

import static org.junit.Assert.*;

// START SOLUTION
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
// END SOLUTION

import org.junit.Test;

import de.unistuttgart.dsass2022.ex01.p5.ISimpleList;
import de.unistuttgart.dsass2022.ex01.p5.Sorter;
import org.junit.jupiter.api.BeforeEach;

public class SorterTest {


    @Test
    public void testSelectionSort() {
        ISimpleList<Integer> list = new SimpleList<>();

        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        list.append(5);
//      Sorter.selectionSort(list);
//      Sorter.insertionSort(list);
        Sorter.bubbleSort(list);

        for (int i = 0; i < list.getSize(); i++) {
            System.out.println(list.getElement(i));

        }
    }


}
