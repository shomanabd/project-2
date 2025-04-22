package org.example.project_2;

public class Stack {
    private int size; //number of elements in the stack
    private   Object Front; // pointer to the top node
    private int index; // the index of the stack list in the cursor
    public Stack (){
//empty stack
        Front=null;
        size=0;
        index=Cursor.createList();
    }

    // Adds a new element to the top of the stack
    public void push(Object element){
        if(element==null)
            return;

       if(Cursor.insertAtHead(element,index)) {
           ++size;
            Front=Cursor.cursorArray[Cursor.cursorArray[index].getNext()].getElement();
       }
    }

    public Object pop() {
        if(!isEmpty()) {
            --size;


            Front=Cursor.cursorArray[Cursor.cursorArray[Cursor.cursorArray[index].getNext()].getNext()].getElement();

            return Cursor.removeFirst(index);
        }
        return null;
    }

    public Object peek(){
// Return the top element without changing the stack
        if (!isEmpty())
            return Front;
        else
            return null;
    }
    public int Size(){
        return size;
    }
    public boolean isEmpty(){
        return (size==0); // return true if the stack is empty
    }

    public int getIndex() {
        return index;
    }
}
