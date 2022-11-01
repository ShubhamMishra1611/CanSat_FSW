import java.awt.*;
import java.awt.event.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;



public class App  {

    private Frame mainFrame;
    private Button btn_Start;
    private Button btn_Exit;
    private Panel panel;

    //constructor
    public App(){
        prepareGUI();   
    }
    private void prepareGUI() {
        mainFrame = new Frame("Flight software");
        mainFrame.setSize(1000 ,1000);
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
        //adding components to panel
        panel.add(btn_Start);
        panel.add(btn_Exit);
        //adding panel to frame
        mainFrame.add(panel);
        mainFrame.setVisible(true);  
        AddChart();
    }

    
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

    public void AddChart(){
        //chart panel  from Jfreechart
        ChartPanel chartPanel = new ChartPanel(createChart());
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // chartPanel.setMouseWheelEnabled(true);
        panel.add(chartPanel);
    }

    private JFreeChart createChart() {
        // plot a sin wave
        XYSeries series = new XYSeries("sin(x)");
        for (int i = 0; i < 100; i++) {
            series.add(i, Math.sin(i / 10.0));
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart("sin(x)", "x", "sin(x)", dataset, null, false, false, false);
        return chart;
    }








    
    //Main function here
    public static void main(String[] args) throws Exception {
        System.out.println("Starting...");
        App app = new App();
        System.out.println("Started");
        // app.launch();

    }
}
