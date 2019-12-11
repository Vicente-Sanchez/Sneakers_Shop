/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author VSN_b
 */
@Stateless
public class ImagenesFacade extends AbstractFacade<Imagenes> {

    @PersistenceContext(unitName = "SneakersShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ImagenesFacade() {
        super(Imagenes.class);
    }

    public Imagenes Buscar(String ruta,String idProd) {
        Query consulta = em.createNamedQuery("Imagenes.buscar", Imagenes.class)
                .setParameter("rura", ruta)
                .setParameter("idProd", idProd);
        List<Imagenes> lista = consulta.getResultList();
        if (!lista.isEmpty()) {
            return lista.get(0);
        }
        return null;
    }

}
