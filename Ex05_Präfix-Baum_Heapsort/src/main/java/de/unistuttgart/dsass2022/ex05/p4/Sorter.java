package de.unistuttgart.dsass2022.ex05.p4;

public class Sorter {

    public static <T extends Comparable<T>> void heapSort(final ISimpleList<T> list) {
        int i;
        for(i = list.size()/2; i >= 0; i-- ){
            percolate(list, i, list.size()-1);
        }
        for (i = list.size() - 1; i > 0; i--){
            list.swap(0, i);
            percolate(list, 0, i-1);
        }
    }
    private static <T extends Comparable<T>> void percolate(ISimpleList<T> list, int idx, int last){
        int i = idx, j;
        while ((2*i)+1 <= last){
            j = (2*i)+1;
            if (j+1 <= last) {
                if (list.get(j).compareTo(list.get(j+1)) > 0){
                    j++;
                }
            }
            if (list.get(i).compareTo(list.get(j)) > 0){
                list.swap(i, j);
                i = j;
            }else {
                break;
            }
        }
    }
}

