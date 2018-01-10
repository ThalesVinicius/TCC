package br.edu.ifnmg.estagio.Controllers;

import br.edu.ifnmg.estagio.entidades.Aluno;
import br.edu.ifnmg.estagio.entidades.Empresa;
import br.edu.ifnmg.estagio.entidades.Estagio;
import br.edu.ifnmg.estagio.entidades.Matricula;
import br.edu.ifnmg.estagio.entidades.Orientacoes;
import br.edu.ifnmg.estagio.entidades.Servidor;
import br.edu.ifnmg.estagio.repositorios.AlunoRepositorio;
import br.edu.ifnmg.estagio.repositorios.EstagioRepositorio;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

@Named(value = "estagioController")
@ConversationScoped
public class EstagioController extends ControllerGenerico<Estagio> implements Serializable {
    
    private boolean disebledSelectSupervisor = true;

    //Variaveis Temporárias
    Orientacoes tempOrientacoes;
    Empresa tempEmpresaConcedente;
    Aluno tempEstagiario;
    Servidor tempOrientador;
    Servidor tempSupervisor;
    
    boolean validaAluno = false;
    boolean validaEmpresa = false;
    
    public EstagioController() {
        super("listagemEstagios.xhtml", "editarEstagio.xhtml");
        entidade = new Estagio();
        filtro = new Estagio();
        filtro.setMatricula(new Matricula());
        tempOrientacoes = new Orientacoes();
        
    }
    
    @EJB
    private EstagioRepositorio repositorio;
    
    @EJB
    private AlunoRepositorio daoEstagiario;
    
    @PostConstruct
    public void configurar() {
        setDao(repositorio);
        
    }
    
    public String limparfiltrosListagem() {
        filtro = new Estagio();
        return paginaListagem;
    }

    //////////////Metodos de Controle de Interface//////////////
    ////////////////////////////////////////////////////////////
    public void SelectAluno(SelectEvent itemSelect) {
        entidade.setMatricula(null);
        entidade.setEstagiario((Aluno) itemSelect.getObject());
    }
    
    public void SelectEmpresa(SelectEvent itemSelect) {
        entidade.setSupervisor(null);
        entidade.setEmpresaConcedente((Empresa) itemSelect.getObject());
        disebledSelectSupervisor = false;
    }
    
    public void SelectSupervisor(SelectEvent itemSelect) {
        //entidade.setSupervisor(null);
        entidade.setSupervisor((Servidor) itemSelect.getObject());
        
    }
    
    public void SelectSituacao(SelectEvent itemSelect) {
        if (!(itemSelect.getObject().equals("") || itemSelect.getObject().equals("Cancelado"))) {
            if (entidade.getTermino().after(new Date())) {
                entidade.setSituacao("Em andamento");
            } else {
                entidade.setSituacao("Concluido");
            }
        }
    }

//    Matricula tempMatricula;
//
//   
//
//    public void SelectMatricula(SelectEvent itemSelect) {
//        tempMatricula = ((Matricula) itemSelect.getObject());
//    }
    public boolean isDisebledSelectSupervisor() {
        return disebledSelectSupervisor;
    }
    
    public void setDisebledSelectSupervisor(boolean disebledSelectSupervisor) {
        this.disebledSelectSupervisor = disebledSelectSupervisor;
    }
    
    public void isDisabledSupervisor() {
        disebledSelectSupervisor = false;
    }
    
    public void SelectOrientador(SelectEvent itemSelect) {
        tempOrientacoes.setOrientador((Servidor) itemSelect.getObject());
    }
    
    public void addOrientacao() {
        tempOrientacoes.setEstagio(entidade);
        
        if (tempOrientacoes.getOrientador() != null) {
            if (entidade.addOrientacao(tempOrientacoes)) {
                entidade.setChc(entidade.getChc() + tempOrientacoes.getChcOrientacao());
                tempOrientacoes = new Orientacoes();
                MensagemSucesso("Sucesso!", "Orientação adicionada com sucesso !!!");
            } else {
                MensagemErro("Erro", "Tente mais tarde");
            }
        } else {
            MensagemErro("Erro", "Preencha o campo Orientador");
        }
        
    }
    
