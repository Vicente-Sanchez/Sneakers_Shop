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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "SneakersShopPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    public Usuario Buscar(String usu, String pas){
        Query consulta = em.createNamedQuery("Usuario.buscar",Usuario.class)
                .setParameter("username", usu)
                .setParameter("password", pas);
        List<Usuario> lista = consulta.getResultList();
        if(!lista.isEmpty()){
            return lista.get(0);
        }
        return null;
    }
}
