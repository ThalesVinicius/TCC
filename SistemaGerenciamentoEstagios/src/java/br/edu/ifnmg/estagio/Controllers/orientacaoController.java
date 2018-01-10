package br.edu.ifnmg.estagio.Controllers;

import br.edu.ifnmg.estagio.entidades.Orientacoes;
import br.edu.ifnmg.estagio.entidades.Servidor;
import br.edu.ifnmg.estagio.repositorios.orientacaoRepositorio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

@Named(value = "orientacaoController")
@ConversationScoped
public class orientacaoController extends ControllerGenerico<Orientacoes> implements Serializable {

    public orientacaoController() {
        super("listagemEstagios.xhtml", "listagemEstagios.xhtml");
        entidade = new Orientacoes();
        filtro = new Orientacoes();
    }

    @EJB
    private orientacaoRepositorio repositorio;

    @PostConstruct
    public void configurar() {
        setDao(repositorio);
    }

    public String limparfiltrosListagem() {
        filtro = new Orientacoes();
        return paginaListagem;
    }

    private int TotalOrientacoes = 0;

    public int getTotalOrientacoes() {
        return TotalOrientacoes;
    }

    public void setTotalOrientacoes(int TotalOrientacoes) {
        this.TotalOrientacoes = TotalOrientacoes;
    }

    public List<Orientacoes> buscarOrientacoes(Servidor orientador) {
        TotalOrientacoes = 0;
        filtro.setOrientador(orientador);

        List<Orientacoes> orientacoes = repositorio.Buscar(filtro);

        for (Orientacoes est : orientacoes) {
            TotalOrientacoes = TotalOrientacoes + est.getQtd_orientacoes();
        }
        return orientacoes;
    }
}
