package com.example.datastructure;

/**
 * Created by {wq} on 2018/4/16.
 */

public class DoubleLink<T> {
    private Node<T> mHeadNode;
    private int mCount;

    private class Node<T> {
        public Node previous;
        public Node next;
        public T value;

        public Node(T value, Node previous, Node next) {
            this.previous = previous;
            this.next = next;
            this.value = value;
        }
    }

    public DoubleLink() {
        mHeadNode = new Node(null, null, null);
        mHeadNode.previous = mHeadNode.next = mHeadNode;
        mCount = 0;
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index >= mCount) {
            throw new IndexOutOfBoundsException();
        }
        // 正向查找
        if (index <= mCount / 2) {
            Node<T> node = mHeadNode.next;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }
        // 反向查找
        Node<T> rnode = mHeadNode.previous;
        int rindex = mCount - index - 1;
        for (int j = 0; j < rindex; j++) {
            rnode = rnode.previous;
        }

        return rnode;
    }

    public T getFirst() {
        return getNode(0).value;
    }

    public T getLast() {
        return getNode(mCount - 1).value;
    }

    public T get(int index) {
        return getNode(index).value;
    }

    /**
     * 添加在第一个
     *
     * @param t
     */
    public void addFirst(T t) {
        add(0, t);
    }

    /**
     * 添加在最后
     *
     * @param t
     */
    public void addLast(T t) {
        Node<T> newNode = new Node(t, mHeadNode.previous, mHeadNode);
        mHeadNode.previous.next = newNode;
        mHeadNode.previous = newNode;
        mCount++;
    }

    /**
     * 添加在某个节点之前
     *
     * @param index
     * @param t
     */
    public void add(int index, T t) {
        if (index == 0) {
            Node<T> newNode = new Node(t, mHeadNode, mHeadNode.next);
            mHeadNode.next.previous = newNode;
            mHeadNode.next = newNode;
            mCount++;
        } else {
            Node<T> Node = getNode(index);
            Node<T> newNode = new Node(t, Node.previous, Node);
            Node.previous.next = newNode;
            Node.previous = newNode;
            mCount++;
        }
    }

    public void delete(int index) {
        Node<T> node = getNode(index);
        node.previous.next = node.next;
        node.next.previous = node.previous;
        node = null;
        mCount--;
    }
}
