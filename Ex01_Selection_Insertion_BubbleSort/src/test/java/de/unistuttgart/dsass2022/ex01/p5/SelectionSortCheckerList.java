// SOLUTION
package de.unistuttgart.dsass2022.ex01.p5;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SelectionSortCheckerList<T extends Comparable<T>> extends AbstractSortCheckerList<T> {


        @Override
        public int getSize() {
                return 0;
        }

        @Override
        public void append(T element) {

        }

        @Override
        public T getElement(int index) {
                return null;
        }

        @Override
        public void swapElements(int i, int j) {

        }
}
