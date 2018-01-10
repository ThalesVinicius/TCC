package br.edu.ifnmg.estagio.DAO;

import br.edu.ifnmg.estagio.entidades.Servidor;
import br.edu.ifnmg.estagio.repositorios.OrientadorRepositorio;

import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class OrientadorDAO extends DAOGenerico<Servidor> implements OrientadorRepositorio {

    public OrientadorDAO() {
        super(Servidor.class);
    }

    @Override
    public List<Servidor> Buscar(Servidor filtro) {

        IgualA("cpf", filtro.getCpf())
                .Like("nome", filtro.getNome())
                .IgualA("email", filtro.getEmail())
                .IgualA("id", filtro.getId())
                .IgualA("orientador", true)
                .OrderBy("nome", "ASC");

        return Buscar();
    }

    @Override
    public Servidor BuscarOrientador(Servidor filtro) {
        return IgualA("nome", filtro.getNome())
                .IgualA("email", filtro.getEmail())
                .Abrir();
    }

}
