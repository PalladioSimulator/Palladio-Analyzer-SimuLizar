package de.upb.mdse.simulizar.loadbalancer.analyser.chart;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

public class JFreeChartObserver extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1424512882668142687L;
	
	final XYSeriesCollection data = new XYSeriesCollection();

	public JFreeChartObserver(final String title) {
		super(title);
		final JFreeChart chart = ChartFactory.createXYLineChart(
				"XY Series Demo", "X", "Y", data, PlotOrientation.VERTICAL,
				true, true, false);
		final XYSeries series = new XYSeries("Random Data");
		data.addSeries(series);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(1000, 470));
		setContentPane(chartPanel);
	}

	public void generateTestData() {
		for (int i = 35; i < 60; i += 5) {
			((XYSeries) data.getSeries().get(0)).add(i, i * 6);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
