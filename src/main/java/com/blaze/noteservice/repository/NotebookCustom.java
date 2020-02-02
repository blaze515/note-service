package com.blaze.noteservice.repository;

import com.blaze.noteservice.model.Notebook;

import javax.transaction.Transactional;

@Transactional
public interface NotebookCustom {

    Notebook createNotebook(String name);
}
