package br.edu.ifnmg.estagio.DAO;

import br.edu.ifnmg.estagio.entidades.Estagio;
import br.edu.ifnmg.estagio.repositorios.EstagioRepositorio;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Singleton;

@Singleton
public class EstagioDAO extends DAOGenerico<Estagio> implements EstagioRepositorio {

    public EstagioDAO() {
        super(Estagio.class);
    }

    @Override
    public List<Estagio> Buscar(Estagio filtro) {

        Map<String, Object> datas = new HashMap<>();

        IgualA("id", filtro.getId())
                .IgualA("estagiario", filtro.getEstagiario())
                .IgualA("empresaConcedente", filtro.getEmpresaConcedente())
                .IgualA("situacao", filtro.getSituacao())
                .IgualA("tipoEstagio", filtro.getTipoEstagio())
                .IgualA("Supervisor", filtro.getSupervisor());

        if (filtro.getInicio() != null && filtro.getTermino() != null) {

            /////////////////////////////////
            datas.put("data1", filtro.getInicio());
            datas.put("data2", filtro.getTermino());

            between("c.Inicio", datas);

        }

        if (filtro.getMatricula() != null) {
            if (filtro.getMatricula().getId() != null && filtro.getMatricula().getId() > 0) {
                IgualA("matricula", filtro.getMatricula());
            } else {
                Join("matricula", "m").IgualA("m.curso", filtro.getMatricula().getCurso());
            }
        }

        return Buscar();
    }

    @Override
    public List<Estagio> BuscarEstagiosPorAno(Estagio filtro, Date Periodo1, Date Periodo2) {

        Map<String, Object> datas = new HashMap<>();

        datas.put("data1", Periodo1);
        datas.put("data2", Periodo2);

        IgualA("tipoEstagio", filtro.getTipoEstagio());

        if (filtro.getSituacao() != null) {

            if (filtro.getSituacao().equals("Em andamento")) {
                between("c.Inicio", datas);
            } else if (filtro.getSituacao().equals("Concluido")) {
                IgualA("situacao", filtro.getSituacao());
                between("c.Termino", datas);
            }
        }

        if (filtro.getMatricula() != null) {
            if (filtro.getMatricula().getId() != null && filtro.getMatricula().getId() > 0) {
                IgualA("matricula", filtro.getMatricula());
            } else {
                Join("matricula", "m").IgualA("m.curso", filtro.getMatricula().getCurso());
            }
        }

        return Buscar();
    }

}
