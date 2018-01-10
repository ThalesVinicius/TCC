package br.edu.ifnmg.estagio.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Endereco implements Serializable {

    public Endereco() {

    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Rua")
    private String Rua;
    @Column(name = "Numero")
    private int Numero;
    @Column(name = "Complemento")
    private String Complemento;
    @Column(name = "bairro")
    private String Bairro;
    @Column(name = "cidade")
    private String Cidade;
    @Column(length = 9)
    private String cep;
    @Column(name = "estado")
    private String UF;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return Rua;
    }

    public void setRua(String Rua) {
        this.Rua = Rua;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    public String getComplemento() {
        return Complemento;
    }

    public void setComplemento(String Complemento) {
        this.Complemento = Complemento;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String Cidade) {
        this.Cidade = Cidade;
    }

    //Variavel que recebe o cep formatado
    @Transient
    private String cepFormatado;

    public String getCep() {
        if (cepFormatado == null) {
            if (cep != null && cep.length() == 8) {
                cepFormatado = cep.substring(0, 2) + "." + cep.substring(2, 5) + "-" + cep.substring(5, 8);
            }
        }
        return cepFormatado;
    }

    public void setCep(String cep) {
        this.cep = cep.replace(".", "").replace("-", "");
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.Rua);
        hash = 37 * hash + this.Numero;
        hash = 37 * hash + Objects.hashCode(this.Bairro);
        hash = 37 * hash + Objects.hashCode(this.Cidade);
        hash = 37 * hash + Objects.hashCode(this.cep);
        hash = 37 * hash + Objects.hashCode(this.UF);
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
        final Endereco other = (Endereco) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.Rua, other.Rua)) {
            return false;
        }
        if (this.Numero != other.Numero) {
            return false;
        }
        if (!Objects.equals(this.Complemento, other.Complemento)) {
            return false;
        }
        if (!Objects.equals(this.Bairro, other.Bairro)) {
            return false;
        }
        if (!Objects.equals(this.Cidade, other.Cidade)) {
            return false;
        }
        if (!Objects.equals(this.cep, other.cep)) {
            return false;
        }
        if (!Objects.equals(this.UF, other.UF)) {
            return false;
        }
        return true;
    }

   

    @Override
    public String toString() {
        return "Endereco (Rua: " + Rua + ", Numero: " + Numero + ", Bairro:" + Bairro + ", Cidade:" + Cidade + ", Cep:" + cep + ", UF:" + UF + ')';
    }


}
