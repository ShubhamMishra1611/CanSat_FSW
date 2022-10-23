//make a button that says hello on beong clicked
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class FSW extends JFrame implements ActionListener
{
    JButton button;
    JLabel label;
    public static void main(String[] args)
    {
        FSW frame = new FSW();
        frame.setSize(300, 200);
        frame.createGUI();
        frame.setVisible(true);
    }
    private void createGUI()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        button = new JButton("Hello");
        window.add(button);
        button.addActionListener(this);
        label = new JLabel("Click the button");
        window.add(label);
    }
    public void actionPerformed(ActionEvent event)
    {
        label.setText("Hello");
    }
}