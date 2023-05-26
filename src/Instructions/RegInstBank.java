package Instructions;

import java.util.HashMap;

public class RegInstBank {
    private static RegInstBank instance;
    private static HashMap<String ,String> regMap;
    private static HashMap<String,String> instMap;
    private RegInstBank(){
        regMap = new HashMap<>();
        instMap = new HashMap<>();
        /////////////////////////////////////////
        //registers codes....
        regMap.put("$zero","00000");
        regMap.put("$at","00001");
        regMap.put("$v0","00010");
        regMap.put("$a0","00100");
        regMap.put("$a1","00101");
        regMap.put("$a2","00110");
        regMap.put("$a3","00111");
        regMap.put("$t0","01000");
        regMap.put("$t1","01001");
        regMap.put("$t2","01010");
        regMap.put("$t3","01011");
        regMap.put("$t4","01100");
        regMap.put("$t5","01101");
        regMap.put("$t6","01110");
        regMap.put("$t7","01111");
        regMap.put("$s0","10000");
        regMap.put("$s1","10001");
        regMap.put("$s2","10010");
        regMap.put("$s3","10011");
        regMap.put("$s4","10100");
        regMap.put("$s5","10101");
        regMap.put("$s6","10110");
        regMap.put("$s7","10111");
        regMap.put("$t8","11000");
        regMap.put("$t9","11001");
        regMap.put("$k0","11010");
        regMap.put("$k1","11011");
        regMap.put("$gp","11100");
        regMap.put("$sp","11101");
        regMap.put("$fp","11110");
        regMap.put("$ra","11111");
////////////////////////////////////
        instMap.put("add","100000");
        instMap.put("addi","001000");
        instMap.put("and","100100");
        instMap.put("andi","001100");
        instMap.put("beq","000100");
        instMap.put("bne","000101");
        instMap.put("j","000010");
        instMap.put("jal","000011");
        instMap.put("jr","001000");
        instMap.put("lb","100000");
        instMap.put("lh","100001");
        instMap.put("lui","001111");
        instMap.put("lw","100011");
        instMap.put("nor","100111");
        instMap.put("or","100101");
        instMap.put("ori","001101");
        instMap.put("sb","101000");
        instMap.put("sh","101001");
        instMap.put("sll","000000");
        instMap.put("slt","101010");
        instMap.put("slti","001010");
        instMap.put("sra","000011");
        instMap.put("srl","000010");
        instMap.put("sub","100010");
        instMap.put("sw","101011");


    }
    public static RegInstBank getInstance(){
        if (instance == null){
            instance = new RegInstBank();
        }
        return instance;
    }

    public String getRegisterCode(String register){return regMap.get(register);}
    public String getInstructionCode(String instruction){return instMap.get(instruction);}
}
