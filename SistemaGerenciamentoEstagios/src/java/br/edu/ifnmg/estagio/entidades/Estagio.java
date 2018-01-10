package br.edu.ifnmg.estagio.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Estagio implements Serializable {

    public Estagio() {
        this.chc = 0;
        this.orientacoes = new ArrayList<>();
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

 

    @Column(name = "tipoEstagio")
    private String tipoEstagio;

    @Column(name = "situacao")
    private String situacao;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dataIncio")
    private Date Inicio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dataTermino")
    private Date Termino;

    @OneToMany(mappedBy = "estagio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orientacoes> orientacoes;

    @ManyToOne
    private Servidor Supervisor;

    @ManyToOne
    private Aluno estagiario;

    @ManyToOne
    private Matricula matricula;

    @ManyToOne
    private Empresa empresaConcedente;

    @Column(name = "carga_horaria")
    private int chc;
    

    //getrs e  setrs/////////////////

     
    
    
    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {

        this.situacao = situacao;
    }

    public int getChc() {
        return chc;
    }

    public void setChc(int chc) {
        this.chc = chc;
    }

    public String getTipoEstagio() {
        return tipoEstagio;
    }

    public Aluno getEstagiario() {
        return estagiario;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Empresa getEmpresaConcedente() {
        return empresaConcedente;
    }

    public void setEmpresaConcedente(Empresa empresaConcedente) {
        this.empresaConcedente = empresaConcedente;
    }

    public void setEstagiario(Aluno estagiario) {
        this.estagiario = estagiario;
    }

    public void setTipoEstagio(String tipoEstagio) {
        this.tipoEstagio = tipoEstagio;
    }

    public Date getInicio() {
        return Inicio;
    }

    public void setInicio(Date Inicio) {
        this.Inicio = Inicio;
    }

    public Date getTermino() {
        return Termino;
    }

    public void setTermino(Date Termino) {
        this.Termino = Termino;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Orientacoes> getOrientacoes() {
        return orientacoes;
    }

    public void setOrientacoes(List<Orientacoes> orientacoes) {
        this.orientacoes = orientacoes;
    }

    //metodos de adicionar
    public boolean addOrientacao(Orientacoes t) {

        if (orientacoes.contains(t)) {
            int index = orientacoes.indexOf(t);
            orientacoes.set(index, t);
            return true;
        } else {
            orientacoes.add(t);
            return true;
        }

    }

    public void removeOrientacao(Orientacoes t) {
        if (orientacoes.contains(t)) {
            orientacoes.remove(t);
        }
    }

    public Servidor getSupervisor() {
        return Supervisor;
    }

    public void setSupervisor(Servidor Supervisor) {
        this.Supervisor = Supervisor;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.tipoEstagio);
        hash = 53 * hash + Objects.hashCode(this.situacao);
        hash = 53 * hash + Objects.hashCode(this.Inicio);
        hash = 53 * hash + Objects.hashCode(this.Termino);
        hash = 53 * hash + Objects.hashCode(this.Supervisor);
        hash = 53 * hash + Objects.hashCode(this.estagiario);
        hash = 53 * hash + Objects.hashCode(this.empresaConcedente);
        hash = 53 * hash + this.chc;
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
        final Estagio other = (Estagio) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.tipoEstagio, other.tipoEstagio)) {
            return false;
        }
        if (!Objects.equals(this.situacao, other.situacao)) {
            return false;
        }
        if (!Objects.equals(this.Inicio, other.Inicio)) {
            return false;
        }
        if (!Objects.equals(this.Termino, other.Termino)) {
            return false;
        }
        if (!Objects.equals(this.Supervisor, other.Supervisor)) {
            return false;
        }
        if (!Objects.equals(this.estagiario, other.estagiario)) {
            return false;
        }
        if (!Objects.equals(this.empresaConcedente, other.empresaConcedente)) {
            return false;
        }
        if (this.chc != other.chc) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Estagio{" + "id=" + id + ", tipoEstagio=" + tipoEstagio + ", situacao=" + situacao + ", Inicio=" + Inicio + ", Termino=" + Termino + ", Supervisor=" + Supervisor + ", estagiario=" + estagiario + ", empresaConcedente=" + empresaConcedente + ", chc=" + chc + '}';
    }

}
