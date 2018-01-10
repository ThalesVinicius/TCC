package br.edu.ifnmg.estagio.Converters;

import br.edu.ifnmg.estagio.entidades.Servidor;
import br.edu.ifnmg.estagio.repositorios.CoordenadorRepositorio;
import br.edu.ifnmg.estagio.repositorios.OrientadorRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Named(value = "coordenadorConverter")
@RequestScoped
public class CoordenadorConverter implements Serializable, Converter {

    public CoordenadorConverter() {
    }

    @EJB
    CoordenadorRepositorio daoCoordenador;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().equals("")) {
            return null;
        } else {
            try {
                Long id = Long.parseLong(value);
                return daoCoordenador.Abrir(id);
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

    public List<Servidor> autoCompleteCoordenador(String query) {
        Servidor i = new Servidor();
        i.setNome(query);
        return daoCoordenador.Buscar(i);
    }

}
