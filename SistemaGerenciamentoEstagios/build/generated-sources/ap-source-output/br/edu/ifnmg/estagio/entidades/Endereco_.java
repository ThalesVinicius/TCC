package br.edu.ifnmg.estagio.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-09-24T13:46:39")
@StaticMetamodel(Endereco.class)
public class Endereco_ { 

    public static volatile SingularAttribute<Endereco, Integer> Numero;
    public static volatile SingularAttribute<Endereco, String> UF;
    public static volatile SingularAttribute<Endereco, String> Complemento;
    public static volatile SingularAttribute<Endereco, Long> id;
    public static volatile SingularAttribute<Endereco, String> Bairro;
    public static volatile SingularAttribute<Endereco, String> Rua;
    public static volatile SingularAttribute<Endereco, String> Cidade;
    public static volatile SingularAttribute<Endereco, String> cep;

}