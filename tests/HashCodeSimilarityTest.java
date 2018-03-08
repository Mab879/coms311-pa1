import org.junit.Test;

import static org.junit.Assert.*;

public class HashCodeSimilarityTest {
    private static final String collisionStr = "cdE";

    @Test
    public void vectorLength() {
        // s1 has no repeated values, but has a hash collision
        // s2 has one pair of repeated values
        HashCodeSimilarity c = new HashCodeSimilarity("tetest", collisionStr, 2);
        assertEquals("Vector length of tetest", 2.6, c.lengthOfS1(), 0.05);
        assertEquals("Vector length of cdE (hash collision)", 2.0, c.lengthOfS2(), 0.05);
    }

    @Test
    public void numbers() {
        HashCodeSimilarity c = new HashCodeSimilarity("1268264612", "251188438", 1);
        assertEquals("Vector length example with numbers", 4.9, c.lengthOfS1(), 0.05);
        assertEquals("Vector length example with numbers", 4.1, c.lengthOfS2(), 0.05);
        assertEquals("Similarity in example with numbers", 0.544, c.similarity(), 0.005);
    }

    @Test
    public void similarity() {
        HashCodeSimilarity c = new HashCodeSimilarity("aroseisaroseisarose", "aroseisaflowerwhichisarose", 4);
        assertEquals("VectorLength of s1", 6.2, c.lengthOfS1(), 0.05);
        assertEquals("VectorLength of s2", 5.2, c.lengthOfS2(), 0.05);
        assertEquals("Example about roses", 0.686, c.similarity(), 0.005);
    }
}