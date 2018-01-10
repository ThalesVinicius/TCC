package br.edu.ifnmg.estagio.DAO;

import br.edu.ifnmg.estagio.entidades.Candidatura;

import br.edu.ifnmg.estagio.repositorios.CandidaturaRepositorio;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class CandidaturaDAO extends DAOGenerico<Candidatura> implements CandidaturaRepositorio {

    public CandidaturaDAO() {
        super(Candidatura.class);
    }

    @Override
    public List<Candidatura> Buscar(Candidatura filtro) {

        return IgualA("id", filtro.getId())
                .IgualA("candidato", filtro.getCandidato())
                .IgualA("vaga", filtro.getVaga())
                .Buscar();
    }

      public Candidatura BuscarCandidatura(Candidatura filtro) {
        return IgualA("candidato", filtro.getCandidato())
                .IgualA("vaga", filtro.getVaga())
                .Abrir();
    }
    
}
