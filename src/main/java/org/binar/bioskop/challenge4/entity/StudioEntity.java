package org.binar.bioskop.challenge4.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Studio")
public class StudioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String studio_id;

    @Column(name = "Studio_name")
    private String Studio_name;
}
