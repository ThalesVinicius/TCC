package br.edu.ifnmg.estagio.Converters;

import br.edu.ifnmg.estagio.entidades.Aluno;
import br.edu.ifnmg.estagio.repositorios.AlunoRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@Named(value = "alunoConverter")
@RequestScoped
public class AlunoConverter implements Serializable, Converter {

    public AlunoConverter() {
    }

    @EJB
    AlunoRepositorio dao;

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
            Aluno c = (Aluno) value;
            return c.getId().toString();
        }
    }

    public List<Aluno> autoCompleteAluno(String query) {
        Aluno i = new Aluno();
        i.setNome(query);
        return dao.Buscar(i);
    }

}
