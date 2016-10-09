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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "categoriaproduto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriaProduto.findByNome", query = "SELECT c FROM CategoriaProduto c WHERE c.categprodNome = :nome")})
public class CategoriaProduto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "categprod_id")
    private Integer categprodId;
    @Size(max = 50)
    @Column(name = "categprod_nome")
    private String categprodNome;
    @Size(max = 150)
    @Column(name = "categprod_descricao")
    private String categprodDescricao;
    @Column(name = "categprod_urlimg")
    private String categprodurlImg;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categprodIdFk")
    private List<Produto> produtoList = new ArrayList<>();

    public CategoriaProduto() {
    }

    public CategoriaProduto(Integer categprodId) {
        this.categprodId = categprodId;
    }

    public Integer getCategprodId() {
        return categprodId;
    }

    public void setCategprodId(Integer categprodId) {
        this.categprodId = categprodId;
    }

    public String getCategprodNome() {
        return categprodNome;
    }

    public void setCategprodNome(String categprodNome) {
        this.categprodNome = categprodNome;
    }

    public String getCategprodDescricao() {
        return categprodDescricao;
    }

    public void setCategprodDescricao(String categprodDescricao) {
        this.categprodDescricao = categprodDescricao;
    }

    public String getCategprodurlImg() {
        return categprodurlImg;
    }

    public void setCategprodurlImg(String categprodurlImg) {
        this.categprodurlImg = categprodurlImg;
    }

    @XmlTransient
    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categprodId != null ? categprodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaProduto)) {
            return false;
        }
        CategoriaProduto other = (CategoriaProduto) object;
        if ((this.categprodId == null && other.categprodId != null) || (this.categprodId != null && !this.categprodId.equals(other.categprodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Categoriaproduto[ categprodId=" + categprodId + " ]";
    }   
}