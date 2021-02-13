/*
* Defines the a doubly-linked list class
 * @author Navdeep Sharad
 */

import java.util.NoSuchElementException;

public class List<T extends Comparable<T>> {
    private class Node {
        public T data;
        private Node next;
        private Node prev;
        
        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    
    private int length;
    private Node first;
    private Node last;
    private Node iterator;
    
    /****CONSTRUCTOR****/
    
    /**
     * Instantiates a new List with default values
     * @postcondition: The values of the variables are set
     */
    public List() {
    	first = null;
    	last = null;
    	iterator = null;
    	length = 0;
 
    }
    
    /**
     * Instantiates a new List by copying another List
     * @param original the List to make a copy of
     * @postcondition a new List object, which is an identical
     * but separate copy of the List original
     */
    public List(List<T> original) {
        if (original == null) {
            return;
        }
        if (original.length == 0) {
            length = 0;
            first = null;
            last = null;
            iterator = null;
        } else {
            Node temp = original.first;
            while (temp != null) {
                addLast(temp.data);
                temp = temp.next;
            }
            iterator = null;
        }
    }
    
    /****ACCESSORS****/
    
    /**
     * Returns the value stored in the first node
     * @precondition length != 0
     * @return the value stored at node first
     * @throws NoSuchElementException when precondition is violated
     */
    public T getFirst() throws NoSuchElementException{
    	 if (length == 0) {
         	throw new NoSuchElementException("getFirst(): Cannot remove from an empty list!");
         }
    	 return first.data;  
   }
    
    /**
     * Returns the value stored in the last node
     * @precondition: length != 0
     * @return the value stored in the node last
     * @throws NoSuchElementException when precondition is violated
     */
   public T getLast() throws NoSuchElementException{
  	 if (length == 0) {
      	throw new NoSuchElementException("getLast(): Cannot remove from an empty list!");
      }
 	 return last.data;
        
   }
    
    /**
     * Returns the current length of the list
     * @return the length of the list from 0 to n
     */
    public int getLength() {
        return length;
    }
    
    /**
     * Returns whether the list is currently empty
     * @return whether the list is empty
     */
    public boolean isEmpty() {
    	return length == 0;
    }
    
    /**
     * Returns element currently pointed at by iterator
     * @precondition: iterator != null
     * @return the value pointed at by iterator
     * @throws NoSuchElementException when precondition is violated
     */
    public T getIterator() throws NullPointerException {
    	if(iterator == null) {
    		throw new NullPointerException("getIterator(): Can't get iterator because it's null");
    	}
    	return iterator.data;
    }
    
    /**
     * Returns whether iterator is off the end of the list(null) or not
     * @return whether iterator is null
     */
    public boolean offEnd() {
    	return iterator == null;
    }
    
    /**
     * Determines whether two Lists have the same data
     * in the same order
     * @param L the List to compare to this List
     * @return whether the two Lists are equal
     */
    @SuppressWarnings("unchecked")
    @Override public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if (!(o instanceof List)) {
            return false;
        } else {
            List<T> L = (List<T>) o; 
            if (this.length != L.length) {
                return false;
            } else {
                Node temp1 = this.first;
                Node temp2 = L.first;
                while (temp1 != null) { //Lists are same length
                    if (temp1.data != temp2.data) {
                        return false;
                    }
                    temp1 = temp1.next;
                    temp2 = temp2.next;
                }
                return true;
            }
        }
    }
    
    /**
     * Returns the index of the iterator
     * from 1 to n. Note that there is 
     * no index 0. Does not use recursion.
     * @precondition iterator != null
     * @return the index of the iterator
     * @throws NullPointerException when
     * the precondition is violated
     */
    public int getIndex() throws NullPointerException{
    	if (offEnd())
    		throw new NullPointerException("getIndex(): Error - iterator points to a null value");
    	else {
    		Node temp = first;
    		int counter = 1;
    		while(temp.next != iterator.next) {
    			temp = temp.next;
    			counter++;
    		}
    		return counter;
    	}
    }
    
