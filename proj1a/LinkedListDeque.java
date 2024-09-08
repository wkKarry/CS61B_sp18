public class LinkedListDeque<T> {
    private static class Node<T> {
        T data;
        Node next;
        Node prev;

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }

        public Node(T data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node tail;
    private int size;
    private Node sentinel;

    public LinkedListDeque() {
        sentinel = new Node("sentinel");
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        tail = sentinel;
        size = 0;
    }

    public void addLast(T data) {
        tail.next = new Node(data, null, tail);
        tail = tail.next;
        size++;
    }

    public void addFirst(T data) {
        sentinel.next = new Node(data, sentinel.next, sentinel);
        if (size == 0) {
            tail = sentinel.next;
        }
        size++;
    }

    public boolean isEmpty() {
        if (size == 0) return true;
        else return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (Node p = sentinel; p != tail; p = p.next) {
            System.out.print(p.next.data + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) return null;
        size--;
        T pop = (T) sentinel.next.data;
        sentinel.next = sentinel.next.next;
        return pop;
    }

    public T removeLast() {
        if (size == 0) return null;
        size--;
        T pop = (T) tail.data;
        tail = tail.prev;
        tail.next = sentinel;
        return pop;
    }

    public T get(int index) {
        if (size <= index) return null;
        Node pointer = sentinel.next;
        for (int i = 0; i < index; i++) {
            pointer = pointer.next;
        }
        return (T) pointer.data;
    }

    public T getRecursive(int index) {
        if (size <= index) return null;
        Node pointer = sentinel.next;
        return helpRecursive(index, pointer);
    }

    private T helpRecursive(int num, Node pointer) {
        if (num == 0) {
            return (T) pointer.data;
        } else {
            return helpRecursive(num - 1, pointer.next);
        }
    }

    /*public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node("sentinel");
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        tail = sentinel;
        size = other.size;
        for(int i =0;i<size;i++){
            T data = (T) other.removeLast();
            this.addLast(data);
        }
    }*/
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
