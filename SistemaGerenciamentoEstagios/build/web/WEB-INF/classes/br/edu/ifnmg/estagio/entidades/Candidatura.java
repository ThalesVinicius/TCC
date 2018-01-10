package br.edu.ifnmg.estagio.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Candidatura implements Serializable {
    
    public Candidatura() {
        this.setSituacao("Em Aberto");
    }
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    private Aluno candidato;
    
    @OneToOne
    private vagaEstagio vaga;
    
    @OneToOne
    private Arquivo Curriculo;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "dataCandidatura")
    private Date dataCandidatura;
    
    private String Situacao;
    private String Etapa;
    private String Informacoes;
    private String Motivo;
    
    public String getSituacao() {
        return Situacao;
    }
    
    public void setSituacao(String Situacao) {
        this.Situacao = Situacao;
    }
    
    public String getEtapa() {
        return Etapa;
    }
    
    public void setEtapa(String Etapa) {
        this.Etapa = Etapa;
    }
    
    public String getInformacoes() {
        return Informacoes;
    }
    
    public void setInformacoes(String Informacoes) {
        this.Informacoes = Informacoes;
    }
    
    public String getMotivo() {
        return Motivo;
    }
    
    public void setMotivo(String Motivo) {
        this.Motivo = Motivo;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Date getDataCandidatura() {
        return dataCandidatura;
    }
    
    public void setDataCandidatura(Date dataCandidatura) {
        this.dataCandidatura = dataCandidatura;
    }
    
    public Aluno getCandidato() {
        return candidato;
    }
    
    public void setCandidato(Aluno candidato) {
        this.candidato = candidato;
    }
    
    public vagaEstagio getVaga() {
        return vaga;
    }
    
    public void setVaga(vagaEstagio vaga) {
        this.vaga = vaga;
    }
    
    public Arquivo getCurriculo() {
        return Curriculo;
    }
    
    public void setCurriculo(Arquivo Curriculo) {
        this.Curriculo = Curriculo;
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
        if (!(object instanceof Candidatura)) {
            return false;
        }
        Candidatura other = (Candidatura) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Candidatura{" + "id=" + id + ", candidato=" + candidato + ", vaga=" + vaga + ", dataCandidatura=" + dataCandidatura + '}';
    }
    
}
