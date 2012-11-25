
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author edemairy
 */
public class Main {

    private static StringBuilder result = new StringBuilder();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        result.append(oneTestCase(reader));
        System.out.print(result);
    }

    private static class ArrayRef {

        public ArrayRef() {
            mins = new ArrayList<Integer>();
            maxs = new ArrayList<Integer>();
        }

        public void update() {
            c0 = baseMem;
            cd = new ArrayList<Integer>(nbDims);
            for (int i = 0; i < nbDims; i++) {
                cd.add(0);
            }
            cd.set(nbDims - 1, sz);
            c0 -= cd.get(nbDims - 1) * mins.get(nbDims - 1);
            for (int i = nbDims - 2; i >= 0; --i) {
                cd.set(i, cd.get(i + 1) * (maxs.get(i + 1) - mins.get(i + 1) + 1));
                c0 -= cd.get(i) * mins.get(i);
            }
        }
        public int c0;
        public int baseMem;
        public int nbDims;
        public int sz;
        ArrayList<Integer> mins;
        ArrayList<Integer> maxs;
        ArrayList<Integer> cd;
    }

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();
        int N = readInt(reader);
        int R = readInt(reader);

        Map<String, ArrayRef> refdict = new HashMap<String, ArrayRef>();
        for (int n = 0; n < N; ++n) {
            ArrayRef newRef = new ArrayRef();
            String line = reader.readLine();
            String[] parts = line.split(" +");
            refdict.put(parts[0], newRef);
            newRef.baseMem = Integer.parseInt(parts[1]);
            newRef.sz = Integer.parseInt(parts[2]);
            newRef.nbDims = Integer.parseInt(parts[3]);
           
            for (int d = 0; d < newRef.nbDims; ++d) {
                newRef.mins.add(d, Integer.parseInt(parts[3 + 1 + 2 * d]));
                newRef.maxs.add(d, Integer.parseInt(parts[3 + 1 + 2 * d + 1]));
            }
            newRef.update();
        }
        for (int r = 0; r < R; ++r) {
            String line = reader.readLine();
            String[] parts = line.split(" +");
            String name = parts[0];
            ArrayRef desc = refdict.get(name);
            ArrayList<Integer> in = new ArrayList<Integer>(desc.nbDims);
            for (int i = 0; i < desc.nbDims; i++) {
                in.add(0);
            }
            formatter.format("%s[", name);
            int address = desc.c0;

            for (int d = 0; d < desc.nbDims; ++d) {
                in.set(d, Integer.parseInt(parts[1 + d]));
                formatter.format("%d", in.get(d));
                if (d != (desc.nbDims - 1)) {
                    formatter.format(", ");
                }
                address += desc.cd.get(d) * in.get(d);
            }
            formatter.format("] = %d\n", address);

        }


//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }

        output.append(formatter.out());
        return output;
    }

    private static int readInt(BufferedReader reader) throws IOException {
        int result = 0;
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
            result = result * 10 + currentChar - '0';
            currentChar = (char) reader.read();
        }
        if (positive) {
            return result;
        } else {
            return -result;
        }
    }

    private static char readChar(BufferedReader reader) throws IOException {
        return (char) reader.read();
    }
}
