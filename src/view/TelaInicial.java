package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TelaInicial extends JFrame{
    
    public GridBagConstraints genConstraint(int x, int y, int w, int h){
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = w;
        c.gridheight = h;
        c.insets = new Insets(10, 10, 10, 10);
        return c;
     }

    public TelaInicial(){
        this.setName("Backup de Arquivos");
        this.setLayout(new GridBagLayout());
        
        JButton buttonBackup = new JButton();
        buttonBackup.setText("Backup");
//        buttonBackup.setIcon();
        JButton buttonRestore = new JButton();
        buttonRestore.setText("Restore");
        
        JLabel labelInicial = new JLabel("Backup de Arquivos");
        
        JPanel panel = new JPanel(new GridLayout());
        panel.add(labelInicial);
        panel.add(buttonBackup);
        panel.add(buttonRestore);
        this.add(panel);
    }
}
