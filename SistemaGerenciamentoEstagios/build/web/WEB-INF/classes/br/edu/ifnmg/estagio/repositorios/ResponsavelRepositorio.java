package br.edu.ifnmg.estagio.repositorios;

import br.edu.ifnmg.estagio.entidades.Representante;
import javax.ejb.Local;

@Local
public interface ResponsavelRepositorio extends Repositorio<Representante> {

    public Representante BuscarRepresentante(Representante filtro);

}
