package de.unistuttgart.dsass2022.ex02.p5;


public class SimpleList<T extends Comparable<T>> implements ISimpleList<T> {
    private int size = 0;
    private ISimpleListNode<T> header = new SimpleListNode<>();


    @Override
    public int getSize() {

        return this.size;
    }

    @Override
    public void prepend(T element) {
        this.size++;
        ISimpleListNode<T> newNode = new SimpleListNode<>();
        newNode.setElement(element);
        newNode.setNext(header.getNext());
        header.setNext(newNode);


    }

    @Override
    public T getElement(int index) {
        if (index >= size) {
            return null;
        }
        ISimpleListNode<T> cur = header;
        for (int i = 0; i <= index; i++) {
            cur = cur.getNext();
        }
        return cur.getElement();
    }


    @Override
    public void sort() {
        int count = 1;
        while (count != 0) {
            count = 0;
            ISimpleListNode<T> cur = header.getNext();
            ISimpleListNode<T> prev = header;
            for (int i = 0; i < size - 1; i++) {
                if (cur.getElement().compareTo(cur.getNext().getElement()) > 0) {
                    ISimpleListNode<T> next =cur.getNext();
                    prev.setNext(next);
                    cur.setNext(next.getNext());
                    next.setNext(cur);
                    prev = prev.getNext();
                    count++;
                } else {
                    prev = prev.getNext();
                    cur = cur.getNext();
                }

            }

        }
    }


}
