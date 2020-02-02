package com.blaze.noteservice.repository;

import com.blaze.noteservice.model.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface NotebookRepository extends JpaRepository<Notebook, Integer>, NotebookCustom {

    String SELECT_NOTEBOOK_BY_TAG_AND_ID = "SELECT n.id, n.title, n.body, n.created, n.last_modified, n.notebook_id, t.tag FROM notes n, note_tag nt, Tag t WHERE n.notebook_id = :id and nt.note_id = :id and t.id = nt.tag_id and t.tag = :tag";

    @Query(value = SELECT_NOTEBOOK_BY_TAG_AND_ID, nativeQuery = true)
    Optional<Notebook> findNotebookByIdAndTag(@Param("id") int id, @Param("tag") String tag);

}
