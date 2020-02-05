package com.blaze.noteservice.repository;

import com.blaze.noteservice.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface TagRepository extends JpaRepository<Tag, Integer> {
}
