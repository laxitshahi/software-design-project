package project.view;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jfree.chart.ChartPanel;

import project.Charts.ChartCharacteristics.ChartType;
import project.Charts.ChartTests.ChartTest;

public class WelcomePage extends JFrame implements ActionListener {
	JLabel welcomeLabel = new JLabel();
	private static WelcomePage welcomePage = new WelcomePage();

	JButton genBarButton = new JButton("Generate Bar Chart");
	JButton genLineButton = new JButton("Generate Line Chart");
	JButton genPieButton = new JButton("Generate Pie Chart");

	private WelcomePage() {
		welcomeLabel.setBounds(0, 0, 200, 25);
		welcomeLabel.setFont(new Font(null, Font.PLAIN, 20));
		welcomeLabel.setText("Welcome!");
		
		add(welcomeLabel);
		addButtons();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 400);
		setLayout(new java.awt.BorderLayout());
		setVisible(true);
	}

	/**
	 * adds buttons to generate charts
	 */
	public void addButtons() {
		genBarButton.setBounds(0, 50, 100, 25);
        genBarButton.addActionListener(this);
        genBarButton.setFocusable(false);

		genLineButton.setBounds(0, 100, 100, 25);
        genLineButton.addActionListener(this);
        genLineButton.setFocusable(false);

		genPieButton.setBounds(0, 150, 100, 25);
        genPieButton.addActionListener(this);
        genPieButton.setFocusable(false);

		add(genBarButton);
		add(genLineButton);
		add(genPieButton);

	}

	@Override
    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == genBarButton) {
			ChartTest ct = new ChartTest();
			ct.testCharts(new ChartType(true, false, false));
		} else if (e.getSource() == genLineButton) {
			ChartTest ct = new ChartTest();
			ct.testCharts(new ChartType(false, true, false));
		} else if (e.getSource() == genPieButton) {
			ChartTest ct = new ChartTest();
			ct.testCharts(new ChartType(false, false, true));
		} 
	}

	public void addChart(ChartPanel cp, String borderLayout) {
		add(cp, borderLayout);
		// pack();
		// validate();
	}

	public static WelcomePage getInstance() {
		return welcomePage;
	}
}