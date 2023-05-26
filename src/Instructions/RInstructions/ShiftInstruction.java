package Instructions.RInstructions;

import Instructions.Instruction;
import Instructions.RInstructions.RInstruction;
import Instructions.RegInstBank;

import Instructions.Writer;
import exceptions.BinaryOverFlowException;
import modules.Statement;

import java.io.FileNotFoundException;

public class ShiftInstruction implements Instruction {
    String targetPath;
    RegInstBank regInstBank;
    Writer writer;
    String binaryCode;
    public ShiftInstruction(String targetPath){
        this.targetPath = targetPath;
        regInstBank = RegInstBank.getInstance();
    }
    @Override
    public void assemble(Statement statement) throws BinaryOverFlowException, FileNotFoundException {
        binaryCode ="000000";
        binaryCode = binaryCode +"00000";
        binaryCode = binaryCode + regInstBank.getRegisterCode(statement.getSecondOP());
        binaryCode = binaryCode + regInstBank.getRegisterCode(statement.getFirstOP());
        if (Integer.parseInt(statement.getThirdOp()) > 32 || Integer.parseInt(statement.getThirdOp()) < 0) {
            throw new BinaryOverFlowException();
        } else {
            binaryCode = binaryCode +
                    String.format("%5s",Integer.toBinaryString(Integer.parseInt(statement.getThirdOp()))).replace(" ","0");
        }

        binaryCode = binaryCode + regInstBank.getInstructionCode(statement.getInstruction());
        writer = Writer.getInstance(targetPath);
        writer.write(binaryCode);
    }
}
