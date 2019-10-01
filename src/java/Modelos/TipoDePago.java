/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Omarb
 */
@Entity
@Table(name = "tipo_de_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDePago.findAll", query = "SELECT t FROM TipoDePago t")
    , @NamedQuery(name = "TipoDePago.findById", query = "SELECT t FROM TipoDePago t WHERE t.id = :id")
    , @NamedQuery(name = "TipoDePago.findByNombre", query = "SELECT t FROM TipoDePago t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoDePago.findByDescripccion", query = "SELECT t FROM TipoDePago t WHERE t.descripccion = :descripccion")
    , @NamedQuery(name = "TipoDePago.findByStatus", query = "SELECT t FROM TipoDePago t WHERE t.status = :status")})
public class TipoDePago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPCCION")
    private String descripccion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUS")
    private int status;

    public TipoDePago() {
    }

    public TipoDePago(Integer id) {
        this.id = id;
    }

    public TipoDePago(Integer id, String nombre, String descripccion, int status) {
        this.id = id;
        this.nombre = nombre;
        this.descripccion = descripccion;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripccion() {
        return descripccion;
    }

    public void setDescripccion(String descripccion) {
        this.descripccion = descripccion;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
        if (!(object instanceof TipoDePago)) {
            return false;
        }
        TipoDePago other = (TipoDePago) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelos.TipoDePago[ id=" + id + " ]";
    }
    
}
