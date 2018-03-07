import org.junit.Test;

/**
 * Test from https://piazza.com/class/jd1e3y0mtbkfp?cid=131
 */
public class TestSimilarity
{
    public static String vectorString1 = "ABFHBFDFAB";
    public static String vectorString2 = "BEAAHHDCH";
    public static String similarityString1 = "aroseisaroseisarose";
    public static String similarityString2 = "aroseisaflowerwhichisarose";

    public static int vectorShingleLength = 1;
    public static int similarityShingleLength = 4;

    public static float vectorAnswer1 = (float) Math.sqrt(24);
    public static float vectorAnswer2 = (float) Math.sqrt(17);
    public static float similarityAnswer = (float) (22 / (Math.sqrt(38) * Math.sqrt(27)));

    /**
     * Returns true if the distance between a and b is within a tolerance.
     *
     * @param a
     * @param b
     * @param tolerance
     * @return
     */
    public static boolean fuzzyEquals(float a, float b, float tolerance)
    {
        return Math.abs(a - b) < tolerance;
    }

    @Test
    public void bruteForceVectorLength()
    {
        BruteForceSimilarity bfs = new BruteForceSimilarity(
                vectorString1, vectorString2, vectorShingleLength);
        assert (bfs.lengthOfS1() == vectorAnswer1);
        assert (bfs.lengthOfS2() == vectorAnswer2);
    }

    @Test
    public void bruteForceSimilarity()
    {
        BruteForceSimilarity bfs = new BruteForceSimilarity(
                similarityString1, similarityString2, similarityShingleLength);
        assert (bfs.similarity() == similarityAnswer);
    }

    @Test
    public void hashStringVectorLength()
    {
        HashStringSimilarity bfs = new HashStringSimilarity(
                vectorString1, vectorString2, vectorShingleLength);
        assert (bfs.lengthOfS1() == vectorAnswer1);
        assert (bfs.lengthOfS2() == vectorAnswer2);
    }

    @Test
    public void hashStringSimilarity()
    {
        HashStringSimilarity bfs = new HashStringSimilarity(
                similarityString1, similarityString2, similarityShingleLength);
        assert (bfs.similarity() == similarityAnswer);
    }

    @Test
    public void hashCodeVectorLength()
    {
        HashCodeSimilarity bfs = new HashCodeSimilarity(
                vectorString1, vectorString2, vectorShingleLength);
        assert (bfs.lengthOfS1() == vectorAnswer1);
        assert (bfs.lengthOfS2() == vectorAnswer2);
    }

    @Test
    public void hashCodeSimilarity()
    {
        HashCodeSimilarity bfs = new HashCodeSimilarity(
                similarityString1, similarityString2, similarityShingleLength);
        assert (fuzzyEquals(bfs.similarity(), similarityAnswer, 0.1f));
    }

    @Test
    public void hashCodeSimilaritySame()
    {
        String testString1 = "aaaaaaaaaaacaaaadse2wqre32rf2frgrdddddddddddddddddddddfsfsfsfsafdfsfsafijsoejfoidkfdsjflkjlkfs";
        String testString2 = "fdsffksfkeoofsfjadajflkasjaaaaaaaaaaaaaaaaaaaaaaadflkjdsafjalfjsalfjdsljfalsjf";
        BruteForceSimilarity bfs = new BruteForceSimilarity(testString1, testString2, 8);
        HashStringSimilarity hss = new HashStringSimilarity(testString1, testString2, 8);
        System.out.println(bfs.similarity());
        System.out.println(hss.similarity());
        assert (bfs.similarity() == hss.similarity());
    }
}