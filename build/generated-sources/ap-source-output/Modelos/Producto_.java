package Modelos;

import Modelos.Categorias;
import Modelos.DetCompra;
import Modelos.DetVenta;
import Modelos.Genero;
import Modelos.Imagenes;
import Modelos.Proveedores;
import Modelos.Talla;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-10-01T09:59:47")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile SingularAttribute<Producto, Proveedores> idProv;
    public static volatile CollectionAttribute<Producto, Imagenes> imagenesCollection;
    public static volatile SingularAttribute<Producto, Genero> idGen;
    public static volatile SingularAttribute<Producto, Float> precioProd;
    public static volatile SingularAttribute<Producto, String> descripProd;
    public static volatile SingularAttribute<Producto, Integer> id;
    public static volatile SingularAttribute<Producto, String> nombreProd;
    public static volatile SingularAttribute<Producto, Categorias> idCategoria;
    public static volatile CollectionAttribute<Producto, DetVenta> detVentaCollection;
    public static volatile CollectionAttribute<Producto, DetCompra> detCompraCollection;
    public static volatile CollectionAttribute<Producto, Talla> tallaCollection;
    public static volatile SingularAttribute<Producto, Integer> status;

}