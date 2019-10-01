package Modelos;

import Modelos.Producto;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-10-01T09:59:47")
@StaticMetamodel(Categorias.class)
public class Categorias_ { 

    public static volatile SingularAttribute<Categorias, String> descripccionCat;
    public static volatile SingularAttribute<Categorias, Integer> id;
    public static volatile CollectionAttribute<Categorias, Producto> productoCollection;
    public static volatile SingularAttribute<Categorias, String> nombreCat;
    public static volatile SingularAttribute<Categorias, Integer> status;

}