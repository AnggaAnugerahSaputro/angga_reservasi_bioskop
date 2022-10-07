package org.binar.bioskop.challenge4.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Films")
public class FilmEntity {

    @Id
    @Column(name = "film_code")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String film_code;

    @Column(name = "film_name")
    private String film_name;

    @Column(name = "film_status")
    private Boolean show_status;

    @Column(name = "genre")
    private String genre;

    @Column(name = "description", length = 400)
    private String description;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "country")
    private String country;

    @Column(name = "language")
    private String language;

    @Column(name = "start_date")
    private LocalDate start_date;

    @Column(name = "end_date")
    private LocalDate end_date;

    @OneToMany(mappedBy = "filmEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ScheduleEntity> scheduleEntities;
}
