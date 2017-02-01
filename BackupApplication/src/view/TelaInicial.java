/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TelaInicial extends JFrame{

    public TelaInicial() throws HeadlessException {
        this.setName("Backup");
        this.setLayout(new GridLayout());
        
        JButton buttonBackup = new JButton();
        JButton buttonRestore = new JButton();
        
        JLabel labelInicial = new JLabel(image);
        
        
    }
    
    
}
