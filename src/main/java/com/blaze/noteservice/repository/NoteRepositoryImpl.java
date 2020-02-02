package com.blaze.noteservice.repository;

import com.blaze.noteservice.model.Note;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@CommonsLog
public class NoteRepositoryImpl implements NoteCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Note createNote(String title, String body, int notebookId) {
        final Note note = new Note();
        note.setTitle(title);
        note.setBody(body);
        note.setNotebookId(notebookId);
        try {
            em.persist(note);
        } catch (Exception e) {
            log.error("Unable to persist", e);
        }
        return note;
    }
}
