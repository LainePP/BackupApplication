package controller;

public class BackupFile {
    private String copyFrom;
    private String copyTo;

    public BackupFile(String copyFrom, String copyTo) {
        this.copyFrom = copyFrom;
        this.copyTo = copyTo;
    }
    
    public void copyFiles(){
        Stream<Path> arquivos = Files.list(Paths.get("/home/welyab/Área de Trabalho/test"));

        arquivos.filter(p -> Files.isRegularFile(p))
                .forEach(p -> {
                    try {
                        InputStream in = Files.newInputStream(p);
                        OutputStream out = Files.newOutputStream(p.getParent().resolve("backup").resolve(p.getFileName()));
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
