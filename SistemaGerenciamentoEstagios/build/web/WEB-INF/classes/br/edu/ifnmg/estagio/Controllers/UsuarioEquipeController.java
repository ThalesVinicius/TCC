package br.edu.ifnmg.estagio.Controllers;

import br.edu.ifnmg.estagio.entidades.Usuario;
import br.edu.ifnmg.estagio.repositorios.UsuarioRepositorio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

@Named(value = "usuarioController")
@ConversationScoped
public class UsuarioEquipeController extends ControllerGenerico<Usuario> implements Serializable {

    private boolean funcaoEscolha = false;

    public UsuarioEquipeController() {
        super("editarAreaPublica.xhtml", "editarUsuario.xhtml");
        entidade = new Usuario();
        entidade.setValidado(false);
        filtro = new Usuario();
        funcaoEscolha = false;

    }

    @EJB
    private UsuarioRepositorio repositorio;

    @PostConstruct
    public void configurar() {
        setDao(repositorio);

    }

    public String limparfiltrosListagem() {
        filtro = new Usuario();
        return paginaListagem;
    }

    public boolean isFuncaoEscolha() {
        return funcaoEscolha;
    }

    public void setFuncaoEscolha(boolean funcaoEscolha) {
        this.funcaoEscolha = funcaoEscolha;
    }

//    public void rendirecionar() {
//        if (entidade.getFuncao().equals("outra")) {
//            this.funcaoEscolha = true;
//            Usuario userTemp = entidade;
//            RequestContext context = RequestContext.getCurrentInstance();
//
//            context.update("form:painelUser");
//            entidade = userTemp;
//        } else {
//            this.funcaoEscolha = false;
//
//        }
//
//    }

    public void validarUsuario(Usuario usuario) {
        entidade = usuario;
        entidade.setValidado(true);
        if (repositorio.Salvar(entidade)) {
            MensagemSucesso("Sucesso", "Usuário validado com sucesso");
        } else {
            MensagemErro("Erro", "Tente novamente mais tarde");
        }
    }

}
