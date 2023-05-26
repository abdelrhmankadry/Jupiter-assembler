package sample;

import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import modules.Memory;


import java.net.URL;
import java.util.ResourceBundle;

public class MemoryController implements Initializable {
    public Text registers;
    public Text num;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registers.setText(Memory.getOutput());
        String n = "";
        for (Integer i = 0; i < 8192; i++) {
            n=n + i.toString()+"\n";
        }
        num.setText(n);
    }
}
