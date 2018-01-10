package br.edu.ifnmg.estagio.repositorios;

import br.edu.ifnmg.estagio.entidades.Estagio;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface EstagioRepositorio extends Repositorio<Estagio> {

    public List<Estagio> BuscarEstagiosPorAno(Estagio filtro, Date Periodo1, Date Periodo2);
}
