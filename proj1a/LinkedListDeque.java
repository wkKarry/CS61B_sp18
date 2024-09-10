public class LinkedListDeque<T> {
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }

        public Node(T data, Node<T> next, Node<T> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<T> tail;
    private int size;
    private Node<T> sentinel;

    public LinkedListDeque() {
        // sentinel node's data is set to null, as it doesn't need to store any value.
        sentinel = new Node<>(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        tail = sentinel;
        size = 0;
    }

    public void addLast(T data) {
        Node<T> newNode = new Node<>(data, sentinel, tail);
        tail.next = newNode;
        tail = newNode;
        sentinel.prev = tail; // Update sentinel's prev to new tail
        size++;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data, sentinel.next, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        if (size == 0) {
            tail = newNode;
        }
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node<T> current = sentinel.next;
        while (current != sentinel) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) return null;
        Node<T> firstNode = sentinel.next;
        T data = firstNode.data;
        sentinel.next = firstNode.next;
        firstNode.next.prev = sentinel;
        if (--size == 0) {
            tail = sentinel; // Reset tail when list becomes empty
        }
        return data;
    }

    public T removeLast() {
        if (size == 0) return null;
        T data = tail.data;
        tail = tail.prev;
        tail.next = sentinel;
        sentinel.prev = tail;
        size--;
        return data;
    }

    public T get(int index) {
        if (index >= size) return null;
        Node<T> current = sentinel.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public T getRecursive(int index) {
        if (index >= size) return null;
        return helpRecursive(index, sentinel.next);
    }

    private T helpRecursive(int num, Node<T> node) {
        if (num == 0) {
            return node.data;
        } else {
            return helpRecursive(num - 1, node.next);
        }
    }

    // Deep copy constructor
    public LinkedListDeque(LinkedListDeque<T> other) {
        sentinel = new Node<>(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        tail = sentinel;
        size = 0;

        Node<T> current = other.sentinel.next;
        while (current != other.sentinel) {
            this.addLast(current.data);
            current = current.next;
        }
    }
}
