package tests;

import Instructions.IInstructions.AddrIInstruction;
import Instructions.IInstructions.IInstruction;
import Instructions.IInstructions.ImmedIInstruction;
import Instructions.IInstructions.TwoOpIInstruction;
import Instructions.Instruction;
import Instructions.JInstruction;
import Instructions.RInstructions.OneOpRInstruction;
import Instructions.RInstructions.RInstruction;
import Instructions.RInstructions.ShiftInstruction;
import exceptions.BinaryOverFlowException;
import modules.Statement;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class InstructionsTest {
    String targetPath;
    Instruction instruction;
    Statement statement;
    File file;
    @Before
    public void setup(){
        targetPath = "C:\\Users\\dodyk\\Desktop\\Assembler\\BinaryCode.txt";
    }
    @Test
    public void testAssembleRInstruction() throws FileNotFoundException, BinaryOverFlowException {
        instruction = new RInstruction(targetPath);
        statement = new Statement("add $t2, $t3, $t4;");
        instruction.assemble(statement);
        file = new File(targetPath);
        Scanner scanner = new Scanner(file);
        if(scanner.hasNext()){
            assertEquals(scanner.nextLine(),"0000_0001_0110_1100_0101_0000_0010_0000");
        }
        file.exists();
    }
    @Test
    public void testAssembleRShiftInstruction() throws FileNotFoundException, BinaryOverFlowException {
        instruction = new ShiftInstruction(targetPath);
        statement = new Statement("sll $t2, $s0, 4;");
        instruction.assemble(statement);
        file = new File(targetPath);
        Scanner scanner = new Scanner(file);
        if(scanner.hasNext()){
            assertEquals(scanner.nextLine(),"0000_0000_0001_0000_0101_0001_0000_0000");
        }
        file.exists();
    }
    @Test
    public void testAssembleROneOPInstruction() throws FileNotFoundException, BinaryOverFlowException {
        instruction = new OneOpRInstruction(targetPath);
        statement = new Statement("jr $t0;");
        instruction.assemble(statement);
        file = new File(targetPath);
        Scanner scanner = new Scanner(file);
        if(scanner.hasNext()){
            assertEquals(scanner.nextLine(),"0000_0001_0000_0000_0000_0000_0000_1000");
        }
        file.exists();
    }
    @Test
    public void testAssembleIInstruction() throws FileNotFoundException, BinaryOverFlowException {
        instruction = new IInstruction(targetPath);
        statement = new Statement("bne $t1, $zero, -7;");
        instruction.assemble(statement);
        file = new File(targetPath);
        Scanner scanner = new Scanner(file);
        if(scanner.hasNext()){
            assertEquals(scanner.nextLine(),"0001_0101_0010_0000_1111_1111_1111_1001");
        }
        file.exists();
    }

    @Test
    public void testAssembleImmedIInstruction() throws FileNotFoundException, BinaryOverFlowException {
        instruction = new ImmedIInstruction(targetPath);
        statement = new Statement("addi $t0, $t0, 4;");
        instruction.assemble(statement);
        file = new File(targetPath);
        Scanner scanner = new Scanner(file);
        if(scanner.hasNext()){
            assertEquals(scanner.nextLine(),"0010_0001_0000_1000_0000_0000_0000_0100");
        }
        file.exists();
    }
    @Test
    public void testAssembleAddrIInstruction() throws FileNotFoundException, BinaryOverFlowException {
        instruction = new ImmedIInstruction(targetPath);
        statement = new Statement("sw $t2, 8($t0);");
        instruction.assemble(statement);
        file = new File(targetPath);
        Scanner scanner = new Scanner(file);
        if(scanner.hasNext()){
            assertEquals(scanner.nextLine(),"1010_1101_0000_1010_0000_0000_0000_1000");
        }
        file.exists();
    }
    @Test
    public void testAssembleTwoOPIInstruction() throws FileNotFoundException, BinaryOverFlowException {
        instruction = new TwoOpIInstruction(targetPath);
        statement = new Statement("lui $t0, 255; ");
        instruction.assemble(statement);
        file = new File(targetPath);
        Scanner scanner = new Scanner(file);
        if(scanner.hasNext()){
            assertEquals(scanner.nextLine(),"0011_1100_0000_1000_0000_0000_1111_1111");
        }
        file.exists();
    }
    @Test
    public void testAssembleJInstruction() throws FileNotFoundException, BinaryOverFlowException {
        instruction = new JInstruction(targetPath);
        statement = new Statement("j 30;");
        instruction.assemble(statement);
        file = new File(targetPath);
        Scanner scanner = new Scanner(file);
        if(scanner.hasNext()){
            assertEquals(scanner.nextLine(),"0000_1000_0000_0000_0000_0000_0001_1110");
        }
        file.exists();
    }






}
