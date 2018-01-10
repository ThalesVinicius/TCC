package br.edu.ifnmg.estagio.DAO;

import br.edu.ifnmg.estagio.entidades.Servidor;
import br.edu.ifnmg.estagio.repositorios.CoordenadorRepositorio;

import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class CoordenadorDAO extends DAOGenerico<Servidor> implements CoordenadorRepositorio {

    public CoordenadorDAO() {
        super(Servidor.class);
    }

    @Override
    public List<Servidor> Buscar(Servidor filtro) {

        IgualA("cpf", filtro.getCpf())
                .Like("nome", filtro.getNome())
                .IgualA("email", filtro.getEmail())
                .IgualA("id", filtro.getId())
                .IgualA("coordenadorCurso", true)
                .OrderBy("nome", "ASC");

        return Buscar();
    }

    @Override
    public Servidor BuscarCoordenador(Servidor filtro) {
        return IgualA("nome", filtro.getNome())
                .Abrir();
    }

}
