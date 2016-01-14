package view;

import java.util.Vector;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class XYSeriesChart extends ApplicationFrame {

	private static final long serialVersionUID = 504794682564910475L;

	/**
	 * A demonstration application showing an XY series containing a null value.
	 *
	 * @param title  the frame title.
	 */
	public XYSeriesChart(Vector<Double> x, Vector<Double> y, String title) {
		
		super(title);
		final XYSeries series = new XYSeries(title);
		for (int i=0; i < x.size(); i++) {
			series.add(x.get(i), y.get(i));
		}
		final XYSeriesCollection data = new XYSeriesCollection(series);
		final JFreeChart chart = ChartFactory.createXYLineChart(
				title,
				"X", 
				"Y", 
				data,
				PlotOrientation.VERTICAL,
				true,
				true,
				false
				);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);
		
	}
	
	/**
	 * Starting point for the demonstration application.
	 *
	 * @param args  ignored.
	 */
	public static void display(Vector<Double> x, Vector<Double> y, String title) {
		
		final XYSeriesChart chart = new XYSeriesChart(x, y, title);
		chart.pack();
		RefineryUtilities.centerFrameOnScreen(chart);
		chart.setVisible(true);
		
	}

}