
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edemairy
 */
public class Main {

    private final static Logger logger = Logger.getLogger(Main.class.getName());
    private static StringBuilder result = new StringBuilder();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        logger.setLevel(Level.OFF);
//        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

//        scanner.nextLine();
        int size;
        int cptTC = 1;
        try {
            while (true) {
                size = readInt(reader);

                result.append(oneTestCase(reader, size, cptTC));
                result.append('\n');
                cptTC++;
            }
        } catch (Exception e) {
        }
        System.out.print(result);
    }

    private static StringBuilder oneTestCase(BufferedReader reader, int size, int cptTC) throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();
        formatter.format(("Pattern %d was "), cptTC);
        ArrayList<String> input = new ArrayList<String>();
        ArrayList<String> transformed = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            String current = reader.readLine();
            input.add(current.substring(0, size));
            transformed.add(current.substring(size + 1, 2 * size + 1));
        }
        
        String result = "improperly transformed.";
        if (isTransformed(input, transformed, new Idem(), size)) {
            result = "preserved.";
        } else if (isTransformed(input, transformed, new Rotate90(), size)) {
            result = "rotated 90 degrees.";
        } else if (isTransformed(input, transformed, new Rotate180(), size)) {
            result = "rotated 180 degrees.";
        } else if (isTransformed(input, transformed, new Rotate270(), size)) {
            result = "rotated 270 degrees.";
        } else if (isTransformed(input, transformed, new Vertical(), size)) {
            result = "reflected vertically.";
        } else if (isTransformed(input, transformed, new Composition(new Vertical(), new Rotate90()), size)) {
            result = "reflected vertically and rotated 90 degrees.";
        } else if (isTransformed(input, transformed, new Composition(new Vertical(), new Rotate180()), size)) {
            result = "reflected vertically and rotated 180 degrees.";
        } else if (isTransformed(input, transformed, new Composition(new Vertical(), new Rotate270()), size)) {
            result = "reflected vertically and rotated 270 degrees.";
        } 
        formatter.format("%s", result);
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

    private static interface Transformation {
        public int getx(int x, int y, int sz);
        public int gety(int x, int y, int sz);        
    }

    private static class Composition implements Transformation {
        private final Transformation t1;
        private final Transformation t2;
        public Composition(Transformation t1, Transformation t2) {
            this.t1 = t1;
            this.t2 = t2;
        }

        @Override
        public int getx(int x, int y, int sz) {
            return t2.getx(t1.getx(x, y, sz), t1.gety(x, y, sz), sz);
        }

        @Override
        public int gety(int x, int y, int sz) {
            return t2.gety(t1.getx(x, y, sz), t1.gety(x, y, sz), sz);
        }
    }
    
    private static class Idem implements Transformation{
        @Override
        public int getx(int x, int y, int sz) {
            return x;
        }
        @Override
        public int gety(int x, int y, int sz) {
            return y;
        }
    }
    
    private static class Rotate90 implements Transformation{
        @Override
        public int getx(int x, int y, int sz) {
            return y;
        }
        @Override
        public int gety(int x, int y, int sz) {
            return sz - x - 1;
        }
    }
    
    private static class Rotate180 implements Transformation{
        @Override
        public int getx(int x, int y, int sz) {
            return sz - x - 1;
        }
        @Override
        public int gety(int x, int y, int sz) {
            return sz - y - 1;
        }
    }
    
    private static class Rotate270 implements Transformation{
        @Override
        public int getx(int x, int y, int sz) {
            return sz - y - 1;
        }

        @Override
        public int gety(int x, int y, int sz) {
            return x;
        }
        
    }
    
    private static class Vertical implements Transformation{
        @Override
        public int getx(int x, int y, int sz) {
            return sz - x - 1;
        }
        @Override
        public int gety(int x, int y, int sz) {
            return y;
        }
    }
    
    
    private static boolean isTransformed(ArrayList<String> input, ArrayList<String> transformed, Transformation transformation, int sz) {
        boolean result = true;
        mainloop: 
        for (int x = 0; x < sz; x++) {
            for (int y = 0; y < sz; y++) {
                int tx = transformation.getx(x, y, sz);
                int ty = transformation.gety(x, y, sz);                
                if (input.get(x).charAt(y)!=transformed.get(tx).charAt(ty)) {
                    return false;
                }
            }
        }
        return result;
    }
}
