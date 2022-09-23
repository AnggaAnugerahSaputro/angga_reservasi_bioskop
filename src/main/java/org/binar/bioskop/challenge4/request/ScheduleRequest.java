package org.binar.bioskop.challenge4.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleRequest {

    private String film_code;

    private LocalDate show_date;

    private LocalTime start_time;

    private LocalTime end_time;

    private BigDecimal price;
}
