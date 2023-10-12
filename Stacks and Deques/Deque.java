import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Deque<Item> implements Iterable<Item> {
    // construct an empty deque
    public Deque(){
        private Node first;
        private Node last;
        private int length;

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
        Item oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = null;
        if (isEmpty()) last = first;
        else oldfirst.next = first;
        length+=1;
    }

    // add the item to the back
    public void addLast(Item item){
        if(item==null){
            throw new IllegalArgumentException("item is null");
        }
        Item oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        length+=1;

    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(list.isEmpty()){
            throw new java.util.NoSuchElementException("Stack is empty");
        }
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        length-=1;
        return item;
    }
    // remove and return the item from the back
    public Item removeLast(){
        if(list.isEmpty()){
            throw new java.util.NoSuchElementException("Stack is empty");
        }
        String item = last.item;
        last = last.next;
        if (isEmpty()) first = null;
        length-=1;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){

    
    }

    // unit testing (required)
    public static void main(String[] args){
        Deque<Item> fin = new Deque<Item>();
        while (!StdIn.isEmpty()) {
            Item item = StdIn.next();
            if (!item==null)
                fin.addLast(item);
            else if (!fin.isEmpty())
                StdOut.print(fin.addfirst() + " ");
        }
        StdOut.println("(" + fin.size() + " left on queue)");
    }

}
