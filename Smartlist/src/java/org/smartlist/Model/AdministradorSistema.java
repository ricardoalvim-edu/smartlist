package org.smartlist.Model;

import java.io.Serializable;
import javax.persistence.Basic;
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

@Entity
@Table(name = "administradorsistema")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdministradorSistema.findByAdmsisId", query = "SELECT a FROM AdministradorSistema a WHERE a.admsisId = :admsisId"),
    @NamedQuery(name = "AdministradorSistema.findByUsuario", query = "SELECT a FROM AdministradorSistema a WHERE a.admsisUsuario = :usuario"),
    @NamedQuery(name = "AdministradorSistema.findByAdmsisSenha", query = "SELECT a FROM AdministradorSistema a WHERE a.admsisSenha = :admsisSenha"),
    @NamedQuery(name = "AdministradorSistema.findByAdmsisNome", query = "SELECT a FROM AdministradorSistema a WHERE a.admsisNome = :admsisNome"),
    @NamedQuery(name = "AdministradorSistema.findByEmail", query = "SELECT a FROM AdministradorSistema a WHERE a.admsisEmail = :email")})
public class AdministradorSistema implements Serializable {
    @OneToMany(mappedBy = "admsisIdFk")
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "admsis_id")
    private Integer admsisId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "admsis_usuario")
    private String admsisUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "admsis_senha")
    private String admsisSenha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "admsis_nome")
    private String admsisNome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "admsis_email")
    private String admsisEmail;
    
    public AdministradorSistema() {
    }

    public AdministradorSistema(Integer admsisId) {
        this.admsisId = admsisId;
    }

    public AdministradorSistema(Integer admsisId, String admsisUsuario, String admsisSenha, String admsisNome, String admsisEmail) {
        this.admsisId = admsisId;
        this.admsisUsuario = admsisUsuario;
        this.admsisSenha = admsisSenha;
        this.admsisNome = admsisNome;
        this.admsisEmail = admsisEmail;
    }

    public Integer getAdmsisId() {
        return admsisId;
    }

    public void setAdmsisId(Integer admsisId) {
        this.admsisId = admsisId;
    }

    public String getAdmsisUsuario() {
        return admsisUsuario;
    }

    public void setAdmsisUsuario(String admsisUsuario) {
        this.admsisUsuario = admsisUsuario;
    }

    public String getAdmsisSenha() {
        return admsisSenha;
    }

    public void setAdmsisSenha(String admsisSenha) {
        this.admsisSenha = admsisSenha;
    }

    public String getAdmsisNome() {
        return admsisNome;
    }

    public void setAdmsisNome(String admsisNome) {
        this.admsisNome = admsisNome;
    }

    public String getAdmsisEmail() {
        return admsisEmail;
    }

    public void setAdmsisEmail(String admsisEmail) {
        this.admsisEmail = admsisEmail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (admsisId != null ? admsisId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdministradorSistema)) {
            return false;
        }
        AdministradorSistema other = (AdministradorSistema) object;
        if ((this.admsisId == null && other.admsisId != null) || (this.admsisId != null && !this.admsisId.equals(other.admsisId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Administradorsistema[ admsisId=" + admsisId + " ]";
    }
}