package br.edu.ifnmg.estagio.Controllers;

import br.edu.ifnmg.estagio.entidades.Area;
import br.edu.ifnmg.estagio.entidades.Empresa;
import br.edu.ifnmg.estagio.entidades.Endereco;
import br.edu.ifnmg.estagio.entidades.Representante;
import br.edu.ifnmg.estagio.entidades.Setor;
import br.edu.ifnmg.estagio.repositorios.EmpresaRepositorio;
import br.edu.ifnmg.estagio.repositorios.ResponsavelRepositorio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

@Named(value = "empresaController")
@ConversationScoped
public class EmpresaController extends ControllerGenerico<Empresa> implements Serializable {

    //Variaveis de  agregado de Endereço
    private Endereco enderecoAdcionado;
    private Representante responsavelAdcionado;
    private Area areas_estagios;
    private Empresa empresaLogada;
    private Setor setorEmpresa;

    //Variaveis de Controle de Interface
    private Boolean habDesCamposEndereco = false;
    private Boolean habDesCamposResponsavel = false;
    private Boolean habDesCamposAreas = false;
    private Boolean habDesCamposSetores = false;

    public EmpresaController() {
        super("listagemEmpresas.xhtml", "editarEmpresa.xhtml");
        entidade = new Empresa();
        entidade.setTipoEmpresa("Empresa");
        filtro = new Empresa();
        enderecoAdcionado = new Endereco();
        responsavelAdcionado = new Representante();
        areas_estagios = new Area();
        // setorEmpresa = new Setor();

    }

    @EJB
    private EmpresaRepositorio repositorio;

    @EJB
    private ResponsavelRepositorio represetante;

    @PostConstruct
    public void configurar() {
        setDao(repositorio);

    }

    public String limparfiltrosListagem() {
        filtro = new Empresa();
        return paginaListagem;
    }

    //Metodos de adcionar, remover e salvar endereços
    public void removeEndereco(Endereco enderecoSelecionado) {
        entidade.removeEndereco(enderecoSelecionado);
        enderecoAdcionado = new Endereco();
        MensagemSucesso("Sucesso!", "Endereco removido com sucesso !!!");
    }

    public void salvarEndereco() {
        entidade.salvarEndereco(enderecoAdcionado);
        enderecoAdcionado = new Endereco();
        MensagemSucesso("Sucesso!", "Endereco salvo com sucesso !!!");

    }

    //Metodos de adcionar, remover e salvar responsaveis
    public void removeResponsavel(Representante responsavelSelecionado) {
        entidade.removeResponsavel(responsavelSelecionado);
        responsavelAdcionado = new Representante();
        MensagemSucesso("Sucesso!", "Resposavel removido com sucesso !!!");
    }

    public void salvarResponsavel() {
        Representante represetanteTemp;

        represetanteTemp = represetante.BuscarRepresentante(responsavelAdcionado);

        if (represetanteTemp == null) {
            responsavelAdcionado.setValidado(true);
            responsavelAdcionado.setAcesso("empresa/index.xhtml");

            entidade.salvarResponsavel(responsavelAdcionado);
            responsavelAdcionado = new Representante();
            MensagemSucesso("Sucesso!", "Resposavel salvo com sucesso !!!");
        } else {
            habDesCamposResponsavel = true;
            MensagemErro("Erro", "Já existe uma pessoa Cadastrada com este nome");
        }
    }

    //Metodos de adcionar, remover e salvar 
    public void removeArea(Area areaSelecionado) {
        entidade.removeArea(areaSelecionado);
        areas_estagios = new Area();
        MensagemSucesso("Sucesso!", "Registro removido com sucesso !!!");
    }

    public void salvarArea() {
        if (areas_estagios != null) {
            entidade.salvarArea(areas_estagios);
            areas_estagios = new Area();
            MensagemSucesso("Sucesso!", "Registro salvo com sucesso !!!");
        } else {
            MensagemErro("Erro", "Selecione uma área");

        }

    }

    public void SelectArea(SelectEvent itemSelect) {
        areas_estagios = (Area) itemSelect.getObject();
    }

    ///////////////////////////////////////////////////////
    public void removeSetor(Setor setorSelecionado) {
        entidade.removeSetor(setorSelecionado);
        setorEmpresa = new Setor();
        MensagemSucesso("Sucesso!", "Registro removido com sucesso !!!");
    }

    public void salvarSetor() {
        if (setorEmpresa != null) {
        entidade.salvarSetor(setorEmpresa);
        setorEmpresa = new Setor();
        MensagemSucesso("Sucesso!", "Registro salvo com sucesso !!!");
        } else {
            MensagemErro("Erro", "Selecione uma setor");
        }

    }

