package br.edu.ifnmg.estagio.Converters;

import br.edu.ifnmg.estagio.entidades.Servidor;
import br.edu.ifnmg.estagio.repositorios.ServidorRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Named(value = "servidorConverter")
@RequestScoped
public class ServidorConverter implements Serializable, Converter {

    public ServidorConverter() {
    }

    @EJB
    ServidorRepositorio daoServidor;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().equals("")) {
            return null;
        } else {
            Long id = Long.parseLong(value);
            return daoServidor.Abrir(id);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.toString().equals("")) {
            return "";
        } else {
            Servidor c = (Servidor) value;
            return c.getId().toString();
        }
    }

    public List<Servidor> autoCompleteServidor(String query) {
        Servidor i = new Servidor();
        i.setNome(query);
        return daoServidor.Buscar(i);
    }
    
//    public List<Servidor> Supervisores(Empresa empresaSelecionada) {
//        Servidor i = new Servidor();
//        i.setInstituicao(empresaSelecionada);
//        return daoServidor.Buscar(i);
//    }

}
