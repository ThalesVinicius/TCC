package br.edu.ifnmg.estagio.Controllers;

import br.edu.ifnmg.estagio.Utilitarios.FileDownLoadView;
import br.edu.ifnmg.estagio.Utilitarios.FileUploadView;
import br.edu.ifnmg.estagio.entidades.Arquivo;
import br.edu.ifnmg.estagio.entidades.Documentos;
import br.edu.ifnmg.estagio.repositorios.DocumentoRepositorio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;

@Named(value = "documentoController")
@ConversationScoped
public class DocumentoController extends ControllerGenerico<Documentos> implements Serializable {

    private boolean renderedArquivo = false;

    public DocumentoController() {
        super("editarAreaPublica.xhtml", "editarDocumento.xhtml");
        entidade = new Documentos();
        filtro = new Documentos();
    }

    @EJB
    private DocumentoRepositorio repositorio;

    @PostConstruct
    public void configurar() {
        setDao(repositorio);
    }

    public String limparfiltrosListagem() {
        filtro = new Documentos();
        return "ListagemDocumentos.xhtml";
    }

    public String filtrarListagem() {
        return "ListagemDocumentos.xhtml";
    }

    public boolean isRenderedArquivo() {
        if (entidade.getDocumento() != null) {
            return true;
        } else {
            return false;
        }
    }

    public void setRenderedArquivo(boolean renderedArquivo) {
        this.renderedArquivo = renderedArquivo;
    }

    Arquivo arquivo;

    public boolean Anexar(FileUploadEvent event) {
        FileUploadView anexarArquivo = new FileUploadView();

        String fileNameAnexado = anexarArquivo.upload(event);

        if (fileNameAnexado != null) {

            arquivo = new Arquivo();
            arquivo.setNome(fileNameAnexado);
            //  C:\\Users\\Thales\\Desktop\\TCC\\
//           arquivo.setUri("C:\\glassfish4\\diretorioArquivos\\");
           arquivo.setUri("/var/www/Thales/diretorio_sisGE/");

            entidade.setDocumento(arquivo);

            MensagemSucesso("Sucesso", event.getFile().getFileName() + " anexado com sucesso");
            arquivo = null;
            return true;

        }

        return false;
    }

    public void dowload() {
        FileDownLoadView baixarArquivo = new FileDownLoadView();
        try {
//            System.err.println("teste1" + entidade.getNome() + entidade.getDescricao() + entidade.getTipo());
//            System.err.println("teste2" + entidade.getDocumento().getNome() + entidade.getDocumento().getUri());

            baixarArquivo.downloadFile(entidade.getDocumento().getNome(), entidade.getDocumento().getUri(), "pdf", FacesContext.getCurrentInstance());
            MensagemSucesso("Sucesso", entidade.getDocumento().getNome() + " baixado com sucesso");
        } catch (Exception ex) {
            MensagemErro("Error", "Nao Existe nenhum documento anexado");
        }
    }

    public void setarFiltro(String tipo) {
        filtro.setTipo(tipo);

    }

}
