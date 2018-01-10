package br.edu.ifnmg.estagio.Relatorios;

import br.edu.ifnmg.estagio.entidades.Estagio;
import br.edu.ifnmg.estagio.entidades.Matricula;
import br.edu.ifnmg.estagio.repositorios.EstagioRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Thales
 */
@Named(value = "relatorioEstagiosPorAnoController")
@RequestScoped
public class RelatorioEstagiosPorAnoController
        extends ControllerGenericoRelatorio<Estagio>
        implements Serializable {

    Estagio filtro;

    public RelatorioEstagiosPorAnoController() {
        setArquivoSaida("RelatorioEstagio");
        setRelatorio("RelatorioEstagiosAno.jasper");
        filtro = new Estagio();
        filtro.setMatricula(new Matricula());

    }

    @PostConstruct
    public void configura() {
        this.configurarAno();
    }

    @EJB
    EstagioRepositorio daoEstagio;

    @Override
    protected Map<String, Object> carregaParametros() {

        try {
            Map<String, Object> tmp = getParametrosComuns();
            tmp.put("data", new Date());
            tmp.put("texto", this.textoRelatorio());

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

    private Date periodo1 = new Date();
    private Date periodo2 = new Date();

    private String ano = "";

    @Override
    public List<Estagio> getDados() {
        return daoEstagio.BuscarEstagiosPorAno(filtro, periodo1, periodo2);
    }

    public String limparfiltrosListagem() {
        filtro = new Estagio();
        return "relatorioEstagioAno.xhtml";
    }

    public String filtrar() {
        return "relatorioEstagioAno.xhtml";
    }

    public Estagio getFiltro() {
        return filtro;
    }

    public void setFiltro(Estagio filtro) {
        this.filtro = filtro;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        String data1 = "01/01/" + ano.trim();
        String data2 = "31/12/" + ano.trim();

        try {
            String pattern = "dd/MM/yyyy";
            DateFormat df = new SimpleDateFormat(pattern);
            this.periodo1 = df.parse(data1);
            this.periodo2 = df.parse(data2);
        } catch (Exception ex) {

        }

        this.ano = ano;
    }

    public void configurarAno() {
        String pattern = "dd/MM/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        this.ano = df.format(new Date());
        this.ano = this.ano.substring(6, ano.length());
    }

    public String textoRelatorio() {
        List<Estagio> ListaEstagiosBuscados;
        ListaEstagiosBuscados = getDados();

        Integer qtd = 0;
        for (Estagio est : ListaEstagiosBuscados) {
            qtd = qtd + 1;
        }

        String txt = "";

        if (filtro.getSituacao() != null) {
            if (filtro.getSituacao().equals("Concluido")) {
                txt = "Ao longo do "
                        + "ano letivo de "
                        + this.ano.trim()
                        + ", "
                        + qtd.toString()
                        + " estágios foram "
                        + "finalizados"
                        + ", sendo:";

            } else if (filtro.getSituacao().equals("Em andamento")) {
                txt = "Ao longo do "
                        + "ano letivo de "
                        + this.ano.trim()
                        + ", "
                        + qtd.toString()
                        + " estágios foram "
                        + "iniciados"
                        + ", sendo:";

            }
        }

        return txt;
    }
}
