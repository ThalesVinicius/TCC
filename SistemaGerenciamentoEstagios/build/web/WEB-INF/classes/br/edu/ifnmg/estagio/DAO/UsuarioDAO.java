package br.edu.ifnmg.estagio.DAO;


import br.edu.ifnmg.estagio.entidades.Usuario;
import br.edu.ifnmg.estagio.repositorios.UsuarioRepositorio;

import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class UsuarioDAO extends DAOGenerico<Usuario> implements UsuarioRepositorio {

    public UsuarioDAO() {
        super(Usuario.class);
    }

    @Override
    public List<Usuario> Buscar(Usuario filtro) {

        return IgualA("cpf", filtro.getCpf())
                .Like("nome", filtro.getNome())
                .IgualA("email", filtro.getEmail())
                .IgualA("id", filtro.getId())
                .OrderBy("nome", "ASC")
                .Buscar();
    }

    

}
