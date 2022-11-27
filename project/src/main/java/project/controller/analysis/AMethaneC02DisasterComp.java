package project.controller.analysis;

import java.util.ArrayList;

// Simply fetch data and add to hashmap.
public class AMethaneC02DisasterComp extends ComparisonAnalysisBase {
    private AMethaneC02DisasterComp(){};
    static private AMethaneC02DisasterComp analysisObj = new AMethaneC02DisasterComp();

    static public AMethaneC02DisasterComp getAnalysisObj(String country, String startYear, String endYear) {
        AMethaneC02DisasterComp.country = country;
        dataSeries = new ArrayList<String>();
        title = "Methane and CO2 Emissions vs Droughts Floods and Extreme Temperatures";
        dataSeries.add("methaneemissions");
        dataSeries.add("co2emissions");
        dataSeries.add("droughtsfloodsextremetemps");

        AMethaneC02DisasterComp.dataSeries = new ArrayList<String>();
        AMethaneC02DisasterComp.title = "Methane and CO2 Emissions vs Droughts Floods and Extreme Temperatures";
        AMortRateSafeWaterComp.yAxisTitle = "Tons Per Cap, % of Pop";
        AMethaneC02DisasterComp.dataSeries.add("methaneemissions");
        AMethaneC02DisasterComp.dataSeries.add("co2emissions");
        AMethaneC02DisasterComp.dataSeries.add("droughtsfloodsextremetemps");
        AMethaneC02DisasterComp.startYear = startYear;
        AMethaneC02DisasterComp.endYear = endYear;
        return analysisObj;
    }
}
