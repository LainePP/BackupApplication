package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class BackupFile {
    private String copyFrom;
    private String copyTo;

    public BackupFile(String copyFrom, String copyTo) {
        this.copyFrom = copyFrom;
        this.copyTo = copyTo;
    }
    
    public void copyFiles() throws Exception{
        Stream<Path> arquivos = Files.list(Paths.get(copyFrom));

        arquivos.filter(p -> Files.isRegularFile(p))
                .forEach(p -> {
                    try {
                        InputStream in = Files.newInputStream(p);
                        OutputStream out = Files.newOutputStream(p.getParent().resolve(copyTo).resolve(p.getFileName()));
                        byte buff[] = new byte[1024];
                        int lidos = 0;
                        while ((lidos = in.read(buff)) >= 0) {
                            out.write(buff, 0, lidos);
                        }
                        in.close();
                        out.close();
                        
                    } catch (IOException ex) {
                        throw new UncheckedIOException(ex);
                    }
                }); 
    } 
}
