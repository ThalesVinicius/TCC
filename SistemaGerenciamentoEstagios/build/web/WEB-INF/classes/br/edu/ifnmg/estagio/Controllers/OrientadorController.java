package br.edu.ifnmg.estagio.Controllers;

import br.edu.ifnmg.estagio.entidades.Servidor;
import br.edu.ifnmg.estagio.repositorios.OrientadorRepositorio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "orientadorController")
@ConversationScoped
public class OrientadorController extends ControllerGenerico<Servidor> implements Serializable {

    public OrientadorController() {
        super("listagemOrientadores.xhtml", "editarOrientador.xhtml");
        entidade = new Servidor();
        entidade.setAcesso("orientador/index.xhtml");
        entidade.setOrientador(true);
        filtro = new Servidor();
    }

    @Inject
    ServidorController servidor;

    @EJB
    private OrientadorRepositorio repositorio;

    @PostConstruct
    public void configurar() {
        setDao(repositorio);
        entidade.setOrientador(true);
    }

    public String limparfiltrosListagem() {
        filtro = new Servidor();
        return paginaListagem;
    }

    public void salvarOrientadorDialog() {

        if (!entidade.getNome().equals("")) {
            entidade.setOrientador(true);
            servidor.salvarServidor(entidade, "", "Orientador");
        } else {
            MensagemErro("Erro", "É obrigatório preencher o nome do Orientador");
        }

    }

    public void atualizaOrientadorDialog() {
        servidor.atualizarServidor(entidade, "", "Orientador");
    }

    public Servidor buscarOrientador() {
        try {
            return repositorio.BuscarOrientador(entidade);
        } catch (Exception ex) {
            return null;
        }
    }

    public void novoOrientador() {
        entidade = new Servidor();
    }

    public void salvarOrientador() {
        servidor.salvarServidor(entidade, paginaListagem, "Orientador");
    }

    public void atualizaOrientador() {
        servidor.atualizarServidor(entidade, paginaListagem, "Orientador");
    }

    public void apagarOrientador() {
        entidade.setOrientador(false);
        servidor.apagarServidor(entidade, paginaListagem);
    }

}
