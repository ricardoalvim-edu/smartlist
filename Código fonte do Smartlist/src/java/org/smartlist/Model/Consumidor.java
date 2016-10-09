package org.smartlist.Model;

import java.io.Serializable;
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
@Table(name = "consumidor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consumidor.findByNome", query = "SELECT c FROM Consumidor c WHERE c.consNome = :nome"),
    @NamedQuery(name = "Consumidor.findByUsuario", query = "FROM Consumidor c WHERE c.consUsuario = :usuario"),
    @NamedQuery(name = "Consumidor.findByEmail", query = "FROM Consumidor c WHERE c.consEmail = :email")})
public class Consumidor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cons_id")
    private Integer consId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cons_email")
    private String consEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "cons_nome")
    private String consNome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "cons_usuario")
    private String consUsuario;
    @Size(max = 100)
    @Column(name = "cons_senha")
    private String consSenha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consIdFk")
    private List<ListaConsumidor> listaconsumidorList;

    public Consumidor() {
    }

    public Consumidor(Integer consId) {
        this.consId = consId;
    }

    public Consumidor(Integer consId, String consNome, String consCpf, String consUsuario, String consEmail) {
        this.consId = consId;
        this.consNome = consNome;
        this.consUsuario = consUsuario;
        this.consEmail = consEmail;
    }
    
    public String getConsEmail() {
        return consEmail;
    }

    public void setConsEmail(String consEmail) {
        this.consEmail = consEmail;
    }

    public Integer getConsId() {
        return consId;
    }

    public void setConsId(Integer consId) {
        this.consId = consId;
    }

    public String getConsNome() {
        return consNome;
    }

    public void setConsNome(String consNome) {
        this.consNome = consNome;
    }

    public String getConsUsuario() {
        return consUsuario;
    }

    public void setConsUsuario(String consUsuario) {
        this.consUsuario = consUsuario;
    }

    public String getConsSenha() {
        return consSenha;
    }

    public void setConsSenha(String consSenha) {
        this.consSenha = consSenha;
    }

    @XmlTransient
    public List<ListaConsumidor> getListaconsumidorList() {
        return listaconsumidorList;
    }

    public void setListaconsumidorList(List<ListaConsumidor> listaconsumidorList) {
        this.listaconsumidorList = listaconsumidorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (consId != null ? consId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consumidor)) {
            return false;
        }
        Consumidor other = (Consumidor) object;
        if ((this.consId == null && other.consId != null) || (this.consId != null && !this.consId.equals(other.consId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Consumidor[ consId=" + consId + " ]";
    }
}