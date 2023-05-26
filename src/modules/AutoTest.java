package modules;

import com.sun.xml.internal.ws.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AutoTest {
    String FPath;
    String TPath;
    String PPath;
    String result;
    String output[];
    String solution[];
    String message;
    String erroeMessage;
    private List<ErrorDetectedListener> ErrorDetectorListeners = new ArrayList<ErrorDetectedListener>();
    public AutoTest(String FPath,String TPath,String PPath) throws FileNotFoundException {
        output = new String[10000];
        solution = new String[10000];
        this.FPath = FPath;
        this.TPath = TPath;
        this.PPath = PPath;

    }

    private boolean test(Integer num) throws FileNotFoundException {
        boolean state;
        String Filenume = num.toString();
        String SPath= FPath +"\\"+Filenume+".txt";
        Model model = new Model(SPath,TPath);
        model.ModelMain(SPath,TPath);
        CommandLine cml = new CommandLine();
        String command = "cd " + PPath+ " && iverilog -o mips32project.vvp mips32project.v && vvp mips32project.vvp";
        cml.command(command);
        output = cml.getOutputArray();
        File file = new File(FPath+"\\s"+Filenume+".txt");
        Scanner scanner = new Scanner(file);
        int i =0;
        while (scanner.hasNextLine()) {
            solution[i] = scanner.nextLine();

            i++;
        }
        state = true;
        for (int j = 1; j < output.length; j++) {

            if(solution[j] ==null || output[j] ==null) continue;
            else if (solution[j].replace("\n","").equals("...")) break;
            if(!((solution[j].replace("\n","")).equals(output[j].replace("\n","")))) {state = false;
            erroeMessage = "dis-matching in line: "+(j+1)+"\n";
            ErrorDetectorListeners.get(0).onErrorDetected();  break;}
        }

        return state;
    }
public void testAll() throws FileNotFoundException {
        boolean flag =true;
        boolean temp;
        String s;
         File file = new File (FPath+"\\1.txt");
         Integer i = 1;
    while (file.exists()) {
        temp = test(i);
        if (!temp) {
            flag = false ;
            message = "File :" + i.toString() +".txt Test Failed";
        }

       i++;
       s = i.toString();
       file = new File(FPath + "\\"+s+".txt");

    }
    if (flag) {
        message = "All Tests are passed";
    }

}
    public String getMessage() {
        return message;
    }
    public void addErrorDetectedListener(ErrorDetectedListener listener) {
        ErrorDetectorListeners.add(listener);
    }

    public String getErrorMessage() {
        return erroeMessage;
    }
}
