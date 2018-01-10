package br.edu.ifnmg.estagio.Controllers;

import br.edu.ifnmg.estagio.Utilitarios.FileDownLoadView;
import br.edu.ifnmg.estagio.Utilitarios.FileUploadView;
import br.edu.ifnmg.estagio.entidades.Arquivo;
import br.edu.ifnmg.estagio.entidades.Candidatura;
import br.edu.ifnmg.estagio.entidades.Pessoa;
import br.edu.ifnmg.estagio.entidades.vagaEstagio;
import br.edu.ifnmg.estagio.repositorios.AlunoRepositorio;
import br.edu.ifnmg.estagio.repositorios.ArquivoRepositorio;
import br.edu.ifnmg.estagio.repositorios.CandidaturaRepositorio;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

@Named(value = "candidaturaController")
@SessionScoped
public class CandidaturaController extends ControllerGenerico<Candidatura> implements Serializable {

    public CandidaturaController() {
        super("listagemCandidaturas.xhtml", "editarCandidatura.xhtml");
        entidade = new Candidatura();
        filtro = new Candidatura();
    }

    @EJB
    private CandidaturaRepositorio repositorio;
    @EJB
    private AlunoRepositorio daoAluno;

    @EJB
    private ArquivoRepositorio repositorioArquivo;

    private vagaEstagio vaga;
    private Pessoa candidato;

    @PostConstruct
    public void configurar() {
        setDao(repositorio);
    }

    public String limparfiltrosListagem() {
        filtro = new Candidatura();
        return paginaListagem;
    }

    public vagaEstagio getVaga() {
        return vaga;
    }

    public void setVaga(vagaEstagio vaga) {
        this.vaga = vaga;
    }

    public Pessoa getCandidato() {
        return candidato;
    }

    public void setCandidato(Pessoa candidato) {
        this.candidato = candidato;
    }

    public void candidatarVaga() {
        if (candidato.getAcesso().contains("aluno/index.xhtml")) {

            Candidatura candidaturaTemp = null;
            try {
                filtro.setCandidato(daoAluno.Abrir(candidato.getId()));
                filtro.setVaga(vaga);

                candidaturaTemp = repositorio.BuscarCandidatura(filtro);
            } catch (Exception ex) {

            }

            if (candidaturaTemp == null) {

                if (candidato != null) {
                    try {

                        Date dataCandidatura = new Date();
                        entidade.setDataCandidatura(dataCandidatura);
                        entidade.setCandidato(daoAluno.Abrir(candidato.getId()));
                        entidade.setVaga(vaga);
                        repositorio.Salvar(entidade);
                        MensagemSucesso("Sucesso", "Sua inscrição foi salva");

                        FacesContext.getCurrentInstance().getExternalContext()
                                .redirect("listagemVagasEstagios.xhtml");

                    } catch (Exception ex) {
                        MensagemErro("erro", "Consulte o administrador");

                    }

                } else {
                    MensagemErro("erro", "Consulte o administrador");
                }
            } else {
                MensagemErro("Atenção", "Você ja se candidatou a essa vaga !");

                try {
                    FacesContext.getCurrentInstance().getExternalContext()
                            .redirect("listagemVagasEstagios.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(CandidaturaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            MensagemErro("Atenção", "Somente Alunos podem se candidatar a vagas de Estagio.");
        }

    }

    public void cancelarCandidatura() {
        try {
            if (repositorio.Apagar(entidade)) {
                MensagemSucesso("Sucesso", "Sua inscrição na vaga foi cancelada!");
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect("MinhasVagas.xhtml");
            } else {
                MensagemErro("erro", "Consulte o administrador");
            }
        } catch (Exception ex) {
            MensagemErro("erro", "Consulte o administrador");
        }
    }

    public void redirecionar() {

        try {
            if (candidato.getId() != null) {

                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect("CandidatarVaga.xhtml");

            } else {
                RequestContext context = RequestContext.getCurrentInstance();

                context.execute("PF('dialogLogin').show();");

            }
        } catch (Exception e) {
            try {
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect("listagemVagasEstagios.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(CandidaturaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    Arquivo arquivo;

    public boolean AnexarCurriculo(FileUploadEvent event) {
        try {
            FileUploadView anexarArquivo = new FileUploadView();

            String fileNameAnexado = anexarArquivo.upload(event);

            if (fileNameAnexado != null) {

                arquivo = new Arquivo();
                arquivo.setNome(fileNameAnexado);
//                arquivo.setUri("C:\\glassfish4\\diretorioArquivos\\");
                arquivo.setUri("/var/www/Thales/diretorio_sisGE/");
                entidade.setCurriculo(arquivo);
                // "/var/www/Thales/diretorio_sisGE/"));

                MensagemSucesso("Sucesso", event.getFile().getFileName() + " anexado com sucesso");
                arquivo = null;
                return true;

            }
        } catch (Exception ex) {
            MensagemErro("Error", "Consulte o administrador");
        }

        return false;
    }

    public void dowload() {
        FileDownLoadView baixarArquivo = new FileDownLoadView();
        try {
            baixarArquivo.downloadFile(entidade.getCurriculo().getNome(), entidade.getCurriculo().getUri(), "pdf", FacesContext.getCurrentInstance());
            MensagemSucesso("Sucesso", entidade.getCurriculo().getNome() + " baixado com sucesso");
        } catch (Exception ex) {
            MensagemErro("Error", "Nao Existe nenhum curriculo anexado");
        }
    }

    public void setarFiltroCandidato(Long idCandidato) {
        filtro.setCandidato(daoAluno.Abrir(idCandidato));
    }

    public void classficarCandidato() {
        entidade.setSituacao("Classificado");
        RequestContext context = RequestContext.getCurrentInstance();

        context.execute("PF('eventDialogClassificar').show();");
    }

    public void desclassficarCandidato() {
        entidade.setSituacao("Desclassificado");
        entidade.setEtapa("");

        RequestContext context = RequestContext.getCurrentInstance();

        context.execute("PF('eventDialogDesClassificar').show();");
    }

    public void resultadoCandidatura(Candidatura item) {
        entidade = item;
        RequestContext context = RequestContext.getCurrentInstance();

        if (entidade.getSituacao().equals("Classificado")) {
            context.execute("PF('eventDialogClassificar').show();");
        } else if (entidade.getSituacao().equals("Desclassificado")) {
            context.execute("PF('eventDialogDesClassificar').show();");
        } else if (entidade.getSituacao().equals("Em Aberto")) {
            context.execute("PF('eventDialogEmAberto').show();");
        }

    }

    public void salvarCandidatura() {
        try {
            if (repositorio.Salvar(entidade)) {
                MensagemSucesso("Sucesso", "Registro Salvo com sucesso");
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect("CandidatosVagas.xhtml");
            } else {
                MensagemErro("erro", "Consulte o administrador");
            }
        } catch (Exception ex) {
            MensagemErro("erro", "Consulte o administrador");
        }
    }

}
