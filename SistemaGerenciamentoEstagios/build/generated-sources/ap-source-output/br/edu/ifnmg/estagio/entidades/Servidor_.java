package br.edu.ifnmg.estagio.entidades;

import br.edu.ifnmg.estagio.entidades.Empresa;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-09-24T13:46:39")
@StaticMetamodel(Servidor.class)
public class Servidor_ extends Pessoa_ {

    public static volatile SingularAttribute<Servidor, Empresa> Instituicao;
    public static volatile SingularAttribute<Servidor, Boolean> coordenadorCurso;
    public static volatile SingularAttribute<Servidor, String> cargo;
    public static volatile SingularAttribute<Servidor, Boolean> orientador;
    public static volatile SingularAttribute<Servidor, Boolean> supervisor;

}