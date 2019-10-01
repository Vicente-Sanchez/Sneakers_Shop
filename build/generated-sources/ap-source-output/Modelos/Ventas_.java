package Modelos;

import Modelos.DetVenta;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-10-01T09:59:46")
@StaticMetamodel(Ventas.class)
public class Ventas_ { 

    public static volatile SingularAttribute<Ventas, Date> fecha;
    public static volatile SingularAttribute<Ventas, Float> total;
    public static volatile SingularAttribute<Ventas, Integer> idCliente;
    public static volatile SingularAttribute<Ventas, Float> iva;
    public static volatile SingularAttribute<Ventas, Integer> idTipodepago;
    public static volatile SingularAttribute<Ventas, Float> subtotal;
    public static volatile SingularAttribute<Ventas, Integer> id;
    public static volatile CollectionAttribute<Ventas, DetVenta> detVentaCollection;
    public static volatile SingularAttribute<Ventas, Integer> status;

}