package project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import project.controller.analysis.AC02GDPRat;
import project.controller.analysis.AForestAgricultureAreaComp;
import project.controller.analysis.AForestAreaAvg;
import project.controller.analysis.AHealthAccessMortRateComp;
import project.controller.analysis.AHealthExpHospBedRat;
import project.controller.analysis.AMethaneC02DisasterComp;
import project.controller.analysis.AMortRateSafeWaterComp;
import project.controller.analysis.ANetUsersElecAccessRat;

import java.util.ArrayList;
import java.util.HashMap;

public class AnalysisTest {
    
    @Test
    public void test_ratio_analysis() {
        HashMap<String, HashMap<Integer, Float>> country1Data = new HashMap<String, HashMap<Integer, Float>>();
        HashMap<Integer, Float> numeratorData = new HashMap<Integer, Float>();
        numeratorData.put(2000, 10.f);
        numeratorData.put(2001, 10.f);
        numeratorData.put(2002, 10.f);
        numeratorData.put(2003, 10.f);
        country1Data.put("healthexpenditure", numeratorData);

        HashMap<String, HashMap<Integer, Float>> country2Data = new HashMap<String, HashMap<Integer, Float>>();
        HashMap<Integer, Float> denominatorData = new HashMap<Integer, Float>();
        denominatorData.put(2000, 10.f);
        denominatorData.put(2001, 5.f);
        denominatorData.put(2002, 2.f);
        denominatorData.put(2003, 1.f);
        country2Data.put("hospitalbeds", denominatorData);

 
        HashMap<String, HashMap<Integer, Float>> data = AHealthExpHospBedRat.getAnalysisObj("DNC", "DNC", "DNC").getAnalyzedDataHelper(country1Data, country2Data);
        assertEquals( 1.f, data.get("Ratio").get(2000), 0.01f);
        assertEquals( 2.f, data.get("Ratio").get(2001), 0.01f);
        assertEquals( 5.f, data.get("Ratio").get(2002), 0.01f);
        assertEquals( 10.f, data.get("Ratio").get(2003), 0.01f);

    }
    
    @Test
    public void test_ratio() {
        // Manually test against pre-computed data
        HashMap<String, HashMap<Integer, Float>> data = AC02GDPRat.getAnalysisObj("US", "2012", "2018").getAnalyzedData();
        assertEquals( 2.6180642E-4, data.get("Ratio").get(2016), 0.01f);
        assertEquals( 2.4740575E-4, data.get("Ratio").get(2017), 0.01f);
        
        data = ANetUsersElecAccessRat.getAnalysisObj("US", "2012", "2018").getAnalyzedData();
        assertEquals( 0.8554442, data.get("Ratio").get(2016), 0.01f);
        assertEquals( 0.87274885, data.get("Ratio").get(2017), 0.01f);

    }
    
    @Test
    public void test_ratio_analysis_empty() {
        HashMap<String, HashMap<Integer, Float>> country1Data = new HashMap<String, HashMap<Integer, Float>>();
        HashMap<Integer, Float> numeratorData = new HashMap<Integer, Float>();
        country1Data.put("healthexpenditure", numeratorData);

        HashMap<String, HashMap<Integer, Float>> country2Data = new HashMap<String, HashMap<Integer, Float>>();
        HashMap<Integer, Float> denominatorData = new HashMap<Integer, Float>();
        country2Data.put("hospitalbeds", denominatorData);

        // Ensure there are no divide by 0 exceptions
        HashMap<String, HashMap<Integer, Float>> data = AHealthExpHospBedRat.getAnalysisObj("DNC", "DNC", "DNC").getAnalyzedDataHelper(country1Data, country2Data);      

    }

    @Test
    public void test_comparison_analysis() {
        ArrayList<HashMap<String, HashMap<Integer, Float>>> allCountryData = new ArrayList<HashMap<String, HashMap<Integer, Float>>>();
        HashMap<String, HashMap<Integer, Float>> country1Data = new HashMap<String, HashMap<Integer, Float>>();
        HashMap<Integer, Float> data1 = new HashMap<Integer, Float>();
        data1.put(2000, 10.f);
        data1.put(2001, 20.f);
        data1.put(2002, 30.f);
        data1.put(2003, 40.f);
        country1Data.put("mortalityunder5", data1);

        HashMap<String, HashMap<Integer, Float>> country2Data = new HashMap<String, HashMap<Integer, Float>>();
        HashMap<Integer, Float> data2 = new HashMap<Integer, Float>();
        data2.put(2000, 10.f);
        data2.put(2001, 5.f);
        data2.put(2002, 2.f);
        data2.put(2003, 1.f);
        country2Data.put("usingwatermanaged", data2);

        allCountryData.add(country1Data);
        allCountryData.add(country2Data);

        HashMap<String, HashMap<Integer, Float>> data = AMortRateSafeWaterComp.getAnalysisObj("DNC", "DNC", "DNC").getAnalyzedDataHelper(allCountryData);
        assertEquals( 10.f, data.get("mortalityunder5").get(2000), 0.01f);
        assertEquals( 20.f, data.get("mortalityunder5").get(2001), 0.01f);
        assertEquals( 30.f, data.get("mortalityunder5").get(2002), 0.01f);
        assertEquals( 40.f, data.get("mortalityunder5").get(2003), 0.01f);
        assertEquals( 10.f, data.get("usingwatermanaged").get(2000), 0.01f);
        assertEquals( 5.f, data.get("usingwatermanaged").get(2001), 0.01f);
        assertEquals( 2.f, data.get("usingwatermanaged").get(2002), 0.01f);
        assertEquals( 1.f, data.get("usingwatermanaged").get(2003), 0.01f);

    }
    
