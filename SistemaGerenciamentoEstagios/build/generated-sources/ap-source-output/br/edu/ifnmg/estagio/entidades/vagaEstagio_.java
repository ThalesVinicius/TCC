package br.edu.ifnmg.estagio.entidades;

import br.edu.ifnmg.estagio.entidades.Curso;
import br.edu.ifnmg.estagio.entidades.Empresa;
import br.edu.ifnmg.estagio.entidades.Endereco;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-09-24T13:46:39")
@StaticMetamodel(vagaEstagio.class)
public class vagaEstagio_ { 

    public static volatile SingularAttribute<vagaEstagio, String> atividades;
    public static volatile SingularAttribute<vagaEstagio, String> Status;
    public static volatile SingularAttribute<vagaEstagio, Empresa> empresaConcedente;
    public static volatile SingularAttribute<vagaEstagio, String> tipoVaga;
    public static volatile SingularAttribute<vagaEstagio, String> horario;
    public static volatile SingularAttribute<vagaEstagio, Integer> periodo;
    public static volatile SingularAttribute<vagaEstagio, String> requesitos;
    public static volatile SingularAttribute<vagaEstagio, String> Habilidades;
    public static volatile SingularAttribute<vagaEstagio, String> turno;
    public static volatile SingularAttribute<vagaEstagio, Integer> cargaHoraria;
    public static volatile SingularAttribute<vagaEstagio, Endereco> localTrabalho;
    public static volatile SingularAttribute<vagaEstagio, BigDecimal> valorBolsa;
    public static volatile SingularAttribute<vagaEstagio, String> tituloVaga;
    public static volatile SingularAttribute<vagaEstagio, Curso> curso;
    public static volatile SingularAttribute<vagaEstagio, String> areaAtuacao;
    public static volatile SingularAttribute<vagaEstagio, String> informacaoComplementar;
    public static volatile SingularAttribute<vagaEstagio, Boolean> validado;
    public static volatile SingularAttribute<vagaEstagio, Long> id;
    public static volatile SingularAttribute<vagaEstagio, Integer> quantidadeVagas;
    public static volatile SingularAttribute<vagaEstagio, String> Nivel;
    public static volatile SingularAttribute<vagaEstagio, String> descricaoVaga;
    public static volatile SingularAttribute<vagaEstagio, String> VagaDeficiente;
    public static volatile SingularAttribute<vagaEstagio, String> beneficios;

}