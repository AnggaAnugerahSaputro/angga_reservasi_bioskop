package org.binar.bioskop.challenge4.service.implement;

import org.binar.bioskop.challenge4.entity.FilmEntity;
import org.binar.bioskop.challenge4.entity.ScheduleEntity;
import org.binar.bioskop.challenge4.repository.FilmRepository;
import org.binar.bioskop.challenge4.request.ScheduleRequest;
import org.binar.bioskop.challenge4.service.FilmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    FilmRepository filmRepository;


    @Override
    public FilmEntity create(FilmEntity filmEntity) {
        filmRepository.saveAndFlush(filmEntity);
        return filmEntity;
    }

    @Override
    public FilmEntity update(String film_code, FilmEntity filmEntity) {
        FilmEntity result = findById(film_code);
        if (result != null) {
            logger.info("Movie data available");
            result.setFilm_name(filmEntity.getFilm_name());
            result.setShow_status(filmEntity.getShow_status());
            result.setGenre(filmEntity.getGenre());
            result.setDescription(filmEntity.getDescription());
            result.setDuration(filmEntity.getDuration());
            result.setCountry(filmEntity.getCountry());
            result.setLanguage(filmEntity.getLanguage());
            result.setStart_date(filmEntity.getStart_date());
            result.setEnd_date(filmEntity.getEnd_date());
            filmRepository.saveAndFlush(result);
        }else{
            logger.info("Movie data available");
        }
        return result;
    }
    @Override
    public Boolean delete(String film_code) {
        final FilmEntity result = findById(film_code);
        if (result != null) { // jika tidak null delete
            logger.info("Delete movie data from database");
            filmRepository.deleteById(film_code);
            return true; // true jika berhasil
        }else {
            logger.error("movie data not available");
        }
        return false;
    }

    @Override
    public List<FilmEntity> findAll() {
        logger.info("Fetch movie data from database");
        return filmRepository.findAll();
    }

    @Override
    public FilmEntity findById(String film_code) {
        Optional<FilmEntity> result = filmRepository.findById(film_code);
        if (result.isPresent()) {  // jika misal ada
            logger.info("Movie data available");
            return result.get();
        }else {
            logger.error("Movie data not entered");
        }
        return null;
    }

    @Override
    public List<FilmEntity> getFilmNow() {
        List<FilmEntity> filmEntities = filmRepository.findAll().stream().filter(FilmEntity::getShow_status).collect(Collectors.toList());
        if (filmEntities.isEmpty()){
            logger.info("Movie data available");
            return new ArrayList<>();
        }else {
            logger.info("movie data not available");
        }
        return filmEntities;
    }

    @Override
    public FilmEntity getFindSchedule(String film_code) {
        Optional<FilmEntity> result = filmRepository.findById(film_code);
        if (result.isPresent()) {  // jika misal ada
            logger.info("Schedule Data available");
            return result.get();
        }else {
            logger.error("Schedule data not available");
        }
        return null;
    }

    @Override
    public ScheduleEntity setSchedules(ScheduleRequest scheduleRequest) {
        Optional<FilmEntity> result = filmRepository.findById(scheduleRequest.getFilm_code());
        if (result.isPresent()) {  // jika misal ada
            logger.info("Set schedule data available");
            FilmEntity filmEntity = result.get();
            ScheduleEntity scheduleEntity = new ScheduleEntity();
            scheduleEntity.setShow_date(scheduleRequest.getShow_date());
            scheduleEntity.setStart_time(scheduleRequest.getStart_time());
            scheduleEntity.setEnd_time(scheduleRequest.getEnd_time());
            scheduleEntity.setPrice(scheduleRequest.getPrice());
            scheduleEntity.setFilmEntity(filmEntity);
            filmEntity.getScheduleEntities().add(scheduleEntity);
            filmRepository.save(filmEntity);
        }else {
            logger.error("Set schedule data not available");
        }
        return null;
    }


}
