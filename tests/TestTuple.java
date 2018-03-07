import org.junit.Test;
import static org.junit.Assert.*;

public class TestTuple {
    @Test
    public void TestJustKey() {
        Tuple t = new Tuple(12, null);
        assertEquals(t.getKey(), 12);
        assertEquals(t.getValue(), null);
    }

    @Test
    public void TestKeyValue() {
        Tuple t = new Tuple(42, "The Answer to Life, everything, and the universe");
        assertEquals(t.getKey(), 42);
        assertEquals(t.getValue(), "The Answer to Life, everything, and the universe");
    }

    @Test
    public void TestEqualsNull() {
        Tuple t = new Tuple(42, "The Answer to Life, everything, and the universe");
        assertFalse(t.equals(null));
    }

    @Test
    public void TestEqualEqual() {
        Tuple t = new Tuple(42, "The Answer to Life, everything, and the universe");
        Tuple t2 = new Tuple(42, "The Answer to Life, everything, and the universe");
        assertTrue(t.equals(t2));
    }

    @Test
    public void TestEqualNotEqualKey() {
        Tuple t = new Tuple(42, "The Answer to Life, everything, and the universe");
        Tuple t2 = new Tuple(43, "The Answer to Life, everything, and the universe");
        assertFalse(t.equals(t2));
    }

    @Test
    public void TestEqualNotEqualValue() {
        Tuple t = new Tuple(42, "Not the answer");
        Tuple t2 = new Tuple(42, "The Answer to Life, everything, and the universe");
        assertFalse(t.equals(t2));
    }
}
