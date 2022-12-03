
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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

    //All series of dataset
    public static final String delimiter = ",";
    private XYSeries altitude_series=new XYSeries("Altitude");
    private XYSeries velocity_series=new XYSeries("Velocity");
    private XYSeries Some_Sensor_series=new XYSeries("Some_Sensor");

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
        //set icon from assets folder
        mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("assets\\icon.png"));
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

    private void Add_Chart_altitude(){
        String path = "res/Altitude.csv";// To be updated as soon as possible
        this.read(path);
		XYSeriesCollection dataset = new XYSeriesCollection(altitude_series);
        JFreeChart chart = ChartFactory.createXYLineChart("Altitude Sensor Reading", "Altitude", "Package number", dataset, org.jfree.chart.plot.PlotOrientation.VERTICAL, false, false, false);
		//change chart size
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 300));
        altitude_panel.add(chartPanel);
        altitude_panel.add(new ChartPanel(chart), BorderLayout.CENTER);
    }

    private void Add_Chart_Velocity(){
        String path = "res/Velocity.csv";// To be updated as soon as possible
        this.read(path);
        XYSeriesCollection dataset = new XYSeriesCollection(velocity_series);
        JFreeChart chart = ChartFactory.createXYLineChart("Velocity Readings", "Velocity", "Package Number", dataset, org.jfree.chart.plot.PlotOrientation.VERTICAL, false, false, false);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 300));
        velocity_panel.add(chartPanel);
        velocity_panel.add(new ChartPanel(chart), BorderLayout.CENTER);
    }

    private void Add_Chart_Some_reading(){
        // XYSeries series = new XYSeries("Light Sensor Readings");
        String path = "res/Sensor.csv";// To be updated as soon as possible
        this.read(path);
        XYSeriesCollection dataset = new XYSeriesCollection(Some_Sensor_series);
        JFreeChart chart = ChartFactory.createXYLineChart("Some Sensor Readings", "Some Sensor", "Package Number", dataset, org.jfree.chart.plot.PlotOrientation.VERTICAL, false, false, false);
        Some_Sensor_panel.add(new ChartPanel(chart), BorderLayout.CENTER);
    }
    /**
     * 
     * @param csvFile :Path to the csv file of corresponding data
     *                  and add all the points to the correspoding dataseries
     * @return None
     * @exception IOException ioe
     */
    public void read(String csvFile){
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br =new BufferedReader(fr);
            String line ="";
            String[] tempArr;
            while((line = br.readLine())!=null){
                tempArr = line.split(delimiter);
                switch (csvFile.substring(4)) {
                    case "Altitude.csv":
                        altitude_series.add(Double.parseDouble(tempArr[0]), Double.parseDouble(tempArr[1]));
                        break;
                    case "Velocity.csv":
                        // timeSeries.add(getlas, Double.parseDouble(tempArr[1]));
                        velocity_series.add(Double.parseDouble(tempArr[0]), Double.parseDouble(tempArr[1]));
                        break;
                    case "Sensor.csv":
                        Some_Sensor_series.add(Double.parseDouble(tempArr[0]), Double.parseDouble(tempArr[1]));
                        break;
                    default:
                        System.out.println("Un recognised csv file given or Chart/dataseries for this does not exist...Please add such chart/dataseries");
                        break;
                }
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }








    
    //Main function here
    public static void main(String[] args) throws Exception {
        System.out.println("Starting...");
        App app = new App();
        System.out.println("Started");
        // app.launch();

    }
}