    public void SelectSetor(SelectEvent itemSelect) {
        setorEmpresa = (Setor) itemSelect.getObject();

    }

    /////////////////////////////////////////////////////////////////////////////
    ///////  GET E SETRS  DOS AGREGADOS ///////////////////
    /////////////////////////////////////////////////////////
    public Endereco getEnderecoAdcionado() {
        return enderecoAdcionado;
    }

    public void setEnderecoAdcionado(Endereco enderecoAdcionado) {
        this.enderecoAdcionado = enderecoAdcionado;
    }

    public Representante getResponsavelAdcionado() {
        return responsavelAdcionado;
    }

    public void setResponsavelAdcionado(Representante responsavelAdcionado) {
        this.responsavelAdcionado = responsavelAdcionado;
    }

    public Area getAreas_estagios() {
        return areas_estagios;
    }

    public void setAreas_estagios(Area areas_estagios) {
        this.areas_estagios = areas_estagios;
    }

    public Setor getSetorEmpresa() {
        return setorEmpresa;
    }

    public void setSetorEmpresa(Setor setorEmpresa) {
        this.setorEmpresa = setorEmpresa;
    }

    ////////////////////////////////////////////////////////////
    ///////  GET E SETRS  DAS VARIAVEIS DE CONTROLE INTERFACE ///////////////////
    /////////////////////////////////////////////////////////   
    public Boolean getHabDesCamposEndereco() {
        return habDesCamposEndereco;
    }

    public void setHabDesCamposEndereco(Boolean habDesCamposEndereco) {
        this.habDesCamposEndereco = habDesCamposEndereco;
    }

    public Boolean getHabDesCamposResponsavel() {
        return habDesCamposResponsavel;
    }

    public void setHabDesCamposResponsavel(Boolean habDesCamposResponsavel) {
        this.habDesCamposResponsavel = habDesCamposResponsavel;
    }

    public Boolean getHabDesCamposAreas() {
        return habDesCamposAreas;
    }

    public void setHabDesCamposAreas(Boolean habDesCamposAreas) {
        this.habDesCamposAreas = habDesCamposAreas;
    }

    public Boolean getHabDesCamposSetores() {
        return habDesCamposSetores;
    }

    public void setHabDesCamposSetores(Boolean habDesCamposSetores) {
        this.habDesCamposSetores = habDesCamposSetores;
    }

////////////////////////////////////////////////////////////
    public void salvarEmpresaDialog() {
        if (!(entidade.getNomeEmpresa().equals(""))) {
            if (!(responsavelAdcionado.getNome().equals(""))) {
                if (!(responsavelAdcionado.getEmail().equals(""))) {

                    if (represetante.BuscarRepresentante(responsavelAdcionado) == null) {
                        responsavelAdcionado.setValidado(true);
                        responsavelAdcionado.setAcesso("empresa/index.xhtml");

                        entidade.salvarResponsavel(responsavelAdcionado);
                        responsavelAdcionado = new Representante();

                        entidade.salvarEndereco(enderecoAdcionado);

                        if (repositorio.Salvar(entidade)) {

                            enderecoAdcionado = new Endereco();
                            responsavelAdcionado = new Representante();
                            // entidade = new Empresa();
                            MensagemSucesso("Sucesso!", "Cadastro de Empresa salvo com sucesso!");
                        } else {
                            MensagemErro("Erro", "Tente novamente mais tarde");
                        }

                    } else {
                        habDesCamposResponsavel = true;
                        MensagemErro("Erro", "Já existe uma pessoa Cadastrada com este nome");
                    }
                } else {
                    MensagemErro("Erro", "Por favor preencha o email do Representante");
                }
            } else {
                MensagemErro("Erro", "Por favor preencha o nome do Representante");
            }
        } else {
            MensagemErro("Erro", "Por favor preencha o nome da Empresa");
        }

    }

    public Empresa buscarEmpresa() {
        try {
            return repositorio.BuscarEmpresa(entidade);
        } catch (Exception ex) {
            return null;
        }
    }

    public Empresa buscarEmpresaLogada(Long id) {
        try {
            return repositorio.BuscarEmpresaLogada(id.toString());
        } catch (Exception ex) {
            MensagemErro("Erro", "Consulte o administrador");

            return null;
        }
    }

    public void setarFiltroEmpresa(Empresa empresa) {
        filtro = empresa;
    }

    public Empresa getEmpresaLogada() {
        return empresaLogada;
    }

    public void setEmpresaLogada(Empresa empresaLogada) {
        this.empresaLogada = empresaLogada;
    }

    public List<Empresa> getListaFiltrada(Empresa empresaConcedente) {
        this.filtro = empresaConcedente;
        return repositorio.Buscar(filtro);
    }

}
