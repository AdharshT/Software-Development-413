package LinkedListWithIterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

//Extending the MyLLISt class and implementing the ListWithIteratorInterface interface
public class LinkedListWithIterator<E extends Comparable> extends MyLList<E> implements ListWithIteratorInterface<E>{

    // Constructor calling the superclass constructor
    public LinkedListWithIterator(){
        super();
    }

    // Method to get an iterator for the linked list
    public Iterator<E> getIterator() {
        return new LinkedListIterator();
    }

    //Overriding the iterator method from the Iterable interface
    @Override
    public Iterator<E> iterator() {
        return getIterator();
    }

    // Inner class implementing the iterator interface
    private class LinkedListIterator implements Iterator<E> {

        //Current position
        private int position;
        //Checks if there is a next element
        private boolean nextArea;

        // Constructor for the iterator
        private LinkedListIterator(){
            position = 1; // Starts from the beginning of the list
            nextArea = false; // Initially, there is no next element
        }

        //Method to check if there is a next element in the list
        @Override
        public boolean hasNext() {
            return position <= getLength(); // Check if the current position
        }

        //Method to get the next element in the list
        @Override
        public E next() {
           if (hasNext()){ // checks if there is a next element
               nextArea = true; // Set the flag to indicate that there is a next element
               E newEntry = (E) getEntry(position); // Get the entry at the current posiiton
               position++; // Move to the next position

               return newEntry; // Return the next element
           }
           else
               throw new NoSuchElementException("Illegal call to next();" +
                       "iterator is after end of list.");
           }

           // Method to remove the current element from the list
        @Override
        public void remove() {
            if (nextArea){
            }
            else
                throw new IllegalStateException("Illegal call to remove(); " +
                        "next() was not called.");
        }

            }
        }



