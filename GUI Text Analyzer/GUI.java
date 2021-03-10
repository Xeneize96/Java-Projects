import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GUI implements ActionListener{
    private WordFrequencies wordFrequencies;
    private JFrame frame;
    private JButton analyzeTextButton;
    private JTextField inputFileName;
    private JTextField outputFileName;
    
    public GUI(){
        wordFrequencies=new WordFrequencies();
        frame=new JFrame();
        Container c=frame.getContentPane();
        c.setLayout(null);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setResizable(false);
        frame.setLocation(400, 100);
        frame.setVisible(true);
        
        JLabel inputLabel=new JLabel("Input File Name : ");
        JLabel outputLabel=new JLabel("Output File Name : ");

        analyzeTextButton = new JButton ("Analyze Text");
        inputFileName = new JTextField(20);
        outputFileName = new JTextField(20);
        analyzeTextButton.addActionListener(this);
        
        inputLabel.setBounds(30,120,150,25);
        outputLabel.setBounds(30,160,150,25);
        inputFileName.setBounds(200,120,170,25);
        outputFileName.setBounds(200,160,170,25);
        analyzeTextButton.setBounds(100,200,150,25);
        
        c.add(analyzeTextButton);
        c.add(inputLabel);
        c.add(inputFileName);
        c.add(outputLabel);
        c.add(outputFileName);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLocation(400, 100);
        frame.setResizable(false);
        frame.setVisible(true);
    }    
    
    public static void main(String args[]){
        GUI gui = new GUI();
    }

    public void actionPerformed(ActionEvent event){
        if(inputFileName.getText().toString().equals("") || outputFileName.getText().toString().equals("")){
            JOptionPane.showMessageDialog(null,"Please enter both file names");
        }else{
            String message = wordFrequencies.countFrequency(inputFileName.getText().toString(), outputFileName.getText().toString());
            JOptionPane.showMessageDialog(null,message);
        }      
    }

}