    public void configurarCHC() {
        entidade.setChc(entidade.getChc() - tempOrientacoes.getChcOrientacao());
    }
    
    public void setDatasOrientacacao() {
        tempOrientacoes.setInicioOrientacao(entidade.getInicio());
        tempOrientacoes.setTerminoOrientacao(entidade.getTermino());
        
        if (entidade.getTermino() != null) {
            if (entidade.getTermino().after(new Date())) {
                entidade.setSituacao("Em andamento");
            } else {
                entidade.setSituacao("Concluido");
            }
        }
    }
    
    public void removeOriencao(Orientacoes OrientacaoSelecionada) {
        entidade.setChc(entidade.getChc() - OrientacaoSelecionada.getChcOrientacao());
        entidade.removeOrientacao(OrientacaoSelecionada);
        
        MensagemSucesso("Sucesso!", "Orientador removido com sucesso !!!");
    }

    /////////////////////////////////////////
    ///////////get e setrs das variaveis temporarias//////////
    public Orientacoes getTempOrientacoes() {
        return tempOrientacoes;
    }
    
    public void setTempOrientacoes(Orientacoes tempOrientacoes) {
        this.tempOrientacoes = tempOrientacoes;
    }
    
    public void setTempEmpresaConcedente(Empresa tempEmpresaConcedente) {
        this.tempEmpresaConcedente = tempEmpresaConcedente;
    }
    
    public Empresa getTempEmpresaConcedente() {
        return entidade.getEmpresaConcedente();
    }
    
    public Aluno getTempEstagiario() {
        return entidade.getEstagiario();
    }
    
    public void setTempEstagiario(Aluno tempEstagiario) {
        this.tempEstagiario = tempEstagiario;
    }
    
    public Servidor getTempOrientador() {
        return tempOrientacoes.getOrientador();
    }
    
    public void setTempOrientador(Servidor tempOrientador) {
        this.tempOrientador = tempOrientador;
    }
    
    public Servidor getTempSupervisor() {
        return entidade.getSupervisor();
    }
    
    public void setTempSupervisor(Servidor tempSupervisor) {
        this.tempSupervisor = tempSupervisor;
    }
    
    public boolean isValidaAluno() {
        return validaAluno;
        
    }
    
    public void setValidaAluno(boolean validaAluno) {
        this.validaAluno = validaAluno;
    }
    
    public boolean isValidaEmpresa() {
        return validaEmpresa;
    }
    
    public void setValidaEmpresa(boolean validaEmpresa) {
        this.validaEmpresa = validaEmpresa;
    }

    //////////////////////////////
    private int chcTotal = 0;
    
    public int getChcTotal() {
        return chcTotal;
    }
    
    public void setChcTotal(int chcTotal) {
        this.chcTotal = chcTotal;
    }
    
    public List<Estagio> buscarEstagio(Aluno estagiario) {
        chcTotal = 0;
        Estagio estagioTemp = new Estagio();
        estagioTemp.setEstagiario(estagiario);
        List<Estagio> estagios = repositorio.Buscar(estagioTemp);
        
        for (Estagio est : estagios) {
            chcTotal = chcTotal + est.getChc();
        }
        return estagios;
    }
    
    public void setarFiltroEstagiario(Long idCandidato) {
        filtro.setEstagiario(daoEstagiario.Abrir(idCandidato));
    }

    public void setarFiltroEmpresa(Empresa empresa) {
        filtro.setEmpresaConcedente(empresa);
    }
    
    public List<Estagio> buscarEstagioSupervisionados(Servidor supervisor) {
        
        Estagio estagioTemp = new Estagio();
        estagioTemp.setSupervisor(supervisor);
        List<Estagio> estagios = repositorio.Buscar(estagioTemp);
        
        return estagios;
    }
    
}
