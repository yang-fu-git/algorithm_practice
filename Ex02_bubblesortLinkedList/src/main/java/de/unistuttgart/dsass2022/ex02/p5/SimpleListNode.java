package de.unistuttgart.dsass2022.ex02.p5;

public class SimpleListNode<T extends Comparable<T>> implements ISimpleListNode<T> {
    private T value = null;
    private ISimpleListNode<T> next = null;


    @Override
    public T getElement() {
        return this.value;
    }

    @Override
    public void setElement(T element) {
        this.value = element;
    }

    @Override
    public ISimpleListNode<T> getNext() {

        return this.next;
    }

    @Override
    public void setNext(ISimpleListNode<T> node) {
        this.next = node;
    }

}
