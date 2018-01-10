package br.edu.ifnmg.estagio.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Aluno")
public class Aluno extends Pessoa implements Serializable {

    public Aluno() {
        this.matriculas = new ArrayList<>();
        this.setAcesso("aluno/index.xhtml");
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Matricula> matriculas;

    /**
     *
     * @return
     */
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    

    public void removeMatricula(Matricula t) {
        if (matriculas.contains(t)) {
            matriculas.remove(t);
        }
    }

    public void salvarMatricula(Matricula t) {

        if (matriculas.contains(t)) {
            int index = matriculas.indexOf(t);
            matriculas.set(index, t);
        } else {
            matriculas.add(t);
        }

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
        if (!(object instanceof Aluno)) {
            return false;
        }
        Aluno other = (Aluno) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Aluno{" + "id=" + id + '}';
    }

}
