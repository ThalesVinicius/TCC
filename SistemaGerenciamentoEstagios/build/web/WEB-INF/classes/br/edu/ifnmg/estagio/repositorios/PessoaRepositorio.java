package br.edu.ifnmg.estagio.repositorios;

import br.edu.ifnmg.estagio.entidades.Pessoa;
import javax.ejb.Local;

/*
 *
 * @author thales
 */
@Local
public interface PessoaRepositorio extends Repositorio<Pessoa> {

    public Pessoa Abrir(String login);

}
