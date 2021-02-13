/**
 * HashTable.java
 * CIS 22C
 */
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class HashTable<T extends Comparable<T>> {
    
    private int numElements;
    private ArrayList<List<T> > Table;

    /**
     * Constructor for the HashTable.java
     * class. Initializes the Table to
     * be sized according to value passed
     * in as a parameter
     * Inserts size empty Lists into the
     * table. Sets numElements to 0
     * @param size the table size
     */
    public HashTable(int size) {
    	numElements = 0;
        Table = new ArrayList<>();
        for (int i = 0; i < size; i++) {
        	Table.add(new List<>());
        }       
    }
       
    /**Accessors*/
    
    /**
     * returns the hash value in the Table
     * for a given Object 
     * @param t the Object
     * @return the index in the Table
     */
    private int hash(T t) {
        int code = t.hashCode();
        return code % Table.size();
    }
    
    /**
     * counts the number of elements at this index
     * @param index the index in the Table
     * @precondition 0 <=  index < Table.length
     * @return the count of elements at this index
     * @throws IndexOutOfBoundsException
     */
    public int countBucket(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= Table.size()) {
        	throw new IndexOutOfBoundsException("countBucket(): " 
        			+ "index is outside bounds of the table");
        }
        return Table.get(index).getLength();
    }
    
    /**
     * returns total number of elements in the Table
     * @return total number of elements
     */
    public int getNumElements() {
        return numElements;
    }
    
    /**
     * searches for a specified element in the Table
     * @param t the element to search for
     * @return the index in the Table (0 to Table.length - 1)
     * or -1 if t is not in the Table
     */
    public int search(T t) {
    	int bucket = hash(t);
    	if (Table.get(bucket).linearSearch(t) != -1) {
    		return bucket;
    	}
    	return -1;
    } 
    
     
    /**Manipulation Procedures*/
    
    /**
     * inserts a new element in the Table
     * calls the hash method to determine placement
     * @param t the element to insert
     */
    public void insert(T t) {    
        int bucket = hash(t);
        Table.get(bucket).addLast(t);
        numElements++;
    }  
     
     
    /**
     * removes the element t from the Table
     * calls the hash method on the key to
     * determine correct placement
     * has no effect if t is not in
     * the Table
     * @param t the key to remove
     * @precondition t must be in the table
     * @throws NoSuchElementException when
     * the element is not in the table
     */
    public void remove(T t) throws NoSuchElementException{
        int bucket = hash(t);
        int index = Table.get(bucket).linearSearch(t);
        if (index == -1) {
        	throw new NoSuchElementException("remove(): "  
        			+ "element is not in the table");
        } else {
        	Table.get(bucket).advanceToIndex(index);
        	Table.get(bucket).removeIterator();
        	numElements--;
        }
    }

    /**Additional Methods*/

    /**
     * Prints all the keys at a specified
     * bucket in the Table. Each element displayed
     * on its own line, with a blank line 
     * separating each element
     * Above the elements, prints the message
     * "Printing bucket #<bucket>:"
     * Note that there is no <> in the output
     * @param bucket the index in the Table
     * @throws IndexOutOfBoundsException
     */
    public void printBucket(int bucket) throws IndexOutOfBoundsException{
        if (bucket < 0 || bucket >= Table.size()) {
        	throw new IndexOutOfBoundsException("printBucket(): "
        		+ "bucket is outside bounds of the table");
        }
        System.out.println("\nPrinting bucket # " + bucket + ":\n");
        List<T> bucketList = Table.get(bucket);
        bucketList.placeIterator();
        while (!bucketList.offEnd()) {
        	System.out.print(bucketList.getIterator() + "\n\n");
        	bucketList.advanceIterator();        	
        }
    }
    
    /**
     * Prints all elements in the hash table
     * with a blank line separating each
     */
    public void printAll() {
    	for (int i = 0; i < Table.size(); i++) {
    		List<T> data = Table.get(i);
    		data.placeIterator();
    		while (!data.offEnd()) {
    			System.out.print(data.getIterator() + "\n");
    			data.advanceIterator();
    		}
    		
    	}
    }
        
    /**
     * Prints the first element at each bucket
     * along with a count of the total elements
     * with the message "+ <count> -1 more 
     * at this bucket." Each bucket separated
     * with two blank lines. When the bucket is 
     * empty, prints the message "This bucket
     * is empty." followed by two blank lines
     */
    public void printTable(){
    	for (int i = 0; i < Table.size(); i++) {
    		List<T> data = Table.get(i);
    		try {
    			System.out.println("Bucket: " + i);
    			System.out.println(data.getFirst());
    			System.out.println("+ " + (data.getLength() - 1) + " more at this bucket\n\n");
    		} catch (NoSuchElementException e) {
    			System.out.println("This bucket is empty.\n\n");
    		}
    	}
     }  
}