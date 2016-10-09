package org.smartlist.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "estabelecimento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estabelecimento.findByNome", query = "SELECT e FROM Estabelecimento e WHERE e.estNome = :nome"),
    @NamedQuery(name = "Estabelecimento.findByRazaoSocial", query = "SELECT e FROM Estabelecimento e WHERE e.estRazaosocial = :razaosocial"),
    @NamedQuery(name = "Estabelecimento.findByCNPJ", query = "SELECT e FROM Estabelecimento e WHERE e.estCnpj = :CNPJ"),
    @NamedQuery(name = "Estabelecimento.findByCidade", query= "SELECT e FROM Estabelecimento e WHERE e.cidadeIdFk.cidadeId = :idCidade"),
    @NamedQuery(name = "Estabelecimento.findByEstCep", query = "SELECT e FROM Estabelecimento e WHERE e.estCep = :estCep")})
public class Estabelecimento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "est_id")
    private Integer estId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "est_nome")
    private String estNome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "est_razaosocial")
    private String estRazaosocial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(name = "est_cnpj")
    private String estCnpj;
    @Size(max = 200)
    @Column(name = "est_logradouro")
    private String estLogradouro;
    @Size(max = 10)
    @Column(name = "est_numeroendereco")
    private String estNumeroendereco;
    @Size(max = 100)
    @Column(name = "est_bairro")
    private String estBairro;    
    @Column(name = "url_imagem")
    private String url_imagem;
    @Size(max = 8)
    @Column(name = "est_cep")
    private String estCep;
    
    @Size(max = 28)
    @Column(name = "est_lat")
    private String estLat;
    
    @Size(max = 28)
    @Column(name = "est_long")
    private String estLong;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estIdFk")
    private List<AdministradorEstabelecimento> administradorestabelecimentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estIdFk")
    private List<Produto> produtoList = new ArrayList<>();
    @JoinColumn(name = "categest_id_fk", referencedColumnName = "categest_id")
    @ManyToOne(optional = false)
    private CategoriaEstabelecimento categestIdFk;
    @JoinColumn(name = "cidade_id_fk", referencedColumnName = "cidade_id")
    @ManyToOne(optional = false)
    private Cidade cidadeIdFk;

    public Estabelecimento() {
    }

    public Estabelecimento(Integer estId) {
        this.estId = estId;
    }

    public Estabelecimento(Integer estId, String estNome, String estRazaosocial, String estCnpj) {
        this.estId = estId;
        this.estNome = estNome;
        this.estRazaosocial = estRazaosocial;
        this.estCnpj = estCnpj;
    }

    public Integer getEstId() {
        return estId;
    }

    public void setEstId(Integer estId) {
        this.estId = estId;
    }

    public String getEstNome() {
        return estNome;
    }

    public void setEstNome(String estNome) {
        this.estNome = estNome;
    }

    public String getEstRazaosocial() {
        return estRazaosocial;
    }

    public void setEstRazaosocial(String estRazaosocial) {
        this.estRazaosocial = estRazaosocial;
    }

    public String getEstCnpj() {
        return estCnpj;
    }

    public void setEstCnpj(String estCnpj) {
        this.estCnpj = estCnpj;
    }

    public String getEstLogradouro() {
        return estLogradouro;
    }

    public void setEstLogradouro(String estLogradouro) {
        this.estLogradouro = estLogradouro;
    }

    public String getEstNumeroendereco() {
        return estNumeroendereco;
    }

    public void setEstNumeroendereco(String estNumeroendereco) {
        this.estNumeroendereco = estNumeroendereco;
    }

    public String getEstBairro() {
        return estBairro;
    }

    public void setEstBairro(String estBairro) {
        this.estBairro = estBairro;
    }

    public String getEstCep() {
        return estCep;
    }

    public void setEstCep(String estCep) {
        this.estCep = estCep;
    }

    @XmlTransient
    public List<AdministradorEstabelecimento> getAdministradorestabelecimentoList() {
        return administradorestabelecimentoList;
    }

    public void setAdministradorestabelecimentoList(List<AdministradorEstabelecimento> administradorestabelecimentoList) {
        this.administradorestabelecimentoList = administradorestabelecimentoList;
    }

    @XmlTransient
    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    public CategoriaEstabelecimento getCategestIdFk() {
        return categestIdFk;
    }

    public void setCategestIdFk(CategoriaEstabelecimento categestIdFk) {
        this.categestIdFk = categestIdFk;
    }

    public Cidade getCidadeIdFk() {
        return cidadeIdFk;
    }

    public void setCidadeIdFk(Cidade cidadeIdFk) {
        this.cidadeIdFk = cidadeIdFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estId != null ? estId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estabelecimento)) {
            return false;
        }
        Estabelecimento other = (Estabelecimento) object;
        if ((this.estId == null && other.estId != null) || (this.estId != null && !this.estId.equals(other.estId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Estabelecimento[ estId=" + estId + " ]";
    }

    public String getUrl_imagem() {
        return url_imagem;
    }

    public void setUrl_imagem(String url_imagem) {
        this.url_imagem = url_imagem;
    }    

    public String getEstLat() {
        return estLat;
    }

    public void setEstLat(String estLat) {
        this.estLat = estLat;
    }

    public String getEstLong() {
        return estLong;
    }

    public void setEstLong(String estLong) {
        this.estLong = estLong;
    }    
    
}