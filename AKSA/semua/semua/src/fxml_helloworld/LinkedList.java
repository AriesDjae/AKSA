package fxml_helloworld;

/**
 *
 * @param <E>
 */
public class LinkedList<E> {
    Element first;
    Element last;
    int size = 0;
    
    public static final class Element<E>{
        E data;
        Element next;
        Element previous;
        
        private Element(E data){
            this.data = data;
        }
    }
    
    public LinkedList(){
        
    }
    
    public Object getFirst(){
        if(size == 0){
            return null;
        }else{
            return first.data;
        }
    }
    
    public Object getLast(){
        if(size == 0){
            return null;
        }else{
            return last.data;
        }
    }
    
    public Object get(int index){
        if (index < 0 || index >= size){
            System.out.println("Index out of bounds");
            System.exit(-1);
        }
        return getElement(index).data;
    }
    
    Element getElement(int n){
        Element e;
        if (n < size / 2){
            e = first;
            // n less than size/2, iterate from start
            while (n-- > 0){
                e = e.next;
            }
        }else{
            e = last;
            // n greater than size/2, iterate from end
            while (++n < size){
                e = e.previous;
            }
        }
        return e;
    }
    
    void removeElement(Element e){
        size--;
        if (size == 0){
            first = last = null;
        }else{
            if (e == first){
                first = e.next;
                e.next.previous = null;
            }else if(e == last){
                last = e.previous;
                e.previous.next = null;
            }else{
                e.next.previous = e.previous;
                e.previous.next = e.next;
            }
        }
    }
    
    public boolean remove(Object o){
        Element e = first;
        
        while (e != null){
            if (o.equals(e.data)){
                removeElement(e);
                return true;
            }
            e = e.next;
        }
        return false;
    }
    
    public Object remove(int index){
        if (index < 0 || index > size){
            System.out.println("Index out of bounds");
            System.exit(-1);
        }
        Element e = getElement(index);
        removeElement(e);
        return e.data;
    }
    
    public Object remove(){
        return removeFirst();
    }
    
    public Object removeFirst(){
        if(size == 0){
            return null;
        }else{
            size--;
            Object removed = first.data;
            
            if (first.next != null){
                first.next.previous = null;
            }else{
                last = null;
            }
            first = first.next;
            return removed;
        }
    }
    
    public Object removeLast(){
        if(size == 0){
            return null;
        }else{
            size--;
            Object removed = last.data;
            
            if (last.previous != null){
                last.previous.next = null;
            }else{
                first = null;
            }
            last = last.previous;
            return removed;
        }
    }
    
    public void add(E obj){
        addLast(obj);
    }
    
    public void addFirst(E obj){
        Element e = new Element(obj);
        
        if (size == 0){
            first = last = e;
        }else{
            e.next = first;
            first.previous = e;
            first = e;
        }
        size++;
    }
    
    public void addLast(E obj){
        addLastElement(new Element (obj));
    }
    
    private void addLastElement(Element e){
        if (size == 0){
            first = last = e;
        }else{
            e.previous = last;
            last.next = e;
            last = e;
        }
        size++;
    }
    
    public void add(int index, E obj){
        if (index < 0 || index > size){
            System.out.println("Index out of bounds");
            System.exit(-1);
        }
        Element e = new Element(obj);
        if (index < size){
            Element after = getElement(index);
            e.next = after;
            e.previous = after.previous;
            if (after.previous == null){
                first = e;
            }else{
                after.previous.next = e;
            }
            after.previous = e;
            size++;
        }else{
            addLastElement(e);
        }
    }
    
    public void clear(){
        if (size > 0){
            first = null;
            last = null;
            size = 0;
        }
    }
    public int size(){
        return size;
    }
    
    public boolean contains(Object o){
        Element e = first;
        while (e != null){
            if (o.equals(e.data)){
                return true;
            }
            e = e.next;
        }
        return false;
    }
}


