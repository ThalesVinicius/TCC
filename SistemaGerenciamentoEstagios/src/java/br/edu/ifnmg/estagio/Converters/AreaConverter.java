package br.edu.ifnmg.estagio.Converters;

import br.edu.ifnmg.estagio.entidades.Area;
import br.edu.ifnmg.estagio.repositorios.AreaRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Named(value = "areaConverter")
@RequestScoped
public class AreaConverter implements Serializable, Converter {

    public AreaConverter() {
    }

    @EJB
    AreaRepositorio daoArea;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().equals("")) {
            return null;
        } else {
            try {
                Long id = Long.parseLong(value);
                return daoArea.Abrir(id);
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
            Area c = (Area) value;
            return c.getId().toString();
        }
    }

    public List<Area> autoCompleteArea(String query) {
        Area i = new Area();
        i.setNome(query);
        return daoArea.Buscar(i);
    }

}
