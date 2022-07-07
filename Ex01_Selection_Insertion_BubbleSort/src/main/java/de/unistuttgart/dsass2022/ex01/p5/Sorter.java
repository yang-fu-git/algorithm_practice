package de.unistuttgart.dsass2022.ex01.p5;

public class Sorter {

    /**
     * Performs selection sort on provided list, works directly on list object,
     * hence no return value
     *
     * @param <T>  The type of list element
     * @param list List to apply the sorting to; unsorted list at first, sorted list
     *             in the end
     */
    public static <T extends Comparable<T>> void selectionSort(ISimpleList<T> list) {
        int start = 0;
        while (start < list.getSize()) {
            T max = list.getElement(start);
            int index = start;
            for (int i = start + 1; i < list.getSize(); i++) {
                if (list.getElement(i).compareTo(max) > 0) {
                    max = list.getElement(i);
                    index = i;
                }
            }
            list.swapElements(start, index);
            start++;
        }
    }

    /**
     * Performs insertion sort on provided list, works directly on list object,
     * hence no return value
     *
     * @param <T>  The type of list element
     * @param list List to apply the sorting to; unsorted list at first, sorted list
     *             in the end
     */
    public static <T extends Comparable<T>> void insertionSort(ISimpleList<T> list) {

        for (int i = 1; i < list.getSize(); i++) {
            for (int j = i; j > 0; j--) {
                if (list.getElement(j).compareTo(list.getElement(j - 1)) > 0) {
                    list.swapElements(j, j - 1);
                } else {
                    break;
                }

            }
        }
    }

    /**
     * Performs bubble sort on provided list, works directly on list object, hence
     * no return value
     *
     * @param <T>  The type of list element
     * @param list List to apply the sorting to; unsorted list at first, sorted list
     *             in the end
     */
    public static <T extends Comparable<T>> void bubbleSort(ISimpleList<T> list) {
        int count = 1;
        while (count != 0) {
            count = 0;
            for (int i = 0; i < list.getSize() - 1; i++) {
                if (list.getElement(i).compareTo(list.getElement(i + 1)) < 0) {
                    list.swapElements(i, i + 1);
                    count++;
                }
            }
        }

    }
}
