package com.blaze.noteservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@Table(name = "notes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Note implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="note_tag",
            joinColumns = @JoinColumn(name = "note_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id")
    )
    private List<Tag> pinnedTags;

}
