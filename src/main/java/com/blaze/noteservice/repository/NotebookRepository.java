package com.blaze.noteservice.repository;

import com.blaze.noteservice.model.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
interface NotebookRepository extends JpaRepository<Notebook, Integer> {


    Notebook findNotebookById(@Param("notebookid") int id);

    @Query("SELECT n.id, n.title, n.body, n.created, n.last_modified, n.notebook_id, t.tag FROM Note n, Note_Tag nt, Tag t WHERE n.notebookid = id and nt.note_id = id and t.id = nt.tag_id and t.tag = tag")
    Notebook findNotebookByIdAndTag(@Param("notebookid") int id, @Param("tag") String tag);
}
