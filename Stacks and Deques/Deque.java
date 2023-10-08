public class Deque<Item> implements Iterable<Item> {
    public Item[] list;
    // construct an empty deque
    public Deque(){
        list = new Item[1];

    }

    // is the deque empty?
    public boolean isEmpty(){
        return list[0] == null;
    }

    // return the number of items on the deque
    public int size(){
        return list[].size();
    }

    // add the item to the front
    public void addFirst(Item item){
        if(item==null){
            throw new IllegalArgumentException("item is null");
        }
        Item[] copy=new Item[size()];
        copy=list;
        if(size()+1>=(size()*2)){
            list=new Item[size()*2];
            list[0]=item;
            for(int i=0;i<size()/2-1;i++){
                list[i+1]=copy[i];
            }
        }
        else{
            list[0]=item;
            for(int i=0;i<size()/2-1;i++){
                list[i+1]=copy[i];
            }
        }
    }

    // add the item to the back
    public void addLast(Item item){
        if(item==null){
            throw new IllegalArgumentException("item is null");
        }
        Item[] copy=new Item[size()];
        copy=list;
        if(size()+1>=(size()*2)){
            list=new Item[size()*2];
            list[copy.size()+1]=item;
            for(int i=0;i<size()/2-1;i++){
                list[i]=copy[i];
            }
        }
        else{
            list[copy.size()+1]=item;
            for(int i=0;i<size()/2-1;i++){
                list[i]=copy[i];
            }
        }
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(list.isEmpty()){
            throw new java.util.NoSuchElementException("Stack is empty")
        }
        Item[] copy=new Item[size()];
        copy=list;
        if(size()-1<=(size()/4)){
            list=new Item[size()/2];
            for(int i=0;i<size()/4;i++){
                list[i]=copy[i+1];
            }
        }
        else{
            for(int i=0;i<size()/4;i++){
                list[i]=copy[i+1];
            }
        }
    }

    // remove and return the item from the back
    public Item removeLast(){
        if(list.isEmpty()){
            throw new java.util.NoSuchElementException("Stack is empty")
        }
        Item[] copy=new Item[size()];
        copy=list;
        if(size()-1<=(size()/4)){
            list=new Item[size()/2];
            for(int i=0;i<size()/4-1;i++){
                list[i]=copy[i];
            }
        }
        else{
            for(int i=0;i<size()/4-1;i++){
                list[i]=copy[i];
            }
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){

    }

    // unit testing (required)
    public static void main(String[] args)

}