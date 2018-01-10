package br.edu.ifnmg.estagio.repositorios;

import br.edu.ifnmg.estagio.entidades.Servidor;
import javax.ejb.Local;

@Local
public interface SupervisorRepositorio extends Repositorio<Servidor> {

    public Servidor BuscarSupervisor(Servidor filtro);



}
