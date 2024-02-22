package LinkedListWithIterator;

//Defining my linked list class that is implementing the ListInterface
public class MyLList<E> implements ListInterface<E>{
    //Private member variables to keep track of the first node in list and the number of entries in the list
    private Node firstNode;
    private int numberOfEntries;

    //Constructor to initialize the linked list
    public MyLList() {
        //
        clear();
    }
    //end the constructor

    //Method to add a new entry to the end of the linked list
    @Override
    public void add(E newEntry) {
        Node newNode = new Node (newEntry); // Create a new node with the given data

        Node lastNode;
        if (isEmpty()) {
            firstNode = newNode; // Checks if the list is empty, and if it is, it makes the new node the first node
        } else {
            lastNode = getNodeAt(numberOfEntries);
            lastNode.next = newNode;// Set the next reference of the last node to the new node
        }
        //Increments the number of entries in the list
        numberOfEntries++;
    }
    //Method to add a new entry at a specific position in the linked list
    @Override
    public boolean add(int newPosition, E newEntry) {
        boolean isSuccessful = true;

        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)) {
            //Make a new node with the given data
            Node newNode = new Node(newEntry);
            if (isEmpty() || (newPosition == 1)) {
                newNode.next = firstNode;
                firstNode = newNode;
            } else {
                Node nodeBefore = getNodeAt(newPosition - 1);// Get the node before the new position
                Node nodeAfter = nodeBefore.getNextNode();// Get the node after the new position
                newNode.setNextNode(nodeAfter);
                nodeBefore.setNextNode(newNode); // Set the next reference of the node before to the new node
            }
            numberOfEntries++;
        } else {
            isSuccessful = false;
        }
        return isSuccessful;
    }

    // Method to remove an entry at a specific position from the linked list
    @Override
    public E remove(int givenPosition) {

       E result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            if (givenPosition == 1) {
                result = (E) firstNode.data;
                firstNode = firstNode.next;
            } else {
                Node nodeBefore = getNodeAt(givenPosition - 1);
                Node nodeToRemove = nodeBefore.next;
                Node nodeAfter = nodeToRemove.next;
                nodeBefore.next = nodeAfter;
                result = (E) nodeToRemove.getData();
            }
            numberOfEntries--; // Decrement the number of entries in the list
        }
        else{
            System.out.println(givenPosition + ": is out of range of the list with size: " + numberOfEntries);
        }
        return result;
    }

    //Method to clear the linked list
    @Override
    public void clear() {
        firstNode = null; //Set first nod to null
        numberOfEntries = 0; // reset the number of entries to 0
    }

    //Method to replace the entry at a specific position with a new entry
    @Override
    public E replace(int givenPosition, E newEntry) {

        boolean success = false;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            return null;
        } else {
            Node theNode = this.getNodeAt(givenPosition - 1);
            theNode.setData(newEntry);
            success = true;
        }
        return newEntry;
    }

    // Method to convert the linked list to an array
    @Override
    public E getEntry(int givenPosition) {
        if (givenPosition >= 1 && givenPosition <= numberOfEntries) {
            Node node = getNodeAt(givenPosition);
            return node.data;
        } else {
            throw new IndexOutOfBoundsException("Invalid position: " + givenPosition);
        }
    }
    // Method to convert the linked list to an array
    @Override
    public E[] toArray() {
        E[] array = (E[])new Comparable[numberOfEntries];
        Node currentNode = firstNode;
        int index = 0;

        while (currentNode != null) {
            array[index] = currentNode.data;
            currentNode = currentNode.next;
            index++;
        }
        return array;
    }
    // Method to check if the linked list contains a specific entry
    @Override
    public boolean contains(E anEntry) {
        Node currentNode = firstNode;

        while (currentNode != null) {
            if (currentNode.data.equals(anEntry)) {
                return true;
            }
            currentNode = currentNode.next;
            if (anEntry.equals(currentNode.getData()))
                return true;
        }
        while (currentNode.next != null) ;

        return false;
    }

    // Method to get the length of the linked list
    @Override
    public int getLength() {
        return numberOfEntries;
    }

    // Method to check if the linked list is empty
    @Override
    public boolean isEmpty() {
        boolean result;
        if (numberOfEntries == 0) {
            assert firstNode == null;
            result = true;
        } else {
            assert firstNode != null;
            result = false;
        }
        return result;
    }

    //Method to get the node at a specific position in the linked list
    private Node getNodeAt(int position) {
        if (1 <= position && position <= numberOfEntries) ;
        Node currentNode = firstNode;

        for (int counter = 1; counter < position; counter++) {
            currentNode = currentNode.next;
        }
        assert currentNode != null;
        return currentNode;
    }

    //Method to print the linked list
    public void printLinkedList() {

        int nodeCount = 1;
        Node currentNode = firstNode;
        E data = (E)currentNode.getData();

        System.out.println("Node[1]: " + data);

        while( (currentNode.getNextNode() != null) ) {

            currentNode = currentNode.getNextNode();
            data = (E)currentNode.getData();
            System.out.println("Node[" + (++nodeCount) + "]: " + data);

        }
    }
    // Inner class representing a node in the linked list
    public class Node {
        public E data;
        public Node next;

        // Constructor to create a node with given data
        public Node(E anEntry){
            this(anEntry, null);
        }

        //Constructor a creat a node with given data and next node reference
        private Node(E anEntry, Node x){
            this.data = anEntry;
            this.next = x;
        }
        public E getData(){
            return data;
        }
        public Node getNextNode(){
            return this.next;
        }
        public void setNextNode(Node anEntry){
            this.next = anEntry;
        }
        public void setData(E anEntry){
            this.data = anEntry;
        }
    }
}
