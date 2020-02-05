package com.blaze.noteservice.repository;

import com.blaze.noteservice.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

@Transactional
public interface NoteRepository extends JpaRepository<Note, Integer>, NoteCustom {

    @Modifying
    @Query(value = "UPDATE notes SET title=:#{#note.title}, body=:#{#note.body}, last_modified=current_timestamp WHERE id = :note_id", nativeQuery = true)
    int updateNote(@Param("note_id") int noteId, @Param("note") Note note);

    @Modifying
    @Query(value = "INSERT INTO note_tag(tag_id,note_id) VALUES(:tag_id,:note_id)", nativeQuery = true)
    int addNoteTag(@Param("note_id") int noteId, @Param("tag_id") int tagId);
}
