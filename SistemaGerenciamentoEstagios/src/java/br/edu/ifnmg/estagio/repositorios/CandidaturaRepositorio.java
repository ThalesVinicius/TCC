package br.edu.ifnmg.estagio.repositorios;

import br.edu.ifnmg.estagio.entidades.Candidatura;
import javax.ejb.Local;

@Local
public interface CandidaturaRepositorio extends Repositorio<Candidatura> {

    public Candidatura BuscarCandidatura(Candidatura filtro);
}
