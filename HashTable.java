// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add member fields and additional methods)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

import java.util.ArrayList;

/**
 * @author Matthew Burket
 * @author Joel May
 */

public class HashTable {
    // member fields and other member methods
    private HashFunction hashFunction;
    private int size;

    public HashTable(int size) {
        this.size = Utils.getPrime(size);
        hashFunction = new HashFunction(size);
    }

    public int maxLoad() {
        throw new UnsupportedOperationException();
    }

    public float averageLoad() {
        throw new UnsupportedOperationException();
    }

    public int size() {
        return size;
    }

    public int numElements() {
        throw new UnsupportedOperationException();
    }

    public float loadFactor() {
        return (float) numElements() / (float) size();
    }

    public void add(Tuple t) {
        throw new UnsupportedOperationException();
    }

    public ArrayList<Tuple> search(int k) {
        throw new UnsupportedOperationException();
    }

    public int search(Tuple t) {
        throw new UnsupportedOperationException();
    }

    public void remove(Tuple t) {
        throw new UnsupportedOperationException();
    }
}