package br.edu.ifnmg.estagio.DAO;

import br.edu.ifnmg.estagio.entidades.Coordenacao;
import br.edu.ifnmg.estagio.repositorios.CoordenacaoRepositorio;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class CoordenacaoDAO extends DAOGenerico<Coordenacao> implements CoordenacaoRepositorio {

    public CoordenacaoDAO() {
        super(Coordenacao.class);
    }

    @Override
    public List<Coordenacao> Buscar(Coordenacao filtro) {

        return IgualA("email", filtro.getEmail())
                .IgualA("id", filtro.getId())
                .Buscar();
    }

}
