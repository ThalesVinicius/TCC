

package br.edu.ifnmg.estagio.repositorios;


import br.edu.ifnmg.estagio.entidades.Arquivo;
import javax.ejb.Local;

/**
 *
 * @author petronio
 */
@Local
public interface ArquivoRepositorio extends Repositorio<Arquivo> {
    
    public Arquivo CriarSalvaArquivo(String NomeArquivo, String DiretorioUplod);
    public boolean Apagar(Arquivo arquivo, String DiretorioUplod);
    public Arquivo Abrir(String nome);
//    public Arquivo AbrirUltimoArquivo();
}
