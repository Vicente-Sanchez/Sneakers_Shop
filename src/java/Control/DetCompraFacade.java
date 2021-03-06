/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author VSN_b
 */
@Stateless
public class DetCompraFacade extends AbstractFacade<DetCompra> {

    @PersistenceContext(unitName = "SneakersShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetCompraFacade() {
        super(DetCompra.class);
    }
    
}
