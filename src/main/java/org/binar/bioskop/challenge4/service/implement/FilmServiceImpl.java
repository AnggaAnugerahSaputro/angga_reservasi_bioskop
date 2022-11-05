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

    private static final Logger logger = LoggerFactory.getLogger(FilmServiceImpl.class);
    @Autowired
    FilmRepository filmRepository;


    @Override
    public FilmEntity create(FilmEntity filmEntity) {
        filmRepository.saveAndFlush(filmEntity);
        return filmEntity;
    }

    @Override
    public FilmEntity update(String filmCode, FilmEntity filmEntity) {
        FilmEntity result = findById(filmCode);
        if (result != null) {
            logger.info("Update Movie data available");
            result.setFilmName(filmEntity.getFilmName());
            result.setShowStatus(filmEntity.getShowStatus());
            result.setGenre(filmEntity.getGenre());
            result.setDescription(filmEntity.getDescription());
            result.setDuration(filmEntity.getDuration());
            result.setCountry(filmEntity.getCountry());
            result.setLanguage(filmEntity.getLanguage());
            result.setStartDate(filmEntity.getStartDate());
            result.setEndDate(filmEntity.getEndDate());
            filmRepository.saveAndFlush(result);
        }else{
            logger.error("Movie data is not available");
        }
        return result;
    }
    @Override
    public Boolean delete(String filmCode) {
        final FilmEntity result = findById(filmCode);
        if (result != null) { // jika tidak null delete
            logger.info("Delete movie data from database");
            filmRepository.deleteById(filmCode);
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
    public FilmEntity findById(String filmCode) {
        Optional<FilmEntity> result = filmRepository.findById(filmCode);
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
        List<FilmEntity> filmEntities = filmRepository.findAll().stream().filter(FilmEntity::getShowStatus).collect(Collectors.toList());
        if (filmEntities.isEmpty()){
            logger.info("Movie data available");
            return new ArrayList<>();
        }else {
            logger.info("movie data not available");
        }
        return filmEntities;
    }

    @Override
    public FilmEntity getFindSchedule(String filmCode) {
        Optional<FilmEntity> result = filmRepository.findById(filmCode);
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
        Optional<FilmEntity> result = filmRepository.findById(scheduleRequest.getFilmCode());
        if (result.isPresent()) {  // jika misal ada
            logger.info("Set schedule data available");
            FilmEntity filmEntity = result.get();
            ScheduleEntity scheduleEntity = new ScheduleEntity();
            scheduleEntity.setShowDate(scheduleRequest.getShowDate());
            scheduleEntity.setStartTime(scheduleRequest.getStartTime());
            scheduleEntity.setEndTime(scheduleRequest.getEndTime());
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
