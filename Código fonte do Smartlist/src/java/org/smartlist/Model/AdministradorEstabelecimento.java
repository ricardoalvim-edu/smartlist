package org.smartlist.Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "administradorestabelecimento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdministradorEstabelecimento.findByAdmestId", query = "SELECT a FROM AdministradorEstabelecimento a WHERE a.admestId = :admestId"),
    @NamedQuery(name = "AdministradorEstabelecimento.findByAdmestNome", query = "SELECT a FROM AdministradorEstabelecimento a WHERE a.admestNome = :admestNome"),
    @NamedQuery(name = "AdministradorEstabelecimento.findByUsuario", query = "SELECT a FROM AdministradorEstabelecimento a WHERE a.admestUsuario = :usuario"),
    @NamedQuery(name = "AdministradorEstabelecimento.findByAdmestSenha", query = "SELECT a FROM AdministradorEstabelecimento a WHERE a.admestSenha = :admestSenha"),
    @NamedQuery(name = "AdministradorEstabelecimento.findByEmail", query = "SELECT a FROM AdministradorEstabelecimento a WHERE a.admestEmail = :email")})
public class AdministradorEstabelecimento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "admest_id")
    private Integer admestId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "admest_nome")
    private String admestNome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "admest_usuario")
    private String admestUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "admest_senha")
    private String admestSenha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "admest_email")
    private String admestEmail;
    @JoinColumn(name = "est_id_fk", referencedColumnName = "est_id")
    @ManyToOne(optional = false)
    private Estabelecimento estIdFk;
 
    public AdministradorEstabelecimento() {
    }

    public AdministradorEstabelecimento(Integer admestId) {
        this.admestId = admestId;
    }

    public AdministradorEstabelecimento(Integer admestId, String admestNome, String admestUsuario, String admestSenha, String admestEmail) {
        this.admestId = admestId;
        this.admestNome = admestNome;
        this.admestUsuario = admestUsuario;
        this.admestSenha = admestSenha;
        this.admestEmail = admestEmail;
    }

    public Integer getAdmestId() {
        return admestId;
    }

    public void setAdmestId(Integer admestId) {
        this.admestId = admestId;
    }

    public String getAdmestNome() {
        return admestNome;
    }

    public void setAdmestNome(String admestNome) {
        this.admestNome = admestNome;
    }

    public String getAdmestUsuario() {
        return admestUsuario;
    }

    public void setAdmestUsuario(String admestUsuario) {
        this.admestUsuario = admestUsuario;
    }

    public String getAdmestSenha() {
        return admestSenha;
    }

    public void setAdmestSenha(String admestSenha) {
        this.admestSenha = admestSenha;
    }

    public String getAdmestEmail() {
        return admestEmail;
    }

    public void setAdmestEmail(String admestEmail) {
        this.admestEmail = admestEmail;
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
        hash += (admestId != null ? admestId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdministradorEstabelecimento)) {
            return false;
        }
        AdministradorEstabelecimento other = (AdministradorEstabelecimento) object;
        if ((this.admestId == null && other.admestId != null) || (this.admestId != null && !this.admestId.equals(other.admestId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Administradorestabelecimento[ admestId=" + admestId + " ]";
    } 
}