package br.edu.ifnmg.estagio.DAO;

import br.edu.ifnmg.estagio.entidades.Servidor;
import br.edu.ifnmg.estagio.repositorios.SupervisorRepositorio;

import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class SupervisorDAO extends DAOGenerico<Servidor> implements SupervisorRepositorio {

    public SupervisorDAO() {
        super(Servidor.class);
    }

    @Override
    public List<Servidor> Buscar(Servidor filtro) {

        IgualA("cpf", filtro.getCpf())
                .Like("nome", filtro.getNome())
                .IgualA("email", filtro.getEmail())
                .IgualA("id", filtro.getId())
                .IgualA("Instituicao", filtro.getInstituicao())
                .IgualA("supervisor", true)
                .OrderBy("nome", "ASC");

        return Buscar();
    }

    @Override
    public Servidor BuscarSupervisor(Servidor filtro) {
        return IgualA("nome", filtro.getNome())
                .Abrir();
    }

}
