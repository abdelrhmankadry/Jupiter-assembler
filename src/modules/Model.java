package modules;

import Instructions.IInstructions.AddrIInstruction;
import Instructions.IInstructions.IInstruction;
import Instructions.IInstructions.ImmedIInstruction;
import Instructions.IInstructions.TwoOpIInstruction;
import Instructions.Instruction;
import Instructions.JInstruction;
import Instructions.RInstructions.OneOpRInstruction;
import Instructions.RInstructions.RInstruction;
import Instructions.RInstructions.ShiftInstruction;
import Instructions.Writer;
import exceptions.BinaryOverFlowException;
import exceptions.SyntexError;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Model {

    private static Model modelRefrence;
    private  HashMap<String, Instruction> instructionsMap ;
    private  HashMap<String,Integer> labelsMap;
    private  int statementsCounter ;
    private  Statement statement;
    private List<ErrorDetectedListener> ErrorDetectorListeners = new ArrayList<ErrorDetectedListener>();
    private  File file;
    private  String targetPath;
    private String errorMessage;
    public Model(String sourcePath, String targetPath) throws FileNotFoundException {


    }


    // code...

    //MODIFIES: this.
    //BEHAVIOUR: this method is responsible of reading the text code and pass the statements to other
    //Objects and assemble that code and put it  in new text file.
    //REQUIRES: Valid file path for both assembly code and the new file .
     public void ModelMain(String sourcePath, String targetPath) throws FileNotFoundException {
        initialize(sourcePath,targetPath);
        try {
            //File file = new File(sourcePath);
            Scanner scanner ;
            scanner = new Scanner(file);
            Writer.changeFile(new File(targetPath));
            Matcher matcher;
            String regex1="^((\\s*\\w+\\s*:)?\\s*[a-zA-Z]{1,6}\\s+((((((\\$\\w{2})|(\\$zero))(\\s*,\\s*((\\$\\w{2})|(\\$zero)))?" +
                    "(\\s*,\\s*((\\$\\w{2})|(\\$zero)|(\\d+\\s*\\(\\s*(\\$\\w{2})\\s*\\))|(-?\\d+)|(\\w+)))?)|(\\w+))\\s*;)|(\\w+)\\s*;))?(\\s*#\\s*\\.*\\s*)?";

            String regex2="^((\\s*\\w+\\s*:)?\\s*[a-zA-Z]{1,6}\\s+((((\\$\\w{2})|(\\$zero))(\\s*,\\s*((\\$\\w{2})|(\\$zero)))?" +
                    "(\\s*,\\s*((\\$\\w{2})|(\\$zero)|(\\d+\\s*\\(\\s*(\\$\\w{2})\\s*\\))|(-?\\d+)|(\\w+)))?)|(\\w+))\\s*;)";
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                matcher = Pattern.compile(regex1).matcher(line);
                if (matcher.matches()) {
                    matcher = Pattern.compile(regex2).matcher(line);
                    if (matcher.find()) {
                        //statementsCounter++;
                        Reposatory.updateCounter();
                        statement = new Statement(matcher.group(0));
                        instructionsMap.get(statement.getInstruction()).assemble(statement);
                    }
                } else {
                    throw new SyntexError();
                }
            }
            Writer.close_file();
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SyntexError syntexError) {
            //syntexError.printStackTrace();
            System.out.printf("error in line : %d",(Reposatory.getCurrentAddress()/4)+2);
            Integer i = (Reposatory.getCurrentAddress()/4)+2;
            errorMessage = "SyntexError in line :"+i.toString()+"\n";
            if (ErrorDetectorListeners.size()>0) ErrorDetectorListeners.get(0).onErrorDetected();
        } catch (BinaryOverFlowException e) {
            e.printStackTrace();
        }


    }

    public void addErrorDetectedListener(ErrorDetectedListener listener) {
        ErrorDetectorListeners.add(listener);
    }
    //BEHAVIOUR: returns the the address of a Label.
    static public int getLabelAddr(String label){
        return Reposatory.getLabelAddr(label);
    }
