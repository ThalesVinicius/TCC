package br.edu.ifnmg.estagio.DAO;

import br.edu.ifnmg.estagio.entidades.Servidor;
import br.edu.ifnmg.estagio.repositorios.ServidorRepositorio;

import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class ServidorDAO extends DAOGenerico<Servidor> implements ServidorRepositorio {

    public ServidorDAO() {
        super(Servidor.class);
    }

    @Override
    public List<Servidor> Buscar(Servidor filtro) {

        IgualA("cpf", filtro.getCpf())
                .Like("nome", filtro.getNome())
                .IgualA("email", filtro.getEmail())
                .IgualA("id", filtro.getId())
                .OrderBy("nome", "ASC");

        return Buscar();
    }

    @Override
    public Servidor BuscarServidor(Servidor filtro) {
        return IgualA("nome", filtro.getNome())
                .Abrir();
    }

}
