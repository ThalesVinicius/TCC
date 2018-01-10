package br.edu.ifnmg.estagio.DAO;

import br.edu.ifnmg.estagio.entidades.BoletimEstagio;
import br.edu.ifnmg.estagio.repositorios.BoletimRepositorio;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class BoletimDAO extends DAOGenerico<BoletimEstagio> implements BoletimRepositorio {

    public BoletimDAO() {
        super(BoletimEstagio.class);
    }

    @Override
    public List<BoletimEstagio> Buscar(BoletimEstagio filtro) {

        return IgualA("aluno", filtro.getAluno())
                .Buscar();
    }

}