//    static public int getLabelAddr(String label){
//        return labelsMap.get(label);
//    }
static public int getCurrentAddress(){
    return Reposatory.getCurrentAddress();
}
//    static public int getCurrentAddress(){
//        return statementsCounter * 4;
//    }
    // BEHAVIOUR : inspect for all Labels in the code and put it in a Map.
     public void inspectLabels(File sourceFile) throws FileNotFoundException {
        int index =-1 ;
        Scanner scanner = new Scanner(sourceFile);
        String regex = "^((\\s*\\w+\\s*:)?\\s*[a-zA-Z]{1,6}\\s+((((\\$\\w{2})|(\\$zero))(\\s*,\\s*((\\$\\w{2})|(\\$zero)))?" +
                "(\\s*,\\s*((\\$\\w{2})|(\\$zero)|(\\d+\\s*\\(\\s*(\\$\\w{2})\\s*\\))|(\\-?\\d+)|(\\w+)))?)|(\\w+))\\s*;)";
        Matcher matcher;
        String Label;
        String LabelRegex = "^\\s*\\w+\\s*:";
        while (scanner.hasNextLine()) { //TODO: need to make it has Next Pattern //TODO: need to support label with out instruction inlined.
            index++;
            matcher = Pattern.compile(LabelRegex).matcher(scanner.nextLine());
            while (matcher.find()) {
                Label = matcher.group(0).substring(0,matcher.group(0).length()-1).trim();
                labelsMap.put(Label,index*4);
            }
        }
        Reposatory.setLabelsMap(labelsMap);
    }

    private  void initialize(String sourcePath, String target) throws FileNotFoundException {
        instructionsMap = new HashMap<>(0);
        labelsMap = new HashMap<String, Integer>(0);
        statementsCounter = -1;
        Reposatory.initializeCounter();
        file = new File(sourcePath);
        inspectLabels(new File(sourcePath));
        targetPath = target;
        instructionsMap.put("add",new RInstruction(target));
        instructionsMap.put("sub",new RInstruction(target));
        instructionsMap.put("and",new RInstruction(target));
        instructionsMap.put("or",new RInstruction(target));
        instructionsMap.put("nor",new RInstruction(target));
        instructionsMap.put("slt",new RInstruction(target));
        //shift...
        instructionsMap.put("sll",new ShiftInstruction(target));
        instructionsMap.put("sra",new ShiftInstruction(target));
        instructionsMap.put("srl",new ShiftInstruction(target));
        //one op R instruction....
        instructionsMap.put("jr",new OneOpRInstruction(target));
        // jump instruction..
        instructionsMap.put("j",new JInstruction(target));
        instructionsMap.put("jal",new JInstruction(target));
        //I immediate ...
        instructionsMap.put("addi",new ImmedIInstruction(target));
        instructionsMap.put("andi",new ImmedIInstruction(target));
        instructionsMap.put("ori",new ImmedIInstruction(target));
        instructionsMap.put("slti",new ImmedIInstruction(target));
        // I two op...
        instructionsMap.put("lui",new TwoOpIInstruction(target));
        // I address instruction..
        instructionsMap.put("lw",new AddrIInstruction(target));
        instructionsMap.put("lh",new AddrIInstruction(target));
        instructionsMap.put("lb",new AddrIInstruction(target));
        instructionsMap.put("sw",new AddrIInstruction(target));
        instructionsMap.put("sh",new AddrIInstruction(target));
        instructionsMap.put("sb",new AddrIInstruction(target));
        // I instruction...
        instructionsMap.put("beq",new IInstruction(target));
        instructionsMap.put("bne",new IInstruction(target));
    }
     private Scanner get_scanner() throws FileNotFoundException {
        return new Scanner(file);
    }
    private File get_file(String sourcePath){
        return new File(sourcePath);
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
