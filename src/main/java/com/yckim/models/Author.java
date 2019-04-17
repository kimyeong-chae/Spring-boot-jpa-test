package com.yckim.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
public class Author implements Serializable {


    private static final long serialVersionUID = -5693505151831354085L;

    public Author() {}

    public Author(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 12)
    private int id;

    @Column(length = 20)
    private String name;

    @Column(length = 12)
    private int age;
}