package project.analysis;

import java.util.ArrayList;
import java.util.HashMap;

import project.controller.Controller;


public class AForestAreaAvg extends AverageAnalysisBase {
    private AForestAreaAvg(){};
    static private AForestAreaAvg analysisObj = new AForestAreaAvg();

    static public AForestAreaAvg getAnalysisObj(String country, String startYear, String endYear) {
        AForestAreaAvg.country = country;
        AForestAreaAvg.dataSeries = "forestarea";
        AForestAreaAvg.yAxisTitle = "Percent";
        AForestAreaAvg.title = String.format("Land Usage, Average From %s to %s", startYear, endYear);
        AForestAreaAvg.startYear = startYear;
        AForestAreaAvg.endYear = endYear;
        return analysisObj;
    }
}