package tests;


import modules.Model;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;


public class ModelTest {
    String sourcePath;
    Model model;
    File file;
    @Before
    public void setup() throws FileNotFoundException {

        sourcePath ="C:\\Users\\dodyk\\Desktop\\Assembler\\assemblyCode.txt";
         file = new File(sourcePath);
        model = new Model(sourcePath,"C:\\Users\\dodyk\\Desktop\\Assembler\\BinaryCode.txt");
    }
    @Test
    public void testInspectLabel() throws FileNotFoundException {
    model.inspectLabels(file);
    assertEquals(Model.getLabelAddr("loop"),16);
    file.exists();
    }
    @Test
    public void testMainModel() throws FileNotFoundException {
        model.ModelMain(sourcePath,"C:\\Users\\dodyk\\Desktop\\Assembler\\BinaryCode.txt");
        file = new File("C:\\Users\\dodyk\\Desktop\\Assembler\\BinaryCode.txt");
        Scanner scanner = new Scanner(file);

        if(scanner.hasNext())
        {
            assertEquals(scanner.nextLine(),"1000_1101_0000_1011_0000_0000_0000_0000");
        }
        if(scanner.hasNext())
        {
            assertEquals(scanner.nextLine(),"1000_1101_0000_1100_0000_0000_0000_0100");
        }
        if(scanner.hasNext())
        {
            assertEquals(scanner.nextLine(),"0000_0001_0110_1100_0101_0000_0010_0000");
        }
        if(scanner.hasNext())
        {
            assertEquals(scanner.nextLine(),"1010_1101_0000_1010_0000_0000_0000_1000");
        }
        if(scanner.hasNext())
        {
            assertEquals(scanner.nextLine(),"0010_0001_0000_1000_0000_0000_0000_0100");
        }
        if(scanner.hasNext())
        {
            assertEquals(scanner.nextLine(),"0010_0001_0010_1001_1111_1111_1111_1111");
        }
        if(scanner.hasNext())
        {
            assertEquals(scanner.nextLine(),"0001_0101_0010_0000_1111_1111_1111_1001");
        }
        file.exists();
    }

}
