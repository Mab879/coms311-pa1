import org.junit.Test;

import static org.junit.Assert.*;

public class RollingHashTest {

    @Test
    public void multipleHashes() {
        RollingHash rh = new RollingHash("test", 2);
        Tuple t;

        // "te"
        assertTrue("Before step 1 should have an available hash", rh.hasNext());
        t = rh.next();
        assertEquals("Hash window on step 1", "te", t.getValue());
        assertEquals("Hash code on step 1", "te".hashCode(), t.getKey());

        // "es"
        assertTrue("Before step 2 should have an available hash", rh.hasNext());
        t = rh.next();
        assertEquals("Hash window on step 2", "es", t.getValue());
        assertEquals("Hash code on step 2", "es".hashCode(), t.getKey());

        // "st"
        assertTrue("Before step 3 should have an available hash", rh.hasNext());
        t = rh.next();
        assertEquals("Hash window on step 3", "st", t.getValue());
        assertEquals("Hash code on step 3", "st".hashCode(), t.getKey());

        // null (past the end of the string
        assertFalse("At the end should not have an available hash", rh.hasNext());
        t = rh.next();
        assertNull("Hash window past the end of the string", t);
    }

    @Test
    public void shortString() {
        RollingHash rh = new RollingHash("test", 5);
        assertFalse("A window too big should result in no hashes available", rh.hasNext());
        assertNull("A window too big should to give a hash", rh.next());
    }

    @Test
    public void emptyString() {
        RollingHash rh = new RollingHash("", 2);
        assertFalse("An empty rollinghash should have no available hashes", rh.hasNext());
        assertNull("An empty rollinghash should return no hash", rh.next());
    }

    @Test
    public void nullString() {
        RollingHash rh = new RollingHash(null, 2);
        assertFalse("A null seeded rolling hash should have no available hashes", rh.hasNext());
        assertNull("A null seeded rolling sash should return no hash", rh.next());
    }

    @Test(expected = IllegalArgumentException.class)
    public void outOfBoundsWindow() {
        // Should throw IllegalArgumentException
        RollingHash rh = new RollingHash("test", 0);
    }
}