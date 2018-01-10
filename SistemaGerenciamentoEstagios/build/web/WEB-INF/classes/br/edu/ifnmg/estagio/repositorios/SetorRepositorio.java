package br.edu.ifnmg.estagio.repositorios;

import br.edu.ifnmg.estagio.entidades.Setor;
import javax.ejb.Local;

@Local
public interface SetorRepositorio extends Repositorio<Setor> {

    public Setor BuscarSetor(Setor filtro);

}
