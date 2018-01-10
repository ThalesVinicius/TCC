package br.edu.ifnmg.estagio.Controllers;

import br.edu.ifnmg.estagio.entidades.Curso;
import br.edu.ifnmg.estagio.entidades.Servidor;
import br.edu.ifnmg.estagio.repositorios.CursoRepositorio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

@Named(value = "cursoController")
@ConversationScoped
public class CursoController extends ControllerGenerico<Curso> implements Serializable {

    Servidor tempCoordenadorCurso;

    public CursoController() {
        super("listagemCursos.xhtml", "editarCurso.xhtml");
        entidade = new Curso();
        filtro = new Curso();


    }

    @EJB
    private CursoRepositorio repositorio;

    @PostConstruct
    public void configurar() {
        setDao(repositorio);
  
    }

    public String limparfiltrosListagem() {
        filtro = new Curso();
        return paginaListagem;
    }

    public Servidor getTempCoordenadorCurso() {
        return entidade.getCoordenadorCurso();
    }

    public void setTempCoordenadorCurso(Servidor tempCoordenadorCurso) {
        this.tempCoordenadorCurso = tempCoordenadorCurso;
    }

    public void SelectCoordenadorCurso(SelectEvent itemSelect) {
        entidade.setCoordenadorCurso((Servidor) itemSelect.getObject());

    }

    
}
