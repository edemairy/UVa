/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Base64Data;
import java.io.*;
import java.net.URISyntaxException;
import java.util.Random;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author edemairy
 */
public class MainTest {

    public MainTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testInput1() throws FileNotFoundException, IOException, URISyntaxException {
        File tempOutput = File.createTempFile("test", "txt");
        File expectedResult = new File(MainTest.class.getResource("/result.txt").toURI());
        System.setIn(MainTest.class.getResourceAsStream("/input.txt"));
        System.setOut(new PrintStream(tempOutput));
        Main.main(new String[0]);
        String result = getFileContent(tempOutput);
        assertEquals(getFileContent(expectedResult), result);
        System.err.println(result);
    }

    @Test
    public void randomTest() throws FileNotFoundException, IOException, URISyntaxException {
        File tempOutput = File.createTempFile("test", "txt");
        StringWriter inputWriter = new StringWriter();
        Random randomGenerator = new Random(0);
        for (int i = 0; i < 10000; ++i) {
            inputWriter.append(Integer.toString(randomGenerator.nextInt(100) - 50)).append('\n');
        }
        inputWriter.close();
        System.setIn(new ByteArrayInputStream(inputWriter.toString().getBytes()));
        System.setOut(new PrintStream(tempOutput));
        Main.main(new String[0]);
        String result = getFileContent(tempOutput);
        System.err.println(result);
    }

    private String getFileContent(final File f) throws FileNotFoundException, IOException {
        FileInputStream fin = new FileInputStream(f);
        BufferedReader myInput = new BufferedReader(new InputStreamReader(fin));
        StringBuilder sb = new StringBuilder();
        String thisLine;
        while ((thisLine = myInput.readLine()) != null) {
            sb.append(thisLine).append('\n');
        }
        return sb.toString();
    }
}
