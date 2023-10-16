import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {
        private Item[] a;  
        private int n; 
    // construct an empty randomized queue
    public RandomizedQueue(){
        a = (Item[]) new Object[2];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return n==0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return n;
    }

    // add the item
    public void enqueue(Item item){
        if (n == a.length){
            Item[] copy = (Item[]) new Object[2*size()];
            for (int i = 0; i < n; i++) {
                copy[i] = a[i];
            }
            a = copy;
        }  
        a[n++] = item;   
    }

    // remove and return a random item
    public Item dequeue(){
        if (isEmpty()){
            throw new NoSuchElementException("Stack underflow");
        }     
        int random=StdRandom.uniformInt(0, n-1);  
        Item item = a[random];                          
        if (n > 0 && n == a.length/4){
            Item[] copy = (Item[]) new Object[size()/2];
            for (int i = 0; i < n; i++) {
                copy[i] = a[i];
            }
            a = copy;
        }
        a[random]=a[n-1];
        a[n-1]=null;
        n--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        Item fin=a[(int)Math.floor(Math.random() * (size()+ 1))];
        return fin;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    // a array iterator, in reverse order
    private class ReverseArrayIterator implements Iterator<Item> {
        private int i;

        public ReverseArrayIterator() {
            i = n-1;
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[i--];
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        
    }

}