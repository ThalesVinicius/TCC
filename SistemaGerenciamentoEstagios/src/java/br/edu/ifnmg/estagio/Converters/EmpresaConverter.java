package br.edu.ifnmg.estagio.Converters;

import br.edu.ifnmg.estagio.entidades.Empresa;
import br.edu.ifnmg.estagio.repositorios.EmpresaRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Named(value = "empresaConverter")
@RequestScoped
public class EmpresaConverter implements Serializable, Converter {

    public EmpresaConverter() {
    }

    @EJB
    EmpresaRepositorio dao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value.trim().equals("") || value == null) {
            return null;
        } else {
            try {

                return dao.Abrir(Long.parseLong(value));

            } catch (NumberFormatException exception) {
                //throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid player"));  
                return null;
            } catch (Exception e) {
                return null;
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.toString().equals("")) {
            return "";
        } else {
            Empresa c = (Empresa) value;
            return c.getId().toString();
        }
    }

    public List<Empresa> autoCompleteEmpresa(String query) {
        Empresa i = new Empresa();
        i.setNomeEmpresa(query);
        return dao.Buscar(i);
    }

}
