package fxml_helloworld;

/**
*
* @param <E>;
*/
public class ArrayList<E> {
  private Object[] arrayList;
  private static final int DEFAULT_CAPACITY = 10;
  private int size;
  public ArrayList() {
  this(DEFAULT_CAPACITY);
  this.size = 0;
  }
  public ArrayList(int capacity) {
  if (capacity <= 0) {
  System.out.println("The size must be greater than 0.");
  return;
  }
  this.arrayList = new Object[capacity];
  this.size = 0;
  }
  public int size() {
  return this.size;
  }
  public boolean isEmpty() {
  return this.size == 0;
  }
  
  public boolean contains(Object obj) {
  return indexOf(obj) >= 0;
  }
  public int indexOf (Object obj) {
  for (int i = 0; i < this.size(); i++) {
    if (obj.equals(this.arrayList[i])) {
      return i;
  }
  }
  return -1;
  }
  public void clear() {
  if(this.size() > 0){
  this.arrayList = null;
  this.size = 0;
  this.arrayList = new Object[ArrayList.DEFAULT_CAPACITY];
  }
  }
  private boolean isFull() {
  return this.arrayList.length == this.size;
  }
  private void resizeArray() {
  int oldCapacity = this.arrayList.length;
  int newCapacity = oldCapacity + (oldCapacity >> 1);
  Object[] tempArray = new Object[newCapacity];
  for (int i = 0; i < this.size(); i++) {
  tempArray[i] = this.arrayList[i];
  }
  this.arrayList = null;
  this.arrayList = new Object[tempArray.length];
  this.arrayList = tempArray;
  }
  public void add(E obj) {
  if (this.isFull()) {
  this.resizeArray();
  }
  this.arrayList[this.size] = obj;
  this.size++;
}


  public void add(int index, E obj) {
  if (index < 0 || index > this.size()) {
  System.out.println("Index out of bounds");
  //System.out.println("Index out of bounds");
  System.exit(-1);
}else{
  if (this.isFull()) {
      this.resizeArray();
  }
  if(index == this.size()){
      this.arrayList[index] = obj;
  }else{
      Object temp = this.arrayList[index];
      this.arrayList[index] = obj;
      
      Object temp2;

      for (int i = index; i < this.size(); i++) {
          temp2 = this.arrayList[i + 1];
          this.arrayList[i + 1] = temp;
          temp = temp2;
      }
  }
  this.size++;
}
}

public Object get(int index) {
Object element = null;

if (index < 0 || index >= this.size()) {
  System.out.println("Index out of bounds");
  System.exit(-1);
}else{
  element = this.arrayList[index];
}
return element;
}

public void set(int index, E obj){
if (index < 0 || index >= this.size()) {
  System.out.println("Index out of bounds");
  System.exit(-1);
}else{
  this.arrayList[index] = obj;
}
}

public void remove (Object obj) {
int indexFound = this.indexOf(obj);
if(indexFound != -1){
  this.shiftArray(indexFound);
}
}
    
public void remove (int index) {
    if (index < 0 || index >= this.size()) {
        System.out.println("Index out of bounds");
        System.exit(-1);
    }else{
        this.shiftArray(index);
    }
}

private void shiftArray(int index) {
    Object[] tempArray = new Object[this.arrayList.length];
    int indexTemp = 0;
        
    for (int j = 0; j < this.size(); j++) {            
        if(index != j){
            tempArray[indexTemp] = this.arrayList[j];
            indexTemp++;
        }
    }
    this.size--;
    this.arrayList = null;
    this.arrayList = tempArray;
}
}
