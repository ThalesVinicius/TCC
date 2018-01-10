package br.edu.ifnmg.estagio.entidades;

import br.edu.ifnmg.estagio.entidades.Servidor;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-09-24T13:46:39")
@StaticMetamodel(Curso.class)
public class Curso_ { 

    public static volatile SingularAttribute<Curso, Integer> CHME;
    public static volatile SingularAttribute<Curso, String> sigla;
    public static volatile SingularAttribute<Curso, String> nome;
    public static volatile SingularAttribute<Curso, Long> id;
    public static volatile SingularAttribute<Curso, Servidor> coordenadorCurso;
    public static volatile SingularAttribute<Curso, String> nivel;
    public static volatile SingularAttribute<Curso, String> modalidade;

}