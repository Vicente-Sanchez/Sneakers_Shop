/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author VSN_b
 */
@Entity
@Table(name = "det_compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetCompra.findAll", query = "SELECT d FROM DetCompra d")
    , @NamedQuery(name = "DetCompra.findById", query = "SELECT d FROM DetCompra d WHERE d.id = :id")
    , @NamedQuery(name = "DetCompra.findByVenta", query = "SELECT d FROM DetCompra d WHERE d.idComp = :idComp")
    , @NamedQuery(name = "DetCompra.findByCantidad", query = "SELECT d FROM DetCompra d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "DetCompra.findByPrecioCompra", query = "SELECT d FROM DetCompra d WHERE d.precioCompra = :precioCompra")
    , @NamedQuery(name = "DetCompra.findByPrecioVenta", query = "SELECT d FROM DetCompra d WHERE d.precioVenta = :precioVenta")
    , @NamedQuery(name = "DetCompra.findByStatus", query = "SELECT d FROM DetCompra d WHERE d.status = :status")})
public class DetCompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO_COMPRA")
    private float precioCompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO_VENTA")
    private float precioVenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUS")
    private int status;
    @JoinColumn(name = "ID_COMP", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Compras idComp;
    @JoinColumn(name = "ID_PROD", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Producto idProd;

    public DetCompra() {
    }

    public DetCompra(Integer id) {
        this.id = id;
    }

    public DetCompra(Integer id, int cantidad, float precioCompra, float precioVenta, int status) {
        this.id = id;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Compras getIdComp() {
        return idComp;
    }

    public void setIdComp(Compras idComp) {
        this.idComp = idComp;
    }

    public Producto getIdProd() {
        return idProd;
    }

    public void setIdProd(Producto idProd) {
        this.idProd = idProd;
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
        if (!(object instanceof DetCompra)) {
            return false;
        }
        DetCompra other = (DetCompra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Control.DetCompra[ id=" + id + " ]";
    }
    
}
