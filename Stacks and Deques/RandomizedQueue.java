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

    // add the item
    public void enqueue(Item item){
        if(item==null){
            throw new NullPointerException("item is null");
        }
        a[n++] = item; 
        if (n == a.length){
            Item[] copy = (Item[]) new Object[2*a.length];
            for (int i = 0; i < n; i++) {
                copy[i] = a[i];
            }
            a = copy;
        }  
          
    }

    // remove and return a random item
    public Item dequeue(){
        if (n==0){
            throw new NoSuchElementException("Stack underflow");
        }     
        int random=StdRandom.uniformInt(n);  
        Item item = a[random];               
        a[random]=a[n-1];
        a[--n]=null;
        n--;           
        if (n > 0 && n == a.length/4){
            Item[] copy = (Item[]) new Object[a.length/2];
            for (int i = 0; i < n; i++) {
                copy[i] = a[i];
            }
            a = copy;
        }

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if(n==0){
            throw new NoSuchElementException("nothing is there");
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
        private int i=n;
        private int[] randomIndex;


        public ReverseArrayIterator() {
            randomIndex=new int[i];
            for(int k=0;k<i;k++){
                randomIndex[k]=k;
            }
            StdRandom.shuffle(randomIndex);
            //call permutation here
        }

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return a[randomIndex[i--]];

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