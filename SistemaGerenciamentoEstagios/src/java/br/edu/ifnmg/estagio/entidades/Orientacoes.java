package br.edu.ifnmg.estagio.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Orientacoes implements Serializable {

    public Orientacoes() {
        this.qtd_orientacoes = 1;
        this.chcOrientacao = 0;
    }

    private Servidor orientador;

    @Column(name = "Qtd_Orientacoes")
    private int qtd_orientacoes;

    @ManyToOne(cascade = CascadeType.ALL)
    private Estagio estagio;

    @Temporal(TemporalType.DATE)
    @Column(name = "dataIncioOrientacao")
    private Date InicioOrientacao;

    @Temporal(TemporalType.DATE)
    @Column(name = "dataTerminoOrientacao")
    private Date TerminoOrientacao;

    @Column(name = "chc_orientacao")
    private int chcOrientacao;

    @ManyToOne(cascade = CascadeType.ALL)
    private Setor setor_orientacao;

    public int getQtd_orientacoes() {
        return qtd_orientacoes;
    }

    public void setQtd_orientacoes(int qtd_orientacoes) {
        this.qtd_orientacoes = qtd_orientacoes;
    }

    public Estagio getEstagio() {
        return estagio;
    }

    public void setEstagio(Estagio estagio) {
        this.estagio = estagio;
    }

    public Servidor getOrientador() {
        return orientador;
    }

    public void setOrientador(Servidor orientador) {
        this.orientador = orientador;
    }

    public Date getInicioOrientacao() {
        return InicioOrientacao;
    }

    public void setInicioOrientacao(Date InicioOrientacao) {
        this.InicioOrientacao = InicioOrientacao;
    }

    public Date getTerminoOrientacao() {
        return TerminoOrientacao;
    }

    public void setTerminoOrientacao(Date TerminoOrientacao) {
        this.TerminoOrientacao = TerminoOrientacao;
    }

    public int getChcOrientacao() {
        return chcOrientacao;
    }

    public void setChcOrientacao(int chcOrientacao) {
        this.chcOrientacao = chcOrientacao;
    }

    public Setor getSetor_orientacao() {
        return setor_orientacao;
    }

    public void setSetor_orientacao(Setor setor_orientacao) {
        this.setor_orientacao = setor_orientacao;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.orientador);
        hash = 71 * hash + this.qtd_orientacoes;
        hash = 71 * hash + Objects.hashCode(this.estagio);
        hash = 71 * hash + Objects.hashCode(this.InicioOrientacao);
        hash = 71 * hash + Objects.hashCode(this.TerminoOrientacao);
        hash = 71 * hash + this.chcOrientacao;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final Orientacoes other = (Orientacoes) obj;
        if (!Objects.equals(this.orientador, other.orientador)) {
            return false;
        }
        if (this.qtd_orientacoes != other.qtd_orientacoes) {
            return false;
        }
        if (!Objects.equals(this.estagio, other.estagio)) {
            return false;
        }
        if (!Objects.equals(this.InicioOrientacao, other.InicioOrientacao)) {
            return false;
        }
        if (!Objects.equals(this.TerminoOrientacao, other.TerminoOrientacao)) {
            return false;
        }
        if (this.chcOrientacao != other.chcOrientacao) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Orientacoes{" + "orientador=" + orientador + ", qtd_orientacoes=" + qtd_orientacoes + ", estagio=" + estagio + ", InicioOrientacao=" + InicioOrientacao + ", TerminoOrientacao=" + TerminoOrientacao + ", chcOrientacao=" + chcOrientacao + ", id=" + id + '}';
    }

}
