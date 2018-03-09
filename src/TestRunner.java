import java.io.*;

public class TestRunner {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        String shak1 = getProcessedFile("shak1.txt");
        String shak2 = getProcessedFile("shak2.txt");
        int sLength = 8;

        AbstractSimilarity s1 = new BruteForceSimilarity(shak1, shak2, sLength);
        AbstractSimilarity s2 = new HashStringSimilarity(shak1, shak2, sLength);
        AbstractSimilarity s3 = new HashCodeSimilarity(shak1, shak2, sLength);
        Thread t1 = new Thread(new Runner(s1));
        Thread t2 = new Thread(new Runner(s2));
        Thread t3 = new Thread(new Runner(s3));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }

    private static String getProcessedFile(String path) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        File f = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(f));
        reader.lines().forEachOrdered(sb::append);
        return sb.toString().replaceAll("[\\s.,;]", "").toLowerCase();
    }

    private static void doTest(AbstractSimilarity subject) {
        StringBuilder sb = new StringBuilder();
        sb.append("Testing class \t").append(subject.getClass().toString()).append("\n");
        long startTime = System.nanoTime();
        sb.append("Similarity: \t").append(subject.similarity()).append("\n");
        sb.append(String.format("Running time: \t%.4f\n\n", ((System.nanoTime() - startTime)) / 1000000000.0));
        System.out.println(sb.toString());
    }

    private static class Runner implements Runnable {
        private AbstractSimilarity similarity;

        public Runner(AbstractSimilarity similarity) {
            this.similarity = similarity;
        }

        @Override
        public void run() {
            doTest(similarity);
        }
    }
}
