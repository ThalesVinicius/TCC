package br.edu.ifnmg.estagio.DAO;


import br.edu.ifnmg.estagio.entidades.Matricula;
import br.edu.ifnmg.estagio.repositorios.MatriculaRepositorio;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class MatriculaDAO extends DAOGenerico<Matricula> implements MatriculaRepositorio {

    public MatriculaDAO() {
        super(Matricula.class);
    }

    @Override
    public List<Matricula> Buscar(Matricula filtro) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}