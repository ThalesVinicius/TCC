package br.edu.ifnmg.estagio.entidades;

import br.edu.ifnmg.estagio.entidades.Aluno;
import br.edu.ifnmg.estagio.entidades.Arquivo;
import br.edu.ifnmg.estagio.entidades.vagaEstagio;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-09-24T13:46:39")
@StaticMetamodel(Candidatura.class)
public class Candidatura_ { 

    public static volatile SingularAttribute<Candidatura, Date> dataCandidatura;
    public static volatile SingularAttribute<Candidatura, String> Etapa;
    public static volatile SingularAttribute<Candidatura, vagaEstagio> vaga;
    public static volatile SingularAttribute<Candidatura, String> Situacao;
    public static volatile SingularAttribute<Candidatura, String> Motivo;
    public static volatile SingularAttribute<Candidatura, String> Informacoes;
    public static volatile SingularAttribute<Candidatura, Long> id;
    public static volatile SingularAttribute<Candidatura, Arquivo> Curriculo;
    public static volatile SingularAttribute<Candidatura, Aluno> candidato;

}