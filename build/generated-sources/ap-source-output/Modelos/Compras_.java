package Modelos;

import Modelos.DetCompra;
import Modelos.Proveedores;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-10-01T09:59:46")
@StaticMetamodel(Compras.class)
public class Compras_ { 

    public static volatile SingularAttribute<Compras, Proveedores> idProv;
    public static volatile SingularAttribute<Compras, Date> fecha;
    public static volatile SingularAttribute<Compras, Float> total;
    public static volatile SingularAttribute<Compras, Float> iva;
    public static volatile SingularAttribute<Compras, Float> subtotal;
    public static volatile SingularAttribute<Compras, Integer> id;
    public static volatile CollectionAttribute<Compras, DetCompra> detCompraCollection;
    public static volatile SingularAttribute<Compras, Integer> status;

}