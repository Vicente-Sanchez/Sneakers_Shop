package Modelos;

import Modelos.Producto;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-10-01T09:59:47")
@StaticMetamodel(Genero.class)
public class Genero_ { 

    public static volatile SingularAttribute<Genero, Integer> id;
    public static volatile CollectionAttribute<Genero, Producto> productoCollection;
    public static volatile SingularAttribute<Genero, String> nombre;
    public static volatile SingularAttribute<Genero, Integer> status;

}