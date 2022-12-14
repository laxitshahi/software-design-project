package project.controller.analysis;

import java.util.ArrayList;
import java.util.HashMap;

import org.jfree.chart.plot.PlotOrientation;
import project.controller.Controller;
import project.view.Charts.ChartCharacteristics.ChartProperties;
import project.view.Charts.ChartCharacteristics.ChartType;
import project.view.Charts.ChartMed;
import org.jfree.chart.plot.PlotOrientation;
import project.view.Invalid.InvalidOption;

/**
 * The base class of all analysis types.
 * Maintains both a list of years and viewers for which this analysis is valid.
 */
abstract public class AnalysisBase<K, T, V> {
    static protected String country;
    static protected String startYear;
    static protected String endYear;
    static protected String title;
    static protected String yAxisTitle;
    /**
     * Stores the years for which analysis cannot be performed, loaded in at initialization time
     */
    protected ArrayList<Integer> invalidYears = new ArrayList<Integer>();

    static protected ChartType chartType;

    public abstract HashMap<K, HashMap<T, V>> getAnalyzedData();

    public void startGen(ChartType chartTypeIn) {
        HashMap<K, HashMap<T, V>> data = getAnalyzedData();
        ChartProperties chartProperties = new ChartProperties(data.keySet().size(), title, "Year", yAxisTitle, PlotOrientation.VERTICAL, true, true, false);

        if (chartTypeIn.BarChart && !chartType.BarChart){
            new InvalidOption("Bar Charts Are Invalid For This Configuration");
        }
        if (chartTypeIn.LineChart && !chartType.LineChart){
            new InvalidOption("Line Charts Are Invalid For This Configuration");
        }
        if (chartTypeIn.PieChart && !chartType.PieChart){
            new InvalidOption("Pie Charts Are Invalid For This Configuration");
        }
        // Mask out non-allowed types
        chartTypeIn.BarChart = chartType.BarChart && chartTypeIn.BarChart;
        chartTypeIn.LineChart = chartType.LineChart && chartTypeIn.LineChart;
        chartTypeIn.PieChart = chartType.PieChart && chartTypeIn.PieChart;

        var chartMed = new ChartMed<>(chartTypeIn, data, chartProperties);
        chartMed.genCharts();
    }


}
