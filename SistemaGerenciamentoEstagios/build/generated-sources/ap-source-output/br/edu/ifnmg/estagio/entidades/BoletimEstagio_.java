package br.edu.ifnmg.estagio.entidades;

import br.edu.ifnmg.estagio.entidades.Aluno;
import br.edu.ifnmg.estagio.entidades.Arquivo;
import br.edu.ifnmg.estagio.entidades.Curso;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-09-24T13:46:39")
@StaticMetamodel(BoletimEstagio.class)
public class BoletimEstagio_ { 

    public static volatile SingularAttribute<BoletimEstagio, Integer> qtd_estagios;
    public static volatile SingularAttribute<BoletimEstagio, Aluno> aluno;
    public static volatile SingularAttribute<BoletimEstagio, Curso> curso;
    public static volatile SingularAttribute<BoletimEstagio, Long> id;
    public static volatile SingularAttribute<BoletimEstagio, Date> dataFinalEstagio;
    public static volatile SingularAttribute<BoletimEstagio, Arquivo> boletim;
    public static volatile SingularAttribute<BoletimEstagio, Date> dataEnvio;

}