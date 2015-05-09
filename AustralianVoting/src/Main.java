import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Formatter;
import java.util.Locale;
import java.util.TreeSet;

/**
 *
 * @author edemairy
 */
public class Main {

    private int nbTC;
    private StringBuilder result = new StringBuilder();

    private static class EndException extends RuntimeException {
    }

    public void run() throws IOException {
        //        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        nbTC = readInt(reader);
//         nbTC = Integer.MAX_VALUE;
//        scanner.nextLine();
        try {
            for (int tc = 1; tc <= nbTC; ++tc) {
                result.append(oneTestCase(reader));
                result.append('\n');
            }
        } catch (EndException e) {
        }
        System.out.print(result);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.run();
    }

    private class Candidate implements Comparable<Candidate> {

        public String name;
        public int votes;

        @Override
        public int compareTo(Candidate o) {
            if (votes != o.votes) {
                return votes - o.votes;
            } else {
                return name.compareTo(o.name);
            }
        }

    }

    private StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }
        int nbCandidates = readInt(reader);
        String[] candidates = new String[nbCandidates];
        for (int i = 0; i < nbCandidates; i++) {
            candidates[i] = reader.readLine();
        }
        int[][] votes = new int[nbCandidates][nbCandidates];
        for (int i = 0; i < nbCandidates; i++) {
            String line = reader.readLine();
            String[] parts = line.split(" ");
            assert (parts.length == nbCandidates);
            for (int j = 0; j < nbCandidates; j++) {
                votes[i][j] = Integer.parseInt(parts[j]);
            }
        }

        BitSet active = new BitSet(nbCandidates);
        int[] nbVotes = new int[nbCandidates];
        for (int turn = 0; turn < nbCandidates; turn++) {
            TreeSet<Candidate> ballot = new TreeSet<Candidate>();
            Arrays.fill(nbVotes, 0);
            for (int vote = 0; vote < nbCandidates; vote++) {
                for (int pref = 0; pref < nbCandidates; pref++) {
                    nbVotes[votes[vote][turn]]++;
                }
            }

            for (int candidate = 0; candidate < nbCandidates; candidate++) {
                ballot.add(new Candidate(candidate, nbVotes[candidate]));
            }
        }
        output.append(formatter.out());
        return output;
    }

    private int readInt(BufferedReader reader) throws IOException {
        int r = 0;
        boolean positive = true;
        char currentChar = (char) reader.read();

        while ((currentChar == ' ') || (currentChar == '\n')) {
            currentChar = (char) reader.read();
        }
        if (currentChar == (char) -1) {
            throw new IOException("end of stream");
        }
        if (currentChar == '-') {
            positive = false;
            currentChar = (char) reader.read();
        }
        while ((currentChar >= '0') && (currentChar <= '9')) {
            r = r * 10 + currentChar - '0';
            currentChar = (char) reader.read();
        }
        if (positive) {
            return r;
        } else {
            return -r;
        }
    }

    private long readLong(BufferedReader reader) throws IOException {
        long r = 0;
        boolean positive = true;
        char currentChar = (char) reader.read();

        while ((currentChar == ' ') || (currentChar == '\n')) {
            currentChar = (char) reader.read();
        }
        if (currentChar == (char) -1) {
            throw new IOException("end of stream");
        }
        if (currentChar == '-') {
            positive = false;
            currentChar = (char) reader.read();
        }
        while ((currentChar >= '0') && (currentChar <= '9')) {
            r = r * 10 + currentChar - '0';
            currentChar = (char) reader.read();
        }
        if (positive) {
            return r;
        } else {
            return -r;
        }
    }

    private char readChar(BufferedReader reader) throws IOException {
        return (char) reader.read();
    }
}
