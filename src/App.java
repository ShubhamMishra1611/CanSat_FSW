
import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;



public class App  {

    // private Frame mainFrame;
    private JFrame mainFrame;
    private Button btn_Start;
    private Button btn_Exit;
    private Panel panel;
    private JPanel altitude_panel;
    private JPanel velocity_panel;
    private JPanel Some_Sensor_panel;

    //constructor
    public App(){
        prepareGUI();   
    }
    private void prepareGUI() {
        mainFrame = new JFrame("Flight software");
        // mainFrame.setSize(1080 ,900);
        //open main frame in maximized mode
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setLayout(new FlowLayout());
        //set icon
        mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\user\\Desktop\\icon.png"));
        //mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }        
        }); 
        //btn_start   
        btn_Start = new Button("Start");
        btn_Start.setActionCommand("Start");
        btn_Start.addActionListener(new ActionListeners()); 
        //btn_exit
        btn_Exit = new Button("Exit");
        btn_Exit.setActionCommand("Exit");
        btn_Exit.addActionListener(new ActionListeners());
        //panel
        panel = new Panel();
        panel.setPreferredSize(new Dimension(600, 300));
        //Altitute panel
        altitude_panel = new JPanel();
        altitude_panel.setBackground(Color.red);
        altitude_panel.setPreferredSize(new Dimension(600, 300));
        //velocity panel
        velocity_panel = new JPanel();
        velocity_panel.setBackground(Color.blue);
        velocity_panel.setPreferredSize(new Dimension(600, 300));
        //Some_Sensor_panel
        Some_Sensor_panel = new JPanel();
        Some_Sensor_panel.setBackground(Color.green);
        Some_Sensor_panel.setPreferredSize(new Dimension(600, 300));
        //adding components to panel
        panel.add(btn_Start);
        panel.add(btn_Exit);
        panel.setBackground(Color.white);
        //adding panel to frame
        mainFrame.add(panel);
        mainFrame.add(altitude_panel);
        mainFrame.add(velocity_panel);
        mainFrame.add(Some_Sensor_panel);
        mainFrame.getContentPane().setBackground(Color.DARK_GRAY);
        mainFrame.setVisible(true);  
        // AddChart();
        Add_Chart_altitude();
        Add_Chart_Velocity();
        Add_Chart_Some_reading();
        //refresh frame
        mainFrame.revalidate();
    }

    //ActionListener class
    class ActionListeners implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();  
            if( command.equals( "Start" ))  {
                System.out.println("Start button clicked.");
                btn_Start.setActionCommand("Stop");
                btn_Start.setLabel("Stop");
            }
            else if( command.equals( "Stop" ) )  {
                System.out.println("Stop button clicked.");
                btn_Start.setActionCommand("Start");
                btn_Start.setLabel("Start");
            }
            else if( command.equals( "Exit" ) )  {
                System.exit(0);
            }
            
        }
    }

    // public void AddChart(){
    //     //chart panel  from Jfreechart
    //     ChartPanel chartPanel = new ChartPanel(createChart());
    //     chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
    //     // chartPanel.setMouseWheelEnabled(true);
    //     panel.add(chartPanel);
    // }

    private void Add_Chart_altitude(){
        XYSeries series = new XYSeries("Light Sensor Readings");
        series.add(1, 1);
        series.add(1, 2);
        series.add(2, 1);
        series.add(3, 9);
        series.add(4, 10);
		XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart("Light Sensor Readings", "Time (seconds)", "Sensor Reading", dataset, org.jfree.chart.plot.PlotOrientation.HORIZONTAL, false, false, false);
		//change chart size
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        altitude_panel.add(chartPanel);
        altitude_panel.add(new ChartPanel(chart), BorderLayout.CENTER);
    }

    private void Add_Chart_Velocity(){
        XYSeries series = new XYSeries("Velocity Readings");
        series.add(5, 1);
        series.add(4, 2);
        series.add(3, 1);
        series.add(2, 9);
        series.add(1, 10);
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart("Velocity Readings", "Time (seconds)", "Sensor Reading", dataset, org.jfree.chart.plot.PlotOrientation.HORIZONTAL, false, false, false);
        velocity_panel.add(new ChartPanel(chart), BorderLayout.CENTER);
    }

    private void Add_Chart_Some_reading(){
        XYSeries series = new XYSeries("sin(x)");
        for (int i = 0; i < 100; i++) {
            series.add(i, Math.sin(i / 10.0));
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart("Some Sensor Readings", "Time (seconds)", "Sensor Reading", dataset, org.jfree.chart.plot.PlotOrientation.HORIZONTAL, false, false, false);
        Some_Sensor_panel.add(new ChartPanel(chart), BorderLayout.CENTER);
    }
		

    // private JFreeChart createChart() {
    //     // plot a sin wave
    //     XYSeries series = new XYSeries("sin(x)");
    //     for (int i = 0; i < 100; i++) {
    //         series.add(i, Math.sin(i / 10.0));
    //     }
    //     XYSeriesCollection dataset = new XYSeriesCollection(series);
    //     JFreeChart chart = ChartFactory.createXYLineChart("sin(x)", "x", "sin(x)", dataset, null, false, false, false);
    //     return chart;
    // }








    
    //Main function here
    public static void main(String[] args) throws Exception {
        System.out.println("Starting...");
        App app = new App();
        System.out.println("Started");
        // app.launch();

    }
}
