package Instructions.IInstructions;

import Instructions.Instruction;
import Instructions.RegInstBank;
import Instructions.Writer;
import exceptions.BinaryOverFlowException;
import modules.Statement;

import javax.xml.soap.SAAJResult;
import java.io.FileNotFoundException;

public class TwoOpIInstruction implements Instruction {
    String targetPath;
    RegInstBank regInstBank;
    String binaryCode;
    Writer writer;
    public TwoOpIInstruction(String targetPath){
        this.targetPath = targetPath;
        regInstBank = RegInstBank.getInstance();
    }

    @Override
    public void assemble(Statement statement) throws BinaryOverFlowException, FileNotFoundException {
        binaryCode = regInstBank.getInstructionCode(statement.getInstruction());
        binaryCode = binaryCode + "00000";
        binaryCode = binaryCode + regInstBank.getRegisterCode(statement.getFirstOP());
        if (Integer.parseInt(statement.getSecondOP()) > 32767 || Integer.parseInt(statement.getSecondOP()) < -32768) {
            throw new BinaryOverFlowException();
        } else {
            if (Integer.parseInt(statement.getSecondOP()) > 0) {
                binaryCode = binaryCode +
                        String.format("%16s", Integer.toBinaryString(Integer.parseInt(statement.getSecondOP()))).replace(" ", "0");
            } else {
                binaryCode = binaryCode + Integer.toBinaryString(Integer.parseInt(statement.getSecondOP())).substring(16);
            }}
        writer = Writer.getInstance(targetPath);
        writer.write(binaryCode);
    }
}
