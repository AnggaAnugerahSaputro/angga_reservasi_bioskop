package org.binar.bioskop.challenge4.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Schedules")
public class ScheduleEntity {

    @Id
    @Column(name = "schedule_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer  schedule_id;

    @Column
    private LocalDate show_date;

    @Column
    private LocalTime start_time; // waktu mulai

    @Column
    private LocalTime end_time; // waktu selesai

    @Column
    private BigDecimal price; // harga tiket

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "film_code")
    private FilmEntity filmEntity;


}
