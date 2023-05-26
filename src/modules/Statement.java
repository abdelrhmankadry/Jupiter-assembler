package modules;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Statement {
    String label;
    String instruction;
    String firstOP;
    String secondOP;
    String thirdOp;
    String statement;
    public Statement(String statement){
        this.statement = statement;
        sliceStatement();
    }

    //MODIFIES: this.
    //BEHAVIOUR: slice the statement into sub strings of label (if there ) ,instruction ,
    //first operator , second operator (if there) and third operator (if there).
    private void sliceStatement(){
        //ex: add $t0 ,$s1,$s2;
        ArrayList<String> splitedStatement;
        String regex = "\\s*[a-zA-Z]{2,6}\\s+((\\$\\w{2})|(\\$zero))(\\s*,\\s*((\\$\\w{2})|(\\$zero)))" +
                "(\\s*,\\s*((\\$\\w{2})|(\\$zero)|(\\-?\\d+)|(\\w+)))\\s*;";

        if (_matches(regex)) {
            splitedStatement = getSplitedStatementList(regex,"[\\s,;]");
            instruction = splitedStatement.get(0);
            firstOP = splitedStatement.get(1);
            secondOP = splitedStatement.get(2);
            thirdOp = splitedStatement.get(3);
        }
        //ex: lw $t2, 32($s2);
        regex = "\\s*[a-zA-Z]{2,6}\\s+((\\$\\w{2})|(\\$zero))" +
                "(\\s*,\\s*((\\d+\\s*\\(\\s*(\\$\\w{2})\\s*\\))))\\s*;";
        if (_matches(regex)) {
            splitedStatement = getSplitedStatementList(regex,"[\\s,;()]");
            instruction = splitedStatement.get(0);
            firstOP = splitedStatement.get(1);
            secondOP = splitedStatement.get(3);
            thirdOp = splitedStatement.get(2);
        }
        //ex: jr $t0;
        regex = "\\s*[a-zA-Z]{1,6}\\s+((\\$\\w{2})|(\\$zero)|(\\w+)|(\\d+))" +
                "\\s*;";
        if (_matches(regex)) {
            splitedStatement = getSplitedStatementList(regex,"[\\s;]");
            instruction = splitedStatement.get(0);
            firstOP = splitedStatement.get(1);
            secondOP = "";
            thirdOp = "";
        }
        //ex: lui $t0 ,255;
         regex = "\\s*[a-zA-Z]{2,6}\\s+((\\$\\w{2})|(\\$zero))(\\s*,\\s*((\\d+)|(\\$zero)))" +
                "\\s*;";

        if (_matches(regex)) {
            splitedStatement = getSplitedStatementList(regex,"[\\s,;]");
            instruction = splitedStatement.get(0);
            firstOP = splitedStatement.get(1);
            secondOP = splitedStatement.get(2);
            thirdOp = "";
        }


    }

    private ArrayList<String> getSplitedStatementList(String regex,String splittingRegex) {
        String splitedStatement[] = getStatement(regex).split(splittingRegex);
        List<String> splitedStatementList = new ArrayList<>();
        for (int i = 0; i < splitedStatement.length; i++) {
            if (splitedStatement[i].equals("") || splitedStatement[i].equals(";")) {
                continue;
            }
            splitedStatementList.add(splitedStatement[i]);
        }
        return (ArrayList<String>) splitedStatementList;
    }

    private boolean _matches(String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher= pattern.matcher(statement);
        return matcher.find();
    }

    private String getStatement(String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher= pattern.matcher(statement);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    public String getLabel() {
        return label;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getFirstOP() {
        return firstOP;
    }

    public String getSecondOP() {
        return secondOP;
    }

    public String getThirdOp() {
        return thirdOp;
    }

    public String getStatement() {
        return statement;
    }
}
