package br.edu.ifnmg.estagio.Controllers;

import br.edu.ifnmg.estagio.Infraestrutura.AutenticacaoService;
import br.edu.ifnmg.estagio.Utilitarios.FileUploadView;
import br.edu.ifnmg.estagio.entidades.Aluno;
import br.edu.ifnmg.estagio.entidades.Area;
import br.edu.ifnmg.estagio.entidades.Arquivo;
import br.edu.ifnmg.estagio.entidades.Empresa;
import br.edu.ifnmg.estagio.entidades.Endereco;
import br.edu.ifnmg.estagio.entidades.Matricula;
import br.edu.ifnmg.estagio.entidades.Pessoa;
import br.edu.ifnmg.estagio.entidades.Representante;
import br.edu.ifnmg.estagio.repositorios.AlunoRepositorio;
import br.edu.ifnmg.estagio.repositorios.AreaRepositorio;
import br.edu.ifnmg.estagio.repositorios.EmpresaRepositorio;
import br.edu.ifnmg.estagio.repositorios.PessoaRepositorio;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import javax.inject.Inject;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

@Named(value = "autenticacaoController")
@ViewScoped
public class AutenticacaoController extends ControllerBase
        implements Serializable {

    public AutenticacaoController() {
        aluno = new Aluno();
        empresa = new Empresa();
        endereco = new Endereco();
        representante = new Representante();
        matricula = new Matricula();

        renderedAluno = false;
        renderedEmpresa = false;
        renderedTipo = true;
        tipoCadastro = new String();

        this.listaAreasCadastradas = new ArrayList<>();

        this.listaAreasDisponiveis = new ArrayList<>();

        dualListModelAreas = new DualListModel<>(listaAreasCadastradas, listaAreasDisponiveis);

    }

    @PostConstruct
    public void configurar() {
        this.setarAreas();
    }

    //Login
    Pessoa pessoa;
    @EJB
    PessoaRepositorio daoPessoa;

    //Variaveis de Cadastros
    @EJB
    AlunoRepositorio daoAluno;
    @EJB
    EmpresaRepositorio daoEmpresa;
    @EJB
    AreaRepositorio daoArea;

    //////////
    String tipoCadastro;
    boolean renderedAluno = false;
    boolean renderedEmpresa = false;
    boolean renderedTipo = true;
    boolean renderedTela = false;

    ////////////////////////////////
    Aluno aluno;
    Empresa empresa;
    Endereco endereco;
    Representante representante;
    Matricula matricula;

    private String login, senha, senhaconferencia, senhaAtual;

    @Inject
    AutenticacaoService autenticacao;

    public void validar() throws IOException {
        if (autenticacao.login(login, senha)) {

            pessoa = getSessao("usuarioAutenticado", daoPessoa);

            if (pessoa != null) {
                System.err.println("teste3");
                if (pessoa.getValidado()) {
                    FacesContext.getCurrentInstance().getExternalContext()
                            .redirect(pessoa.getAcesso());
                } else {
                    System.err.println("testefff");
                    Mensagem(FacesMessage.SEVERITY_WARN,
                            "Atenção " + pessoa.getNome(),
                            "Para ter acesso ao sistema é necessário aguardar o coordenador validar o seu cadastro");
                    pessoa = null;
                    autenticacao.logout();
                    FacesContext.getCurrentInstance().getExternalContext()
                            .redirect("PaginaInicial.xhtml");
                }
            }

        } else {
            Mensagem(FacesMessage.SEVERITY_ERROR, "Erro", "Email ou Senha inválidos");
            //  return "PaginaInicial.xhtml";

        }
    }

    public void validarExterno(String paginaRedirecionar) throws IOException {
        if (autenticacao.login(login, senha)) {
            try {

                pessoa = (Pessoa) getSessao("usuarioAutenticado", daoPessoa);

                if (pessoa.getValidado()) {
                    FacesContext.getCurrentInstance().getExternalContext()
                            .redirect(paginaRedirecionar);
                } else {

                    Mensagem(FacesMessage.SEVERITY_WARN,
                            "Atenção " + pessoa.getNome(),
                            "Para ter acesso ao sistema é necessário aguardar o coordenador validar o seu cadastro");
                    pessoa = null;
                    autenticacao.logout();
                }

            } catch (Exception ex) {

            }

        } else {
            Mensagem(FacesMessage.SEVERITY_ERROR, "Erro", "Email ou Senha inválidos");

        }

    }

    public void logout() throws IOException {
        try {
            pessoa = null;
            autenticacao.logout();

            FacesContext.getCurrentInstance().getExternalContext()
                    .invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("../PaginaInicial.xhtml");
        } catch (Exception ex) {
            pessoa = null;
            autenticacao.logout();

            FacesContext.getCurrentInstance().getExternalContext()
                    .invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("../PaginaInicial.xhtml");
        }

    }

    public void idelListener() {
        RequestContext context = RequestContext.getCurrentInstance();

        context.execute("PF('msgIdle').show();");
    }

    public void idelListenerExterno() throws IOException {
        //Mensagem(FacesMessage.SEVERITY_WARN, "Atenção", "Está página ficou ociosa por mais de 5 minutos e você foi redirecionado para página principal");
        try {
            logoutExterno();
        } catch (Exception e) {

        }
    }

    public void logoutExterno() throws IOException {
        try {
            pessoa = null;
            autenticacao.logout();

            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("PaginaInicial.xhtml");
        } catch (Exception ex) {
            pessoa = null;
            autenticacao.logout();

            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("PaginaInicial.xhtml");

        }
    }

    public void salvar() {
        if (daoPessoa.Salvar(pessoa)) {
            Mensagem(FacesMessage.SEVERITY_INFO, "Sucesso", "Seus dados pessoais foram alterados");
            try {
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect("editarDadosPessoais.xhtml");
            } catch (Exception ex) {

            }
        } else {
            Mensagem(FacesMessage.SEVERITY_ERROR, "Erro", "Nao foi Possivel alterar seus dados pessoais");

        }
    }

    public boolean AnexarFotoPerfil(FileUploadEvent event) {
        FileUploadView anexarArquivo = new FileUploadView();

        String fileNameAnexado = anexarArquivo.upload(event);

        if (fileNameAnexado != null) {

            Arquivo arquivo = new Arquivo();
            arquivo.setNome(fileNameAnexado);

            arquivo.setUri("C:\\Users\\Thales\\Desktop\\TCC\\");

            pessoa.setFoto_perfil(arquivo);

            MensagemSucesso("Sucesso", event.getFile().getFileName() + " anexado com sucesso");
            arquivo = null;
            return true;

        }

        return false;
    }

    public void alterarSenha() {
        pessoa = getPessoa();
        if (senhaAtual.equals(pessoa.getSenha())) {

            if (senha.equals(senhaconferencia)) {

                pessoa.setSenha(senha);
                if (daoPessoa.Salvar(pessoa)) {
                    Mensagem(FacesMessage.SEVERITY_INFO, "Sucesso", "Sua senha foi alterada com sucesso");
                    senhaAtual = "";
                    senhaconferencia = "";
                    senha = "";
                } else {
                    senhaAtual = "";
                    senhaconferencia = "";
                    senha = "";
                    Mensagem(FacesMessage.SEVERITY_ERROR, "Erro", "Nao foi Possivel alterar Sua senha");
                }

            } else {
                senhaconferencia = "";
                Mensagem(FacesMessage.SEVERITY_ERROR, "Erro", "Senhas nao Conferem");
            }

        } else {
            senhaAtual = "";
            Mensagem(FacesMessage.SEVERITY_ERROR, "Erro", "Senha atual Incorreta");
        }

    }

    public Pessoa getPessoa() {
        try {
            if (pessoa == null) {
                pessoa = (Pessoa) getSessao("usuarioAutenticado", daoPessoa);
                if (pessoa == null) {
                    pessoa = new Pessoa();
                }
            }

        } catch (Exception ex) {

        }
        return pessoa;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenhaconferencia() {
        return senhaconferencia;
    }

    public void setSenhaconferencia(String senhaconferencia) {
        this.senhaconferencia = senhaconferencia;
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getTipoCadastro() {
        return tipoCadastro;
    }

    public void setTipoCadastro(String tipoCadastro) {
        this.tipoCadastro = tipoCadastro;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Representante getRepresentante() {
        return representante;
    }

    public void setRepresentante(Representante representante) {
        this.representante = representante;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    /////Controle de interface
    public boolean isRenderedAluno() {
        return renderedAluno;
    }

    public void setRenderedAluno(boolean renderedAluno) {
        this.renderedAluno = renderedAluno;
    }

    public boolean isRenderedEmpresa() {
        return renderedEmpresa;
    }

    public void setRenderedEmpresa(boolean renderedEmpresa) {
        this.renderedEmpresa = renderedEmpresa;
    }

    public boolean isRenderedTipo() {
        return renderedTipo;
    }

    public void setRenderedTipo(boolean renderedTipo) {
        this.renderedTipo = renderedTipo;
    }

    public void rendered() {
        if (!(tipoCadastro.equals("Selecione"))) {
            if (tipoCadastro.equals("Aluno")) {
                renderedAluno = true;
                renderedEmpresa = false;
                renderedTipo = false;

            } else if (tipoCadastro.equals("Empresa")) {
                renderedAluno = false;
                renderedEmpresa = true;
                renderedTipo = false;
            }
        } else {
            MensagemErro("Erro!", "É necessário selecionar o tipo de Usuário.");
        }
    }
    //////////////fim

    ////////////Cadastros//////////
    public void cadastrarAluno() {

        aluno.salvarEndereco(endereco);
        aluno.salvarMatricula(matricula);
        aluno.setValidado(false);

        if (daoAluno.Salvar(aluno)) {

            endereco = new Endereco();
            matricula = new Matricula();
            aluno = new Aluno();
            MensagemSucesso("Sucesso!", "Cadastro de Aluno salvo com sucesso!");

            Mensagem(FacesMessage.SEVERITY_WARN,
                    "Atenção Aluno!",
                    "Para ter acesso ao sistema é necessário aguardar o coordenador validar o seu cadastro");

            Redirect("PaginaInicial.xhtml");
        } else {

            MensagemErro("Erro!", "Consulte o administrador do sistema!");
        }
    }

    private DualListModel<Area> dualListModelAreas;
    private List<Area> listaAreasCadastradas;
    private List<Area> listaAreasDisponiveis;

    public DualListModel<Area> getDualListModelAreas() {
        return dualListModelAreas;
    }

    public void setDualListModelAreas(DualListModel<Area> dualListModelAreas) {
        this.dualListModelAreas = dualListModelAreas;
    }

    public void setarAreas() {

        List<Area> listTemp;
        listTemp = new ArrayList<>();
        Area filtro = new Area();
        listTemp = daoArea.Buscar(filtro);

        for (Area a : listTemp) {
            listaAreasCadastradas.add(a);
        }
    }

    public void onTransfer(TransferEvent event) {

        for (Object item : event.getItems()) {
            if (empresa.areas_estagios.contains((Area) item)) {
                empresa.areas_estagios.remove((Area) item);
            } else {
                empresa.areas_estagios.add((Area) item);
            }
        }

    }

    public void cadastrarEmpresa() {
        empresa.salvarEndereco(endereco);
        representante.setValidado(false);
        empresa.salvarResponsavel(representante);

        //empresa.areas_estagios = this.listaAreasDisponiveis;
        if (daoEmpresa.Salvar(empresa)) {
            endereco = new Endereco();
            representante = new Representante();
            empresa = new Empresa();

            MensagemSucesso("Sucesso!", "Cadastro de Empresa salvo com sucesso!");

            Mensagem(FacesMessage.SEVERITY_WARN,
                    "Atenção Representante!",
                    "Para ter acesso ao sistema é necessário aguardar o coordenador validar o seu cadastro");
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('bar').show();");

            Redirect("PaginaInicial.xhtml");
        } else {
            MensagemErro("Erro!", "Consulte o administrador do sistema!");
        }
    }

    public String voltarCadastro() {
        renderedTipo = true;
        renderedAluno = false;
        renderedEmpresa = false;

        return "Cadastro.xhtml";
    }

    protected void MensagemSucesso(String titulo, String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, titulo);
        context.addMessage(null, m);
        context.getExternalContext().getFlash().setKeepMessages(true);
    }

    protected void MensagemErro(String titulo, String msg) {
        FacesContext context = FacesContext.getCurrentInstance();

        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, titulo);
        context.addMessage(null, m);
        context.getExternalContext().getFlash().setKeepMessages(true);
    }

    public void novasenha() {
        if (autenticacao.redefinirSenha(login)) {
            MensagemSucesso("Sucesso!", "Foi enviado para o seu e-mail uma nova senha ! ");
        } else {
            MensagemErro("Falha!", "Houve um problema ao enviar o e-mail com a nova senha! "
                    + "Consulte o administrador do sistema ou tente novamente em alguns instantes.");
        }
    }

    public boolean isRenderedTela() {
        if (pessoa == null) {
            renderedTela = true;
        } else {
            renderedTela = false;
        }
        return renderedTela;
    }

}
