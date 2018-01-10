package br.edu.ifnmg.estagio.repositorios;


import br.edu.ifnmg.estagio.entidades.Servidor;
import javax.ejb.Local;

@Local
public interface OrientadorRepositorio extends Repositorio<Servidor> {
     public Servidor BuscarOrientador(Servidor filtro);

}
