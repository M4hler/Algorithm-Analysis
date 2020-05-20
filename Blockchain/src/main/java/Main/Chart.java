package Main;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Map;

public class Chart<T1 extends Number, T2 extends Number> extends JFrame
{
    private XYSeriesCollection dataSet;
    private String plotTitle;
    private String xAxis;
    private  String yAxis;

    public Chart(String title, Map<T1, T2> data, String plotTitle, String xAxis, String yAxis, String key)
    {
        super(title);
        this.plotTitle = plotTitle;
        this.xAxis = xAxis;
        this.yAxis = yAxis;

        dataSet = createDataSet(data, key);
    }

    public void display()
    {
        // Create chart
        JFreeChart chart = ChartFactory.createScatterPlot(
                plotTitle, xAxis, yAxis, dataSet);

        //Changes background color
        XYPlot plot = (XYPlot)chart.getPlot();
        plot.setBackgroundPaint(new Color(255,228,196));

        //additional options
        XYItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.blue);
        double size = 2.0;
        double delta = size / 2.0;
        Shape shape1 = new Rectangle2D.Double(-delta, -delta, size, size);
        renderer.setSeriesShape(0, shape1);

        if(dataSet.getSeriesCount() >= 2)
        {
            XYItemRenderer renderer2 = plot.getRenderer();
            renderer2.setSeriesPaint(1, Color.green);
            double size2 = 2.0;
            double delta2 = size2 / 2.0;
            Shape shape2 = new Rectangle2D.Double(-delta2, -delta2, size2, size2);
            renderer2.setSeriesShape(1, shape2);

            if(dataSet.getSeriesCount() >= 3)
            {
                XYItemRenderer renderer3 = plot.getRenderer();
                renderer3.setSeriesPaint(2, Color.red);
                double size3 = 2.0;
                double delta3 = size3 / 2.0;
                Shape shape3 = new Rectangle2D.Double(-delta3, -delta3, size3, size3);
                renderer3.setSeriesShape(2, shape3);

                if(dataSet.getSeriesCount() >= 4)
                {
                    XYItemRenderer renderer4 = plot.getRenderer();
                    renderer4.setSeriesPaint(3, Color.black);
                    double size4 = 1.0;
                    double delta4 = size4 / 2.0;
                    Shape shape4 = new Rectangle2D.Double(-delta4, -delta4, size4, size4);
                    renderer4.setSeriesShape(3, shape4);

                    if(dataSet.getSeriesCount() >= 5)
                    {
                        XYItemRenderer renderer5 = plot.getRenderer();
                        renderer5.setSeriesPaint(4, Color.black);
                        double size5 = 1.0;
                        double delta5 = size5 / 2.0;
                        Shape shape5 = new Rectangle2D.Double(-delta5, -delta5, size5, size5);
                        renderer5.setSeriesShape(4, shape5);
                    }
                }
            }
        }

        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);

        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void addBound(double value, String key)
    {
        XYSeries series = new XYSeries(key);

        for(int i = 0; i < 1000; i++)
        {
            series.add(i, value);
        }

        dataSet.addSeries(series);
    }

    public void addDataSet(Map<T1, T2> data, String key)
    {
        XYSeries series = new XYSeries(key);

        for(Map.Entry<T1, T2> entry : data.entrySet())
        {
            series.add(entry.getKey(), entry.getValue());
        }

        dataSet.addSeries(series);
    }

    private XYSeriesCollection createDataSet(Map<T1, T2> data, String key)
    {
        dataSet = new XYSeriesCollection();

        XYSeries series = new XYSeries(key);
        for(Map.Entry<T1, T2> entry : data.entrySet())
        {
            series.add(entry.getKey(), entry.getValue());
        }

        dataSet.addSeries(series);

        return dataSet;
    }
}
