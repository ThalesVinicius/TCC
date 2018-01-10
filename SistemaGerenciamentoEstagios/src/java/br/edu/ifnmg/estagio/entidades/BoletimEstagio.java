package br.edu.ifnmg.estagio.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class BoletimEstagio implements Serializable {

    public BoletimEstagio() {

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
    
    private  int qtd_estagios;

    @Temporal(TemporalType.DATE)
    private Date dataEnvio = new Date();

    @Temporal(TemporalType.DATE)
    private Date dataFinalEstagio = new Date();

    @ManyToOne
    private Aluno aluno;

    @ManyToOne
    private Curso curso;

    @OneToOne
    private Arquivo boletim;

    public int getQtd_estagios() {
        return qtd_estagios;
    }

    public void setQtd_estagios(int qtd_estagios) {
        this.qtd_estagios = qtd_estagios;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public Date getDataFinalEstagio() {
        return dataFinalEstagio;
    }

    public void setDataFinalEstagio(Date dataFinalEstagio) {
        this.dataFinalEstagio = dataFinalEstagio;
    }
     

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Arquivo getBoletim() {
        return boletim;
    }

    public void setBoletim(Arquivo boletim) {
        this.boletim = boletim;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BoletimEstagio)) {
            return false;
        }
        BoletimEstagio other = (BoletimEstagio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BoletimEstagio{" + "id=" + id + ", dataEnvio=" + dataEnvio + ", aluno=" + aluno + ", curso=" + curso + ", boletim=" + boletim + '}';
    }

}
