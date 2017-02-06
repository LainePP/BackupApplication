package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class TelaInicial extends JFrame{
    public JButton buttonBackup;
    public JButton buttonRestore;
    public JPanel panel;
    public JLabel labelInicial;
    public JLabel labelIcon;

    public TelaInicial(){
        this.setName("Backup de Arquivos");
        this.setLayout(new GridBagLayout());
        
        labelIcon = new JLabel();
        ImageIcon imagePrincipal = new ImageIcon(new ImageIcon("resources/icon.png").getImage().
                getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        labelIcon.setIcon(imagePrincipal);
        labelIcon.setAlignmentX(TOP_ALIGNMENT);
        
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("resources/backup.png").getImage().
                getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        buttonBackup = new JButton("Backup", imageIcon);
        buttonBackup.setHorizontalTextPosition(SwingConstants.RIGHT);
        buttonBackup.setPreferredSize(new Dimension(200, 50));

        ImageIcon imageRestore = new ImageIcon(new ImageIcon("resources/restore.png").getImage().
                getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        buttonRestore = new JButton("Restore", imageRestore);
        buttonRestore.setHorizontalTextPosition(SwingConstants.RIGHT);
        buttonRestore.setPreferredSize(new Dimension(200, 50));
        
        labelInicial = new JLabel("Backup de Arquivos");
        labelInicial.setPreferredSize(new Dimension(300, 100));
        labelInicial.setFont(new Font("Serif", Font.PLAIN, 35));
        labelInicial.setAlignmentX(LEFT_ALIGNMENT);
        
        panel = new JPanel(new GridBagLayout());
        panel.add(labelIcon, genConstraint(0, 1, 4, 4));
        panel.add(labelInicial, genConstraint(2, 5, 3, 3));
        panel.add(buttonBackup, genConstraint(2, 8, 1, 1));
        panel.add(buttonRestore, genConstraint(3, 8, 1, 1));
//        panel.add(buttonBackup,genConstraint(COLUNA,LINHA,QUANTAS CELULAS ELE OCUPA NA LINHA,QUANTAS CELULAS ELE OCUPA NA COLUNA));
        this.add(panel);
        
        addActionListeners();
    }
    
    public void clickedBtnBackup(){
        TelaBackup backup = new TelaBackup("Backup");
        backup.setBounds(20, 20, 800, 800);
        JScrollPane scroll = new JScrollPane();
        backup.add(scroll);
        backup.setVisible(true);
    }
    
    public void clickedBtnRestore(){
        TelaBackup backup = new TelaBackup("Restore");
        backup.setBounds(20, 20, 800, 800);
        JScrollPane scroll = new JScrollPane();
        backup.add(scroll);
        backup.setVisible(true);
    }
    
     public GridBagConstraints genConstraint(int x, int y, int w, int h){
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = w;
        c.gridheight = h;
        c.insets = new Insets(10, 10, 10, 10);
        return c;
     }
     
     public void addActionListeners(){
         
        buttonBackup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickedBtnBackup();
            }
        });
        buttonRestore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                clickedBtnRestore();
            }
        });
     }
}
