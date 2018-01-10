package br.edu.ifnmg.estagio.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Curso implements Serializable {

    public Curso() {

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

    @Column(name = "nome", nullable = true)
    private String nome;

    @ManyToOne
    private Servidor coordenadorCurso;

    @Column(name = "sigla", nullable = true)
    private String sigla;

    @Column(name = "modalidade", nullable = true)
    private String modalidade;

    @Column(name = "nivel", nullable = true)
    private String nivel;

    @Column(name = "CHME", nullable = true)
    private int CHME;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCHME() {
        return CHME;
    }

    public void setCHME(int CHME) {
        this.CHME = CHME;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Servidor getCoordenadorCurso() {
        return coordenadorCurso;
    }

    public void setCoordenadorCurso(Servidor coordenadorCurso) {
        this.coordenadorCurso = coordenadorCurso;
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
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Curso{" + "id=" + id + ", nome=" + nome + ", coordenadorCurso=" + coordenadorCurso + ", sigla=" + sigla + ", modalidade=" + modalidade + ", nivel=" + nivel + ", CHME=" + CHME + '}';
    }

    

}
