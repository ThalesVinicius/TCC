package br.edu.ifnmg.estagio.Controllers;

import br.edu.ifnmg.estagio.entidades.Area;
import br.edu.ifnmg.estagio.repositorios.AreaRepositorio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

@Named(value = "areaController")
@ConversationScoped
public class AreaController extends ControllerGenerico<Area> implements Serializable {

    public AreaController() {
        super("listagemAreas.xhtml", "editarArea.xhtml");
        entidade = new Area();
        filtro = new Area();


    }

    @EJB
    private AreaRepositorio repositorio;

    @PostConstruct
    public void configurar() {
        setDao(repositorio);
  
    }

    public String limparfiltrosListagem() {
        filtro = new Area();
        return paginaListagem;
    }

    

  

    
}
