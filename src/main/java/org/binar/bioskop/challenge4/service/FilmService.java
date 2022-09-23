package org.binar.bioskop.challenge4.service;


import org.binar.bioskop.challenge4.entity.FilmEntity;
import org.binar.bioskop.challenge4.entity.ScheduleEntity;
import org.binar.bioskop.challenge4.request.ScheduleRequest;

import java.util.List;

public interface FilmService {

    FilmEntity create (FilmEntity filmEntity);
    FilmEntity update(String film_code, FilmEntity filmEntity);
    Boolean delete(String film_code);

    List<FilmEntity> findAll();
    FilmEntity findById(String film_code);

    List<FilmEntity> getFilmNow();

    FilmEntity getFindSchedule(String film_code);

    ScheduleEntity setSchedules(ScheduleRequest scheduleRequest);


}
