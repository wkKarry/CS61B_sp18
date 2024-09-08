import java.util.Arrays;

public class ArrayDeque<T> {
    private T[] array;
    private int size;
    public ArrayDeque() {
        array= (T[]) new Object[8];
        size = 0;
    }
    public void addLast(T data){
        if(size == array.length){
            this.resize();
        }
        array[size++] = data;
    }

    public void addFirst(T data){
        if(size == array.length){
            this.resize();
        }
        for (int i = 0; i < size; i++) {
            array[i+1] = array[i];
        }
        array[0] = data;
        size++;
    }


    public T removeLast(){
        if(size == 0){
            return null;
        }
        T res = array[--size];
        array[size] = null;
        if(size <= array.length/4 && array.length >16){
            this.shrink();
        }
        return res;
    }

    public T removeFirst(){
        if(size == 0){
            return null;
        }
        T res = array[0];
        for (int i = 0; i < size; i++) {
            array[i] = array[i+1];
        }
        if(size <= array.length/4 && array.length >16){
            this.shrink();
        }
        return res;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }
    public T get(int index){
        return array[index];
    }

    public ArrayDeque(ArrayDeque other){
        this.array = (T[]) new Object[other.array.length];
        System.arraycopy(other.array, 0, this.array, 0, other.size);
    }

    private ArrayDeque(int len){
        this.array = (T[]) new Object[len];
    }

    private void shrink() {
            T[] temp_array = (T[]) new Object[array.length / 2];  // 缩小为原来的一半
            System.arraycopy(array, 0, temp_array, 0, size);
            this.array = temp_array;
    }

    private void resize(){
        T[] temp_array = (T[])new Object[array.length*2];
        System.arraycopy(array, 0, temp_array, 0, array.length);
        this.array = temp_array;
    }

}
