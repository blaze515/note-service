package com.blaze.noteservice.repository;

import com.blaze.noteservice.model.Note;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.JDBCException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@CommonsLog
public class NoteRepositoryImpl implements NoteCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Integer createNote(String title, String body, int notebookId) {
        Integer res = null;
        try {
            Note note = new Note();
            note.setTitle(title);
            note.setBody(body);
            note.setNotebookId(notebookId);
            note.setLastModified(LocalDateTime.now());
            note.setCreated(LocalDateTime.now());
            em.persist(note);
            em.flush();
            em.refresh(note);
            res = note.getId();
        } catch (JDBCException e) {
            log.debug(e);
        }

        return res;
    }


}
