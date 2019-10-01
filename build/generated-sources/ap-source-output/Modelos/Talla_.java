package Modelos;

import Modelos.Producto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-10-01T09:59:46")
@StaticMetamodel(Talla.class)
public class Talla_ { 

    public static volatile SingularAttribute<Talla, Float> numeroTalla;
    public static volatile SingularAttribute<Talla, Integer> id;
    public static volatile SingularAttribute<Talla, Integer> cantidad;
    public static volatile SingularAttribute<Talla, Producto> idProducto;
    public static volatile SingularAttribute<Talla, Integer> status;

}