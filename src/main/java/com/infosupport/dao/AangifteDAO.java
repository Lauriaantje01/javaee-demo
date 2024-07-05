package com.infosupport.dao;

import com.infosupport.domein.Aangifte;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Dependent
public class AangifteDAO implements AbstractDao<Aangifte>{

    @PersistenceContext(name = "MySQL")
    EntityManager entityManager;

    public List<Aangifte> getAangiftes(){
        return entityManager.createQuery("select a from Aangifte a", Aangifte.class).getResultList();
    }

    @Transactional
    public void delete(int id){
        entityManager.remove(entityManager.find(Aangifte.class, id));
    }

    @Transactional
    public Aangifte add(Aangifte aangifte){
        return entityManager.merge(aangifte);
    }

    public Aangifte get(int id) {
       return entityManager.find(Aangifte.class, id);
    }
}
