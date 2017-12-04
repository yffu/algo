import java.util.Iterator;


public class Deque<Item> implements Iterable<Item> {
    
    private int n;
    private Node first;
    private Node last;
    
    private class Node {
        private Item item;
        private Node next = null;
        private Node prev = null;
    }

    public Deque() {
        n=0;
        first=null;
        last=null;
    }
    
    public boolean isEmpty() {
        // when first == null, last == null, n == 0;
        return size()==0;
    }
    
    public int size() {
        return n;
    }
    
    public void addFirst(Item item) {
        if (item==null) throw new java.lang.IllegalArgumentException("null item");
        Node oldfirst=first;
        first=new Node();
        first.item=item;
        first.next=oldfirst;
        if (oldfirst==null) last=first; //oldfirst is null
        else oldfirst.prev=first; //oldfirst is not null - is an item
        n++;
    }
    
    public void addLast(Item item) {
        if (item==null) throw new java.lang.IllegalArgumentException("null item");
        Node oldlast=last;
        last=new Node();
        last.item=item;
        last.prev=oldlast;
        if (oldlast==null) first=last;
        else oldlast.next=last;
        n++;
    }
    
    public Item removeFirst() {
        if (isEmpty()) throw new java.util.NoSuchElementException("empty deque");
        Item item=first.item;
        if(first.next!=null) first.next.prev=first.prev; // if more than 1 element
        else last=first.next; // if 1 element, last is null after removal
        first=first.next;
        n--;
        return item;
    }
    
    public Item removeLast() {
        if (isEmpty()) throw new java.util.NoSuchElementException("empty deque");
        Item item=last.item;
        if (last.prev!=null) last.prev.next=last.next; // if more than 1 element
        else first = last.prev;
        last=last.prev;
        n--;
        return item;
    }
    
    public Iterator<Item> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
