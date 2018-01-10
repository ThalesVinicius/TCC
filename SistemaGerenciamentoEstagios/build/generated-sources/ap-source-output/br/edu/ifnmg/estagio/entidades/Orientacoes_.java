package br.edu.ifnmg.estagio.entidades;

import br.edu.ifnmg.estagio.entidades.Estagio;
import br.edu.ifnmg.estagio.entidades.Servidor;
import br.edu.ifnmg.estagio.entidades.Setor;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-09-24T13:46:39")
@StaticMetamodel(Orientacoes.class)
public class Orientacoes_ { 

    public static volatile SingularAttribute<Orientacoes, Estagio> estagio;
    public static volatile SingularAttribute<Orientacoes, Date> TerminoOrientacao;
    public static volatile SingularAttribute<Orientacoes, Integer> chcOrientacao;
    public static volatile SingularAttribute<Orientacoes, Integer> qtd_orientacoes;
    public static volatile SingularAttribute<Orientacoes, Setor> setor_orientacao;
    public static volatile SingularAttribute<Orientacoes, Date> InicioOrientacao;
    public static volatile SingularAttribute<Orientacoes, Long> id;
    public static volatile SingularAttribute<Orientacoes, Servidor> orientador;

}