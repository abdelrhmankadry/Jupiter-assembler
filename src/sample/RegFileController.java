package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modules.RegFile;

import java.net.URL;
import java.util.ResourceBundle;

public class RegFileController implements Initializable {
    public Text registers;
    public Text num;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registers.setText(RegFile.getOutput());
        String n = "";
        for (Integer i = 0; i < 32; i++) {
            n=n + i.toString()+"\n";
        }
        num.setText(n);

    }
}
