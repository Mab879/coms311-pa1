// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add member fields and additional methods)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Matthew Burket
 * @author Joel May
 */

public class HashTable {
    // member fields and other member methods
    /** The Hash function to use */
    private HashFunction hashFunction;
    /** Size of the hashtable */
    private int size;
    /** This is the data structure  */
    private LinkedList<Tuple>[] table;
    /** Total number of elements including dups */
    private int totalElements;

    /**
     * Constructs a HashTable with a size of the prime larger and closest to size.
     *
     * @param size the basis for the table size; used to find a prime
     */
    public HashTable(int size) {
        this.size = Utils.getPrime(size);
        hashFunction = new HashFunction(size);
        table = new LinkedList[size];
        totalElements = 0;

    }

    /**
     * The maximum load of the hash table.
     *
     * @return the maximum load of the hash table
     */
    public int maxLoad() {
        throw new UnsupportedOperationException();
    }

    /**
     * The average load of the hash table.
     * Total number of elements (including dups) / size
     *
     * @return the average load of the hash table
     */
    public float averageLoad() {
        return (float) totalElements / (float) size;
    }


    /**
     * The current size of the hash table.
     *
     * @return the current size of the hash table
     */
    public int size() {
        return size;
    }

    /**
     * The number of *distinct* tuples stored in the hash table.
     *
     * @return the number of *distinct* tuples in the hash table
     */
    public int numElements() {
        throw new UnsupportedOperationException();
    }

    /**
     * The load factor
     *
     * @return numElements()/size()
     */
    public float loadFactor() {
        return (float) numElements() / (float) size();
    }

    /**
     * Adds the tuple to the hash table. May increase the size of the table as necessary.
     *
     * @param t the tuple to be added.
     */
    public void add(Tuple t) {
        totalElements++;
        if (loadFactor() >= 0.7) {
            rehash();
        }
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the elements whose key equals k.
     *
     * @param k the key to search for
     * @return the elements with the key
     */
    public ArrayList<Tuple> search(int k) {
        throw new UnsupportedOperationException();
    }

    /**
     * Finds the number of occurrences of the specified tuple.
     *
     * @param t the tuple to search for
     * @return the number of occurrences of the tuple
     */
    public int search(Tuple t) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes *one occurrence* of the tuple, if it exists.
     *
     * @param t the tuple to remove
     */
    public void remove(Tuple t) {
        throw new UnsupportedOperationException();
    }

    private void rehash() {
        throw new UnsupportedOperationException();
    }
}