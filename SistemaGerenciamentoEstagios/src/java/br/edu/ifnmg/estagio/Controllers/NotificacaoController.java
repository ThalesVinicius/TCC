package br.edu.ifnmg.estagio.Controllers;

import br.edu.ifnmg.estagio.Infraestrutura.AutenticacaoService;
import br.edu.ifnmg.estagio.entidades.Aluno;
import br.edu.ifnmg.estagio.entidades.Empresa;
import br.edu.ifnmg.estagio.entidades.Representante;
import br.edu.ifnmg.estagio.entidades.vagaEstagio;
import br.edu.ifnmg.estagio.repositorios.AlunoRepositorio;
import br.edu.ifnmg.estagio.repositorios.EmpresaRepositorio;
import br.edu.ifnmg.estagio.repositorios.ResponsavelRepositorio;
import br.edu.ifnmg.estagio.repositorios.vagaEstagioRepositorio;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import javax.inject.Named;
import org.primefaces.context.RequestContext;

@Named(value = "notificacaoController")
@ViewScoped
public class NotificacaoController implements Serializable {

    Aluno filtroAluno;

    vagaEstagio filtroVagas;

    String QuantidadeNotificacoes = "0", QuantidadeCadastrosAlunos = "0",
            QuantidadeCadastrosEmpresas = "0",
            QuantidadeVagas = "0";

    @Inject
    AutenticacaoService autenticacao;

    public NotificacaoController() {
        filtroAluno = new Aluno();

        filtroVagas = new vagaEstagio();
    }

    @EJB
    private AlunoRepositorio repositorioAluno;

    @EJB
    private EmpresaRepositorio repositorioEmpresa;

    @EJB
    private ResponsavelRepositorio representanteRepositorio;

    @EJB
    private vagaEstagioRepositorio vagasRepositorio;

    ////////////////////////////////////////////////////////////
    public List<Aluno> getListagemAlunos() {
        return repositorioAluno.Buscar(filtroAluno);
    }

    public List<Empresa> getListagemEmpresas() {
        return repositorioEmpresa.BuscarEmpresasNaoValidadas(false);
    }

    public List<vagaEstagio> getListagemVagas() {
        return vagasRepositorio.Buscar(filtroVagas);
    }
    ///////////////////////////////////////////////////////////

    public String getQuantidadeNotificacoes() {
        return QuantidadeNotificacoes;
    }

    public void setQuantidadeNotificacoes(String QuantidadeNotificacoes) {
        this.QuantidadeNotificacoes = QuantidadeNotificacoes;
    }

    public String getQuantidadeCadastrosAlunos() {
        return QuantidadeCadastrosAlunos;
    }

    public void setQuantidadeCadastrosAlunos(String QuantidadeCadastrosAlunos) {
        this.QuantidadeCadastrosAlunos = QuantidadeCadastrosAlunos;
    }

    public String getQuantidadeCadastrosEmpresas() {
        return QuantidadeCadastrosEmpresas;
    }

    public void setQuantidadeCadastrosEmpresas(String QuantidadeCadastrosEmpresas) {
        this.QuantidadeCadastrosEmpresas = QuantidadeCadastrosEmpresas;
    }

    public String getQuantidadeVagas() {
        return QuantidadeVagas;
    }

    public void setQuantidadeVagas(String QuantidadeVagas) {
        this.QuantidadeVagas = QuantidadeVagas;
    }

    private String msgValidacao = "";

    public void validarCadastroAluno(Aluno alunoSelecionado) {
        alunoSelecionado.setValidado(Boolean.TRUE);

        try {
            repositorioAluno.Salvar(alunoSelecionado);
            msgValidacao = alunoSelecionado.getNome() + " Seu cadastro no SGE - Sistema de Gerenciamento de Estágios foi validado pelo coordenador -"
                    + " Email de Acesso: " + alunoSelecionado.getEmail()
                    + " e Senha: " + alunoSelecionado.getSenha();

            autenticacao.Enviarmail(alunoSelecionado.getEmail(), "Validação de Cadastro", msgValidacao);
            Mensagem(FacesMessage.SEVERITY_INFO, "Sucesso!", "Cadastro do Aluno: " + alunoSelecionado.getNome() + " Validado com sucesso !!!");
            Redirect("listagemNotificacoesCadastros.xhtml");

        } catch (Exception ex) {
            Mensagem(FacesMessage.SEVERITY_WARN, "Atenção", "Ocorreu um erro. Informe ao Administrador.");
        }
    }

