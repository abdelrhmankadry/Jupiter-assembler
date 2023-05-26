package Instructions.IInstructions;

import Instructions.Instruction;
import Instructions.RegInstBank;

import Instructions.Writer;
import exceptions.BinaryOverFlowException;
import modules.Model;
import modules.Statement;

import java.io.FileNotFoundException;

public class IInstruction implements Instruction {
    String targetPath;
    RegInstBank regInstBank;
    String binaryCode;
    Writer writer;
    public IInstruction(String targetPath){
        this.targetPath = targetPath;
        regInstBank = RegInstBank.getInstance();
    }
    @Override
    public void assemble(Statement statement) throws BinaryOverFlowException, FileNotFoundException {
        binaryCode = regInstBank.getInstructionCode(statement.getInstruction());
        binaryCode = binaryCode + regInstBank.getRegisterCode(statement.getFirstOP());
        binaryCode = binaryCode + regInstBank.getRegisterCode(statement.getSecondOP());
        if (statement.getThirdOp().matches("\\-?\\d+")) {
            if (Integer.parseInt(statement.getThirdOp()) > 32767 || Integer.parseInt(statement.getThirdOp()) < -32768) {
                throw new BinaryOverFlowException();
            } else {
                if (Integer.parseInt(statement.getThirdOp()) >= 0) {
                    binaryCode = binaryCode +
                            String.format("%16s", Integer.toBinaryString(Integer.parseInt(statement.getThirdOp()))).replace(" ", "0");
                } else {
                    binaryCode = binaryCode + Integer.toBinaryString(Integer.parseInt(statement.getThirdOp())).substring(16);
                }
            }

        } else {

              int LabelAddress = (Model.getLabelAddr(statement.getThirdOp()) - (Model.getCurrentAddress()+4)  )/4 ;

            if (LabelAddress > 0) {
                binaryCode = binaryCode +
                        String.format("%16s", Integer.toBinaryString(LabelAddress)).replace(" ", "0");

            } else {
                binaryCode = binaryCode + Integer.toBinaryString(LabelAddress).substring(16);
            }
//            binaryCode = binaryCode +
//                    String.format("%16s", Integer.toBinaryString(LabelAddress)).replace(" ", "0").substring(16);
        }
        writer = Writer.getInstance(targetPath);
        writer.write(binaryCode);
    }
}
