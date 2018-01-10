package br.edu.ifnmg.estagio.Relatorios;

import br.edu.ifnmg.estagio.entidades.Estagio;
import br.edu.ifnmg.estagio.repositorios.AlunoRepositorio;
import br.edu.ifnmg.estagio.repositorios.EstagioRepositorio;
import javax.inject.Named;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Thales
 */
@Named(value = "boletimEstagioController")
@ViewScoped
public class BoletimEstagioController
        extends ControllerGenericoRelatorio<Estagio>
        implements Serializable {

    public BoletimEstagioController() {
        setArquivoSaida("RelatorioBoletimEstagios");

        filtro = new Estagio();
        filtro.setSituacao("Concluido");

    }

    @Inject
    protected Conversation conversacao;

    @PostConstruct
    public void configurar() {
        this.datasInicioTermino();
    }

    @EJB
    EstagioRepositorio daoEstagio;

    @EJB
    AlunoRepositorio daoEstagiario;

    Estagio filtro;

    @Override
    protected Map<String, Object> carregaParametros() {

        try {
            Map<String, Object> tmp = getParametrosComuns();
            tmp.put("data", new Date());
////
//            tmp.put("logo", "C:\\glassfish4\\diretorioArquivos\\ifnmg.png");
//           tmp.put("brasao", "C:\\glassfish4\\diretorioArquivos\\brasaoSimbolo.gif");

//            tmp.put("logo", "/home/ifnmg/sge/diretorioArquivos/ifnmg.png");
//            tmp.put("brasao", "/home/ifnmg/sge/diretorioArquivos/brasaoSimbolo.gif");
            
            tmp.put("logo", "C:\\Users\\Thales\\Desktop\\TCC\\SistemaGerenciamentoEstagios\\web\\resources\\img\\ifnmg.png");
            tmp.put("brasao", "C:\\Users\\Thales\\Desktop\\TCC\\SistemaGerenciamentoEstagios\\web\\resources\\img\\brasaoSimbolo.gif");
           
            this.datasInicioTermino();
            tmp.put("inicioA", inicio);
            tmp.put("terminoA", termino);
            tmp.put("chcTotal", chcTotal());
            tmp.put("situacao", this.situacao);
            return tmp;
        } catch (MalformedURLException ex) {
            return new HashMap<>();
        }
    }

    @Override
    public List<Estagio> getDados() {

        return daoEstagio.Buscar(filtro);
    }

    public Estagio getFiltro() {
        return filtro;
    }

    public void setFiltro(Estagio filtro) {
        this.filtro = filtro;
    }

    private int chcTotal() {

        List<Estagio> estagiosBuscados;
        estagiosBuscados = getDados();

        int chc = 0;

        for (Estagio estagio : estagiosBuscados) {
            chc = chc + estagio.getChc();
        }

        if (filtro.getMatricula().getCurso().getCHME() > chc) {
            this.situacao = "Reprovado";
        } else {
            this.situacao = "Aprovado";
        }

        return chc;
    }

    private String inicio;
    private String termino;
    private String situacao;
    private int qtd_estagios = 0;
    private Date DataUltimoEstagio;

    private void datasInicioTermino() {

        List<Estagio> estagiosBuscados;
        estagiosBuscados = getDados();
        this.qtd_estagios = estagiosBuscados.size();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date tempInicio = null;
        Date tempTermino = null;

        for (Estagio estagio : estagiosBuscados) {

            if (tempInicio == null) {
                tempInicio = estagio.getInicio();
            }
            if (tempTermino == null) {
                tempTermino = estagio.getTermino();
            }

            if (estagio.getInicio().before(tempInicio)) {
                tempInicio = estagio.getInicio();
            }

            if (estagio.getTermino().after(tempTermino)) {
                tempTermino = estagio.getTermino();
            }

        }

        this.inicio = formatter.format(tempInicio);
        this.termino = formatter.format(tempTermino);

    }

    public Date getDataultimoEstagio() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            DataUltimoEstagio = formatter.parse(termino);
        } catch (Exception ex) {

        }
        return DataUltimoEstagio;
    }

    public int getQtd_estagios() {
        return qtd_estagios;
    }

    public void setarEstagiario(Long idEstagiario) {
        filtro.setEstagiario(daoEstagiario.Abrir(idEstagiario));

    }



 
}