    /**
     * Uses the iterative linear search
     * algorithm to locate a specific
     * element in the list
     * @param element the value to search for
     * @return the location of value in the
     * List or -1 to indicate not found
     * Note that if the List is empty we will
     * consider the element to be not found
     * @postcondition: position of the iterator remains
     * unchanged!
     */
    public int linearSearch(T element) { 
    	Node temp = iterator;
    	placeIterator();
    	for (int i = 1; i < length; i ++) {
    		if (iterator.data == element) {
    			iterator = temp;
    			return i;
    		}
    		advanceIterator();
    	}
    	return -1;
    }
    
    /**
     * Returns the index from 1 to length
     * where value is located in the List
     * by calling the private helper method
     * binarySearch
     * @param value the value to search for
     * @return the index where value is 
     * stored from 1 to length, or -1 to
     * indicate not found
     * @precondition isSorted()
     * @postcondition the position of the
     * iterator must remain unchanged! 
     * @throws IllegalStateException when the
     * precondition is violated.
     */
    public int binarySearch(T value) throws IllegalStateException {
    	if (!isSorted()) {
    		throw new IllegalStateException("binarySearch(): The list is not sorted!");
    	}
    	return binarySearch(1, length, value);
    }
    
    /**
     * Searches for the specified value in
     * the List by implementing the recursive
     * binarySearch algorithm
     * @param low the lowest bounds of the search
     * @param high the highest bounds of the search
     * @param value the value to search for
     * @return the index at which value is located
     * or -1 to indicate not found
     * @postcondition the location of the iterator
     * must remain unchanged
     */
    private int binarySearch(int low, int high, T value) {
    	int index = getIndex();
    	if (high < low) {
    		return -1;
    	}
    	int mid = low + (high - low) /2;
    	advanceToIndex(mid);
    	if (getIterator()==value) {
    		advanceToIndex(index);
    		return mid;	
    	} else if (getIterator().compareTo(value)>0) {
    		return binarySearch(low, mid -1, value);
    	} else {
    		return binarySearch(mid+1, high, value);
    	}
    }
    
 
    /****MUTATORS****/
    
    /**
     * Creates a new first element
     * @param data the data to insert at the 
     * front of the list
     * @postcondition There is a new first Node.
     */
    public void addFirst(T data) {
        if (first == null) {
        	first = last = new Node(data);
        }
        else {
        	Node n = new Node(data);
        	n.next = first;
        	first.prev = n;
        	first = n;
        }
        length++;
    }
    
    /**
     * Creates a new last element
     * @param data the data to insert at the 
     * end of the list
     * @postcondition There is a new last Node
     */
    public void addLast(T data) {
        if (last == null) {
        	first = last = new Node(data);
        }
        else {
        	Node n = new Node(data);
        	last.next = n;
        	n.prev = last;
        	last = n;
        }
        length++;
    }
    
    /**
    * removes the element at the front of the list
    * @precondition First cannot be equal to null
    * @postcondition The second element turns into the first
    * @throws NoSuchElementException when precondition is violated
    */
    public void removeFirst() throws NoSuchElementException{
        if (length == 0) {
        	throw new NoSuchElementException("removeFirst(): Cannot remove from an empty list!");
        }
        else if(length == 1) {
        	first = last = null;
        }
        else {
        	first = first.next;
        	first.prev = null;
        }
        length--;
    }
    
    /**
     * removes the element at the end of the list
     * @precondition Last in not a null value
     * @postcondition Second to last element becomes the last element
     * @throws NoSuchElementException when precondition is violated
     */
    public void removeLast() throws NoSuchElementException{
    	 if (length == 0) {
         	throw new NoSuchElementException("removeLast(): Cannot remove from an empty list!");
         }
         else if(length == 1) {
         	first = last = null;
         }
         else {
        	 last = last.prev;
        	 last.next = null;
         }
    	length--;   
    }
    /**
     * Places iterator at the start of the list
     * 
     */
    public void placeIterator() {
    	iterator = first;
    }
    
