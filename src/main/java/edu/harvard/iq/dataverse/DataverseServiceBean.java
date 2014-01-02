/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.harvard.iq.dataverse;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author gdurand
 */

@Stateless
@Named
public class DataverseServiceBean {

    private static final Logger logger = Logger.getLogger(DataverseServiceBean.class.getCanonicalName());
    @EJB
    IndexServiceBean indexService;

    @PersistenceContext(unitName = "VDCNet-ejbPU")
    private EntityManager em;

    public Dataverse save(Dataverse dataverse) {
        Dataverse savedDataverse = em.merge(dataverse);
        String indexingResult = indexService.index();
        logger.info("during dataverse save, indexing result was: " + indexingResult);
        return savedDataverse;
    }

    public Dataverse find(Object pk) {
        return (Dataverse) em.find(Dataverse.class, pk);
    }    
    
    public List<Dataverse> findAll() {
        return em.createQuery("select object(o) from Dataverse as o order by o.name").getResultList();
    }
    
    public List<Dataverse> findByOwnerId(Long ownerId) {
         Query query = em.createQuery("select object(o) from Dataverse as o where o.owner.id =:ownerId order by o.name");
         query.setParameter("ownerId", ownerId);
         return query.getResultList();
    }  
    
    public Dataverse findRootDataverse() {
        return (Dataverse) em.createQuery("select object(o) from Dataverse as o where o.owner.id = null").getSingleResult();
    }    
}
