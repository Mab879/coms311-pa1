import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class IterableHashTableTest {

    @Test
    public void distinctElements() {
        IterableHashTable ht = new IterableHashTable(10);
        Tuple[] t = new Tuple[10];
        for (int i = 0; i < 5; i++) {
            t[i * 2] = new Tuple(10 - i, "test " + i);
            t[i * 2 + 1] = new Tuple(i + 1, "test " + (i + 1));
        }
        for (Tuple tuple : t) {
            ht.add(tuple);
        }

        // Test against original objects
        Iterator<Tuple> it = ht.iterator();
        for (int i = 0; i < 5; i++) {
            assertEquals("The iteration should match the array: " + i, t[i * 2], it.next());
            assertEquals("The iteration should match the array: " + i, t[i * 2 + 1], it.next());
        }
    }

    @Test
    public void equalElementsOfSeparateInstances() {
        IterableHashTable ht = new IterableHashTable(10);
        Tuple[] t = new Tuple[10];
        for (int i = 0; i < 5; i++) {
            t[i * 2] = new Tuple(i, "test " + i);
            t[i * 2 + 1] = new Tuple(i, "test " + i);
        }
        for (Tuple tuple : t) {
            ht.add(tuple);
        }

        // Test against original objects
        Iterator<Tuple> it = ht.iterator();
        for (int i = 0; i < 5; i++) {
            assertEquals("The iteration should match the array: " + i, t[i * 2], it.next());
            assertEquals("The iteration should match the array: " + i, t[i * 2 + 1], it.next());
        }
    }

    @Test
    public void sameInstance() {
        IterableHashTable ht = new IterableHashTable(10);
        Tuple t = new Tuple(1, "test");
        for (int i = 0; i < 10; i++) {
            ht.add(t);
        }

        // Test against the original object
        Iterator<Tuple> it = ht.iterator();
        for (int i = 0; i < 10; i++) {
            assertEquals("The iteration should match the array: " + i, t, it.next());
        }
    }
}