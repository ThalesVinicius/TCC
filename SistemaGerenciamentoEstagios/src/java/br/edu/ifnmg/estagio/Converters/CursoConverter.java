package br.edu.ifnmg.estagio.Converters;

import br.edu.ifnmg.estagio.entidades.Curso;
import br.edu.ifnmg.estagio.repositorios.CursoRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Named(value = "cursoConverter")
@RequestScoped
public class CursoConverter implements Serializable, Converter {

    public CursoConverter() {
    }

    @EJB
    CursoRepositorio daoCurso;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().equals("")) {
            return null;
        } else {
            Long id = Long.parseLong(value);
            return daoCurso.Abrir(id);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.toString().equals("")) {
            return "";
        } else {
            Curso c = (Curso) value;
            return c.getId().toString();
        }
    }

    public List<Curso> autoCompleteCurso(String query) {
        Curso i = new Curso();
        i.setNome(query);
        return daoCurso.Buscar(i);
    }

}
