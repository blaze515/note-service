package com.blaze.noteservice.repository;

import com.blaze.noteservice.model.Note;


import javax.transaction.Transactional;

@Transactional
public interface NoteCustom {

    Note createNote(String title, String body, int notebookId);

}
