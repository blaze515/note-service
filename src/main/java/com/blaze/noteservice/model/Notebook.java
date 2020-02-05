package com.blaze.noteservice.model;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "notebooks")
public class Notebook implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "notebookId", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    List<Note> notes;
}
