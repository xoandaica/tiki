
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.file.Files;
import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import tiki.SimpleExcelApplication;
import tiki.input.FileInputProvider;
import tiki.input.IInputProvider;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author soithattha
 * @date Mar 20, 2019
 * @version
 * @description
 */
public class TestSimpleExcelApplication extends TestCase {

    public void testSimpleLikeExample() throws Exception {

        int NUMBER_TEST_CASE = 3;
        int index = 0;
        while (index++ < NUMBER_TEST_CASE) {
            String inputFile = String.format("test/input%s.txt", index);
            String outputTemplate = String.format("test/output_tem%s.txt", index);
            String outputFile = String.format("test/output%s.txt", index);
            IInputProvider fileInput = new FileInputProvider(inputFile);
            SimpleExcelApplication app = new SimpleExcelApplication();
            app.setInputProvider(fileInput);
            app.readInput();
            app.process();
            app.printOutPutToFile(outputFile);
            assertEquals(true, FileUtils.contentEquals(new File(outputTemplate), new File(outputFile)));
        }

    }

}
