package view;

import controller.BackupController;
import static java.awt.Component.LEFT_ALIGNMENT;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TelaBackup extends JFrame{
    public JPanel panel;
    public JLabel lblIcon;
    public JLabel lblTitulo;
    public JLabel labelFrom;   
    public JTextField txtFrom;
    public JLabel labelTo;
    public JTextField txtTo; 
    public JButton buttonFrom;
    public JButton buttonTo;
    public JButton btnSalvar;
    public JButton btnCancelar;
    
    private String enderecoFrom = "";
    private String enderecoTo = "";
    private String titulo = "";
    
    private BackupController controller;

    public TelaBackup(String Titulo) {
        titulo = Titulo;
        controller = new BackupController();
        panel = new JPanel(new GridBagLayout());
        this.setLayout(new GridBagLayout());
        
        lblIcon = new JLabel();
        ImageIcon imagePrincipal = new ImageIcon(new ImageIcon("resources/icon.png").getImage().
                getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        lblIcon.setIcon(imagePrincipal);
        lblIcon.setAlignmentX(TOP_ALIGNMENT);
        
        lblTitulo = new JLabel(titulo);
        lblTitulo.setPreferredSize(new Dimension(200, 50));
        lblTitulo.setFont(new Font("Serif", Font.PLAIN, 35));
        lblTitulo.setAlignmentX(LEFT_ALIGNMENT);
        
        labelFrom = new JLabel("Endereço de Origem");
        labelFrom.setFont(new Font("Serif", Font.PLAIN, 14));
        txtFrom = new JTextField(30);
        labelTo = new JLabel("Endereço de Destino");
        labelTo.setFont(new Font("Serif", Font.PLAIN, 14));
        txtTo = new JTextField(30);

        ImageIcon imageFile = new ImageIcon(new ImageIcon("resources/folder.png").getImage().
                getScaledInstance(20, 20, Image.SCALE_DEFAULT));      
        buttonFrom = new JButton("Abrir", imageFile);
        buttonFrom.setHorizontalTextPosition(SwingConstants.RIGHT);
        buttonFrom.setPreferredSize(new Dimension(100, 20));
        buttonTo = new JButton("Abrir", imageFile);
        buttonTo.setHorizontalTextPosition(SwingConstants.RIGHT);
        buttonTo.setPreferredSize(new Dimension(100, 20));
        
        btnSalvar = new JButton("Fazer " + titulo);
        btnSalvar.setPreferredSize(new Dimension(150, 20));
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setPreferredSize(new Dimension(150, 20));
        
        addActionListeners();
        if(titulo == "Restore"){
            txtTo.setEnabled(false);
            buttonTo.setEnabled(false);
        }
        
        panel.add(lblIcon, genConstraint(1, 1, 4, 4));
        panel.add(lblTitulo, genConstraint(1, 5, 3, 3));
        panel.add(labelFrom, genConstraint(0, 8, 1, 1));
        panel.add(txtFrom, genConstraint(1, 8, 25, 1));
        panel.add(buttonFrom, genConstraint(28, 8, 1, 1));
        panel.add(labelTo, genConstraint(0, 9, 1, 1));
        panel.add(txtTo, genConstraint(1, 9, 25, 1));
        panel.add(buttonTo, genConstraint(28, 9, 1, 1));
        panel.add(btnSalvar, genConstraint(1, 10, 1, 1));
        panel.add(btnCancelar, genConstraint(2, 10, 1, 1));
        
        this.add(panel);
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
        buttonFrom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                clickedButtonFrom();
            }
        });
        buttonTo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                clickedButtonTo();
            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                limparBackup();
            }
        });
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(enderecoFrom == "" || (enderecoTo == "" && titulo == "Backup")){
                    JOptionPane.showMessageDialog(null, "Preencha o diretório origem e o diretório destino");
                    return;
                }
                if(titulo == "Backup"){
                    controller.setFrom(enderecoFrom);
                    controller.setTo(enderecoTo + "/backup");
                    try {
                        if(controller.copiarArquivos()){ 
                            JOptionPane.showMessageDialog(null, "Backup realizado com sucesso!");
                        }else{
                            JOptionPane.showMessageDialog(null, "Desculpe! Algo deu errado no backup. Por favor, tente novamente.");
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(TelaBackup.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    File f = new File(enderecoFrom + "/backup.txt");
                    if(f.exists()){
                        FileReader arq;
                        try {
                            arq = new FileReader(f);
                            BufferedReader lerArq = new BufferedReader(arq);
                        
                            String linha = lerArq.readLine();
                            String endereco = "";
                            while (linha != null) {
                                endereco = linha.substring(linha.indexOf("/"), linha.indexOf("#")); 
                                linha = lerArq.readLine();
                            }

                            arq.close();
                            if(endereco == ""){
                                JOptionPane.showMessageDialog(null, "Nenhum backup foi realizado nesse diretorio antes!");
                            }else{
                                controller.setFrom(enderecoFrom);
                                controller.setTo(endereco + "/restore");
                                if(controller.copiarArquivos()){
                                    JOptionPane.showMessageDialog(null, "Restore realizado com sucesso!");
                                }else{
                                    JOptionPane.showMessageDialog(null, "Desculpe! Algo deu errado no restore.");
                                }
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Nenhum backup realizado nesse diretorio!");
                        }   
                    }else{
                        JOptionPane.showMessageDialog(null, "Escolha um diretorio que armazena um backup!");
                    }
                    
                }
            }
        });
    }
    
    public void clickedButtonFrom(){
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
        f.showSaveDialog(null);
        
//      Just to make sure that the user will choose a path
        do{
            try {
                enderecoFrom = f.getSelectedFile().toString();
                txtFrom.setText(enderecoFrom);
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Escolha um diretório:");
                f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
                f.showSaveDialog(null);
                enderecoFrom = f.getSelectedFile().toString();
                txtFrom.setText(enderecoFrom);
            }
        }while(f.getSelectedFile() == null);
    }
    
    public void clickedButtonTo(){
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
        f.showSaveDialog(null);
        
//      Just to make sure that the user will choose a path
        do{
             try {
                enderecoTo = f.getSelectedFile().toString();
                txtTo.setText(enderecoTo);
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Escolha um diretório:");
                f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
                f.showSaveDialog(null);
                enderecoTo = f.getSelectedFile().toString();
                txtTo.setText(enderecoTo);
            }
        }while(f.getSelectedFile() == null);
    }
    
    public void limparBackup(){
        txtFrom.setText("");
        txtTo.setText("");
        enderecoFrom = "";
        enderecoTo = "";
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
