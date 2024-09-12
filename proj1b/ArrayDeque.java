public class ArrayDeque<Item> implements Deque<Item> {
    private Item[] array;
    private int size;
    private int front;  // 头指针
    private int back;   // 尾指针

    public ArrayDeque() {
        array = (Item[]) new Object[8];
        size = 0;
        front = 0;
        back = 0;
    }

    @Override
    public void addLast(Item data) {
        if (size == array.length) {
            this.resize();
        }
        array[back] = data;
        back = (back + 1) % array.length;  // 循环数组，使用取模操作
        size++;
    }

    @Override
    public void addFirst(Item data) {
        if (size == array.length) {
            this.resize();
        }
        front = (front - 1 + array.length) % array.length;  // 更新 front，循环数组
        array[front] = data;
        size++;
    }

    @Override
    public Item removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        back = (back - 1 + array.length) % array.length;  // 更新 back，循环数组
        Item res = array[back];
        array[back] = null;
        size--;
        if (size <= array.length / 4 && array.length > 16) {
            this.shrink();
        }
        return res;
    }

    @Override
    public Item removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        Item res = array[front];
        array[front] = null;
        front = (front + 1) % array.length;  // 更新 front，循环数组
        size--;
        if (size <= array.length / 4 && array.length > 16) {
            this.shrink();
        }
        return res;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Item get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return array[(front + index) % array.length];  // 通过 front 偏移得到正确的索引
    }

    public ArrayDeque(ArrayDeque<Item> other) {
        this.array = (Item[]) new Object[other.array.length];
        this.size = other.size;
        this.front = other.front;
        this.back = other.back;
        System.arraycopy(other.array, 0, this.array, 0, other.array.length);
    }

    private void resize() {
        Item[] tempArray = (Item[]) new Object[array.length * 2];
        for (int i = 0; i < size; i++) {
            tempArray[i] = array[(front + i) % array.length];  // 重新调整数据的顺序
        }
        array = tempArray;
        front = 0;
        back = size;
    }

    private void shrink() {
        Item[] tempArray = (Item[]) new Object[array.length / 2];
        for (int i = 0; i < size; i++) {
            tempArray[i] = array[(front + i) % array.length];  // 重新调整数据的顺序
        }
        array = tempArray;
        front = 0;
        back = size;
    }
}
