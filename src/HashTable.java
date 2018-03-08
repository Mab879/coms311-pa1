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
    /**
     * The Hash function to use
     */
    private HashFunction hashFunction;
    /**
     * Size of the hashtable
     */
    private int size;
    /**
     * This is the data structure
     */
    private LinkedList<Tuple>[] table;
    /**
     * Total number of elements
     */
    private int totalElements;
    /**
     * Number of lists that have been initialized
     **/
    private int numInitLists;

    /**
     * Constructs a HashTable with a size of the prime larger and closest to size.
     *
     * @param size the basis for the table size; used to find a prime
     */
    public HashTable(int size) {
        this.size = Utils.getPrime(size);
        hashFunction = new HashFunction(size);
        table = new LinkedList[this.size];
        totalElements = 0;
        numInitLists = 0;

    }

    /**
     * The maximum load of the hash table.
     *
     * @return the maximum load of the hash table
     */
    public int maxLoad() {
        int max = 0;
        for (LinkedList l : table) {
            if (l == null) {
                continue;
            }
            if (l.size() > max) {
                max = l.size();
            }
        }
        return max;
    }

    /**
     * The average load of the hash table.
     * Total number of elements (including dups) / size
     *
     * @return the average load of the hash table
     */
    public float averageLoad() {
        if (numInitLists == 0) {
            return 0;
        }
        return (float) totalElements / (float) numInitLists;
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
        ArrayList<Tuple> nonDups = new ArrayList<>();
        for (LinkedList<Tuple> l : table) {
            if (l != null) {
                for (Tuple t : l) {
                    if (!nonDups.contains(t)) {
                        nonDups.add(t);
                    }
                }
            }
        }
        return nonDups.size();
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
        int hash = hashFunction.hash(t.getKey());
        if (table[hash] == null) {
            table[hash] = new LinkedList<>();
            numInitLists++;
        }
        table[hash].add(t);
        if (loadFactor() >= 0.7) {
            rehash();
        }
    }

    /**
     * Gets the elements whose key equals k.
     *
     * @param k the key to search for
     * @return the elements with the key
     */
    public ArrayList<Tuple> search(int k) {
        int hash = hashFunction.hash(k);
        ArrayList<Tuple> result = new ArrayList<>();
        if (table[hash] == null) {
            return result;
        }
        for (Tuple t : table[hash]) {
            if (t.getKey() == k) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * Finds the number of occurrences of the specified tuple.
     *
     * @param t the tuple to search for
     * @return the number of occurrences of the tuple
     */
    public int search(Tuple t) {
        int hash = hashFunction.hash(t.getKey());
        if (table[hash] == null) {
            return 0;
        }
        int count = 0;
        for (Tuple loopTuple : table[hash]) {
            if (loopTuple.equals(t)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Removes *one occurrence* of the tuple, if it exists.
     *
     * @param t the tuple to remove
     */
    public void remove(Tuple t) {
        int hash = hashFunction.hash(t.getKey());
        if (table[hash] == null) {
            return;
        }
        for (Tuple loopT : table[hash]) {
            if (loopT.equals(t)) {
                table[hash].removeFirstOccurrence(loopT);
                totalElements--;
            }
        }
        if (table[hash].size() <= 0) {
            table[hash] = null;
            numInitLists--;
        }
    }

    /**
     * Resize the hashtable table
     */
    private void rehash() {
        int newSize = Utils.getPrime(size() * 2);
        HashFunction newHash = new HashFunction(newSize);
        LinkedList<Tuple>[] newTable = new LinkedList[newSize];
        int numInit = 0;
        for (LinkedList<Tuple> l : table) {
            if (l != null) {
                for (Tuple t : l) {
                    int hash = newHash.hash(t.getKey());
                    if (newTable[hash] == null) {
                        numInit++;
                        newTable[hash] = new LinkedList<>();
                    }
                    newTable[hash].add(t);
                }
            }
        }
        this.size = newSize;
        this.table = newTable;
        this.numInitLists = numInit;
        this.hashFunction = newHash;
    }
}