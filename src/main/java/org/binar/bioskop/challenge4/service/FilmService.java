package org.binar.bioskop.challenge4.service;


import org.binar.bioskop.challenge4.entity.FilmEntity;
import org.binar.bioskop.challenge4.entity.ScheduleEntity;
import org.binar.bioskop.challenge4.request.ScheduleRequest;

import java.util.List;

public interface FilmService {

    FilmEntity create (FilmEntity filmEntity);
    FilmEntity update(String filmCode, FilmEntity filmEntity);
    Boolean delete(String filmCode);

    List<FilmEntity> findAll();
    FilmEntity findById(String filmCode);

    List<FilmEntity> getFilmNow();

    FilmEntity getFindSchedule(String filmCode);

    ScheduleEntity setSchedules(ScheduleRequest scheduleRequest);


}
