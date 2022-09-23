package org.binar.bioskop.challenge4.service.implement;

import org.binar.bioskop.challenge4.entity.FilmEntity;
import org.binar.bioskop.challenge4.entity.ScheduleEntity;
import org.binar.bioskop.challenge4.entity.UserEntity;
import org.binar.bioskop.challenge4.repository.FilmRepository;
import org.binar.bioskop.challenge4.request.ScheduleRequest;
import org.binar.bioskop.challenge4.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService {

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
            System.out.println("Data film ada");
            result.setFilm_name(filmEntity.getFilm_name());
            result.setShow_status(filmEntity.getShow_status());
            filmRepository.saveAndFlush(result);
        }else{
            System.out.println("Data film tidak masuk");
        }
        return result;
    }
    @Override
    public Boolean delete(String film_code) {
        final FilmEntity result = findById(film_code);
        if (result != null) { // jika tidak null delete
            // hard delete
            filmRepository.deleteById(film_code);
            return true; // true jika berhasil
        }
        return false;
    }

    @Override
    public List<FilmEntity> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public FilmEntity findById(String film_code) {
        Optional<FilmEntity> result = filmRepository.findById(film_code);
        if (result.isPresent()) {  // jika misal ada
            System.out.println("data masuk");
            return result.get();
        }else {
            System.out.println("data tidak masuk");
        }
        return null;
    }

    @Override
    public List<FilmEntity> getFilmNow() {
        List<FilmEntity> filmEntities = filmRepository.findAll().stream().filter(FilmEntity::getShow_status).collect(Collectors.toList());
        if (filmEntities.isEmpty()){
            return new ArrayList<>();
        }
        return filmEntities;
    }

    @Override
    public FilmEntity getFindSchedule(String film_code) {
        Optional<FilmEntity> result = filmRepository.findById(film_code);
        if (result.isPresent()) {  // jika misal ada
            System.out.println("data masuk");
            return result.get();
        }else {
            System.out.println("data tidak masuk");
        }
        return null;
    }

    @Override
    public ScheduleEntity setSchedules(ScheduleRequest scheduleRequest) {
        Optional<FilmEntity> result = filmRepository.findById(scheduleRequest.getFilm_code());
        if (result.isPresent()) {  // jika misal ada
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
            System.out.println("data tidak masuk");
        }
        return null;
    }


}
