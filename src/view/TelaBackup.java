package view;

import java.awt.Desktop;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaBackup extends JFrame{
    JPanel panel;

    public TelaBackup() {
        panel = new JPanel(new GridLayout(2,3));
        this.setLayout(new GridLayout(1,2));
        
        JLabel labelFrom = new JLabel("Endereço de Origem");
        JTextField txtFrom = new JTextField();
        JLabel labelTo = new JLabel("Endereço de Destino");
        JTextField txtTo = new JTextField();
        JButton buttonFrom = new JButton();
        JButton buttonTo = new JButton();
        
        panel.add(labelFrom);
        panel.add(txtFrom);
        panel.add(buttonFrom);
        panel.add(labelTo);
        panel.add(txtTo);
        panel.add(buttonTo);
        
        this.add(panel);    
//                Desktop.getDesktop().open(new File("C:\\folder"));

    }

    
}
