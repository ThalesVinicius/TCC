package br.edu.ifnmg.estagio.Controllers;

import br.edu.ifnmg.estagio.entidades.Empresa;
import br.edu.ifnmg.estagio.entidades.vagaEstagio;
import br.edu.ifnmg.estagio.repositorios.vagaEstagioRepositorio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "vagaEstagioController")
@ConversationScoped
public class VagaEstagioController extends ControllerGenerico<vagaEstagio> implements Serializable {

    public VagaEstagioController() {
        super("listagemVagasEstagios.xhtml", "editarVagaEstagio.xhtml");
        entidade = new vagaEstagio();
        filtro = new vagaEstagio();
        filtro.setValidado(true);

    }

    @EJB
    private vagaEstagioRepositorio repositorio;

    @PostConstruct
    public void configurar() {
        setDao(repositorio);
    }

    public String limparfiltrosListagem() {
        filtro = new vagaEstagio();
        return paginaListagem;
    }

    public void setarFiltro(Empresa filtroSetado) {
        filtro.setEmpresaConcedente(filtroSetado);
    }

    public List<vagaEstagio> getListaFiltrada(Empresa empresaConcedente, boolean reandolyFiltro) {

        if (reandolyFiltro == true) {
            filtro.setEmpresaConcedente(empresaConcedente);
            return repositorio.Buscar(filtro);
        } else {
            return repositorio.Buscar(filtro);
        }
    }

    public Empresa setarEmpresaConcedente(Empresa empresaLogada) {
        entidade.setEmpresaConcedente(empresaLogada);

        return empresaLogada;
    }

    public void finalizarVaga(vagaEstagio item) {
        entidade = item;
        entidade.setStatus("Finalizada");

        try {
//            if (!(item.getStatus().contains("Finalizar"))) {
            if (repositorio.Salvar(entidade)) {
                MensagemSucesso("Sucesso", "Vaga Finalizada");
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect("listagemVagasEstagios.xhtml");
            } else {

                MensagemErro("Erro", "Consulte o administrador");
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect("listagemVagasEstagios.xhtml");
            }
//            } else {
//                MensagemErro("Atenção", "Esta vaga ja foi finalizada !!!");
//                FacesContext.getCurrentInstance().getExternalContext()
//                        .redirect("listagemVagasEstagios.xhtml");
//            }
        } catch (Exception ex) {
            MensagemErro("Erro", "Consulte o administrador");
        }

    }

    public void situacaoVaga(Boolean situacao) {
        entidade.setValidado(situacao);
        filtro.setValidado(situacao);

    }

}
