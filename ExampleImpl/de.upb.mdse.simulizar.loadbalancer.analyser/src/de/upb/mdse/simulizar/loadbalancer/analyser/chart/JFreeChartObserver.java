package de.upb.mdse.simulizar.loadbalancer.analyser.chart;

import java.awt.Color;
import java.awt.Shape;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.ShapeUtilities;

public class JFreeChartObserver extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1424512882668142687L;
	
	final XYSeriesCollection data = new XYSeriesCollection();

	public JFreeChartObserver(final String title) {
		super(title);
		
        final JFreeChart chart = ChartFactory.createScatterPlot(
                "ProtoDyn",		// title
                "Request",		// x-axis label
                "Response Time [sec]",	// y-axis label
                data,			// data
                PlotOrientation.VERTICAL,
                true,			// create a legend?
                true,			// generate tooltips?
                false			// generate URLs?
         );
        
        Shape cross = ShapeUtilities.createDiagonalCross(3, 1);
        XYPlot xyPlot = (XYPlot) chart.getPlot();
        xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setRangeCrosshairVisible(true);
        XYItemRenderer renderer = xyPlot.getRenderer();
        renderer.setSeriesShape(0, cross);
        renderer.setSeriesPaint(0, Color.red);
        renderer.setSeriesPaint(1, Color.blue);
        renderer.setSeriesPaint(2, Color.green);

		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(1000, 470));
		setContentPane(chartPanel);
	}
	
	public void addSeries(XYSeries series) {
		data.addSeries(series);
	}
	
	public void showObserver() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFreeChartObserver.this.pack();
				RefineryUtilities.centerFrameOnScreen(JFreeChartObserver.this);
				JFreeChartObserver.this.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent event) {
						System.exit(0);
					}
				});
				JFreeChartObserver.this.setVisible(true);
			}

		});
	}

}
