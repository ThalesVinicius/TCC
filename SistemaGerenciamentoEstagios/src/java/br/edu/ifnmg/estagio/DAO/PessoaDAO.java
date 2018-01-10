package br.edu.ifnmg.estagio.DAO;

import br.edu.ifnmg.estagio.entidades.Pessoa;
import br.edu.ifnmg.estagio.repositorios.PessoaRepositorio;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class PessoaDAO extends DAOGenerico<Pessoa> implements PessoaRepositorio {

    public PessoaDAO() {
        super(Pessoa.class);
    }

    @Override
    public Pessoa Abrir(String login) {
        return IgualA("email", login).Abrir();
    }

    @Override
    public List<Pessoa> Buscar(Pessoa filtro) {
        return Like("nome", filtro.getNome())
                .IgualA("validaddo", filtro.getValidado())
            
                .OrderBy("nome", "ASC")
                .Buscar();
    }

}
