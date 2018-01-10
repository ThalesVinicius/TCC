package br.edu.ifnmg.estagio.DAO;


import br.edu.ifnmg.estagio.entidades.Endereco;
import br.edu.ifnmg.estagio.repositorios.EnderecoRepositorio;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class EnderecoDAO extends DAOGenerico<Endereco> implements EnderecoRepositorio {

    public EnderecoDAO() {
        super(Endereco.class);
    }

    @Override
    public List<Endereco> Buscar(Endereco filtro) {
        throw new UnsupportedOperationException("Not supported yet.");

    }

}