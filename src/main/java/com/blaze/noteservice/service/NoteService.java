package com.blaze.noteservice.service;

import com.blaze.noteservice.model.Note;
import com.blaze.noteservice.model.Notebook;
import com.blaze.noteservice.model.Tag;
import com.blaze.noteservice.repository.NoteRepository;
import com.blaze.noteservice.repository.NotebookRepository;
import com.blaze.noteservice.repository.TagRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private NotebookRepository notebookRepository;
    private NoteRepository noteRepository;
    private TagRepository tagRepository;

    @Autowired
    public NoteService(NotebookRepository notebookRepository, NoteRepository noteRepository, TagRepository tagRepository) {
        this.notebookRepository = notebookRepository;
        this.noteRepository = noteRepository;
        this.tagRepository = tagRepository;
    }

    public Notebook createNotebook(String name) {
        return this.notebookRepository.createNotebook(name);
    }

    public Optional<Note> createNote(String title, String body, int notebookId) {
        this.noteRepository.flush();
        Optional<Notebook> nb = this.notebookRepository.findById(notebookId);
        Optional<Note> result = Optional.empty();
        if (!nb.isPresent()) return result;
        Integer id = this.noteRepository.createNote(title, body, notebookId);
        this.noteRepository.flush();
        if (id != null) {
            result = this.noteRepository.findById(id);
        }
        return result;
    }

    public Optional<Note> updateNote(int noteId, Note note) {

        Optional<Note> prev = this.noteRepository.findById(noteId);
        Optional<Note> res = Optional.empty();
        if (!prev.isPresent()) return res;
        Note prevNote = prev.get();
        if (!StringUtils.isEmpty(note.getTitle())) prevNote.setTitle(note.getTitle());
        if (!StringUtils.isEmpty(note.getBody())) prevNote.setBody(note.getBody());
        if (!CollectionUtils.isEmpty(note.getPinnedTags())) {
            prevNote.setPinnedTags(addTags(note.getPinnedTags()));
        }
        this.noteRepository.updateNote(noteId, prevNote);
        this.noteRepository.flush();
        res = Optional.of(prevNote);
        return res;
    }

    private List<Tag> addTags(List<Tag> tags) {
        List<Tag> result = this.tagRepository.saveAll(tags);
        this.tagRepository.flush();
        return result;
    }

    public Optional<Notebook> getNotebook(int notebookId, String tag) {
        if (StringUtils.isEmpty(tag)) return this.notebookRepository.findById(notebookId);
        Optional<Notebook> nbOptional = this.notebookRepository.findNotebookByIdAndTag(notebookId, tag);
        if (!nbOptional.isPresent()) return nbOptional;
        Notebook notebook = nbOptional.get();
        List<Note> filtered = notebook.getNotes().stream().filter(note -> {
            List<Tag> tags = note.getPinnedTags();
            return tags.stream().anyMatch(t -> tag.equals(t.getTag()));
        }).collect(Collectors.toList());
        notebook.setNotes(filtered);
        return Optional.of(notebook);
    }

    public Optional<Note> getNote(int noteId) {
        return this.noteRepository.findById(noteId);
    }

    public Boolean deleteNote(int noteId) {
        Boolean exists = this.noteRepository.existsById(noteId);
        if (exists){
            this.noteRepository.deleteById(noteId);
            this.noteRepository.flush();
            this.tagRepository.flush();
            this.notebookRepository.flush();
            exists = this.noteRepository.existsById(noteId);
        }
        return !exists;
    }
}
