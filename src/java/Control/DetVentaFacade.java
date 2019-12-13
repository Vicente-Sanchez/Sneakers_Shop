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
public class DetVentaFacade extends AbstractFacade<DetVenta> {

    @PersistenceContext(unitName = "SneakersShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetVentaFacade() {
        super(DetVenta.class);
    }
      public List<DetVenta> DetalleVenta(int idventa) {
        Query consulta = em.createNamedQuery("DetalleVenta.findByidventa", DetVenta.class)
                .setParameter("idVenta", idventa);
        List<DetVenta> lista = consulta.getResultList();
        
        return lista;
    }
}
