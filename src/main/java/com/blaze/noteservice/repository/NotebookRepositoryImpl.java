package com.blaze.noteservice.repository;

import com.blaze.noteservice.model.Notebook;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class NotebookRepositoryImpl implements NotebookCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Notebook createNotebook(String name) {
        final Notebook n = new Notebook();
        n.setName(name);
        em.persist(n);
        return n;
    }
}
