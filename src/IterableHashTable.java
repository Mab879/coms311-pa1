import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Matthew Burket
 * @author Joel May
 */
public class IterableHashTable extends HashTable implements Iterable<Tuple> {
    private ArrayList<Tuple> arr;

    /**
     * Constructs a HashTable with a size of the prime larger and closest to size.
     *
     * @param size the basis for the table size; used to find a prime
     */
    public IterableHashTable(int size) {
        super(size);
        arr = new ArrayList<>(size);
    }

    @Override
    public void add(Tuple t) {
        super.add(t);
        arr.add(t);
    }

    @Override
    public Iterator<Tuple> iterator() {
        return arr.iterator();
    }
}
