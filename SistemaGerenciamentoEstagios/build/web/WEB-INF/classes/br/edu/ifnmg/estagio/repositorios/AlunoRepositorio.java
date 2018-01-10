package br.edu.ifnmg.estagio.repositorios;

import br.edu.ifnmg.estagio.entidades.Aluno;
import javax.ejb.Local;

@Local
public interface AlunoRepositorio extends Repositorio<Aluno> {

 public Aluno BuscarAluno(Aluno filtro);
 
}
