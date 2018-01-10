package br.edu.ifnmg.estagio.Controllers;

import br.edu.ifnmg.estagio.Infraestrutura.SessaoService;
import br.edu.ifnmg.estagio.entidades.Pessoa;
import br.edu.ifnmg.estagio.repositorios.Repositorio;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@RequestScoped
public abstract class ControllerBase {
    
    @Inject
    SessaoService sessao;
    
    protected void Mensagem(Severity severity, String titulo, String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        
        FacesMessage m = new FacesMessage(severity, msg, titulo);
        context.addMessage(null, m);
        context.getExternalContext().getFlash().setKeepMessages(true);
    }
    
    protected void Redirect(String url) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(ControllerBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setSessao(String key, Pessoa obj) {
        if (sessao == null) {
            return;
        }
        
        if (obj != null && obj.getId() != null) {
            sessao.put(key, obj.getId().toString());
        } else {
            sessao.delete(key);
        }
    }
    
    public void setSessao(String key, String obj) {
        if (sessao == null) {
            return;
        }
        
        if (obj != null) {
            sessao.put(key, obj);
        } else {
            sessao.delete(key);
        }
    }
    
    public Pessoa getSessao(String key, Repositorio dao) {
        if (sessao == null) {
           return null;
        }
        
        String tmp = sessao.get(key);
      
        if (tmp != null && !tmp.isEmpty()) {
            System.err.println("sessio3");
            return (Pessoa) dao.Abrir(Long.parseLong(tmp));
        } else {
            return null;
        }
        
    }
    
}
