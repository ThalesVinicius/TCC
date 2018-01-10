package br.edu.ifnmg.estagio.Converters;

import br.edu.ifnmg.estagio.entidades.Empresa;
import br.edu.ifnmg.estagio.entidades.Servidor;
import br.edu.ifnmg.estagio.repositorios.SupervisorRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Named(value = "supervisorConverter")
@RequestScoped
public class SupervisorConverter implements Serializable, Converter {

    public SupervisorConverter() {
    }

    @EJB
    SupervisorRepositorio daoSupervisor;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().equals("")) {
            return null;
        } else {
            Long id = Long.parseLong(value);
            return daoSupervisor.Abrir(id);
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

    public List<Servidor> autoCompleteSupervisor(String query) {
        Servidor i = new Servidor();
        i.setNome(query);
        return daoSupervisor.Buscar(i);
    }
    
    public List<Servidor> Supervisores(Empresa empresaSelecionada) {
        Servidor i = new Servidor();
        i.setInstituicao(empresaSelecionada);
        return daoSupervisor.Buscar(i);
    }

}