    /**
     * Removes element currently pointed to by the iterator
     * @precondition Iterator != null;
     * @postcondition Iterator points to null
     * @throws NullPointerException when precondition is violated
     */
    public void removeIterator() throws NullPointerException {
    	if(offEnd()) {
    		throw new NullPointerException("removeIterator(): Can't remove when iterator points to null");
    	}
    	else if(iterator == first) {
    		removeFirst();
    	}
    	else if(iterator == last) {
    		removeLast();
    	}
    	else {
    	iterator.prev.next = iterator.next;
    	iterator.next.prev = iterator.prev;
    	length--;
    	}
    	iterator = null;
    }
    
    /**
     * Adds element after node currently pointed to by the iterator
     * @param the new data to be inserted into the list
     * @precondition Iterator != null;
     * @throws NullPointerException when precondition is violated
     */
    public void addIterator(T data) throws NullPointerException {
    	if(offEnd()) {
    		throw new NullPointerException("addIterator(): Can't add when iterator points to null");
    	}
    	else if(iterator == last) {
    		addLast(data);
    	}
    	else {
    		Node n = new Node(data);
    		n.next = iterator.next;
    		n.prev = iterator;
    		iterator.next.prev = n;
    		iterator.next = n;
    		length++;
    	}
    	
    }
    
    /**
     * Moves iterator up by one node in the list
     * @precondition Iterator != null;
     * @throws NullPointerException when precondition is violated
     */
    public void advanceIterator() throws NullPointerException {
    	if(offEnd()) {
    		throw new NullPointerException("advanceIterator(): The iterator is equal to null and cannot advance.");
    	}
    	iterator = iterator.next;
    }
    
    /**
     * Moves iterator down by one node in the list
     * @precondition Iterator != null;
     * @throws NullPointerException when precondition is violated
     */
    public void reverseIterator() throws NullPointerException {
    	if(offEnd()) {
    		throw new NullPointerException("reverseIterator(): The iterator is equal to null and cannot reverse.");
    	}
    	iterator = iterator.prev;
    }
    
    /**
     * Places the iterator at first
     * and then iteratively advances 
     * it to the specified index
     * no recursion
     * @param index the index where
     * the iterator should be placed
     * @precondition 1 <= index <= length
     * @throws IndexOutOfBoundsException
     * when precondition is violated
     */
    public void advanceToIndex(int index) throws IndexOutOfBoundsException{
    	if (index > length || index < 1 )
    		throw new IndexOutOfBoundsException("advanceToIndex(): The index selected is out of bounds");
        placeIterator();
        for (int i = 1; i < index; i++) {
        	advanceIterator();
        }
    }
    
    /****ADDITIONAL OPERATIONS****/
    
    /**
     * List with each value on its own line
     * At the end of the List a new line
     * @return the List as a String for display
     */
    @Override public String toString() {
    	String result = "";
        Node temp = first;
        while(temp != null) {
            result += temp.data;
            result += " ";
            temp = temp.next;
        }
        return result;
    }
    
    /**
     * Prints the contents of the list in a numbered order
     */
    public void printNumberedList() {
    	placeIterator();
    	int counter = 1;
    	while(iterator != null) {
    		System.out.println(counter + ": " + iterator.data);
    		advanceIterator();
    		counter++;
    	}
    	
    }
    
    /**
     * Prints a linked list to the console
     * in reverse by calling the private 
     * recursive helper method printReverse
     */
    public void printReversed() {
       printReversed(first);
    }
    
    /**
     * Recursively prints a linked list to the console
     * in reverse order from last to first (no loops)
     * Each element separated by a space
     * Should print a new line after all
     * elements have been displayed
     */ 
    private void printReversed(Node node) {
        if (node.next != null) {
        	printReversed(node.next);
        }
        	System.out.println(node.data);
    }
    
    /**
     * Determines whether a List is sorted
     * by calling its recursive helper method
     * isSorted
     * Note: An empty List can be
     * considered to be (trivially) sorted
     * @return whether this List is sorted
     */
    public boolean isSorted() {
        return isSorted(first);
    }
    
    /**
     * Determines whether a List is 
     * sorted in ascending order recursively
     * @return whether this List is sorted
     */
    private boolean isSorted(Node node) {
        if (node == null || node.next == null) {
        	return true;
        } else {
        	if (node.data.compareTo(node.next.data) > 0)
        		return false;
        	return isSorted(node.next);
        }
    }
    

}
