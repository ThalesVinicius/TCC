package br.edu.ifnmg.estagio.Utilitarios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

public class FileUploadView {

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String upload(FileUploadEvent event) {
        file = event.getFile();
        String nomeArquivoAnexado = null;

        if (file != null) {

//            File file1 = new File("C:\\glassfish4\\diretorioArquivos\\", file.getFileName());
            // File file1 = new File("C:\\Users\\Thales\\Desktop\\TCC\\", file.getFileName());
            File file1 = new File("/home/ifnmg/sge/diretorioArquivos/", file.getFileName());
            int controlador = 0;

            if (file1.exists()) {

                while (file1.exists()) {
                    controlador = controlador + 1;

//                    File fileTemp = new File("C:\\glassfish4\\diretorioArquivos\\", controlador + file.getFileName());
                    File fileTemp = new File("/home/ifnmg/sge/diretorioArquivos/", controlador + file.getFileName());
                    file1 = fileTemp;
                }

            }

            try {
                FileOutputStream fos = new FileOutputStream(file1);
                fos.write(event.getFile().getContents());

                fos.close();
                //  file = (UploadedFile) file1;

                nomeArquivoAnexado = file1.getName();
            } catch (FileNotFoundException e) {
                e.printStackTrace();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }

        }
        return nomeArquivoAnexado;
    }

}
