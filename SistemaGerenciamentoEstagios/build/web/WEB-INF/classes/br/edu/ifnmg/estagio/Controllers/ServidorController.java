package br.edu.ifnmg.estagio.Controllers;

import br.edu.ifnmg.estagio.entidades.Servidor;
import br.edu.ifnmg.estagio.repositorios.ServidorRepositorio;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

@RequestScoped

@Named(value = "servidorController")
public class ServidorController implements Serializable {
    
    public ServidorController() {
        
    }
    
    @EJB
    private ServidorRepositorio repositorioServidor;
    
    public void salvarServidor(Servidor servidor, String paginaListagem, String tipo) {
        try {
            Servidor servidorBuscado = repositorioServidor.BuscarServidor(servidor);
            
            if (servidorBuscado == null) {
                
                if (repositorioServidor.Salvar(servidor)) {
                    MensagemS("Sucesso!", tipo + " salvo com sucesso!");
                    if (!(paginaListagem.equals(""))) {
                        FacesContext.getCurrentInstance().getExternalContext()
                                .redirect(paginaListagem);
                    }
                } else {
                    MensagemE("Erro!", "Ocorreu um Erro! Tente mais tarde.");
                }
                
            } else {
                if (servidor.getId() == null) {
                    this.atualizarServidor(servidor, paginaListagem, tipo);
                } else {
                    RequestContext context = RequestContext.getCurrentInstance();
                    
                    context.execute("PF('msgConfirmacao').show();");
                }
                
            }
            
        } catch (Exception ex) {
            MensagemE(ex.getMessage(), "Ocorreu um Erro! Tente mais tarde.");
        }
    }
    
    public void apagarServidor(Servidor servidor, String paginaListagem) {
        try {
            if (repositorioServidor.Salvar(servidor)) {
                MensagemS("Sucesso!", "Registro removido com sucesso!");
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect(paginaListagem);
            } else {
                MensagemE("Erro!", "Ocorreu um Erro! Tente mais tarde.");
            }
        } catch (Exception ex) {
            MensagemE(ex.getMessage(), "Ocorreu um Erro! Tente mais tarde.");
            
        }
    }
    
    public void atualizarServidor(Servidor servidor, String paginaListagem, String tipo) {
        try {
            Servidor servidorBuscado = repositorioServidor.BuscarServidor(servidor);
            servidor.setId(servidorBuscado.getId());
            
            servidor.setOrientador(servidorBuscado.isOrientador());
            servidor.setSupervisor(servidorBuscado.isSupervisor());
            servidor.setCoordenadorCurso(servidorBuscado.isCoordenadorCurso());
            servidor.setMyDType(servidorBuscado.getMyDType());
            
            
            
            if (tipo.equals("Orientador")) {
                servidor.setOrientador(true);
            }
            if (tipo.equals("Supervisor")) {
                servidor.setSupervisor(true);
                
            }
            if (tipo.equals("Coordenador")) {
                servidor.setCoordenadorCurso(true);
            }
            
            if (repositorioServidor.Salvar(servidor)) {
                
                MensagemS("Sucesso!", tipo + " salvo com sucesso!");
                if (!(paginaListagem.equals(""))) {
                    FacesContext.getCurrentInstance().getExternalContext()
                            .redirect(paginaListagem);
                }
                
            } else {
                
                MensagemE("Erro!", "Ocorreu um Erro! Tente mais tarde.");
            }
            
        } catch (Exception ex) {
            
        }
        
    }
    
    protected void MensagemS(String titulo, String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, titulo);
        context.addMessage(null, m);
        context.getExternalContext().getFlash().setKeepMessages(true);
    }
    
    protected void MensagemE(String titulo, String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        
        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, titulo);
        context.addMessage(null, m);
        context.getExternalContext().getFlash().setKeepMessages(true);
    }
    
}
