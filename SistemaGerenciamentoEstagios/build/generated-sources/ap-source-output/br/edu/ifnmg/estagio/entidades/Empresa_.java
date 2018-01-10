package br.edu.ifnmg.estagio.entidades;

import br.edu.ifnmg.estagio.entidades.Area;
import br.edu.ifnmg.estagio.entidades.Endereco;
import br.edu.ifnmg.estagio.entidades.Representante;
import br.edu.ifnmg.estagio.entidades.Setor;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-09-24T13:46:39")
@StaticMetamodel(Empresa.class)
public class Empresa_ { 

    public static volatile SingularAttribute<Empresa, String> telefone;
    public static volatile ListAttribute<Empresa, Setor> setoresEmpresa;
    public static volatile SingularAttribute<Empresa, String> emailContato;
    public static volatile SingularAttribute<Empresa, String> tipoEmpresa;
    public static volatile SingularAttribute<Empresa, String> Cnpj;
    public static volatile ListAttribute<Empresa, Endereco> enderecos;
    public static volatile SingularAttribute<Empresa, String> AreaAtuacao;
    public static volatile ListAttribute<Empresa, Representante> responsaveis;
    public static volatile ListAttribute<Empresa, Area> areas_estagios;
    public static volatile SingularAttribute<Empresa, Long> id;
    public static volatile SingularAttribute<Empresa, String> NomeEmpresa;

}