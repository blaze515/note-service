package com.blaze.noteservice.service;

import com.blaze.noteservice.model.Note;
import com.blaze.noteservice.model.Notebook;
import com.blaze.noteservice.repository.NoteRepository;
import com.blaze.noteservice.repository.NotebookRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoteService {

    private NotebookRepository notebookRepository;
    private NoteRepository noteRepository;

    @Autowired
    public NoteService(NotebookRepository notebookRepository, NoteRepository noteRepository) {
        this.notebookRepository = notebookRepository;
        this.noteRepository = noteRepository;
    }

    public Notebook createNotebook(String name) {
        return this.notebookRepository.createNotebook(name);
    }

    public Note createNote(String title, String body, int notebookId) {
        return this.noteRepository.createNote(title, body, notebookId);
    }

    public Note updateNote(int noteId, Note note) {
        return this.noteRepository.updateNote(noteId, note);
    }

    public Optional<Notebook> getNotebook(int notebookId, String tag) {
        if (StringUtils.isEmpty(tag)) return this.notebookRepository.findById(notebookId);
        return this.notebookRepository.findNotebookByIdAndTag(notebookId, tag);
    }

    public Optional<Note> getNote(int noteId) {
        return this.noteRepository.findById(noteId);
    }

    public Boolean deleteNote(int noteId) {
        this.noteRepository.deleteById(noteId);
        final Optional<Note> note = getNote(noteId);
        return !note.isPresent();
    }
}
