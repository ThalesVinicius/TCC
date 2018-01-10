package br.edu.ifnmg.estagio.entidades;

import br.edu.ifnmg.estagio.entidades.Curso;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-09-24T13:46:39")
@StaticMetamodel(Matricula.class)
public class Matricula_ { 

    public static volatile SingularAttribute<Matricula, Date> dataIntegralizacao;
    public static volatile SingularAttribute<Matricula, Integer> periodo;
    public static volatile SingularAttribute<Matricula, String> possuiDependencia;
    public static volatile SingularAttribute<Matricula, Curso> curso;
    public static volatile SingularAttribute<Matricula, String> integralizouCH;
    public static volatile SingularAttribute<Matricula, Integer> matricula;
    public static volatile SingularAttribute<Matricula, String> Cursando;
    public static volatile SingularAttribute<Matricula, Long> id;

}