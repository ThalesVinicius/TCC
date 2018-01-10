package br.edu.ifnmg.estagio.repositorios;

import br.edu.ifnmg.estagio.entidades.Servidor;
import javax.ejb.Local;

@Local
public interface ServidorRepositorio extends Repositorio<Servidor> {

    public Servidor BuscarServidor(Servidor filtro);
}
