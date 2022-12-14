package project.controller.analysis;

import project.view.Charts.ChartCharacteristics.ChartType;

public class AHealthExpHospBedRat extends RatioAnalysisBase {

    private AHealthExpHospBedRat(){};
    static private AHealthExpHospBedRat analysisObj = new AHealthExpHospBedRat();

    static public AHealthExpHospBedRat getAnalysisObj(String country, String startYear, String endYear) {
        AHealthExpHospBedRat.country = country;
        AHealthExpHospBedRat.chartType = new ChartType(true, true, false);
        title = "Health Expenditure vs Hospital Beds (Ratio)";
        numData = "healthexpenditure";
        denomData = "hospitalbeds";

        AHealthExpHospBedRat.title = "Health Expenditure vs Hospital Beds (Ratio)";
        AHealthExpHospBedRat.yAxisTitle = "$ Per Bed";
        AHealthExpHospBedRat.numData = "healthexpenditure";
        AHealthExpHospBedRat.denomData = "hospitalbeds";
        AHealthExpHospBedRat.startYear = startYear;
        AHealthExpHospBedRat.endYear = endYear;
        return analysisObj;
    }
}
