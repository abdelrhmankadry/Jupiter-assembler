package modules;

public class Filter {
    String regfile[];
    String memory[];
    String pc[];
    String input[];
    public Filter(String input[]) {
        regfile = new String[32];
        memory = new String[8192];
        pc = new String[2000];
        this.input = input;
        filter(input);
    }

    private void filter(String input[]) {
        String regRegex = "reg = \\w{32}";
        String memRegex = "mem = \\w{32}";
        String pcRegex = "PC = \\w+";
        int regCounter =0;
        int memCounter =0;
        int pcCounter =0;

        for (String s:
             input) {
            if(s == null) continue;
            if (s.contains("reg")) {
                regfile[regCounter] = s;
                regCounter++;
            } else if (s.contains("Memory")) {
                memory[memCounter] = s;
                memCounter++;
            } else if (s.contains("PC")) {
                pc[pcCounter] = s;
                pcCounter++;
            }
        }
    }

    public String[] getRegfile() {
        return regfile;
    }

    public String[] getMemory() {
        return memory;
    }

    public String[] getPc() {
        return pc;
    }
}
