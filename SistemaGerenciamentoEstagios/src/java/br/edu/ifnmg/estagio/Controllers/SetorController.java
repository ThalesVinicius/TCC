package br.edu.ifnmg.estagio.Controllers;

import br.edu.ifnmg.estagio.entidades.Setor;
import br.edu.ifnmg.estagio.repositorios.SetorRepositorio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

@Named(value = "setorController")
@ConversationScoped
public class SetorController extends ControllerGenerico<Setor> implements Serializable {

    public SetorController() {
        super("listagemSetores.xhtml", "editarSetor.xhtml");
        entidade = new Setor();
        filtro = new Setor();

    }

    @EJB
    private SetorRepositorio repositorio;

    @PostConstruct
    public void configurar() {
        setDao(repositorio);

    }

    public String limparfiltrosListagem() {
        filtro = new Setor();
        return paginaListagem;
    }

    public Setor buscarSetor() {
        try {
            return repositorio.BuscarSetor(entidade);
        } catch (Exception ex) {
            return null;
        }
    }

    public void salvarSetorDialog() {
        if (!entidade.getNome().equals("")) {
            try {
                repositorio.Salvar(entidade);
                MensagemSucesso("Sucesso!", "Cadastro realizado com sucesso");
            } catch (Exception ex) {
                MensagemErro("Erro", "É obrigatório preencher o nome do Setor");
            }
        } else {
            MensagemErro("Erro", "É obrigatório preencher o nome do Setor");
        }

    }

}
