package br.edu.ifnmg.estagio.Converters;

import br.edu.ifnmg.estagio.entidades.Servidor;
import br.edu.ifnmg.estagio.repositorios.OrientadorRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Named(value = "orientadorConverter")
@RequestScoped
public class OrientadorConverter implements Serializable, Converter {

    public OrientadorConverter() {
    }

    @EJB
    OrientadorRepositorio daoOrientador;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().equals("")) {
            return null;
        } else {
            try {
                Long id = Long.parseLong(value);
                return daoOrientador.Abrir(id);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.toString().equals("")) {
            return "";
        } else {
            try {
                Servidor c = (Servidor) value;
                return c.getId().toString();
            } catch (Exception ex) {
                return null;
            }
        }
    }

    public List<Servidor> autoCompleteOrientador(String query) {
        Servidor i = new Servidor();
        i.setNome(query);
        return daoOrientador.Buscar(i);
    }

}
