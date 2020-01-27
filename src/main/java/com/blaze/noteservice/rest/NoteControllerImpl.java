package com.blaze.noteservice.rest;

import com.blaze.noteservice.model.Note;
import com.blaze.noteservice.model.Notebook;
import com.blaze.noteservice.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class NoteControllerImpl implements NoteController {

    private NoteService noteService;

    @Autowired
    public NoteControllerImpl(NoteService noteService) {
        this.noteService = noteService;
    }

    @Override
    public ResponseEntity<String> createNotebook(String name) {
        return null;
    }

    @Override
    public ResponseEntity<String> createNote(int notebookId, Note note) {
        return null;
    }

    @Override
    public ResponseEntity<String> updateNote(int noteId, Note note) {
        return null;
    }

    @Override
    public ResponseEntity<Notebook> getNotebook(int notebookId, String tag) {
        return null;
    }

    @Override
    public ResponseEntity<Note> getNote(int noteId) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> deleteNote(int noteId) {
        return null;
    }
}
