package org.binar.bioskop.challenge4.controller;

import org.binar.bioskop.challenge4.entity.FilmEntity;
import org.binar.bioskop.challenge4.entity.ScheduleEntity;
import org.binar.bioskop.challenge4.request.ScheduleRequest;
import org.binar.bioskop.challenge4.respon.Respon;
import org.binar.bioskop.challenge4.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/films")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @PostMapping("/create")
    public Respon create(@RequestBody FilmEntity filmEntity){
        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(filmService.create(filmEntity));
        return ress;
    }



    @PutMapping("/update/{film_code}")
    public Respon update(@PathVariable String film_code, @RequestBody FilmEntity filmEntity){
        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(filmService.update(film_code, filmEntity));
        return ress;
    }


    @GetMapping("/all")
    public Respon findAll(){
        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(filmService.findAll());
        return ress;
    }


    @DeleteMapping("/delete/{film_code}")
    public Respon delete(@PathVariable String film_code){
        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(filmService.delete(film_code));
        return ress;
    }

    @GetMapping("/now_playing")
    public Respon getNowPlaying(){
        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(filmService.getFilmNow());
        return ress;
    }

    @GetMapping("/schedule_film")
    public Respon getScheduleFilm(@RequestParam String film_code){
        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(filmService.getFindSchedule(film_code));
        return ress;
    }

    @PostMapping("/set_schedule")
    public Respon setScheduleFilm(@RequestBody ScheduleRequest scheduleRequest){
        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(filmService.setSchedules(scheduleRequest));
        return ress;
    }
}
