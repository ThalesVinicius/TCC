package br.edu.ifnmg.estagio.repositorios;

import java.util.List;
import java.util.Map;

public interface Repositorio<T> {

    public boolean Salvar(T obj);

    public T Abrir(Long id);

    public boolean Apagar(T obj);

    public Repositorio<T> between(String campo, Map<String, Object> datas);

    public List<T> Buscar(T filtro);

    public T Abrir();

    public Repositorio<T> IgualA(String campo, Object valor);

    public Class getTipo();

    public List<T> Buscar();

    public Repositorio<T> Join(String campo, String alias);
}
