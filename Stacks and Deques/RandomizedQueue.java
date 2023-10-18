import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {
        private Item[] a;  
        private int n; 
    // construct an empty randomized queue
    public RandomizedQueue(){
        a = (Item[]) new Object[1];
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
    private void resize(int length){
        Item[]copy=(Item[]) new Object[length];
        for(int i=0;i<n;i++){
            copy[i]=a[i];
        }
        a=copy;
        copy=null;
    }

    // add the item
    public void enqueue(Item item){
        if(item==null){
            throw new IllegalArgumentException();
        }
        
        if (n == a.length){
            resize(a.length*2);
        }  
        a[n++] = item; 
          
    }

    // remove and return a random item
    public Item dequeue(){
        if (isEmpty()){
            throw new NoSuchElementException("Stack underflow");
        }     
        int random=StdRandom.uniformInt(n);  
        Item item = a[random];               
        if(random!=n-1){
            a[random]=a[n-1];
        }
        a[n-1]=null;
        n--;  
        if (n > 0 && n == a.length/4){
            resize(a.length/2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        int random =StdRandom.uniformInt(n);
        return a[random];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    // a array iterator, in reverse order
    private class ReverseArrayIterator implements Iterator<Item> {
        private int i;
        private int[] randomIndex;


        public ReverseArrayIterator() {
            i=0;
            randomIndex=StdRandom.permutation(n);
            //call permutation here
        }

        public boolean hasNext() {
            return i <n;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[randomIndex[i++]];

        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<Integer> fin= new RandomizedQueue<>();
        fin.enqueue(5);
        fin.enqueue(10);
        fin.dequeue();
        Iterator<Integer> iterator=fin.iterator();
        System.out.println(iterator.next());
    }

}