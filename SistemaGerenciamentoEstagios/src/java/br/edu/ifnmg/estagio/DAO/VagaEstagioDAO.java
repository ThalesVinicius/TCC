package br.edu.ifnmg.estagio.DAO;

import br.edu.ifnmg.estagio.entidades.vagaEstagio;
import br.edu.ifnmg.estagio.repositorios.vagaEstagioRepositorio;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class VagaEstagioDAO extends DAOGenerico<vagaEstagio> implements vagaEstagioRepositorio {

    public VagaEstagioDAO() {
        super(vagaEstagio.class);
    }

    @Override
    public List<vagaEstagio> Buscar(vagaEstagio filtro) {

        return Like("tituloVaga", filtro.getTituloVaga())
                .IgualA("TipoVaga", filtro.getTipoVaga())
                .IgualA("VagaDeficiente", filtro.getVagaDeficiente())
                .IgualA("empresaConcedente", filtro.getEmpresaConcedente())
                .IgualA("validado", filtro.getValidado())
                .IgualA("curso", filtro.getCurso())
                .IgualA("id", filtro.getId())
                .Buscar();
    }

}
