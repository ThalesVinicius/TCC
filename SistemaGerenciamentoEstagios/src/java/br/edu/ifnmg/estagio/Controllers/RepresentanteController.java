package br.edu.ifnmg.estagio.Controllers;

import br.edu.ifnmg.estagio.entidades.Representante;
import br.edu.ifnmg.estagio.repositorios.ResponsavelRepositorio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

@Named(value = "representante")
@ConversationScoped
public class RepresentanteController extends ControllerGenerico<Representante> implements Serializable {

 

    public RepresentanteController() {
        super("", "");
        entidade = new Representante();
        entidade.setValidado(Boolean.TRUE);
        filtro = new Representante();
    }

    @EJB
    private ResponsavelRepositorio repositorio;

    @PostConstruct
    public void configurar() {
        setDao(repositorio);

    }

}
