package br.edu.ifnmg.estagio.entidades;

import br.edu.ifnmg.estagio.entidades.Aluno;
import br.edu.ifnmg.estagio.entidades.Empresa;
import br.edu.ifnmg.estagio.entidades.Matricula;
import br.edu.ifnmg.estagio.entidades.Orientacoes;
import br.edu.ifnmg.estagio.entidades.Servidor;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-09-24T13:46:39")
@StaticMetamodel(Estagio.class)
public class Estagio_ { 

    public static volatile SingularAttribute<Estagio, Date> Termino;
    public static volatile SingularAttribute<Estagio, Servidor> Supervisor;
    public static volatile SingularAttribute<Estagio, Aluno> estagiario;
    public static volatile SingularAttribute<Estagio, String> tipoEstagio;
    public static volatile SingularAttribute<Estagio, Empresa> empresaConcedente;
    public static volatile SingularAttribute<Estagio, String> situacao;
    public static volatile SingularAttribute<Estagio, Date> Inicio;
    public static volatile SingularAttribute<Estagio, Matricula> matricula;
    public static volatile SingularAttribute<Estagio, Long> id;
    public static volatile ListAttribute<Estagio, Orientacoes> orientacoes;
    public static volatile SingularAttribute<Estagio, Integer> chc;

}