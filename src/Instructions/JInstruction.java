package Instructions;

import exceptions.BinaryOverFlowException;
import modules.Model;
import modules.Statement;

import java.io.FileNotFoundException;

public class JInstruction implements Instruction {
    String targetPath;
    RegInstBank regInstBank;
    String binaryCode;
    Writer writer;
    public JInstruction(String targetPath){
        this.targetPath = targetPath;
        regInstBank = RegInstBank.getInstance();
    }
    public void assemble(Statement statement) throws BinaryOverFlowException, FileNotFoundException {
        binaryCode = regInstBank.getInstructionCode(statement.getInstruction());
        if (statement.getFirstOP().matches("\\d+")) {
            if (Integer.parseInt(statement.getFirstOP()) > 67108863 || Integer.parseInt(statement.getFirstOP()) < 0 ) {
                throw new BinaryOverFlowException();
            } else {

                    binaryCode = binaryCode +
                            String.format("%26s", Integer.toBinaryString(Integer.parseInt(statement.getFirstOP()))).replace(" ", "0");
                 }
        }
        else{

            int LabelAddress = Model.getLabelAddr(statement.getFirstOP())/4;
            binaryCode = binaryCode +
                    String.format("%26s", Integer.toBinaryString(LabelAddress)).replace(" ", "0");
        }

        writer = Writer.getInstance(targetPath);
        writer.write(binaryCode);
    }
}
