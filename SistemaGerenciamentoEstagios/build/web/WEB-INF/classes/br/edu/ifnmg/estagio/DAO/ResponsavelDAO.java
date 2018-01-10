package br.edu.ifnmg.estagio.DAO;

import br.edu.ifnmg.estagio.entidades.Representante;
import br.edu.ifnmg.estagio.repositorios.ResponsavelRepositorio;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class ResponsavelDAO extends DAOGenerico<Representante> implements ResponsavelRepositorio {

    public ResponsavelDAO() {
        super(Representante.class);
    }

    @Override
    public List<Representante> Buscar(Representante filtro) {

        return Like("nome", filtro.getNome())
                .IgualA("id", filtro.getId())
                .OrderBy("nome",  "ASC")
                .Buscar();
    }

    @Override
    public Representante BuscarRepresentante(Representante filtro) {
       return IgualA("nome", filtro.getNome())
                .Abrir();
    }
    
   

  

  

}
