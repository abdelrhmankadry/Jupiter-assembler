package Instructions.IInstructions;

import Instructions.Instruction;
import Instructions.RegInstBank;

import Instructions.Writer;
import exceptions.BinaryOverFlowException;
import modules.Statement;

import java.io.FileNotFoundException;

public class ImmedIInstruction implements Instruction {

    String targetPath;
    RegInstBank regInstBank;
    String binaryCode;
    Writer writer;
    public ImmedIInstruction(String targetPath){
        this.targetPath = targetPath;
        regInstBank = RegInstBank.getInstance();
    }
    @Override
    public void assemble(Statement statement) throws BinaryOverFlowException, FileNotFoundException {
        binaryCode = regInstBank.getInstructionCode(statement.getInstruction());
        binaryCode = binaryCode + regInstBank.getRegisterCode(statement.getSecondOP());
        binaryCode = binaryCode + regInstBank.getRegisterCode(statement.getFirstOP());
        if (statement.getThirdOp().equals("$zero")){
            binaryCode = binaryCode + "0000000000000000"; // note.........
        }
        else if (Integer.parseInt(statement.getThirdOp()) > 32767 || Integer.parseInt(statement.getThirdOp()) < -32768) {
            throw new BinaryOverFlowException();
        } else {
            if (Integer.parseInt(statement.getThirdOp()) >= 0) { // note..........
                binaryCode = binaryCode +
                        String.format("%16s", Integer.toBinaryString(Integer.parseInt(statement.getThirdOp()))).replace(" ", "0");
            } else {
                binaryCode = binaryCode + Integer.toBinaryString(Integer.parseInt(statement.getThirdOp())).substring(16);
            }}
        writer = Writer.getInstance(targetPath);
        writer.write(binaryCode);

    }
}
