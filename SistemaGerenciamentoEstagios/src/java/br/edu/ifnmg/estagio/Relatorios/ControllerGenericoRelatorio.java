/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.estagio.Relatorios;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Thales
 * @param <T>
 */
public abstract class ControllerGenericoRelatorio<T> {

    private String relatorio;
    private String arquivoSaida;
    private String acaoTela;

    private List<T> dados;

    protected abstract Map<String, Object> carregaParametros();

    public abstract List<T> getDados();

    public Map<String, Object> getParametrosComuns() throws MalformedURLException {
        HashMap<String, Object> par = new HashMap<>();
        par.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
        return par;
    }

    public void executaRelatorioPDF() throws JRException, IOException {

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String caminho = session.getServletContext().getContextPath();
       // System.err.println("caminho relatorio: " + caminho);

        try {
            if (!getDados().isEmpty()) {

                InputStream reportStream = getClass().getResourceAsStream(relatorio);

                JRDataSource ds = new JRBeanCollectionDataSource(getDados(), true);

                JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, carregaParametros(), ds);

                HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

                httpServletResponse.addHeader("Content-disposition", "inline; ;filename=" + getArquivoSaida() + ".pdf");

                ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();

                JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);

                FacesContext.getCurrentInstance().responseComplete();
             
            } else {
                MensagemSucesso("Atencao", "Não foram encontrados dados para o relatório");
            }
        } catch (JRException ex) {
            MensagemSucesso("Atencao", "Não foi possivel emitir o relatorio");
            Logger.getLogger(ControllerGenericoRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(String relatorio) {
        this.relatorio = relatorio;
    }

    public String getArquivoSaida() {
        return arquivoSaida;
    }

    public void setArquivoSaida(String arquivoSaida) {
        this.arquivoSaida = arquivoSaida;
    }

    public String getAcaoTela() {
        return acaoTela;
    }

    public void setAcaoTela(String acaoTela) {
        this.acaoTela = acaoTela;
    }

    protected void MensagemSucesso(String titulo, String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, titulo);
        context.addMessage(null, m);
        context.getExternalContext().getFlash().setKeepMessages(true);
    }
}
