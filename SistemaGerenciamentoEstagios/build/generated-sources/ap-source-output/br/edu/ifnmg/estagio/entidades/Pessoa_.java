package br.edu.ifnmg.estagio.entidades;

import br.edu.ifnmg.estagio.entidades.Arquivo;
import br.edu.ifnmg.estagio.entidades.Endereco;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-09-24T13:46:39")
@StaticMetamodel(Pessoa.class)
public class Pessoa_ { 

    public static volatile SingularAttribute<Pessoa, String> telefone;
    public static volatile SingularAttribute<Pessoa, String> acesso;
    public static volatile SingularAttribute<Pessoa, Arquivo> foto_perfil;
    public static volatile SingularAttribute<Pessoa, String> nome;
    public static volatile SingularAttribute<Pessoa, String> Celular;
    public static volatile SingularAttribute<Pessoa, String> senha;
    public static volatile SingularAttribute<Pessoa, String> myDType;
    public static volatile SingularAttribute<Pessoa, String> RG;
    public static volatile ListAttribute<Pessoa, Endereco> enderecos;
    public static volatile SingularAttribute<Pessoa, String> cpf;
    public static volatile SingularAttribute<Pessoa, Boolean> validado;
    public static volatile SingularAttribute<Pessoa, Long> id;
    public static volatile SingularAttribute<Pessoa, String> sexo;
    public static volatile SingularAttribute<Pessoa, Date> dataNascimento;
    public static volatile SingularAttribute<Pessoa, String> EstadoCivil;
    public static volatile SingularAttribute<Pessoa, String> email;

}