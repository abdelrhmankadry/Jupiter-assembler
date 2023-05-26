package modules;

public class Memory {
    private static String output;
    public Memory(String output[]) {

        setText(output);
    }


    private void setText(String regs[]) {
        output = "";

        for (String s :
                regs) {
            output = output + s;
        }

    }

    public static String getOutput() {
        return output;
    }
}
