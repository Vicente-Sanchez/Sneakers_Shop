package Modelos;

import Modelos.Compras;
import Modelos.Producto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-10-01T09:59:46")
@StaticMetamodel(DetCompra.class)
public class DetCompra_ { 

    public static volatile SingularAttribute<DetCompra, Compras> idComp;
    public static volatile SingularAttribute<DetCompra, Producto> idProd;
    public static volatile SingularAttribute<DetCompra, Integer> precioCompra;
    public static volatile SingularAttribute<DetCompra, Integer> id;
    public static volatile SingularAttribute<DetCompra, Integer> cantidad;
    public static volatile SingularAttribute<DetCompra, Integer> precioVenta;
    public static volatile SingularAttribute<DetCompra, Integer> status;

}