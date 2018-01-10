package br.edu.ifnmg.estagio.Controllers;

import br.edu.ifnmg.estagio.entidades.Coordenacao;
import br.edu.ifnmg.estagio.repositorios.CoordenacaoRepositorio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

@Named(value = "coordenacaoController")
@ConversationScoped
public class CoordenacaoController extends ControllerGenerico<Coordenacao> implements Serializable {

    public CoordenacaoController() {
        super("editarAreaPublica.xhtml", "editarCoordenacao.xhtml");
        entidade = new Coordenacao();
        filtro = new Coordenacao();

    }

    @EJB
    private CoordenacaoRepositorio repositorio;

    @PostConstruct
    public void configurar() {
        setDao(repositorio);

    }

    public String limparfiltrosListagem() {
        filtro = new Coordenacao();
        return paginaListagem;
    }

    public void setarCoordenacao() {

        if (!(getListagem().isEmpty())) {
            
             for (int i = 0; i < getListagem().size(); i++) {                
                entidade = getListagem().get(i);
            }
        }
    }

}
