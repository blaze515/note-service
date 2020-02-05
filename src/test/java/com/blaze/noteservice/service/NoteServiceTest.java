package com.blaze.noteservice.service;

import com.blaze.noteservice.model.Note;
import com.blaze.noteservice.model.Notebook;
import com.blaze.noteservice.model.Tag;
import com.blaze.noteservice.repository.NoteRepository;
import com.blaze.noteservice.repository.NotebookRepository;
import com.blaze.noteservice.repository.TagRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class NoteServiceTest {

    @Mock
    NoteRepository mockNoteRepo;
    @Mock
    NotebookRepository mockNotebookRepo;
    @Mock
    TagRepository mockTagRepo;

    @InjectMocks
    NoteService noteService;

    private Notebook notebook;
    private Note note;
    private Tag tag;
    private List<Note> noteList;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.notebook = new Notebook();
        this.notebook.setId(1);
        this.note = new Note();
        this.tag = new Tag();
        this.note.setPinnedTags(Collections.singletonList(tag));
        this.noteList = Collections.singletonList(note);
        this.notebook.setNotes(noteList);
        Mockito.when(this.mockNotebookRepo.createNotebook(Mockito.anyString())).thenReturn(this.notebook);
    }

    @Test
    public void createNotebook() {
        final Notebook nb = this.noteService.createNotebook("hello");
        assertEquals("Failed create notebook", this.notebook, nb);
    }

    @Test
    public void createNote() {
        final Optional<Note> n = this.noteService.createNote("test", "body", 1);
        assertFalse("Should have failed to create note", n.isPresent());
        final Note note = new Note();
        note.setId(1);
        Mockito.when(this.mockNotebookRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(new Notebook()));
        Mockito.when(this.mockNoteRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(note));
        Mockito.when(this.mockNoteRepo.createNote(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt())).thenReturn(1);
        final Optional<Note> n2 = this.noteService.createNote("test", "body", 1);
        assertTrue("Failed to create note", n2.isPresent());
        assertEquals("Not equal", note, n2.get());
    }

    @Test
    public void updateNote() {
        note.setPinnedTags(Collections.singletonList(tag));
        final Optional<Note> updateNote = this.noteService.updateNote(1, note);
        assertFalse("Should have failed", updateNote.isPresent());
        Mockito.when(this.mockNoteRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(note));
        Mockito.when(this.mockTagRepo.saveAll(Mockito.anyIterable())).thenReturn(Collections.singletonList(tag));
        final Optional<Note> updateNote2 = this.noteService.updateNote(1, note);
        assertEquals("Failed to update", note, updateNote2.get());
    }

    @Test
    public void getNotebook() {
        Mockito.when(this.mockNotebookRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(this.notebook));
        Mockito.when(this.mockNotebookRepo.findNotebookByIdAndTag(Mockito.anyInt(), Mockito.anyString())).thenReturn(Optional.of(this.notebook));
        assertEquals("Not equal", this.notebook, this.noteService.getNotebook(1, "").get());
        assertEquals("Not equal w/ tag", this.notebook, this.noteService.getNotebook(1, "tag").get());
    }

    @Test
    public void getNote() {
        Mockito.when(this.mockNoteRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(note));
        assertEquals("Not equal", this.note, this.noteService.getNote(1).get());
    }

    @Test
    public void deleteNote() {
        Mockito.when(this.mockNoteRepo.existsById(Mockito.anyInt())).thenReturn(true);
        assertFalse("Note false", this.noteService.deleteNote(1));
    }
}