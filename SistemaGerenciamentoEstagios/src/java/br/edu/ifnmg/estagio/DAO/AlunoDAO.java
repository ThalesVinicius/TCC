package br.edu.ifnmg.estagio.DAO;

import br.edu.ifnmg.estagio.entidades.Aluno;
import br.edu.ifnmg.estagio.repositorios.AlunoRepositorio;

import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class AlunoDAO extends DAOGenerico<Aluno> implements AlunoRepositorio {

    public AlunoDAO() {
        super(Aluno.class);
    }

    @Override
    public List<Aluno> Buscar(Aluno filtro) {

        return IgualA("cpf", filtro.getCpf())
                .Like("nome", filtro.getNome())
                .IgualA("email", filtro.getEmail())
                .IgualA("id", filtro.getId())
                .IgualA("validado", filtro.getValidado())
                .OrderBy("nome", "ASC")
                .Buscar();
    }

    @Override
    public Aluno BuscarAluno(Aluno filtro) {
        return IgualA("nome", filtro.getNome())
                .IgualA("email", filtro.getEmail())
                .Abrir();
    }

}
