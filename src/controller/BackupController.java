package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import model.BackupFile;

public class BackupController {
    
    private String from;
    private String to;
    private BackupFile backupModel;

    public BackupController() {
    }
    
    public BackupController(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }
    
    public boolean copiarArquivos() throws IOException{
        if(this.from == "" || this.to == ""){
            return false;
        }else{
            backupModel = new BackupFile(from, to);
            new File(to).mkdir();
            FileWriter arq = new FileWriter(to + "/backup.txt");
            PrintWriter gravarArq = new PrintWriter(arq);
 
            gravarArq.printf("from: " + from + "#");
            arq.close();
            try {
                backupModel.copyFiles();
            } catch (Exception ex) {
                return false;
            }
        return true;
        }   
    }
}
