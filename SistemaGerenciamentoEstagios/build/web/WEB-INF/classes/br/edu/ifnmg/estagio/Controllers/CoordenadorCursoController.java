package br.edu.ifnmg.estagio.Controllers;

import br.edu.ifnmg.estagio.entidades.Servidor;
import br.edu.ifnmg.estagio.repositorios.CoordenadorRepositorio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "coordenadorCursoController")
@ConversationScoped
public class CoordenadorCursoController extends ControllerGenerico<Servidor> implements Serializable {

    public CoordenadorCursoController() {
        super("listagemCoordenadores.xhtml", "editarCoordCurso.xhtml");
        entidade = new Servidor();
        entidade.setAcesso("coordenadorCurso/index.xhtml");
        entidade.setCoordenadorCurso(true);
        filtro = new Servidor();

    }

    @EJB
    private CoordenadorRepositorio repositorio;

    @Inject
    ServidorController servidor;

    @PostConstruct
    public void configurar() {
        setDao(repositorio);
    }

    public String limparfiltrosListagem() {
        filtro = new Servidor();
        return paginaListagem;
    }

    public void salvarCoordenadorCurso() {
        if (!entidade.getNome().equals("")) {
            servidor.salvarServidor(entidade, "", "Coordenador");
        } else {
            MensagemErro("Erro", "É obrigatório preencher o nome do Supervisor");
        }

    }

    public Servidor buscarCoordenador() {
        try {
            return repositorio.BuscarCoordenador(entidade);
        } catch (Exception ex) {
            return null;
        }
    }

    public void salvarCoordenador() {
        servidor.salvarServidor(entidade, paginaListagem, "Coordenador");
    }

    public void atualizaCoordenador() {
        servidor.atualizarServidor(entidade, paginaListagem, "Coordenador");
    }

    public void apagarCoordenador() {
        entidade.setCoordenadorCurso(false);
        servidor.apagarServidor(entidade, paginaListagem);
    }

}
