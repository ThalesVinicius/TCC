package br.edu.ifnmg.estagio.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Matricula implements Serializable {

    public Matricula() {

    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne
    private Curso curso;

    @Column(name = "matricula")
    private int matricula;

    @Column(name = "Cursando")
    private String Cursando;

    @Column(name = "CursandoPeriodo")
    private int periodo;

    @Column(name = "IntegralizouCH")
    private String integralizouCH;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_Integralizacao")
    private Date dataIntegralizacao;

    @Column(name = "PossuiDependencia")
    private String possuiDependencia;

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getCursando() {
        return Cursando;
    }

    public void setCursando(String Cursando) {
        this.Cursando = Cursando;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public String getIntegralizouCH() {
        return integralizouCH;
    }

    public void setIntegralizouCH(String integralizouCH) {
        this.integralizouCH = integralizouCH;
    }

    public Date getDataIntegralizacao() {
        return dataIntegralizacao;
    }

    public void setDataIntegralizacao(Date dataIntegralizacao) {
        this.dataIntegralizacao = dataIntegralizacao;
    }

    public String getPossuiDependencia() {
        return possuiDependencia;
    }

    public void setPossuiDependencia(String possuiDependencia) {
        this.possuiDependencia = possuiDependencia;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.curso);
        hash = 29 * hash + this.matricula;
        hash = 29 * hash + this.periodo;
        hash = 29 * hash + Objects.hashCode(this.integralizouCH);
        hash = 29 * hash + Objects.hashCode(this.dataIntegralizacao);
        hash = 29 * hash + Objects.hashCode(this.possuiDependencia);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Matricula other = (Matricula) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.curso, other.curso)) {
            return false;
        }
        if (this.matricula != other.matricula) {
            return false;
        }
        if (!Objects.equals(this.Cursando, other.Cursando)) {
            return false;
        }
        if (this.periodo != other.periodo) {
            return false;
        }
        if (!Objects.equals(this.integralizouCH, other.integralizouCH)) {
            return false;
        }
        if (!Objects.equals(this.dataIntegralizacao, other.dataIntegralizacao)) {
            return false;
        }
        if (!Objects.equals(this.possuiDependencia, other.possuiDependencia)) {
            return false;
        }
        return true;
    }

   

    @Override
    public String toString() {
        return "Matricula( Cod: " + id + " , Curso: " + curso.getNome() + " , Nº matricula: " + matricula + " , Cursando: " + Cursando + ')';
    }

}
