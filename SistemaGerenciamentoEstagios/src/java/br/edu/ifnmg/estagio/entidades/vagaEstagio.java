package br.edu.ifnmg.estagio.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class vagaEstagio implements Serializable {

    public vagaEstagio() {
        periodo = 0;
        valorBolsa = new BigDecimal("0.00");
        cargaHoraria = 0;
        localTrabalho = new Endereco();
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

    //Dados da Empresa
    ///////////////////////////////
    @ManyToOne
    private Empresa empresaConcedente;

    //Dados da Formação
    ////////////////////////////////
    @ManyToOne
    private Curso curso;

    @Column(name = "Habilidades")
    private String Habilidades;

    @Column(name = "Nivel")
    private String Nivel;

    @Column(name = "CursandoPeriodo")
    private int periodo;

    //Dados gerais da  vaga
    /////////////////////////////////////
    @Column(name = "tipoVaga")
    private String tipoVaga;

    @Column(name = "tituloVaga")
    private String tituloVaga;

    @Column(name = "turno")
    private String turno;

    @Column(name = "valorBolsa")
    private BigDecimal valorBolsa;

    @Column(name = "quantidadeVagas")
    private int quantidadeVagas;

    @Column(name = "cargaHoraria")
    private int cargaHoraria;

    @Column(name = "horario")
    private String horario;

    @Column(name = "descricaoVaga")
    private String descricaoVaga;

    @Column(name = "areaAtuacao")
    private String areaAtuacao;

    @Column(name = "VagaDeficiente")
    private String VagaDeficiente;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco localTrabalho;

    ////////////////////
    /////////////////////////
    ////////////////////////////////
    @Column(name = "Atividades")
    private String atividades;

    @Column(name = "Requesitos")
    private String requesitos;

    @Column(name = "Beneficios")
    private String beneficios;

    @Column(name = "informacoesComplementares")
    private String informacaoComplementar;

    @Column
    private String Status;

    @Column(name = "validado")
    private Boolean validado;

    ///////////////////////////////
    ////////////////////
    /////////
    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public Boolean getValidado() {
        return validado;
    }

    public void setValidado(Boolean validado) {
        this.validado = validado;
    }

    
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getHabilidades() {
        return Habilidades;
    }

    public void setHabilidades(String Habilidades) {
        this.Habilidades = Habilidades;
    }

    public String getNivel() {
        return Nivel;
    }

    public void setNivel(String Nivel) {
        this.Nivel = Nivel;
    }

    public String getVagaDeficiente() {
        return VagaDeficiente;
    }

    public void setVagaDeficiente(String VagaDeficiente) {
        this.VagaDeficiente = VagaDeficiente;
    }

    public Endereco getLocalTrabalho() {
        return localTrabalho;
    }

    public void setLocalTrabalho(Endereco localTrabalho) {
        this.localTrabalho = localTrabalho;
    }

    public String getInformacaoComplementar() {
        return informacaoComplementar;
    }

    public void setInformacaoComplementar(String informacaoComplementar) {
        this.informacaoComplementar = informacaoComplementar;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Empresa getEmpresaConcedente() {
        return empresaConcedente;
    }

    public void setEmpresaConcedente(Empresa empresaConcedente) {
        this.empresaConcedente = empresaConcedente;
    }

    public String getTipoVaga() {
        return tipoVaga;
    }

    public void setTipoVaga(String tipoVaga) {
        this.tipoVaga = tipoVaga;
    }

    public String getTituloVaga() {
        return tituloVaga;
    }

    public void setTituloVaga(String tituloVaga) {
        this.tituloVaga = tituloVaga;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public BigDecimal getValorBolsa() {
        return valorBolsa;
    }

    public void setValorBolsa(BigDecimal valorBolsa) {
        this.valorBolsa = valorBolsa;
    }

    public int getQuantidadeVagas() {
        return quantidadeVagas;
    }

    public void setQuantidadeVagas(int quantidadeVagas) {
        this.quantidadeVagas = quantidadeVagas;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getDescricaoVaga() {
        return descricaoVaga;
    }

    public void setDescricaoVaga(String descricaoVaga) {
        this.descricaoVaga = descricaoVaga;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public String getAtividades() {
        return atividades;
    }

    public void setAtividades(String atividades) {
        this.atividades = atividades;
    }

    public String getRequesitos() {
        return requesitos;
    }

    public void setRequesitos(String requesitos) {
        this.requesitos = requesitos;
    }

    public String getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(String beneficios) {
        this.beneficios = beneficios;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
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
        if (!(object instanceof vagaEstagio)) {
            return false;
        }
        vagaEstagio other = (vagaEstagio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vagaEstagio{" + "id=" + id + ", empresaConcedente=" + empresaConcedente + ", curso=" + curso + ", Habilidades=" + Habilidades + ", Nivel=" + Nivel + ", periodo=" + periodo + ", tipoVaga=" + tipoVaga + ", tituloVaga=" + tituloVaga + ", turno=" + turno + ", valorBolsa=" + valorBolsa + ", quantidadeVagas=" + quantidadeVagas + ", cargaHoraria=" + cargaHoraria + ", horario=" + horario + ", descricaoVaga=" + descricaoVaga + ", areaAtuacao=" + areaAtuacao + ", VagaDeficiente=" + VagaDeficiente + ", localTrabalho=" + localTrabalho + ", atividades=" + atividades + ", requesitos=" + requesitos + ", beneficios=" + beneficios + ", informacaoComplementar=" + informacaoComplementar + '}';
    }

}
