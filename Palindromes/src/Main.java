
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Formatter;
import java.util.Locale;

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
//        nbTC = readInt(reader);
         nbTC = Integer.MAX_VALUE;
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

    private boolean isPalyndrome(String s) {
	    int n = s.length();
	    for (int i=0; i<=n/2; i++){
		if (s.charAt(i) != s.charAt(n-i-1)) {
			return false;
		}
	    }
	    return true;
    }
    private boolean isMirror(String s) {
	    int n = s.length();
	    StringBuilder mirror = new StringBuilder(n);
	    for (int i=0; i<n; i++) {
		char mchar = ' ';
		switch (s.charAt(n-i-1)) {
			case 'A': mchar = 'A'; break;
			case 'E': mchar = '3'; break;
			case 'H': mchar = 'H'; break;
			case 'I': mchar = 'I'; break;
			case 'J': mchar = 'L'; break;
			case 'L': mchar = 'J'; break;
			case 'M': mchar = 'M'; break;
			case 'O': mchar = 'O'; break;
			case 'S': mchar = '2'; break;
			case 'T': mchar = 'T'; break;
			case 'U': mchar = 'U'; break;
			case 'V': mchar = 'V'; break;
			case 'W': mchar = 'W'; break;
			case 'X': mchar = 'X'; break;
			case 'Y': mchar = 'Y'; break;
			case 'Z': mchar = '5'; break;
			case '1': mchar = '1'; break;
			case '2': mchar = 'S'; break;
			case '3': mchar = 'E'; break;
			case '5': mchar = 'Z'; break;
			case '8': mchar = '8'; break;
		}
		mirror.append( mchar );
	    }
	    return s.equals(mirror.toString());
    }
    
    private StringBuilder oneTestCase(BufferedReader reader) throws IOException {
        Formatter formatter = new Formatter(Locale.ENGLISH);
        StringBuilder output = new StringBuilder();
	String line = reader.readLine();
	if (line == null) {
		throw new EndException();
	}
	boolean ism = isMirror(line);
	boolean isp = isPalyndrome(line);
	if (!ism && !isp) {
		formatter.format("%s -- is not a palindrome.",line);
	} else if (ism && !isp) {
		formatter.format("%s -- is a mirrored string.",line);
	} else if (isp && !ism) {
		formatter.format("%s -- is a regular palindrome.",line);
	} else {
		formatter.format("%s -- is a mirrored palindrome.",line);
	}
	formatter.format("%n");
//        for (int i = 0; i < 5; ++i) {
//            formatter.format("%3d", n[i]);
//        }

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
