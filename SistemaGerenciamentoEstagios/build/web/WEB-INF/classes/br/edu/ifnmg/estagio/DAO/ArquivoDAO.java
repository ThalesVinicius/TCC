package br.edu.ifnmg.estagio.DAO;

import br.edu.ifnmg.estagio.entidades.Arquivo;
import br.edu.ifnmg.estagio.repositorios.ArquivoRepositorio;
import java.io.File;
import java.util.List;
import javax.ejb.Singleton;

/**
 *
 * @author Thales
 */
@Singleton
public class ArquivoDAO extends DAOGenerico<Arquivo> implements ArquivoRepositorio {

    public ArquivoDAO() {
        super(Arquivo.class);
    }

    @Override
    public List<Arquivo> Buscar(Arquivo filtro) {
        return IgualA("id", filtro.getId())
                .Like("nome", filtro.getNome())
                .Like("uri", filtro.getUri())
                .Buscar();
    }

    @Override
    public Arquivo Abrir(String nome) {
        return IgualA("nome", nome).Abrir();
    }

    @Override
    public boolean Apagar(Arquivo a, String diretorio) {
        try {
            File file = new File(diretorio + a.getNome());

            if (!file.delete()) {
                return false;
            }

            return super.Apagar(a);

        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Arquivo CriarSalvaArquivo(String NomeArquivo, String DiretorioUplod) {
        Arquivo arqUplod = new Arquivo();
        arqUplod.setNome(NomeArquivo);
        arqUplod.setUri(DiretorioUplod);

        if (Salvar(arqUplod)) {
            return arqUplod;

        } else {
            return null;
        }
    }

   

}
