package br.edu.ifnmg.estagio.Controllers;

import br.edu.ifnmg.estagio.entidades.Aluno;
import br.edu.ifnmg.estagio.entidades.Endereco;
import br.edu.ifnmg.estagio.entidades.Matricula;
import br.edu.ifnmg.estagio.repositorios.AlunoRepositorio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;

import javax.inject.Named;

@Named(value = "alunoController")
@ConversationScoped
public class AlunoController extends ControllerGenerico<Aluno> implements Serializable {

    //Variaveis de  agregado de Endereço
    private Endereco enderecoAdcionado;
    private Matricula MatriculaAdicionada;

    //Variaveis de Controle de Interface
    private Boolean habDesCamposEndereco = false;
    private Boolean habDesCamposMatricula = false;

    public AlunoController() {
        super("listagemAlunos.xhtml", "editarAluno.xhtml");
        entidade = new Aluno();
        entidade.setValidado(true);

        filtro = new Aluno();
        filtro.setValidado(true);
        enderecoAdcionado = new Endereco();
        MatriculaAdicionada = new Matricula();
    }

    @EJB
    private AlunoRepositorio repositorio;

    @PostConstruct
    public void configurar() {
        entidade.setValidado(true);
        setDao(repositorio);
    }

    public String limparfiltrosListagem() {
        filtro = new Aluno();
        return paginaListagem;
    }

    //////////////////////////////Agregados endereços /////////////////////////////////
    public void removeEndereco(Endereco enderecoSelecionado) {
        entidade.removeEndereco(enderecoSelecionado);
        enderecoAdcionado = new Endereco();
        MensagemSucesso("Sucesso!", "Endereco removido com sucesso !!!");
    }

    public void salvarEndereco() {
        entidade.salvarEndereco(enderecoAdcionado);
        enderecoAdcionado = new Endereco();
        MensagemSucesso("Sucesso!", "Endereco salvo com sucesso !!!");
    }

    //////////////////////////////Agregados matriculas/////////////////////////////////
    public void removeMatricula(Matricula matriculaSelecionada) {
        entidade.removeMatricula(matriculaSelecionada);
        MatriculaAdicionada = new Matricula();
        MensagemSucesso("Sucesso!", "Matricula removido com sucesso!");
    }

    public void salvarMatricula() {
        entidade.salvarMatricula(MatriculaAdicionada);
        MatriculaAdicionada = new Matricula();
        MensagemSucesso("Sucesso!", "Matricula salva com sucesso!");
    }

    /////////////////////////////////////////////////////////////////////////////
    ///////  GET E SETRS  DOS AGREGADOS ///////////////////
    /////////////////////////////////////////////////////////
    public Endereco getEnderecoAdcionado() {
        return enderecoAdcionado;
    }

    public void setEnderecoAdcionado(Endereco enderecoAdcionado) {
        this.enderecoAdcionado = enderecoAdcionado;
    }

    public Matricula getMatriculaAdicionada() {
        return MatriculaAdicionada;
    }

    public void setMatriculaAdicionada(Matricula MatriculaAdicionada) {
        this.MatriculaAdicionada = MatriculaAdicionada;
    }

    ////////////////////////////////////////////////////////////
    ///////  GET E SETRS  DAS VARIAVEIS DE CONTROLE INTERFACE ///////////////////
    /////////////////////////////////////////////////////////   
    public Boolean getHabDesCamposEndereco() {
        return habDesCamposEndereco;
    }

    public void setHabDesCamposEndereco(Boolean habDesCamposEndereco) {
        this.habDesCamposEndereco = habDesCamposEndereco;
    }

    public Boolean getHabDesCamposMatricula() {
        return habDesCamposMatricula;
    }

    public void setHabDesCamposMatricula(Boolean habDesCamposMatricula) {
        this.habDesCamposMatricula = habDesCamposMatricula;
    }

    public void salvarAlunoDialog() {
        if (!(entidade.getNome().equals(""))) {
            if (MatriculaAdicionada.getCurso() != null) {

                entidade.salvarEndereco(enderecoAdcionado);

                entidade.salvarMatricula(MatriculaAdicionada);

                MatriculaAdicionada = new Matricula();
                enderecoAdcionado = new Endereco();

                if (repositorio.Salvar(entidade)) {;
                    MensagemSucesso("Sucesso!", "Cadastro de Aluno salvo com sucesso!");

                } else {
                    MensagemErro("Erro", "Consulte o administrador");
                }

            } else {
                MensagemErro("Erro", "Selecione um Curso na Matricula");
            }

        } else {
            MensagemErro("Erro", "Preencha o nome do Aluno");
        }
    }

    public Aluno buscarAluno() {
        try {
            return repositorio.BuscarAluno(entidade);
        } catch (Exception ex) {
            return null;
        }
    }

}
