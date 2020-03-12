package org.lider;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import java.util.Map;

public class Histogram extends ApplicationFrame
{
//    private double[][] data;
    private Map<Integer, Integer> dataMap;

//    public Histogram(String title, double[][] data)
//    {
//        super(title);
//        this.data = data;
//        IntervalXYDataset dataset = createDatasetTable();
//        JFreeChart chart = createChart(dataset);
//        final ChartPanel chartPanel = new ChartPanel(chart);
//        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
//        setContentPane(chartPanel);
//    }

    public Histogram(String title, Map<Integer, Integer> dataMap)
    {
        super(title);
        this.dataMap = dataMap;
        IntervalXYDataset dataset = createDataSetMap();
        JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }

//    private IntervalXYDataset createDatasetTable()
//    {
//        final XYSeries series = new XYSeries("L");
//        for (double[] pair: data) {
//            series.add(pair[0], pair[1]);
//        }
//        final XYSeriesCollection dataset = new XYSeriesCollection(series);
//        return dataset;
//    }

    private IntervalXYDataset createDataSetMap()
    {
        final XYSeries series = new XYSeries("L");
        for (Map.Entry<Integer, Integer> entry: dataMap.entrySet()) {
            series.add(entry.getKey(), entry.getValue());
        }

        return new XYSeriesCollection(series);
    }

    private JFreeChart createChart(IntervalXYDataset dataSet)
    {
        return ChartFactory.createXYBarChart(
                "Histogram",
                "Number of rounds",
                false,
                "Amount",
                dataSet,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
    }
}
