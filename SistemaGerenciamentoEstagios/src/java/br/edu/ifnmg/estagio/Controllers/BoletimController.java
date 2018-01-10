package br.edu.ifnmg.estagio.Controllers;

import br.edu.ifnmg.estagio.Utilitarios.FileDownLoadView;
import br.edu.ifnmg.estagio.Utilitarios.FileUploadView;
import br.edu.ifnmg.estagio.entidades.Arquivo;
import br.edu.ifnmg.estagio.entidades.BoletimEstagio;
import br.edu.ifnmg.estagio.repositorios.ArquivoRepositorio;
import br.edu.ifnmg.estagio.repositorios.BoletimRepositorio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;

@Named(value = "boletimController")
@ConversationScoped
public class BoletimController extends ControllerGenerico<BoletimEstagio> implements Serializable {

    private boolean renderedArquivo = false;

    public BoletimController() {
        super("listagemBoletins.xhtml", "registrarBoletim.xhtml");
        entidade = new BoletimEstagio();

        entidade.setBoletim(arquivo);
        filtro = new BoletimEstagio();

    }

    @EJB
    private BoletimRepositorio repositorio;

    @EJB
    private ArquivoRepositorio repositorioArquivo;

    @PostConstruct
    public void configurar() {

        setDao(repositorio);

    }

    public String limparfiltrosListagem() {
        filtro = new BoletimEstagio();
        return paginaListagem;
    }

    public boolean isRenderedArquivo() {
        if (entidade.getBoletim() != null) {
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
            // arquivo.setUri("C:\\Users\\Thales\\Desktop\\TCC\\");
//            arquivo.setUri("C:\\glassfish4\\diretorioArquivos\\");
               arquivo.setUri("/var/www/Thales/diretorio_sisGE/");
            entidade.setBoletim(arquivo);

            MensagemSucesso("Sucesso", event.getFile().getFileName() + " anexado com sucesso");

            return true;

        }
        MensagemErro("Erro", "Ocorreu um erro! Não foi anexado.");
        return false;
    }

    public void dowload() {
        FileDownLoadView baixarArquivo = new FileDownLoadView();
        try {
            baixarArquivo.downloadFile(entidade.getBoletim().getNome(), entidade.getBoletim().getUri(), "pdf", FacesContext.getCurrentInstance());
            MensagemSucesso("Sucesso", entidade.getBoletim().getNome() + " baixado com sucesso");
        } catch (Exception ex) {
            MensagemErro("Error", "Nao Existe nenhum boletim anexado");
        }
    }

    public boolean apagarArquivo(Arquivo arqApagado) {
        return repositorioArquivo.Apagar(arqApagado, "/var/www/Thales/diretorio_sisGE/");
    }

}
