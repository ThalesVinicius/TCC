package br.edu.ifnmg.estagio.Converters;

import br.edu.ifnmg.estagio.entidades.Area;
import br.edu.ifnmg.estagio.entidades.Setor;
import br.edu.ifnmg.estagio.repositorios.SetorRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Named(value = "setorConverter")
@RequestScoped
public class SetorConverter implements Serializable, Converter {

    public SetorConverter() {
    }

    @EJB
    SetorRepositorio daoSetor;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().equals("")) {
            return null;
        } else {
            try {
                Long id = Long.parseLong(value);
                return daoSetor.Abrir(id);
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
            Setor c = (Setor) value;
            return c.getId().toString();
        }
    }

    public List<Setor> autoCompleteSetor(String query) {
        Setor i = new Setor();
        i.setNome(query);
        return daoSetor.Buscar(i);
    }

}
