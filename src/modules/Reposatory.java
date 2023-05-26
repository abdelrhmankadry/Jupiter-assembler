package modules;

import java.util.HashMap;

public class Reposatory {
    static private int statementsCounter = -1;
    private static HashMap<String,Integer> labelsMap;

    static public void updateCounter(){
        statementsCounter++;
    }
    static public int getLabelAddr(String label){
        return labelsMap.get(label);
    }
    static public int getCurrentAddress(){
        return statementsCounter * 4;
    }

    static public void initializeCounter() {
        statementsCounter = -1;
    }
    public static void setLabelsMap(HashMap<String, Integer> labelsMap) {
        Reposatory.labelsMap = labelsMap;
    }
}

