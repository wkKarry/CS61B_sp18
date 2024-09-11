public class LinkedListDeque<Item> {
    private static class Node<Item> {
        Item data;
        Node<Item> next;
        Node<Item> prev;

        public Node(Item data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }

        public Node(Item data, Node<Item> next, Node<Item> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<Item> tail;
    private int size;
    private Node<Item> sentinel;

    public LinkedListDeque() {
        // sentinel node's data is set to null, as it doesn't need to store any value.
        sentinel = new Node<>(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        tail = sentinel;
        size = 0;
    }

    public void addLast(Item data) {
        Node<Item> newNode = new Node<>(data, sentinel, tail);
        tail.next = newNode;
        tail = newNode;
        sentinel.prev = tail; // Update sentinel's prev to new tail
        size++;
    }

    public void addFirst(Item data) {
        Node<Item> newNode = new Node<>(data, sentinel.next, sentinel);
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
        Node<Item> current = sentinel.next;
        while (current != sentinel) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public Item removeFirst() {
        if (size == 0) return null;
        Node<Item> firstNode = sentinel.next;
        Item data = firstNode.data;
        sentinel.next = firstNode.next;
        firstNode.next.prev = sentinel;
        if (--size == 0) {
            tail = sentinel; // Reset tail when list becomes empty
        }
        return data;
    }

    public Item removeLast() {
        if (size == 0) return null;
        Item data = tail.data;
        tail = tail.prev;
        tail.next = sentinel;
        sentinel.prev = tail;
        size--;
        return data;
    }

    public Item get(int index) {
        if (index >= size) return null;
        Node<Item> current = sentinel.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public Item getRecursive(int index) {
        if (index >= size) return null;
        return helpRecursive(index, sentinel.next);
    }

    private Item helpRecursive(int num, Node<Item> node) {
        if (num == 0) {
            return node.data;
        } else {
            return helpRecursive(num - 1, node.next);
        }
    }

    // Deep copy constructor
    public LinkedListDeque(LinkedListDeque<Item> other) {
        sentinel = new Node<>(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        tail = sentinel;
        size = 0;

        Node<Item> current = other.sentinel.next;
        while (current != other.sentinel) {
            this.addLast(current.data);
            current = current.next;
        }
    }
}
