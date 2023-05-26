package Instructions.RInstructions;

import Instructions.Instruction;
import Instructions.RInstructions.RInstruction;
import Instructions.RegInstBank;

import Instructions.Writer;
import modules.Statement;

import java.io.FileNotFoundException;

public class OneOpRInstruction implements Instruction {
    String targetPath;
    RegInstBank regInstBank;
    String binaryCode;
    Writer writer;
    public OneOpRInstruction(String targetPath){
        this.targetPath = targetPath;
        regInstBank = RegInstBank.getInstance();
    }
    @Override
    public void assemble(Statement statement) throws FileNotFoundException {
        binaryCode ="000000";
        binaryCode = binaryCode + regInstBank.getRegisterCode(statement.getFirstOP());
        binaryCode = binaryCode + "00000";
        binaryCode = binaryCode + "00000";
        binaryCode = binaryCode + "00000";
        binaryCode = binaryCode + regInstBank.getInstructionCode(statement.getInstruction());
        writer = Writer.getInstance(targetPath);
        writer.write(binaryCode);
    }
}
