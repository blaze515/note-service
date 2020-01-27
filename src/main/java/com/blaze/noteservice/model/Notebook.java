package com.blaze.noteservice.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Notebook")
public class Notebook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @OneToMany
    @JoinTable(
            name = "Note",
            joinColumns = @JoinColumn(name = "notebook_id")
    )
    List<Note> notes;
}
