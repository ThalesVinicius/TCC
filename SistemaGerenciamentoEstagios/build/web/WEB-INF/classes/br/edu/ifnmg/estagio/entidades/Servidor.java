package br.edu.ifnmg.estagio.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Servidor")
public class Servidor extends Pessoa implements Serializable {

    public Servidor() {
        orientador = false;
        supervisor = false;
        coordenadorCurso = false;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cargo;

    @OneToOne
    private Empresa Instituicao;

    @Column(name = "orientador")
    private boolean orientador;

    @Column(name = "supervisor")
    private boolean supervisor;

    @Column(name = "coordenadorCurso")
    private boolean coordenadorCurso;

    //gets e setrs
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Empresa getInstituicao() {
        return Instituicao;
    }

    public void setInstituicao(Empresa Instituicao) {
        this.Instituicao = Instituicao;
    }

    public boolean isOrientador() {
        return orientador;
    }

    public void setOrientador(boolean orientador) {
        this.orientador = orientador;
    }

    public boolean isSupervisor() {
        return supervisor;
    }

    public void setSupervisor(boolean supervisor) {
        this.supervisor = supervisor;
    }

    public boolean isCoordenadorCurso() {
        return coordenadorCurso;
    }

    public void setCoordenadorCurso(boolean coordenadorCurso) {
        this.coordenadorCurso = coordenadorCurso;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.cargo);
        hash = 53 * hash + Objects.hashCode(this.Instituicao);
        hash = 53 * hash + (this.orientador ? 1 : 0);
        hash = 53 * hash + (this.supervisor ? 1 : 0);
        hash = 53 * hash + (this.coordenadorCurso ? 1 : 0);
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
        final Servidor other = (Servidor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.cargo, other.cargo)) {
            return false;
        }
        if (!Objects.equals(this.Instituicao, other.Instituicao)) {
            return false;
        }
        if (this.orientador != other.orientador) {
            return false;
        }
        if (this.supervisor != other.supervisor) {
            return false;
        }
        if (this.coordenadorCurso != other.coordenadorCurso) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Servidor{" + "id=" + id + ", cargo=" + cargo + ", Instituicao=" + Instituicao + ", orientador=" + orientador + ", supervisor=" + supervisor + ", coordenadorCurso=" + coordenadorCurso + '}';
    }
    
    
    

}
