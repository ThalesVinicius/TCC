package br.edu.ifnmg.estagio.DAO;

import br.edu.ifnmg.estagio.entidades.Empresa;
import br.edu.ifnmg.estagio.repositorios.EmpresaRepositorio;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.Query;

@Singleton
public class EmpresaDAO extends DAOGenerico<Empresa> implements EmpresaRepositorio {

    public EmpresaDAO() {
        super(Empresa.class);
    }

    @Override
    public List<Empresa> Buscar(Empresa filtro) {

        Like("Cnpj", filtro.getCnpj())
                .Like("NomeEmpresa", filtro.getNomeEmpresa())
                .Like("AreaAtuacao", filtro.getAreaAtuacao())
                .IgualA("id", filtro.getId())
                .OrderBy("NomeEmpresa", "ASC");

        

        return Buscar();
    }

    @Override
    public Empresa BuscarEmpresa(Empresa filtro) {
        return IgualA("NomeEmpresa", filtro.getNomeEmpresa())
                .IgualA("id", filtro.getId())
                .Abrir();
    }

    @Override
    public Empresa BuscarEmpresaLogada(String id) {
        Query q = getManager().createNamedQuery("empresa.representante")
                .setParameter("idRepresentante", Long.parseLong(id))
                .setHint("eclipselink.QUERY_RESULTS_CACHE", "TRUE");
        return (Empresa) q.getSingleResult();
    }

    @Override
    public List<Empresa> BuscarEmpresasNaoValidadas(boolean Status) {
        Query q = getManager().createNamedQuery("empresa.representantesNaoValidados")
                .setParameter("StatusRepresentante", Status)
                .setHint("eclipselink.QUERY_RESULTS_CACHE", "TRUE");
        return q.getResultList();
    }
}
