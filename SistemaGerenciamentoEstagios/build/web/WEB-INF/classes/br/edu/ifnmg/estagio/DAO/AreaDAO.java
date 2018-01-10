package br.edu.ifnmg.estagio.DAO;

import br.edu.ifnmg.estagio.entidades.Area;
import br.edu.ifnmg.estagio.repositorios.AreaRepositorio;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class AreaDAO extends DAOGenerico<Area> implements AreaRepositorio {

    public AreaDAO() {
        super(Area.class);
    }

    @Override
    public List<Area> Buscar(Area filtro) {

        return Like("nome", filtro.getNome())
                .IgualA("id", filtro.getId())
                .Buscar();
    }

}
