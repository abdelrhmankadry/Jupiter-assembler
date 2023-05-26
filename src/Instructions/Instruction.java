package Instructions;

import exceptions.BinaryOverFlowException;
import modules.Statement;

import java.io.FileNotFoundException;

public  interface Instruction  {
    void assemble(Statement statement) throws FileNotFoundException, BinaryOverFlowException;
}
