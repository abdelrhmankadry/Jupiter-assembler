package tests;

import modules.Statement;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StatementTest {
    Statement statement;

    @Test
    public  void testSliceRInsruction(){
        statement = new Statement("add $t2, $t3, $t4;");

        assertEquals(statement.getInstruction(),"add");
        assertEquals(statement.getFirstOP(),"$t2");
        assertEquals(statement.getSecondOP(),"$t3");
        assertEquals(statement.getThirdOp(),"$t4");
    }
    @Test
    public void testSliceRShift(){
        statement = new Statement("sll $t1, $t2, 5;");

        assertEquals(statement.getInstruction(),"sll");
        assertEquals(statement.getFirstOP(),"$t1");
        assertEquals(statement.getSecondOP(),"$t2");
        assertEquals(statement.getThirdOp(),"5");
    }
    @Test
    public void testSliceROneOp(){
        statement = new Statement("jr $ra;");
        assertEquals(statement.getInstruction(),"jr");
        assertEquals(statement.getFirstOP(),"$ra");
        assertEquals(statement.getSecondOP(),"");
        assertEquals(statement.getThirdOp(),"");
    }
    @Test
    public void testSliceIImmedInstruction(){
        statement = new Statement("addi $t1, $t2, 15;");
        assertEquals(statement.getInstruction(),"addi");
        assertEquals(statement.getFirstOP(),"$t1");
        assertEquals(statement.getSecondOP(),"$t2");
        assertEquals(statement.getThirdOp(),"15");
    }
    @Test
    public void testSliceIAddrInstruction(){
        statement = new Statement("lw $t1, 15 ($t2);");
        assertEquals(statement.getInstruction(),"lw");
        assertEquals(statement.getFirstOP(),"$t1");
        assertEquals(statement.getSecondOP(),"$t2");
        assertEquals(statement.getThirdOp(),"15");
    }
    @Test
    public void testSliceIInstruction(){
        statement = new Statement("beq $t1, $t2, Loop;");
        assertEquals(statement.getInstruction(),"beq");
        assertEquals(statement.getFirstOP(),"$t1");
        assertEquals(statement.getSecondOP(),"$t2");
        assertEquals(statement.getThirdOp(),"Loop");
    }
    @Test
    public void testSliceJInstruction(){
        statement = new Statement("L1: j 30;");
        assertEquals(statement.getInstruction(),"j");
        assertEquals(statement.getFirstOP(),"30");
        assertEquals(statement.getSecondOP(),"");
        assertEquals(statement.getThirdOp(),"");
    }
    @Test
    public void testSliceTwoOpIInstruction(){
        statement = new Statement("lui $t1 ,255;");
        assertEquals(statement.getInstruction(),"lui");
        assertEquals(statement.getFirstOP(),"$t1");
        assertEquals(statement.getSecondOP(),"255");
        assertEquals(statement.getThirdOp(),"");
    }



}
