package br.edu.ifnmg.estagio.DAO;

import br.edu.ifnmg.estagio.entidades.Orientacoes;
import br.edu.ifnmg.estagio.repositorios.orientacaoRepositorio;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Singleton;

@Singleton
public class OrientacaoDAO extends DAOGenerico<Orientacoes> implements orientacaoRepositorio {

    public OrientacaoDAO() {
        super(Orientacoes.class);
    }

    @Override
    public List<Orientacoes> Buscar(Orientacoes filtro) {

        Map<String, Object> datas = new HashMap<>();
        System.err.println("testedata1" + filtro.getInicioOrientacao());
        System.err.println("testedata2" + filtro.getTerminoOrientacao());
        IgualA("orientador", filtro.getOrientador())
                .IgualA("id", filtro.getId());

        if (filtro.getInicioOrientacao() != null && filtro.getTerminoOrientacao() != null) {
            System.err.println("sfsfsfs");
            datas.put("data1", filtro.getInicioOrientacao());
            datas.put("data2", filtro.getTerminoOrientacao());

            between("c.InicioOrientacao", datas);
        }

        if (filtro.getEstagio() != null) {
            if (filtro.getEstagio().getId() != null && filtro.getEstagio().getId() > 0) {
                IgualA("estagio", filtro.getEstagio());
            } else {
                Join("estagio", "e")
                        .IgualA("e.situacao", filtro.getEstagio().getSituacao())
                        .IgualA("e.estagiario", filtro.getEstagio().getEstagiario())
                        .IgualA("e.empresaConcedente", filtro.getEstagio().getEmpresaConcedente())
                        .IgualA("e.tipoEstagio", filtro.getEstagio().getTipoEstagio())
                        .IgualA("e.supervisor", filtro.getEstagio().getSupervisor());
            }
        }

        return Buscar();
    }

}
