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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "categoriaestabelecimento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriaEstabelecimento.findAll", query = "SELECT c FROM CategoriaEstabelecimento c"),
    @NamedQuery(name = "CategoriaEstabelecimento.findById", query = "SELECT c FROM CategoriaEstabelecimento c WHERE c.catEstId = :categestId"),
    @NamedQuery(name = "CategoriaEstabelecimento.findByNome", query = "SELECT c FROM CategoriaEstabelecimento c WHERE c.catEstNome = :nome"),
    @NamedQuery(name = "CategoriaEstabelecimento.findByDescricao", query = "SELECT c FROM CategoriaEstabelecimento c WHERE c.catEstDescricao = :categestaDescricao")})
public class CategoriaEstabelecimento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "categest_id")
    private Integer catEstId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "categest_nome")
    private String catEstNome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "categesta_descricao")
    private String catEstDescricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categestIdFk")
    private List<Estabelecimento> estabelecimentoList = new ArrayList<Estabelecimento>();

    public CategoriaEstabelecimento() {
    }

    public CategoriaEstabelecimento(Integer categestId) {
        this.catEstId = categestId;
    }

    public CategoriaEstabelecimento(Integer categestId, String categestNome, String categestaDescricao) {
        this.catEstId = categestId;
        this.catEstNome = categestNome;
        this.catEstDescricao = categestaDescricao;
    }

    public Integer getCatEstId() {
        return catEstId;
    }

    public void setCatEestId(Integer catEstId) {
        this.catEstId = catEstId;
    }

    public String getCatEstNome() {
        return catEstNome;
    }

    public void setCatEstNome(String categestNome) {
        this.catEstNome = categestNome;
    }

    public String getCatEstDescricao() {
        return catEstDescricao;
    }

    public void setCatEstDescricao(String catEstDescricao) {
        this.catEstDescricao = catEstDescricao;
    }

    @XmlTransient
    public List<Estabelecimento> getEstabelecimentoList() {
        return estabelecimentoList;
    }

    public void setEstabelecimentoList(List<Estabelecimento> estabelecimentoList) {
        this.estabelecimentoList = estabelecimentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catEstId != null ? catEstId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaEstabelecimento)) {
            return false;
        }
        CategoriaEstabelecimento other = (CategoriaEstabelecimento) object;
        if ((this.catEstId == null && other.catEstId != null) || (this.catEstId != null && !this.catEstId.equals(other.catEstId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Categoriaestabelecimento[ categestId=" + catEstId + " ]";
    }   
}