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
@Table(name = "cidade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cidade.findAll", query = "SELECT c FROM Cidade c"),
    @NamedQuery(name = "Cidade.findByCidadeId", query = "SELECT c FROM Cidade c WHERE c.cidadeId = :cidadeId"),
    @NamedQuery(name = "Cidade.findByNome", query = "SELECT c FROM Cidade c WHERE c.cidadeNome = :nome")})
public class Cidade implements Serializable {
    @Size(max = 255)
    @Column(name = "cidade_imgurl")
    private String cidadeImgurl;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cidade_id")
    private Integer cidadeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "cidade_nome")
    private String cidadeNome;
    @JoinColumn(name = "estado_id_fk", referencedColumnName = "estado_id")
    @ManyToOne(optional = false)
    private Estado estadoIdFk;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cidadeIdFk")
    private List<Estabelecimento> estabelecimentoList = new ArrayList<>();

    public Cidade() {
    }

    public Cidade(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }

    public Cidade(Integer cidadeId, String cidadeNome) {
        this.cidadeId = cidadeId;
        this.cidadeNome = cidadeNome;
    }

    public Integer getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }

    public String getCidadeNome() {
        return cidadeNome;
    }

    public void setCidadeNome(String cidadeNome) {
        this.cidadeNome = cidadeNome;
    }

    public Estado getEstadoIdFk() {
        return estadoIdFk;
    }

    public void setEstadoIdFk(Estado estadoIdFk) {
        this.estadoIdFk = estadoIdFk;
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
        hash += (cidadeId != null ? cidadeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cidade)) {
            return false;
        }
        Cidade other = (Cidade) object;
        if ((this.cidadeId == null && other.cidadeId != null) || (this.cidadeId != null && !this.cidadeId.equals(other.cidadeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Cidade[ cidadeId=" + cidadeId + " ]";
    }

    public String getCidadeImgurl() {
        return cidadeImgurl;
    }

    public void setCidadeImgurl(String cidadeImgurl) {
        this.cidadeImgurl = cidadeImgurl;
    }
    
}
