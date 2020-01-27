package com.blaze.noteservice.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@Table(name = "Note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String body;
    private LocalDateTime created;
    private LocalDateTime lastModified;

    @ManyToMany
    @JoinTable(
            name="Note_Tag",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "note_id")
    )
    private List<Tag> tags;

}
