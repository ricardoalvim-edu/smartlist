package org.smartlist.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "listaconsumidor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListaConsumidor.findAll", query = "SELECT l FROM ListaConsumidor l"),
    @NamedQuery(name = "ListaConsumidor.findByUsuario", query = "SELECT l FROM ListaConsumidor l WHERE l.consIdFk.consId = :id"),
    @NamedQuery(name = "ListaConsumidor.findByNome", query = "SELECT l FROM ListaConsumidor l WHERE l.lstNome = :nome")})
public class ListaConsumidor implements Serializable {
    @JoinTable(name = "item_lista", joinColumns = {
        @JoinColumn(name = "lst_id_fk", referencedColumnName = "lst_id")}, inverseJoinColumns = {
        @JoinColumn(name = "pro_id_fk", referencedColumnName = "pro_id")})
    @ManyToMany
    private List<Produto> produtoList = new ArrayList<>();
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lst_id")
    private Integer lstId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "lst_nome")
    private String lstNome;
    @JoinColumn(name = "cons_id_fk", referencedColumnName = "cons_id")
    @ManyToOne(optional = false)
    private Consumidor consIdFk;

    public ListaConsumidor() {
    }

    public ListaConsumidor(Integer lstId) {
        this.lstId = lstId;
    }

    public ListaConsumidor(Integer lstId, String lstNome) {
        this.lstId = lstId;
        this.lstNome = lstNome;
    }

    public Integer getLstId() {
        return lstId;
    }

    public void setLstId(Integer lstId) {
        this.lstId = lstId;
    }

    public String getLstNome() {
        return lstNome;
    }

    public void setLstNome(String lstNome) {
        this.lstNome = lstNome;
    }

    @XmlTransient
    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    public Consumidor getConsIdFk() {
        return consIdFk;
    }

    public void setConsIdFk(Consumidor consIdFk) {
        this.consIdFk = consIdFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lstId != null ? lstId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaConsumidor)) {
            return false;
        }
        ListaConsumidor other = (ListaConsumidor) object;
        if ((this.lstId == null && other.lstId != null) || (this.lstId != null && !this.lstId.equals(other.lstId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Listaconsumidor[ lstId=" + lstId + " ]";
    }

}