    @Test
    public void test_comparison() {
        // Manually test against pre-computed data
        HashMap<String, HashMap<Integer, Float>> data = AForestAgricultureAreaComp.getAnalysisObj("US", "2012", "2018").getAnalyzedData();
        assertEquals( 33.899723, data.get("forestarea").get(2016), 0.01f);
        assertEquals( 44.303707, data.get("agriculturalland").get(2016), 0.01f);
        
        data = AHealthAccessMortRateComp.getAnalysisObj("US", "2012", "2018").getAnalyzedData();
        assertEquals( 0.0, data.get("problemsaccessinghealthcare").get(2016), 0.01f);
        assertEquals( 5.7, data.get("mortalityinfant").get(2016), 0.01f);
        
        data = AMethaneC02DisasterComp.getAnalysisObj("US", "2012", "2018").getAnalyzedData();
        assertEquals( 15.149885, data.get("co2emissions").get(2016), 0.01f);
        assertEquals( 0.0, data.get("droughtsfloodsextremetemps").get(2016), 0.01f);
 
    }
    
    @Test
    public void test_comparison_analysis_empty() {
        ArrayList<HashMap<String, HashMap<Integer, Float>>> allCountryData = new ArrayList<HashMap<String, HashMap<Integer, Float>>>();
        HashMap<String, HashMap<Integer, Float>> country1Data = new HashMap<String, HashMap<Integer, Float>>();
        HashMap<Integer, Float> data1 = new HashMap<Integer, Float>();
        country1Data.put("mortalityunder5", data1);

        HashMap<String, HashMap<Integer, Float>> country2Data = new HashMap<String, HashMap<Integer, Float>>();
        HashMap<Integer, Float> data2 = new HashMap<Integer, Float>();
        country2Data.put("usingwatermanaged", data2);

        allCountryData.add(country1Data);
        allCountryData.add(country2Data);

        // Ensure there are 
        HashMap<String, HashMap<Integer, Float>> data = AMortRateSafeWaterComp.getAnalysisObj("DNC", "DNC", "DNC").getAnalyzedDataHelper(allCountryData);

    }

    @Test
    public void test_average_analysis() {
        HashMap<String, HashMap<Integer, Float>> country1Data = new HashMap<String, HashMap<Integer, Float>>();

        HashMap<String, HashMap<String,Float>> data = AForestAreaAvg.getAnalysisObj("DNC", "DNC", "DNC").getAnalyzedDataHelper(country1Data);

        // Tests
        HashMap<Integer, Float> data1 = new HashMap<Integer, Float>();
        data1.put(2000, 10.f);
        country1Data.put("forestarea", data1);

        data = AForestAreaAvg.getAnalysisObj("DNC", "DNC", "DNC").getAnalyzedDataHelper(country1Data);
        assertEquals( 10.f, data.get("DNC").get("forestarea"), 0.01f);

        data1.put(2001, 2.f);
        country1Data.put("forestarea", data1);

        data = AForestAreaAvg.getAnalysisObj("DNC", "DNC", "DNC").getAnalyzedDataHelper(country1Data);
        assertEquals( 6.f, data.get("DNC").get("forestarea"), 0.01f);

    }
    
    @Test
    public void test_average() {
        HashMap<String, HashMap<String, Float>> data = AForestAreaAvg.getAnalysisObj("US", "2012", "2018").getAnalyzedData();
        assertEquals( 33.864586, data.get("US").get("forestarea"), 0.01f);

    }
    
    @Test
    public void test_average_analysis_empty() {
        HashMap<String, HashMap<Integer, Float>> country1Data = new HashMap<String, HashMap<Integer, Float>>();

        // Input is empty
        HashMap<String, HashMap<String,Float>> data = AForestAreaAvg.getAnalysisObj("DNC", "DNC", "DNC").getAnalyzedDataHelper(country1Data);
        assert (data.get("DNC").keySet().size() == 0);

    }
}