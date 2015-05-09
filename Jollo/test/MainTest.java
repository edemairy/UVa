

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

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
        Path dirPath = FileSystems.getDefault().getPath("./test");
        DirectoryStream<Path> fs = Files.newDirectoryStream(dirPath.toAbsolutePath(), "input*.txt");
        Pattern testPattern = Pattern.compile("(.*)input(.*).txt");
        for (Path path : fs) {
            Matcher matcher = testPattern.matcher(path.toString());
            if (matcher.matches()) {
                System.err.printf("----- Testing with file %s -----\n", path.toString());
                
                StringBuilder testFileName = new StringBuilder();
                testFileName.append(matcher.group(1)).append("result").append(matcher.group(2)).append(".txt");
                System.err.printf("----- Searching for result file %s -----\n", testFileName);
                
                
                File expectedResult = new File(testFileName.toString());
                System.setIn(new FileInputStream(path.toFile()));
                System.setOut(new PrintStream(tempOutput));
                Main.main(new String[0]);
                String result = getFileContent(tempOutput);
                assertEquals(getFileContent(expectedResult), result);
                System.err.println(result);
                System.err.println(tempOutput.delete());
                tempOutput = File.createTempFile("test", "txt");
            }
        }
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
