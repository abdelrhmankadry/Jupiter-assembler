package modules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandLine {
    String output;
    String outputArray[];
    public CommandLine() {
        output = "";
        outputArray = new String[10000];
    }
    public void command(String command ){
        ProcessBuilder processBuilder = new ProcessBuilder();
        // Windows
        processBuilder.command("cmd.exe", "/c", command);



        try {

            Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            int i = 0 ;
            while ((line = reader.readLine()) != null) {
                outputArray[i] = line + "\n";
                i++;
            }

            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    public String getOutput() {
        return output;
    }

    public String[] getOutputArray() {
        return outputArray;
    }
}
