
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Stack;
/**
 *
 * @author edemairy
 */
public class Main {

    private static int nbTC;
    private static StringBuilder result = new StringBuilder();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        nbTC = readInt(reader);
//        scanner.nextLine();
        for (int tc = 1; tc <= nbTC; ++tc) {
            result.append(oneTestCase(reader));
            result.append('\n');
            if (tc != nbTC) {
                result.append('\n');
            }
        }
        System.out.print(result);
    }

    private static StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();

        int nbVars = readInt(reader);
        formatter.format("program sort(input,output);\nvar\n");
        for (char c = 'a'; c < 'a' + nbVars; c++) {
            formatter.format("%c", c);
            if (c != 'a' + nbVars - 1) {
                formatter.format(",");
            }
        }
        formatter.format(" : integer;\nbegin\n");
        formatter.format("  readln(");
        for (char c = 'a'; c < 'a' + nbVars; c++) {
            formatter.format("%c", c);
            if (c != 'a' + nbVars - 1) {
                formatter.format(",");
            }
        }
        formatter.format(");\n");

        Stack<LinkedList<Character>> orders = new Stack<LinkedList<Character>>();
        LinkedList<Character> order = new LinkedList<Character>();
        order.add('a');
        orders.push(order);
        mainloop:
        while (!orders.isEmpty()) {
            ifthenelse(orders, nbVars, formatter);
        }

        formatter.format("end.");
        output.append(formatter.out());
        return output;
    }

//    private static void printWriteln(LinkedList<Character> order, Formatter formatter, int nbVars) {
//        formatter.format("writeln(");
//        for (int i = 0; i < order.size(); ++i) {
//            formatter.format("%c", order.get(i));
//            if (i != order.size() - 1) {
//                formatter.format(",");
//            }
//        }
//        formatter.format(")");
//    }

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

    private static void ifthenelse(Stack<LinkedList<Character>> orders, int nbVars, Formatter formatter) {
        LinkedList<Character> order = orders.pop();
        int nbCars = order.size();
        if (nbCars != nbVars) {
            char newc = (char) ('a' + order.size());

            for (int i = nbCars - 1; i >= 0; --i) {

                LinkedList<Character> o2 = new LinkedList<Character>(order);

                formatter.format("if %c < %c then\n", order.get(i), newc);
                o2.add(i + 1, newc);
                orders.push(o2);
                ifthenelse(orders, nbVars, formatter);
                formatter.format("else\n");
            }
            LinkedList<Character> o2 = new LinkedList<Character>(order);

            o2.add(0, newc);
            orders.push(o2);
            ifthenelse(orders, nbVars, formatter);
            
        } else {
            formatter.format("writeln(");
            for (int i = 0; i < order.size(); ++i) {
                formatter.format("%c", order.get(i));
                if (i != order.size()-1) {
                    formatter.format(",");
                }
            }
            formatter.format(")\n");
        }
    }
}
