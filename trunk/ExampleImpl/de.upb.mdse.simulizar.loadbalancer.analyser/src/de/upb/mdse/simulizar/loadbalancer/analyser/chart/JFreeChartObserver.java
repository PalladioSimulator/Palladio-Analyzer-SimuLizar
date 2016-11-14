package de.upb.mdse.simulizar.loadbalancer.analyser.chart;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

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

import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class JFreeChartObserver extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1424512882668142687L;
	
	private JPanel contentPane;
	
	final static XYSeriesCollection data = new XYSeriesCollection();
	final static JFreeChart chart =  ChartFactory.createScatterPlot(
            "ProtoDyn Live View",		// title
            "Request [#]",				// x-axis label
            "Response Time [sec]",		// y-axis label
            data,						// data
            PlotOrientation.VERTICAL,	// plot orientation
            true,						// create a legend?
            true,						// generate tooltips?
            false						// generate URLs?
     );
	
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;

	public JFreeChartObserver(final String title) {
		
		// Layout content pane with GridBafLayout
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("window"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{100, 200, 100};
		gbl_contentPane.rowHeights = new int[]{0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		// Configure chart and renderer
		chart.setAntiAlias(true);
        Shape cross = ShapeUtilities.createDiagonalCross(3, 1);
        XYPlot xyPlot = (XYPlot) chart.getPlot();
        xyPlot.setDomainCrosshairVisible(true);
        xyPlot.setRangeCrosshairVisible(true);
        XYItemRenderer xyRenderer = xyPlot.getRenderer();
        xyRenderer.setSeriesShape(0, cross);
        xyRenderer.setSeriesPaint(0, Color.red);
        xyRenderer.setSeriesPaint(1, Color.blue);
        xyRenderer.setSeriesPaint(2, Color.green);

        // Add jFreeChart panel to content pane
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(1000, 500));
		GridBagConstraints gbc_chartPanel = new GridBagConstraints();
		gbc_chartPanel.gridwidth = 3;
		gbc_chartPanel.insets = new Insets(0, 0, 5, 5);
		gbc_chartPanel.fill = GridBagConstraints.BOTH;
		gbc_chartPanel.gridx = 0;
		gbc_chartPanel.gridy = 0;
	    contentPane.add(chartPanel, gbc_chartPanel);
	    
	    // Add label for filename
		JLabel lblFilename = new JLabel("Filename:");
		GridBagConstraints gbc_lblFilename = new GridBagConstraints();
		gbc_lblFilename.insets = new Insets(0, 0, 0, 5);
		gbc_lblFilename.anchor = GridBagConstraints.EAST;
		gbc_lblFilename.gridx = 0;
		gbc_lblFilename.gridy = 1;
		contentPane.add(lblFilename, gbc_lblFilename);
	    
		// Add text area as input field for PDF filename
		final JTextArea txtFilename = new JTextArea("C:/data/output.pdf");
		txtFilename.setBackground(UIManager.getColor("OptionPane.background"));
		GridBagConstraints gbc_txtFilename = new GridBagConstraints();
		gbc_txtFilename.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFilename.insets = new Insets(0, 0, 0, 5);
		gbc_txtFilename.gridx = 1;
		gbc_txtFilename.gridy = 1;
	    contentPane.add(txtFilename, gbc_txtFilename);
	    
	    // Add button for saving chart to PDF
	    JButton button = new JButton("Write to PDF");
	    button.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
				try {
					if (!txtFilename.getText().isEmpty()) {
						writeChartAsPDF(txtFilename.getText(), chartPanel.getWidth(), chartPanel.getHeight());
					} // should throw exception here
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	    });
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 1;
	    contentPane.add(button, gbc_btnNewButton);
		
		setContentPane(contentPane);
	}
	
	public void addSeries(XYSeries series) {
		data.addSeries(series);
	}
	
	public static void writeChartAsPDF(String fileName, int width, int height) throws IOException {
		
		PdfWriter writer = null;
		Rectangle pagesize = new Rectangle(width, height);
		Document document = new Document();
		
		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.addAuthor("University of Paderborn");
			document.addSubject("ProtoDyn Live View");
			document.setPageSize(pagesize);
			document.open();
			PdfContentByte cb = writer.getDirectContent();
			PdfTemplate tp = cb.createTemplate(width, height);
			Graphics2D g2 = tp.createGraphics(width, height, new DefaultFontMapper());
			Rectangle2D r2D = new Rectangle2D.Double(0, 0, width, height);
			chart.draw(g2, r2D, null);
			g2.dispose();
			cb.addTemplate(tp, 0, 0);
		}
		catch(DocumentException de) {
			System.err.println(de.getMessage());
		}
		document.close();
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
