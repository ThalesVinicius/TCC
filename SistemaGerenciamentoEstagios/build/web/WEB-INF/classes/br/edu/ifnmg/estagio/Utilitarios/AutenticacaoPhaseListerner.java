/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.estagio.Utilitarios;

import br.edu.ifnmg.estagio.Infraestrutura.AutenticacaoService;
import br.edu.ifnmg.estagio.entidades.Pessoa;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

/**
 *
 * @author Thales
 */
public class AutenticacaoPhaseListerner implements PhaseListener {

    @Inject
    AutenticacaoService autenticacao;

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext fc = event.getFacesContext();
        ExternalContext ec = fc.getExternalContext();

        if (fc.getViewRoot() == null) {
            return;
        }

        String paginaAtual = fc.getViewRoot().getViewId();

        if (paginaAtual.contains("/coordenador/")
                || paginaAtual.contains("/empresa/")
                || paginaAtual.contains("/aluno/")
                || paginaAtual.contains("/orientador/")) {

            Pessoa pessoa = (Pessoa) autenticacao.getUsuarioCorrente();

            if (pessoa == null) {
                try {
                    ec.redirect(ec.getApplicationContextPath() + "/PaginaInicial.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(AutenticacaoPhaseListerner.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (paginaAtual.contains("/coordenador/")) {

                if (!pessoa.getAcesso().contains("coordenador/index.xhtml")) {
                    try {
                        ec.redirect(ec.getApplicationContextPath() + "/PaginaInicial.xhtml");
                    } catch (IOException ex) {
                        Logger.getLogger(AutenticacaoPhaseListerner.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if (paginaAtual.contains("/aluno/")) {
               
                if (!pessoa.getAcesso().contains("aluno/index.xhtml")) {
                    try {
                        ec.redirect(ec.getApplicationContextPath() + "/PaginaInicial.xhtml");
                    } catch (IOException ex) {
                        Logger.getLogger(AutenticacaoPhaseListerner.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            } else if (paginaAtual.contains("/empresa/")) {
             
                if (!(pessoa.getAcesso().contains("empresa/index.xhtml") || pessoa.getMyDType().equals("supervisor/index.xhtml"))) {

                    try {
                        ec.redirect(ec.getApplicationContextPath() + "/PaginaInicial.xhtml");
                    } catch (IOException ex) {
                        Logger.getLogger(AutenticacaoPhaseListerner.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

        }
    }

    @Override
    public void beforePhase(PhaseEvent event
    ) {

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }

}
