package Modelos;

import Modelos.Compras;
import Modelos.Producto;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-10-01T09:59:46")
@StaticMetamodel(Proveedores.class)
public class Proveedores_ { 

    public static volatile SingularAttribute<Proveedores, Integer> numExt;
    public static volatile CollectionAttribute<Proveedores, Compras> comprasCollection;
    public static volatile SingularAttribute<Proveedores, String> cp;
    public static volatile SingularAttribute<Proveedores, String> rfc;
    public static volatile SingularAttribute<Proveedores, String> colonia;
    public static volatile SingularAttribute<Proveedores, Integer> numInt;
    public static volatile SingularAttribute<Proveedores, String> razonSocial;
    public static volatile SingularAttribute<Proveedores, Integer> telefono1;
    public static volatile SingularAttribute<Proveedores, Integer> telefono2;
    public static volatile SingularAttribute<Proveedores, Integer> id;
    public static volatile CollectionAttribute<Proveedores, Producto> productoCollection;
    public static volatile SingularAttribute<Proveedores, String> email;
    public static volatile SingularAttribute<Proveedores, Integer> status;

}