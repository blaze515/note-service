package com.blaze.noteservice.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "notebooks")
public class Notebook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;

    @OneToMany
    @JoinTable(
            name = "notes",
            joinColumns = @JoinColumn(name = "notebook_id", referencedColumnName = "id")
    )
    List<Note> notes;
}
