package br.edu.ifnmg.estagio.repositorios;

import br.edu.ifnmg.estagio.entidades.Servidor;
import javax.ejb.Local;

@Local
public interface CoordenadorRepositorio extends Repositorio<Servidor> {

       public Servidor BuscarCoordenador(Servidor filtro);
}
