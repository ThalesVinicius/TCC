package br.edu.ifnmg.estagio.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/*
 * @author thales
 */
@Entity
@Table(name = "Pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "myDType")
public class Pessoa implements Serializable {

    public Pessoa() {
        this.enderecos = new ArrayList<>();
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String myDType;

    @Column(nullable = false, name = "nome", unique = true)
    private String nome;

    private Arquivo foto_perfil;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "identidade")
    private String RG;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;

    @Column(name = "EstadoCivil")
    private String EstadoCivil;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "celular")
    private String Celular;

    @Column(name = "email")
    private String email;

    @Column(name = "validado")
    private Boolean validado;

    private String senha;

    private String acesso;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Endereco> enderecos;

    public void removeEndereco(Endereco t) {
        if (enderecos.contains(t)) {
            enderecos.remove(t);
        }
    }

    public void salvarEndereco(Endereco t) {

        if (enderecos.contains(t)) {
            int index = enderecos.indexOf(t);
            enderecos.set(index, t);
        } else {
            enderecos.add(t);
        }

    }

    //////////////////////////////////////////////
/////////////////////////////////////////////////////
    //Get  e   setrs
    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Boolean getValidado() {
        return validado;
    }

    public void setValidado(Boolean validado) {
        this.validado = validado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String Sexo) {
        this.sexo = Sexo;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public String getEstadoCivil() {
        return EstadoCivil;
    }

    public void setEstadoCivil(String EstadoCivil) {
        this.EstadoCivil = EstadoCivil;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMyDType() {
        return myDType;
    }

    public void setMyDType(String myDType) {
        this.myDType = myDType;
    }

    public String getAcesso() {
        return acesso;
    }

    public void setAcesso(String acesso) {
        this.acesso = acesso;
    }

    public Arquivo getFoto_perfil() {
        return foto_perfil;
    }

    public void setFoto_perfil(Arquivo foto_perfil) {
        this.foto_perfil = foto_perfil;
    }

    //Variavel receber cpf formatado
    @Transient
    private String cpfFormatado;

    public String getCpf() {
        if (cpfFormatado == null) {
            if (cpf != null && cpf.length() == 11) {
                cpfFormatado = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
            }
        }
        return cpfFormatado;
    }

    public void setCpf(String cpf) {
        if (cpf != null) {
            this.cpf = cpf.replace(".", "").replace("-", "");
            cpfFormatado = null;
        }
    }

    //Variavel receber telefone formatado
    @Transient
    private String telefoneFormatado;

    public String getTelefone() {
        if (telefoneFormatado == null) {
            if (telefone != null && telefone.length() == 10) {
                telefoneFormatado = "(" + telefone.substring(0, 2) + ") " + telefone.substring(2, 6) + "-" + telefone.substring(6, 10);
            }
        }
        return telefoneFormatado;
    }

    public void setTelefone(String telefone) {
        if (telefone != null) {
            this.telefone = telefone.replace(" ", "").replace("(", "").replace(")", "").replace("-", "");
        }
    }

    //Variavel receber celular formatado
    @Transient
    private String celularFormatado;

    public String getCelular() {
        if (celularFormatado == null) {
            if (Celular != null && Celular.length() == 10) {
                celularFormatado = "(" + Celular.substring(0, 2) + ") " + Celular.substring(2, 6) + "-" + Celular.substring(6, 10);
            }
        }
        return celularFormatado;
    }

    public void setCelular(String celular) {
        if (celular != null) {
            this.Celular = celular.replace(" ", "").replace("(", "").replace(")", "").replace("-", "");
        }
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + Objects.hashCode(this.myDType);
        hash = 43 * hash + Objects.hashCode(this.nome);
        hash = 43 * hash + Objects.hashCode(this.RG);
        hash = 43 * hash + Objects.hashCode(this.cpf);
        hash = 43 * hash + Objects.hashCode(this.telefone);
        hash = 43 * hash + Objects.hashCode(this.Celular);
        hash = 43 * hash + Objects.hashCode(this.email);
        hash = 43 * hash + Objects.hashCode(this.senha);
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
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.myDType, other.myDType)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.RG, other.RG)) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        if (!Objects.equals(this.Celular, other.Celular)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "Pessoa{" + "id" + id + ", nome=" + nome + ", RG=" + RG + ", cpf=" + cpf + '}';
    }

}
