package backupapplication;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import view.TelaBackup;
import view.TelaInicial;


public class BackupApplication {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TelaInicial tela = new TelaInicial();
                tela.setBounds(20, 20, 700, 700);
                tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JScrollPane scroll = new JScrollPane();
                tela.add(scroll);
                tela.setVisible(true);
            }
        });
    }
}
