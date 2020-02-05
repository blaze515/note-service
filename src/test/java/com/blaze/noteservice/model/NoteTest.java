package com.blaze.noteservice.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class NoteTest {

    public static final String TITLE = "Title";
    public static final String BODY = "body";
    public static final int NOTEBOOK_ID = 1;
    public static final LocalDateTime NOW = LocalDateTime.now();
    public static final int ID = 1;
    public static final List<Tag> PINNED_TAGS = Collections.emptyList();
    private Note note;

    @Before
    public void setUp() throws Exception {
        this.note = new Note();
        this.note.setTitle(TITLE);
        this.note.setBody(BODY);
        this.note.setNotebookId(NOTEBOOK_ID);
        this.note.setCreated(NOW);
        this.note.setLastModified(NOW);
        this.note.setId(ID);
        this.note.setPinnedTags(PINNED_TAGS);
    }

    @Test
    public void getId() {
        Assert.assertEquals("ID note equal", ID, this.note.getId());
    }

    @Test
    public void getTitle() {
        Assert.assertEquals("Title not equal", TITLE, this.note.getTitle());
    }

    @Test
    public void getBody() {
        Assert.assertEquals("Body not equal", BODY, this.note.getBody());
    }

    @Test
    public void getCreated() {
        Assert.assertEquals("Created not equal", NOW, this.note.getCreated());
    }

    @Test
    public void getLastModified() {
        Assert.assertEquals("Last modified not equal", NOW, this.note.getLastModified());
    }

    @Test
    public void getNotebookId() {
        Assert.assertEquals("Notebook id not equal", NOTEBOOK_ID, this.note.getNotebookId());
    }

    @Test
    public void getPinnedTags() {
        Assert.assertEquals("Tags not equal", PINNED_TAGS, this.note.getPinnedTags());
    }
}