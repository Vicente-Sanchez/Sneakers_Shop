/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.util.Collection;
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

/**
 *
 * @author Omarb
 */
@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findById", query = "SELECT p FROM Producto p WHERE p.id = :id")
    , @NamedQuery(name = "Producto.findByNombreProd", query = "SELECT p FROM Producto p WHERE p.nombreProd = :nombreProd")
    , @NamedQuery(name = "Producto.findByDescripProd", query = "SELECT p FROM Producto p WHERE p.descripProd = :descripProd")
    , @NamedQuery(name = "Producto.findByPrecioProd", query = "SELECT p FROM Producto p WHERE p.precioProd = :precioProd")
    , @NamedQuery(name = "Producto.findByStatus", query = "SELECT p FROM Producto p WHERE p.status = :status")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE_PROD")
    private String nombreProd;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "DESCRIP_PROD")
    private String descripProd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRECIO_PROD")
    private float precioProd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STATUS")
    private int status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProd")
    private Collection<Imagenes> imagenesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto")
    private Collection<Talla> tallaCollection;
    @JoinColumn(name = "ID_CATEGORIA", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Categorias idCategoria;
    @JoinColumn(name = "ID_GEN", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Genero idGen;
    @JoinColumn(name = "ID_PROV", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Proveedores idProv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProd")
    private Collection<DetCompra> detCompraCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProd")
    private Collection<DetVenta> detVentaCollection;

    public Producto() {
    }

    public Producto(Integer id) {
        this.id = id;
    }

    public Producto(Integer id, String nombreProd, String descripProd, float precioProd, int status) {
        this.id = id;
        this.nombreProd = nombreProd;
        this.descripProd = descripProd;
        this.precioProd = precioProd;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreProd() {
        return nombreProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    public String getDescripProd() {
        return descripProd;
    }

    public void setDescripProd(String descripProd) {
        this.descripProd = descripProd;
    }

    public float getPrecioProd() {
        return precioProd;
    }

    public void setPrecioProd(float precioProd) {
        this.precioProd = precioProd;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Imagenes> getImagenesCollection() {
        return imagenesCollection;
    }

    public void setImagenesCollection(Collection<Imagenes> imagenesCollection) {
        this.imagenesCollection = imagenesCollection;
    }

    @XmlTransient
    public Collection<Talla> getTallaCollection() {
        return tallaCollection;
    }

    public void setTallaCollection(Collection<Talla> tallaCollection) {
        this.tallaCollection = tallaCollection;
    }

    public Categorias getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categorias idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Genero getIdGen() {
        return idGen;
    }

    public void setIdGen(Genero idGen) {
        this.idGen = idGen;
    }

    public Proveedores getIdProv() {
        return idProv;
    }

    public void setIdProv(Proveedores idProv) {
        this.idProv = idProv;
    }

    @XmlTransient
    public Collection<DetCompra> getDetCompraCollection() {
        return detCompraCollection;
    }

    public void setDetCompraCollection(Collection<DetCompra> detCompraCollection) {
        this.detCompraCollection = detCompraCollection;
    }

    @XmlTransient
    public Collection<DetVenta> getDetVentaCollection() {
        return detVentaCollection;
    }

    public void setDetVentaCollection(Collection<DetVenta> detVentaCollection) {
        this.detVentaCollection = detVentaCollection;
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
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelos.Producto[ id=" + id + " ]";
    }
    
}
