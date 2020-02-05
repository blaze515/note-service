package com.blaze.noteservice.repository;

import com.blaze.noteservice.model.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface NotebookRepository extends JpaRepository<Notebook, Integer>, NotebookCustom {

    String SELECT_NOTEBOOK_BY_TAG_AND_ID = "SELECT * FROM notebooks nb, notes n, note_tag nt, tags t WHERE nb.id = n.notebook_id and n.notebook_id = :id and nt.note_id = n.id and t.id = nt.tag_id and t.tag = :tag";

    @Query(value = SELECT_NOTEBOOK_BY_TAG_AND_ID, nativeQuery = true)
    Optional<Notebook> findNotebookByIdAndTag(@Param("id") int id, @Param("tag") String tag);

}
