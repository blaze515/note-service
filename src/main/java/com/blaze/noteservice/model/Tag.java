package com.blaze.noteservice.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String tag;
}
