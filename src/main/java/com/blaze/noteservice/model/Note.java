package com.blaze.noteservice.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column(name="title")
    private String title;
    @Column(name="body")
    private String body;
    @Column(name="created")
    private LocalDateTime created;
    @Column(name="last_modified")
    private LocalDateTime lastModified;
    @Column(name="notebook_id")
    private int notebookId;

    @ManyToMany
    @JoinTable(
            name="note_tag",
            joinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "note_id", referencedColumnName = "id")
    )
    private List<Tag> tags;

}
