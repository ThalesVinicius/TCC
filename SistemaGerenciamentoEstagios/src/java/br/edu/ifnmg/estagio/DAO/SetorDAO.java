package br.edu.ifnmg.estagio.DAO;

import br.edu.ifnmg.estagio.entidades.Setor;
import br.edu.ifnmg.estagio.repositorios.SetorRepositorio;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class SetorDAO extends DAOGenerico<Setor> implements SetorRepositorio {

    public SetorDAO() {
        super(Setor.class);
    }

    @Override
    public List<Setor> Buscar(Setor filtro) {

        return Like("nome", filtro.getNome())
                .IgualA("id", filtro.getId())
                .Buscar();
    }
    
    @Override
    public Setor BuscarSetor(Setor filtro) {
        return IgualA("nome", filtro.getNome())
                .Abrir();
    }

}
