package view;

import java.util.Collection;
import java.util.Vector;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * The Class XYSeriesChart.
 */
public class XYSeriesChart extends ApplicationFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 504794682564910475L;

	/**
	 * Instantiates a new XY series chart.
	 *
	 * @param x the x
	 * @param y the y
	 * @param title the title
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
	 * Display.
	 *
	 * @param x the x
	 * @param y the y
	 * @param title the title
	 */
	public static void display(Vector<Double> x, Vector<Double> y, String title) {
		
		final XYSeriesChart chart = new XYSeriesChart(x, y, title);
		chart.pack();
		RefineryUtilities.centerFrameOnScreen(chart);
		chart.setVisible(true);
		
	}

	/**
	 * Plot.
	 *
	 * @param series the series
	 * @param title the title
	 */
	public static void plot(Collection<Double> series, String title) {
		Vector<Double> x = new Vector<Double>();
		Vector<Double> y = new Vector<Double>();
		int i = 1;
		for (Double d : series) {
			x.add(i+0.0);
			y.add(d);
			i++;
		}
		XYSeriesChart.display(x, y, title);
	}
	
}