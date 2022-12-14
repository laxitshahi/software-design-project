package project.controller.analysis;

import project.controller.Controller;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ComparisonAnalysisBase extends AnalysisBase<String, Integer, Float> {
    static protected ArrayList<String> dataSeries;

    public HashMap<String, HashMap<Integer, Float>> getAnalyzedDataHelper(ArrayList<HashMap<String, HashMap<Integer, Float>>> data) {
        HashMap<String, HashMap<Integer, Float>> result = new HashMap<String, HashMap<Integer, Float>>();

        for (HashMap<String, HashMap<Integer, Float>> mappedData : data) {
            String outerKey = (new ArrayList<String>(mappedData.keySet())).get(0);
            result.put(outerKey, mappedData.get(outerKey));
        }
        return result;
    }

    public HashMap<String, HashMap<Integer, Float>> getAnalyzedData() {
        ArrayList<HashMap<String, HashMap<Integer, Float>>> dataSet = new ArrayList<HashMap<String, HashMap<Integer, Float>>>();
        for (String series : dataSeries){
            dataSet.add(Controller.getFilteredData(country, series, startYear, endYear));
        }
        return getAnalyzedDataHelper(dataSet);
    }
}
