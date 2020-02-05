package com.blaze.noteservice.rest;

import com.blaze.noteservice.model.Note;
import com.blaze.noteservice.model.Notebook;
import com.blaze.noteservice.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Controller
public class NoteControllerImpl implements NoteController {

    private NoteService noteService;

    @Autowired
    public NoteControllerImpl(NoteService noteService) {
        this.noteService = noteService;
    }

    @Override
    public ResponseEntity<Notebook> createNotebook(String name) {
        final Notebook result = this.noteService.createNotebook(name);
        if (ObjectUtils.isEmpty(result)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Note> createNote(String title, String body, int notebookId) {
        final Optional<Note> result = this.noteService.createNote(title, body, notebookId);
        return result.map(note -> new ResponseEntity<>(note, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @Override
    public ResponseEntity<Note> updateNote(int noteId, Note note) {
        final Optional<Note> result = this.noteService.updateNote(noteId, note);
        if (!result.isPresent()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(result.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Notebook> getNotebook(int notebookId, String tag) {
        final Optional<Notebook> result = this.noteService.getNotebook(notebookId, tag);
        return result.map(notebook -> new ResponseEntity<>(notebook, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Note> getNote(int noteId) {
        final Optional<Note> result = this.noteService.getNote(noteId);
        return result.map(note -> new ResponseEntity<>(note, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Boolean> deleteNote(int noteId) {
        final Boolean result = this.noteService.deleteNote(noteId);
        if (ObjectUtils.isEmpty(result)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
