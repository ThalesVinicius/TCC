package br.edu.ifnmg.estagio.DAO;

import br.edu.ifnmg.estagio.entidades.Documentos;
import br.edu.ifnmg.estagio.repositorios.DocumentoRepositorio;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class DocumentoDAO extends DAOGenerico<Documentos> implements DocumentoRepositorio {

    public DocumentoDAO() {
        super(Documentos.class);
    }

    @Override
    public List<Documentos> Buscar(Documentos filtro) {

        return Like("nome", filtro.getNome())
                .IgualA("tipo", filtro.getTipo())
                .Buscar();
    }

}
