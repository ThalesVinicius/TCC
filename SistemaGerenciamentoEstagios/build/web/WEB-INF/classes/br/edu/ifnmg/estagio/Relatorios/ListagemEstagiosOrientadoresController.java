package br.edu.ifnmg.estagio.Relatorios;

import br.edu.ifnmg.estagio.entidades.Estagio;
import br.edu.ifnmg.estagio.entidades.Orientacoes;
import br.edu.ifnmg.estagio.entidades.Servidor;

import br.edu.ifnmg.estagio.repositorios.orientacaoRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;

import org.primefaces.event.SelectEvent;

@Named(value = "listagemEstagioOrientadoresController")
@ViewScoped
public class ListagemEstagiosOrientadoresController
        extends ControllerGenericoRelatorio<Orientacoes>
        implements Serializable {

    public ListagemEstagiosOrientadoresController() {
        setArquivoSaida("RelatorioDeclaracaoEstagioOrientadores");
        filtro = new Orientacoes();
        filtro.setEstagio(new Estagio());
        filtro.getEstagio().setSituacao("Concluido");
        filtro.setEstagio(estagio = new Estagio());

    }

    @EJB
    orientacaoRepositorio daoOrientacao;
    Orientacoes filtro;
    Estagio estagio;

    @Override
    protected Map<String, Object> carregaParametros() {
        try {
            Map<String, Object> tmp = getParametrosComuns();
            tmp.put("data", new Date());
            tmp.put("orientador", filtro.getOrientador().getNome());
            tmp.put("texto", textoDeclaracao());
//
//            tmp.put("logo", "C:\\glassfish4\\diretorioArquivos\\ifnmg.png");
//            tmp.put("brasao", "C:\\glassfish4\\diretorioArquivos\\brasaoSimbolo.gif");

//            tmp.put("logo", "C:\\Users\\Thales\\Desktop\\TCC\\SistemaGerenciamentoEstagios\\web\\resources\\img\\ifnmg.png");
//            tmp.put("brasao", "C:\\Users\\Thales\\Desktop\\TCC\\SistemaGerenciamentoEstagios\\web\\resources\\img\\brasaoSimbolo.gif");

            tmp.put("logo", "/home/ifnmg/sge/diretorioArquivos/ifnmg.png");
            tmp.put("brasao", "/home/ifnmg/sge/diretorioArquivos/brasaoSimbolo.gif");
            return tmp;
        } catch (MalformedURLException ex) {
            return new HashMap<>();
        }
    }

    @Override
    public List<Orientacoes> getDados() {

        return daoOrientacao.Buscar(filtro);
    }

    public void SelectdOrientador(SelectEvent event) {
        filtro.setOrientador((Servidor) event.getObject());
    }

    public Orientacoes getFiltro() {
        return filtro;
    }

    public void setFiltro(Orientacoes filtro) {
        this.filtro = filtro;
    }

    private String textoDeclaracao() {

        List<Orientacoes> OrientacoesBuscadas;
        OrientacoesBuscadas = getDados();

        //Total de Orientações
        int totalOrientacoes = 0;

        for (Orientacoes ori : OrientacoesBuscadas) {
            totalOrientacoes = totalOrientacoes + ori.getQtd_orientacoes();
        }

        String texto = "";

        try {
            String pattern = "dd/MM/yyyy";
            DateFormat df = new SimpleDateFormat(pattern);
            String inicio = df.format(filtro.getInicioOrientacao());
            String termino = df.format(filtro.getTerminoOrientacao());

            //Configuracão de texto da declaração
            texto = filtro.getOrientador().getNome()
                    + " realizou " + totalOrientacoes
                    + " orientações entre o período de "
                    + inicio
                    + " á "
                    + termino;

        } catch (Exception ec) {

        }

        return texto;
    }

}
