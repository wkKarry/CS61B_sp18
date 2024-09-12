public interface Deque <Item>{
    public boolean isEmpty();
    public int size();
    public Item removeFirst();
    public Item removeLast();
    public void addFirst(Item item);
    public void addLast(Item item);
    public Item get(int index);
    default public void printDeque(){
        for(int i = 0; i < size(); i++){
            System.out.print(get(i) + " ");
        }
    }
}
