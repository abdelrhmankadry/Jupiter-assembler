package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modules.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Controller  {
    String sourceParent;
    String source;
    String sourceName;
    String target;
    Model model;
    String testFolder;
    boolean Error;
    @FXML
    private Text output;
    RegFileController reg;

    public void FileChooserBtn(javafx.event.ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            sourceParent = selectedFile.getParent();
            sourceName = selectedFile.getName();
        }
        else {
            System.out.println("error");
        }
    }

    public void runBtn(javafx.event.ActionEvent actionEvent) throws FileNotFoundException {
        output.setText(output.getText()+"\n"+ "Assembling and simulating..."+"\n");
        Error = false;
        target = sourceParent+"\\myfile.txt";
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model = new Model(source, target);
                    model.addErrorDetectedListener(() -> {
                        output.setText(output.getText()+"\n"+model.getErrorMessage());
                        Error = true;
                    });
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    model.ModelMain(source, target);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                if(!Error){
                CommandLine cml = new CommandLine();
                String command = "cd " + sourceParent+ " && iverilog -o mips32project.vvp mips32project.v && vvp mips32project.vvp";
                // String command = "cd C:\\Modeltech_pe_edu_10.4a\\examples\\MIPS && iverilog -o mips32project.vvp mips32project.v && vvp mips32project.vvp";

                cml.command(command);
                String outputText = "";

                Filter filter = new Filter (cml.getOutputArray());
        for (String s :
                cml.getOutputArray()) {
            System.out.print(s);
        }
                RegFile regFile = new RegFile(filter.getRegfile());
                Memory memory = new Memory(filter.getMemory());
                String PC="";
                for (String s :
                        filter.getPc()) {
                    if(s == null) break;
                    PC = PC + s;
                }
                output.setText(output.getText()+"\n"+PC);
                    output.setText(output.getText()+"\n"+"Done!!");
                }

            }
        });




    }


    public void generateBtn(ActionEvent actionEvent) throws FileNotFoundException {
        //target = sourceParent+"\\myfile.txt";

    }


    public void exit(MouseEvent mouseEvent) {
        Stage s = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        s.close();
    }

    public void move(MouseDragEvent mouseDragEvent) {
        Stage s = (Stage) ((Node) mouseDragEvent.getSource()).getScene().getWindow();
        s.setX(10);
        s.setY(10);
    }

    public void openRegFile(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("RegFile.fxml"));

        Stage stage = new Stage();
        stage.setTitle("RegFile");
        stage.setScene(new Scene(root, 600, 400));

        stage.show();


    }


    public void openMemory(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Memory.fxml"));

        Stage stage = new Stage();
        stage.setTitle("Memory");
        stage.setScene(new Scene(root, 600, 400));

        stage.show();
    }

    public void btnSourceFile(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            source = selectedFile.getAbsolutePath();
        }
        else {
            System.out.println("error");
        }
    }

    public void btnTestFolder(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            testFolder = selectedFile.getParent();
        }
        else {
            System.out.println("error");
        }
    }

    public void autoTest(ActionEvent actionEvent) throws FileNotFoundException {
        output.setText(output.getText()+ "Assembling and simulating files ..."+"\n");
        AutoTest autoTest = new AutoTest(testFolder,sourceParent+"\\myfile.txt",sourceParent);
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(new Runnable() {
            @Override
            public void run() {

                autoTest.addErrorDetectedListener(()-> {
                    output.setText(output.getText()+"\n"+autoTest.getErrorMessage());
                });
                try {
                    autoTest.testAll();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            output.setText(output.getText()+"\n"+autoTest.getMessage());
                        }
                    });
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