    public void validarCadastroVagas(vagaEstagio vagaSelecionado) {
        vagaSelecionado.setValidado(Boolean.TRUE);

        try {
            vagasRepositorio.Salvar(vagaSelecionado);
            msgValidacao = "O cadastro da vaga de estágio realizado no SGE - Sistema de Gerenciamento de Estágios foi validado pelo coordenador";

            autenticacao.Enviarmail(vagaSelecionado.getEmpresaConcedente().getEmailContato(), "Validação de Cadastro", msgValidacao);

            Mensagem(FacesMessage.SEVERITY_INFO, "Sucesso!", "Cadastro da Vaga de Estágio da Empresa: " + vagaSelecionado.getEmpresaConcedente().getNomeEmpresa() + " Validado com sucesso !!!");
            Redirect("listagemNotificacoesVagas.xhtml");

        } catch (Exception ex) {
            Mensagem(FacesMessage.SEVERITY_WARN, "Atenção", "Ocorreu um erro. Informe ao Administrador.");
        }
    }

    public void validarCadastroRepresentante(Empresa EmpresaSelecionada) {

        try {
        
            for (Representante representanteTEMP : EmpresaSelecionada.getResponsaveis()) {
                representanteTEMP.setValidado(Boolean.TRUE);
                representanteRepositorio.Salvar(representanteTEMP);

                msgValidacao = representanteTEMP.getNome() + " Seu cadastro no SGE - Sistema de Gerenciamento de Estágios como representante da Empresa: " + EmpresaSelecionada.getNomeEmpresa() + " foi validado pelo coordenador -"
                        + " Email de Acesso: " + representanteTEMP.getEmail()
                        + " e Senha: " + representanteTEMP.getSenha();

                autenticacao.Enviarmail(representanteTEMP.getEmail(), "Validação de Cadastro", msgValidacao);
               
            }

           

            Mensagem(FacesMessage.SEVERITY_INFO, "Sucesso!", "Cadastro da Empresa: " + EmpresaSelecionada.getNomeEmpresa() + " Validado com sucesso !!!");
            Redirect("listagemNotificacoesCadastros.xhtml");

        } catch (Exception ex) {
            Mensagem(FacesMessage.SEVERITY_WARN, "Atenção", "Ocorreu um erro. Informe ao Administrador.");
        }
    }

    public void validarTodos(String tipoCadastro) {

        if (tipoCadastro.equals("Aluno")) {
            for (Aluno AunoTEMP : this.getListagemAlunos()) {
                this.validarCadastroAluno(AunoTEMP);
            }
        }

        if (tipoCadastro.equals("Empresa")) {
            for (Empresa EmpresaTEMP : this.getListagemEmpresas()) {
                this.validarCadastroRepresentante(EmpresaTEMP);
            }
        }
        if (tipoCadastro.equals("Vaga")) {
            for (vagaEstagio VagaTEMP : this.getListagemVagas()) {
                this.validarCadastroVagas(VagaTEMP);
            }
        }
    }

    public void setarFiltroValidacao() {
        filtroAluno.setValidado(false);

        filtroVagas.setValidado(false);

    }

    protected void Mensagem(FacesMessage.Severity severity, String titulo, String msg) {
        FacesContext context = FacesContext.getCurrentInstance();

        FacesMessage m = new FacesMessage(severity, msg, titulo);
        context.addMessage(null, m);
        context.getExternalContext().getFlash().setKeepMessages(true);
    }

    protected void Redirect(String url) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(ControllerBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void exibirNotificacao() {

        filtroAluno.setValidado(Boolean.FALSE);

        filtroVagas.setValidado(Boolean.FALSE);

        Integer quantidadeTemp = 0;
        Integer quantidadeNotificacoesTemp = 0;

        quantidadeTemp = this.getListagemAlunos().size();
        QuantidadeCadastrosAlunos = quantidadeTemp.toString();
        quantidadeNotificacoesTemp = quantidadeNotificacoesTemp + quantidadeTemp;

        quantidadeTemp = this.getListagemEmpresas().size();
        QuantidadeCadastrosEmpresas = quantidadeTemp.toString();
        quantidadeNotificacoesTemp = quantidadeNotificacoesTemp + quantidadeTemp;

        quantidadeTemp = this.getListagemVagas().size();
        QuantidadeVagas = quantidadeTemp.toString();
        quantidadeNotificacoesTemp = quantidadeNotificacoesTemp + quantidadeTemp;

        QuantidadeNotificacoes = quantidadeNotificacoesTemp.toString();

        if (quantidadeNotificacoesTemp > 0) {

            RequestContext context = RequestContext.getCurrentInstance();

            context.execute("PF('bar').show();");
        }
    }
}
