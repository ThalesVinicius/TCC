package br.edu.ifnmg.estagio.Relatorios;

import br.edu.ifnmg.estagio.entidades.Estagio;
import br.edu.ifnmg.estagio.entidades.Matricula;
import br.edu.ifnmg.estagio.repositorios.EstagioRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Thales
 */
@Named(value = "relatorioEstagiosController")
@RequestScoped
public class RelatorioEstagiosController
        extends ControllerGenericoRelatorio<Estagio>
        implements Serializable {

    Estagio filtro;

    public RelatorioEstagiosController() {
        setArquivoSaida("RelatorioListagemEstagios");
        setRelatorio("RelatorioListagemEstagios.jasper");
        filtro = new Estagio();
        filtro.setMatricula(new Matricula());
    }

    @EJB
    EstagioRepositorio daoEstagio;

    @Override
    protected Map<String, Object> carregaParametros() {

        try {
            Map<String, Object> tmp = getParametrosComuns();
            tmp.put("data", new Date());
            
//            tmp.put("logo", "C:\\glassfish4\\diretorioArquivos\\ifnmg.png");
//            tmp.put("brasao", "C:\\glassfish4\\diretorioArquivos\\brasaoSimbolo.gif");

            tmp.put("logo", "/home/ifnmg/sge/diretorioArquivos/ifnmg.png");
            tmp.put("brasao", "/home/ifnmg/sge/diretorioArquivos/brasaoSimbolo.gif");
            
//            tmp.put("logo", "C:\\Users\\Thales\\Desktop\\TCC\\SistemaGerenciamentoEstagios\\web\\resources\\img\\ifnmg.png");
//            tmp.put("brasao", "C:\\Users\\Thales\\Desktop\\TCC\\SistemaGerenciamentoEstagios\\web\\resources\\img\\brasaoSimbolo.gif");
            return tmp;
        } catch (MalformedURLException ex) {
            return new HashMap<>();
        }
    }

    @Override
    public List<Estagio> getDados() {
        return daoEstagio.Buscar(filtro);
    }

    public String limparfiltrosListagem() {
        filtro = new Estagio();
        return "relatorioListagemEstagios.xhtml";
    }

    public String filtrar() {
        return "relatorioListagemEstagios.xhtml";
    }

    public Estagio getFiltro() {
        return filtro;
    }

    public void setFiltro(Estagio filtro) {
        this.filtro = filtro;
    }

}
