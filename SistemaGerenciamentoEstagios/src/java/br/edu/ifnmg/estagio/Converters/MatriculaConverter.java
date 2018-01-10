package br.edu.ifnmg.estagio.Converters;

import br.edu.ifnmg.estagio.entidades.Matricula;
import br.edu.ifnmg.estagio.repositorios.MatriculaRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Named(value = "matriculaConverter")
@RequestScoped
public class MatriculaConverter implements Serializable, Converter {

    public MatriculaConverter() {
    }

    @EJB
    MatriculaRepositorio daoMatricula;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().equals("")) {
            return null;
        } else {
            Long id = Long.parseLong(value);
            return daoMatricula.Abrir(id);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.toString().equals("")) {
            return "";
        } else {
            Matricula c = (Matricula) value;

            return c.getId().toString();
        }
    }

}
