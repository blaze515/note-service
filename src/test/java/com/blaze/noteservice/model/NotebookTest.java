package com.blaze.noteservice.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@SpringBootTest
public class NotebookTest {

    public static final int ID = 1;
    public static final String NAME = "My Awesome Notebook";
    public static final List<Note> NOTES = Collections.emptyList();
    private Notebook notebook;

    @Before
    public void setUp() throws Exception {
        this.notebook = new Notebook();
        this.notebook.setId(ID);
        this.notebook.setName(NAME);
        this.notebook.setNotes(NOTES);
    }

    @Test
    public void getId() {
        Assert.assertEquals("Id not equal", ID, this.notebook.getId());
    }

    @Test
    public void getName() {
        Assert.assertEquals("Name not equal", NAME, this.notebook.getName());
    }

    @Test
    public void getNotes() {
        Assert.assertEquals("Notes not equal", NOTES, this.notebook.getNotes());
    }
}