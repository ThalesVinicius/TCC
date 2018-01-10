package br.edu.ifnmg.estagio.Controllers;

import br.edu.ifnmg.estagio.entidades.Empresa;
import br.edu.ifnmg.estagio.entidades.Servidor;
import br.edu.ifnmg.estagio.repositorios.SupervisorRepositorio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "supervisorController")
@ConversationScoped
public class SupervisorController extends ControllerGenerico<Servidor> implements Serializable {

    public SupervisorController() {
        super("listagemSupervisores.xhtml", "editarSupervisor.xhtml");
        entidade = new Servidor();
        entidade.setAcesso("supervisor/index.xhtml");
        entidade.setSupervisor(true);
        filtro = new Servidor();
    }

    @EJB
    private SupervisorRepositorio repositorio;

    @Inject
    ServidorController servidor;

    @PostConstruct
    public void configurar() {
        setDao(repositorio);
        entidade.setSupervisor(true);
    }

    public String limparfiltrosListagem() {
        filtro = new Servidor();
        return paginaListagem;
    }

    public void salvarSupervisorDialog() {
        if (!entidade.getNome().equals("")) {
            servidor.salvarServidor(entidade, "", "Supervisor");
        } else {
            MensagemErro("Erro", "É obrigatório preencher o nome do Supervisor");
        }

    }

    public void atualizaSupervisorDialog() {
        servidor.atualizarServidor(entidade, "", "Supervisor");
    }

    public Servidor buscarSupervisor() {
        try {
            return repositorio.BuscarSupervisor(entidade);
        } catch (Exception ex) {
            return null;
        }
    }

    public Servidor novoSupervisor() {
        entidade = new Servidor();
        return entidade;
    }

    public List<Servidor> buscarSupervisores() {
        // filtro.setInstituicao(empresa);
        try {
            return repositorio.Buscar(entidade);
        } catch (Exception ex) {
            return null;
        }
    }

    public List<Servidor> buscarSupervisoresPorEmpresa(Empresa empresa) {
        System.err.println("...." + empresa.getNomeEmpresa());
        filtro.setInstituicao(empresa);
        try {
            return repositorio.Buscar(filtro);
        } catch (Exception ex) {
            return null;
        }
    }

    public Empresa setarEmpresa(Empresa empresa) {

        entidade.setInstituicao(empresa);
        return empresa;
    }

    public void salvarSupervisor() {
        servidor.salvarServidor(entidade, paginaListagem, "Supervisor");
    }

    public void atualizaSupervisor() {
        servidor.atualizarServidor(entidade, paginaListagem, "Supervisor");
    }

    public void apagarSupervisor() {
        entidade.setSupervisor(false);
        servidor.apagarServidor(entidade, paginaListagem);
    }

}
