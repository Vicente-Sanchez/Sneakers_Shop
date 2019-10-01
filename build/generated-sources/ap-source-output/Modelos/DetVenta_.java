package Modelos;

import Modelos.Producto;
import Modelos.Ventas;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-10-01T09:59:46")
@StaticMetamodel(DetVenta.class)
public class DetVenta_ { 

    public static volatile SingularAttribute<DetVenta, Producto> idProd;
    public static volatile SingularAttribute<DetVenta, Float> precioCompra;
    public static volatile SingularAttribute<DetVenta, Integer> id;
    public static volatile SingularAttribute<DetVenta, Integer> cantidad;
    public static volatile SingularAttribute<DetVenta, Float> precioVenta;
    public static volatile SingularAttribute<DetVenta, Integer> status;
    public static volatile SingularAttribute<DetVenta, Ventas> idVenta;

}