package synthesizer;

public abstract class AbstractBoundedQueue <T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;
    public int capacity(){
        return 0;
    }
    public int fillCount(){
        return 0;
    }
    public abstract T peek();
    public abstract T dequeue();
    public abstract void enqueue(T x);
}
