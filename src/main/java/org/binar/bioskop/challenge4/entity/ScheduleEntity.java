package org.binar.bioskop.challenge4.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "Schedules")
public class ScheduleEntity {

    @Id
    @Column(name = "schedule_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer  scheduleId;

    @Column(name = "show_date")
    private LocalDate showDate;

    @Column(name = "start_time")
    private LocalTime startTime; // waktu mulai

    @Column(name = "end_time")
    private LocalTime endTime; // waktu selesai

    @Column(name = "price")
    private BigDecimal price; // harga tiket

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "filmCode")
    private FilmEntity filmEntity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "studio_id")
    private StudioEntity studioEntity;


}
