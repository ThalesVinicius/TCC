package br.edu.ifnmg.estagio.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

@Entity
@NamedQueries({
    @NamedQuery(name = "empresa.representante",
            query = "SELECT e FROM Empresa e join fetch e.responsaveis r where r.id = :idRepresentante"),
    @NamedQuery(name = "empresa.representantesNaoValidados",
            query = "SELECT e FROM Empresa e join fetch e.responsaveis r where r.validado = :StatusRepresentante")})
public class Empresa implements Serializable {

    public Empresa() {
        this.enderecos = new ArrayList<>();
        this.responsaveis = new ArrayList<>();
        this.areas_estagios = new ArrayList<>();
        this.setoresEmpresa = new ArrayList<>();
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NomeEmpresa", nullable = true, unique = true)
    private String NomeEmpresa;
    @Column(name = "areaAtuacao", unique = false)
    private String AreaAtuacao;
    @Column(name = "Cnpj")
    private String Cnpj;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "emailContato")
    private String emailContato;
    @Column(name = "TipoEmpresa")
    private String tipoEmpresa;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Representante> responsaveis;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Endereco> enderecos;

    @ManyToMany(cascade = CascadeType.ALL)
    public List<Area> areas_estagios;

    @ManyToMany(cascade = CascadeType.ALL)
    public List<Setor> setoresEmpresa;

    //metodos de adicionar , remover e salvar endereços
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

    //metodos de adicionar , remover e salvar responsaveis
    public void removeResponsavel(Representante t) {
        if (responsaveis.contains(t)) {
            responsaveis.remove(t);
        }
    }

    public void salvarResponsavel(Representante t) {
        if (responsaveis.contains(t)) {
            int index = responsaveis.indexOf(t);
            responsaveis.set(index, t);
        } else {
            responsaveis.add(t);
        }

    }

    public void removeArea(Area t) {
        if (areas_estagios.contains(t)) {
            areas_estagios.remove(t);
        }
    }

    public void salvarArea(Area t) {
        if (areas_estagios.contains(t)) {
            int index = areas_estagios.indexOf(t);
            areas_estagios.set(index, t);
        } else {

            areas_estagios.add(t);
        }

    }

    public void removeSetor(Setor t) {
        if (setoresEmpresa.contains(t)) {
            setoresEmpresa.remove(t);
        }
    }

    public void salvarSetor(Setor t) {
        if (setoresEmpresa.contains(t)) {
            int index = setoresEmpresa.indexOf(t);
            setoresEmpresa.set(index, t);
        } else {

            setoresEmpresa.add(t);
        }

    }

    ///////////////////////////////////////////////////////////////////////////
    //Get  e   setrs
    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Representante> getResponsaveis() {
        return responsaveis;
    }

    public void setResponsaveis(List<Representante> responsaveis) {
        this.responsaveis = responsaveis;
    }

    public List<Area> getAreas_estagios() {
        return areas_estagios;
    }

    public void setAreas_estagios(List<Area> areas_estagios) {
        this.areas_estagios = areas_estagios;
    }

    public List<Setor> getSetoresEmpresa() {
        return setoresEmpresa;
    }

    public void setSetoresEmpresa(List<Setor> setoresEmpresa) {
        this.setoresEmpresa = setoresEmpresa;
    }

    /// continuação
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeEmpresa() {
        return NomeEmpresa;
    }

    public void setNomeEmpresa(String NomeEmpresa) {
        this.NomeEmpresa = NomeEmpresa;
    }

    public String getAreaAtuacao() {
        return AreaAtuacao;
    }

    public void setAreaAtuacao(String AreaAtuacao) {
        this.AreaAtuacao = AreaAtuacao;
    }

    public String getCnpj() {
        return Cnpj;
    }

    public void setCnpj(String Cnpj) {
        this.Cnpj = Cnpj;
    }

    public String getEmailContato() {
        return emailContato;
    }

    public void setEmailContato(String emailContato) {
        this.emailContato = emailContato;
    }

    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.id);
        hash = 13 * hash + Objects.hashCode(this.NomeEmpresa);
        hash = 13 * hash + Objects.hashCode(this.AreaAtuacao);
        hash = 13 * hash + Objects.hashCode(this.Cnpj);
        hash = 13 * hash + Objects.hashCode(this.telefone);
        hash = 13 * hash + Objects.hashCode(this.emailContato);
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
        final Empresa other = (Empresa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.NomeEmpresa, other.NomeEmpresa)) {
            return false;
        }
        if (!Objects.equals(this.AreaAtuacao, other.AreaAtuacao)) {
            return false;
        }
        if (!Objects.equals(this.Cnpj, other.Cnpj)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        if (!Objects.equals(this.emailContato, other.emailContato)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Empresa{" + "id=" + id + ", NomeEmpresa=" + NomeEmpresa + ", AreaAtuacao=" + AreaAtuacao + ", Cnpj=" + Cnpj + ", telefone=" + telefone + ", telefoneFormatado=" + telefoneFormatado + '}';
    }

}
