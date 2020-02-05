package com.blaze.noteservice.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@SpringBootTest
public class TagTest {

    public static final int ID = 1;
    public static final String TAG = "chemistry";
    private Tag tag;
    private List<Note> noteList;

    @Before
    public void setUp() throws Exception {
        this.tag = new Tag();
        this.tag.setId(ID);
        noteList = Collections.singletonList(new Note());
        this.tag.setNotes(noteList);
        this.tag.setTag(TAG);
    }

    @Test
    public void getId() {
        Assert.assertEquals("Not equal -- id", ID, this.tag.getId());
    }

    @Test
    public void getTag() {
        Assert.assertEquals("Not equal -- tag", TAG, this.tag.getTag());
    }

    @Test
    public void getNotes() {
        Assert.assertEquals("Not equal -- notes", noteList, this.tag.getNotes());
    }
}