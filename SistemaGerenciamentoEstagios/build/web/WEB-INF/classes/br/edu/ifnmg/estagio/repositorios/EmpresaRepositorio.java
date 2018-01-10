package br.edu.ifnmg.estagio.repositorios;

import br.edu.ifnmg.estagio.entidades.Empresa;
import java.util.List;
import javax.ejb.Local;

@Local
public interface EmpresaRepositorio extends Repositorio<Empresa> {

    public Empresa BuscarEmpresa(Empresa filtro);
    
    public Empresa BuscarEmpresaLogada(String id);
    
    public List<Empresa> BuscarEmpresasNaoValidadas(boolean Status);
}
