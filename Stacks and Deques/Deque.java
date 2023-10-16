import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    // construct an empty deque
    private class Node
    {
        Item item;
        Node next;
        Node prev;
    }

    private Node first;
    private Node last;
    private int length;
    
    public Deque(){
        first=null;
        last=null;
        length=0;

    }


    // is the deque empty?
    public boolean isEmpty(){
        return first == null;
    }

    // return the number of items on the deque
    public int size(){
        return length;
    }

    // add the item to the front
    public void addFirst(Item item){
        if(item==null){
            throw new IllegalArgumentException("item is null");
        }
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.prev = null;
        if (isEmpty()){
            last = first;
        }
        else{
            oldfirst.prev = first;
        }
        length+=1;
    }

    // add the item to the back
    public void addLast(Item item){
        if(item==null){
            throw new IllegalArgumentException("item is null");
        }
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldlast;
        length+=1;
        if (isEmpty()){
            first = last;
        } 
        else {
            oldlast.next = last;
        }
        length+=1;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(isEmpty()){
            throw new java.util.NoSuchElementException("Stack is empty");
        }
        Item item = first.item;
        first = first.next;
        length--;
        if (isEmpty()){
            last = first;
        }
        else{
            first.prev = null;
        } 
        return item;
    }
    // remove and return the item from the back
    public Item removeLast(){
        if(isEmpty()){
            throw new java.util.NoSuchElementException("Stack is empty");
        }

        Item item = last.item;
        last = last.prev;
        length--;
        if (isEmpty()){
            first = last;
        }
        else{
            last.next = null;
        } 
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()  {
        return new LinkedIterator(first);
    }

    // a linked-list iterator
    private class LinkedIterator implements Iterator<Item> {
        private Node current;

        public LinkedIterator(Node first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    //unit testing (required)
    public static void main(String[] args){
        Deque<Integer> fin = new Deque<Integer>();
        fin.addFirst(2);
        fin.addLast(3);
        fin.addFirst(5);
        fin.removeLast();
        fin.removeFirst();
        Iterator<Integer> iterator = fin.iterator();
        System.out.println(iterator.next());
    }

}
