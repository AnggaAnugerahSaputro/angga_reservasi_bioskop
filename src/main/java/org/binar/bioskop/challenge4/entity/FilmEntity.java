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
    private String filmCode;

    @Column(name = "film_name")
    private String filmName;

    @Column(name = "film_status")
    private Boolean showStatus;

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
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @OneToMany(mappedBy = "filmEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ScheduleEntity> scheduleEntities;
}
