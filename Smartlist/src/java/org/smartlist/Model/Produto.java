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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "produto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produto.findByNome", query = "SELECT p FROM Produto p WHERE p.proNome = :nome"),
    @NamedQuery(name = "Produto.findByPreco", query = "SELECT p FROM Produto p WHERE p.proPreco = :proPreco"),
    @NamedQuery(name = "Produto.findByQuantidade", query = "SELECT p FROM Produto p WHERE p.proQuantidade = :proQuantidade"),
    @NamedQuery(name = "Produto.findByEstabelecimento", query= "SELECT p FROM Produto p WHERE p.estIdFk.estId = :estId"),
    @NamedQuery(name = "Produto.findByCidade", query = "SELECT p FROM Produto p WHERE p.estIdFk.cidadeIdFk.cidadeId = :cidadeId"),
    @NamedQuery(name = "Produto.findByCategoria", query = "SELECT p FROM Produto p WHERE p.categprodIdFk.categprodId = :categoriaprodId")
})
public class Produto implements Serializable {
    @Size(max = 255)
    @Column(name = "pro_imgurl")
    private String proImgurl;
    @ManyToMany(mappedBy = "produtoList")
    private List<ListaConsumidor> listaConsumidorList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pro_id")
    private Integer proId;
    @Size(max = 100)
    @Column(name = "pro_nome")
    private String proNome;
    @Column(name = "pro_preco")
    private Float proPreco;
    @Column(name = "pro_peso")
    private Float proPeso;    
    @Column(name = "pro_quantidade")
    private Integer proQuantidade;
    @Column(name = "pro_unidade")
    private String proUnidade;
    
    @JoinTable(name = "item_lista", joinColumns = {
        @JoinColumn(name = "pro_id_fk", referencedColumnName = "pro_id")}, inverseJoinColumns = {
        @JoinColumn(name = "lst_id_fk", referencedColumnName = "lst_id")})
    @ManyToMany
    private List<ListaConsumidor> listaconsumidorList = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proIdFk")
    @JoinColumn(name = "categprod_id_fk", referencedColumnName = "categprod_id")
    @ManyToOne(optional = false)
    private CategoriaProduto categprodIdFk;
    @JoinColumn(name = "est_id_fk", referencedColumnName = "est_id")
    @ManyToOne(optional = false)
    private Estabelecimento estIdFk;

    public Produto() {
    }

    public Produto(Integer proId) {
        this.proId = proId;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getProNome() {
        return proNome;
    }

    public void setProNome(String proNome) {
        this.proNome = proNome;
    }

    public Float getProPreco() {
        return proPreco;
    }

    public void setProPreco(Float proPreco) {
        this.proPreco = proPreco;
    }

    public Integer getProQuantidade() {
        return proQuantidade;
    }

    public void setProQuantidade(Integer proQuantidade) {
        this.proQuantidade = proQuantidade;
    }

    @XmlTransient
    public List<ListaConsumidor> getListaconsumidorList() {
        return listaconsumidorList;
    }

    public void setListaconsumidorList(List<ListaConsumidor> listaconsumidorList) {
        this.listaconsumidorList = listaconsumidorList;
    }

    public CategoriaProduto getCategprodIdFk() {
        return categprodIdFk;
    }

    public void setCategprodIdFk(CategoriaProduto categprodIdFk) {
        this.categprodIdFk = categprodIdFk;
    }

    public Estabelecimento getEstIdFk() {
        return estIdFk;
    }

    public void setEstIdFk(Estabelecimento estIdFk) {
        this.estIdFk = estIdFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proId != null ? proId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.proId == null && other.proId != null) || (this.proId != null && !this.proId.equals(other.proId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Produto[ proId=" + proId + " ]";
    }

    public Float getProPeso() {
        return proPeso;
    }

    public void setProPeso(Float peso) {
        this.proPeso = peso;
    }

    public String getProUnidade() {
        return proUnidade;
    }

    public void setProUnidade(String unidade) {
        this.proUnidade = unidade;
    }   

    public String getProImgurl() {
        return proImgurl;
    }

    public void setProImgurl(String proImgurl) {
        this.proImgurl = proImgurl;
    }
}