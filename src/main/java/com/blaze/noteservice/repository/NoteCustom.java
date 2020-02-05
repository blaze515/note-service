package com.blaze.noteservice.repository;

import javax.transaction.Transactional;

@Transactional
public interface NoteCustom {

    Integer createNote(String title, String body, int notebookId);

}
