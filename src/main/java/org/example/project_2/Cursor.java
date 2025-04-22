package org.example.project_2;

public class Cursor {
    public static Node[] cursorArray;
    private final static int MAX_SIZE = 100; /* max array size ofcursor */

    public  Cursor(){

    }

    static {
        initialization();
    }


    /* Initialize the cursor array to null and next index */
    public static void initialization() {
        cursorArray = new Node[MAX_SIZE];
        for (int i = 0; i < MAX_SIZE; i++)
            cursorArray[i] = new Node(null, i + 1);
        cursorArray[MAX_SIZE - 1].setNext(0);
    }

    /* returns the first node after the header (next of the head) */
    public static int cursorAlloc() {
        int p = cursorArray[0].getNext();
        cursorArray[0].setNext(cursorArray[p].getNext());
        return p;// return the index of the available node (most likely empty node)
    }



    public static int createList(){
        /*create new empty list*/
        int l= cursorAlloc();
        if (l==0)
            System.out.println ("Error:out of space");
        else
            cursorArray[l]=new Node("-"
                    ,0);//Empty Linked List
        return l; /*Head of the list*/
    }


    private static void cursorFree(int p) {
        cursorArray[p].setElement(null); // free the content
        cursorArray[p].setNext(cursorArray[0].getNext());
        cursorArray[0].setNext( p);
    }

    public static boolean isNull (int l){
        /*return true if the list not created*/
        return cursorArray[l]==null;
    }
    public static boolean isEmpty (int l){
//return true if the list is empty
        return cursorArray[l].getNext()== 0;
    }
    public static boolean isLast(int p){
//check if the node p is last or not
        return cursorArray[p].getNext()==0;
    }

    public static Boolean insertAtHead (Object data,int l)
    {
        if (isNull(l)) //list not created
            return false;
        int p=cursorAlloc();
        if (p!=0){
            cursorArray[p]=new Node (data,cursorArray[l].getNext());
            cursorArray[l].setNext(p);
            return true;
        }
        else
            System.out.println("Out Of Space");
        return false;
    }

    public static int find (Object data,int l) {
        int p = cursorArray[l].getNext();
        while((p != 0 )&& !cursorArray[p].getElement().equals(data))
            p = cursorArray[p].getNext();
        return p;
    }

    public static void remove(Object data,int l ) {
        int pos = findPrevious( data,l );//Implementation left as
//an exercise
        if( cursorArray[pos].getNext() != 0 ){//!isLast (pos)
            int tmp = cursorArray[pos].getNext();
            cursorArray[pos].setNext(cursorArray[tmp].getNext());
            cursorFree( tmp );
        }
    }


    public static Object removeFirst(int l ) {

        if( cursorArray[l].getNext() != 0 ){// list empty or not
            int tmp = cursorArray[l].getNext();
            Object ans=cursorArray[tmp].getElement();
            cursorArray[l].setNext(cursorArray[tmp].getNext());
            cursorFree( tmp );
            return ans;
        }
        System.out.println("List is empty");
        return null;
    }


    public static void insert(Object data, int l, int p) {
        // Insert data at position p in list l
        if (isNull(l) || p <= 0) {
            System.out.println("Invalid list or position");
            return;
        }

        int newNode = cursorAlloc();
        if (newNode != 0) {
            cursorArray[newNode] = new Node(data, cursorArray[l].getNext());
            cursorArray[l].setNext(newNode);
        } else {
            System.out.println("Out Of Space");
        }
    }

    public static void traversList(int l) {
        // Traverse and print the elements of the list
        if (isNull(l)) {
            System.out.println("List not created");
            return;
        }

        int p = cursorArray[l].getNext();
        while (p != 0) {
            System.out.print(cursorArray[p].getElement() + " ");
            p = cursorArray[p].getNext();
        }
        System.out.println(); // Add a newline after printing the list
    }

    public static int findPrevious(Object element, int l) {
        // Returns the previous location for a specific element
        if (isNull(l) || isEmpty(l)) {
            System.out.println("List not created or empty");
            return -1; // Return an invalid position
        }

        int pos = l;
        int current = cursorArray[l].getNext();
        while (current != 0 && !cursorArray[current].getElement().equals(element)) {
            pos = current;
            current = cursorArray[current].getNext();
        }
        return pos;
    }


    public static Node getLast(int l){

        // Returns the previous location for a specific element
        if (isNull(l) || isEmpty(l)) {
            System.out.println("List not created or empty");
            return null; // Return an invalid position
        }

        int pos = l;
        int current = cursorArray[l].getNext();
        while (current != 0 ) {
            pos = current;
            current = cursorArray[current].getNext();
        }
        return Cursor.cursorArray[Cursor.cursorArray[pos].getNext()];


    }





}

