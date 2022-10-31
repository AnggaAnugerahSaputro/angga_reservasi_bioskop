package org.binar.bioskop.challenge4.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.binar.bioskop.challenge4.entity.FilmEntity;
import org.binar.bioskop.challenge4.request.ScheduleRequest;
import org.binar.bioskop.challenge4.respon.Respon;
import org.binar.bioskop.challenge4.service.FilmService;
import org.binar.bioskop.challenge4.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/films")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @Autowired
    private SeatService seatService;

    @Operation(summary = "this is to create film from Database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "create film into from Database User",
                    content = {@Content(mediaType="application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Request Not Avilable",
                    content = @Content)
    })
    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity create(@RequestBody FilmEntity filmEntity){
        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(filmService.create(filmEntity));
        return ResponseEntity.ok().body(ress);
    }

    @Operation(summary = "this is to update films from Database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "update films into from Database User",
                    content = {@Content(mediaType="application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Request Not Avilable",
                    content = @Content)
    })
    @PutMapping("/update/{film_code}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity update(@PathVariable String film_code, @RequestBody FilmEntity filmEntity){
        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(filmService.update(film_code, filmEntity));
        return ResponseEntity.ok().body(ress);
    }


    @Operation(summary = "this is to Find All films from Database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Find All films into from Database User",
                    content = {@Content(mediaType="application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Request Not Avilable",
                    content = @Content)
    })
    @GetMapping("/all")
//    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity findAll(){
        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(filmService.findAll());
        return ResponseEntity.ok().body(ress);
    }


    @Operation(summary = "this is to Delete films from Database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "delete films into from Database User",
                    content = {@Content(mediaType="application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Request Not Avilable",
                    content = @Content)
    })
    @DeleteMapping("/delete/{film_code}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity delete(@PathVariable String film_code){
        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(filmService.delete(film_code));
        return ResponseEntity.ok().body(ress);
    }

    @Operation(summary = "this is to take the currently showing films from Database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "get films status showing into from Database User",
                    content = {@Content(mediaType="application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Request Not Avilable",
                    content = @Content)
    })
    @GetMapping("/now_playing")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity getNowPlaying(){
        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(filmService.getFilmNow());
        return ResponseEntity.ok().body(ress);
    }


    @Operation(summary = "this is to get schedules films from Database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "get schedules films into from Database User",
                    content = {@Content(mediaType="application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Request Not Avilable",
                    content = @Content)
    })
    @GetMapping("/schedule_film")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity getScheduleFilm(@RequestParam String film_code){
        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(filmService.getFindSchedule(film_code));
        return ResponseEntity.ok().body(ress);
    }

    @Operation(summary = "this is to create schedules film from Database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "create schedules films into from Database User",
                    content = {@Content(mediaType="application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Request Not Avilable",
                    content = @Content)
    })
    @PostMapping("/set_schedule")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity setScheduleFilm(@RequestBody ScheduleRequest scheduleRequest){
        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(filmService.setSchedules(scheduleRequest));
        return ResponseEntity.ok().body(ress);
    }

    @Operation(summary = "this is to get All Seat from Database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "get All Seat into from Database User",
                    content = {@Content(mediaType="application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Request Not Avilable",
                    content = @Content)
    })
    @GetMapping("/getAllSeat")
    @PreAuthorize("hasRole('ADMIN' or hasRole('CUSTOMER'))")
    public ResponseEntity findAllSeats(){
        Respon ress = new Respon();
        ress.setResponCode("200");
        ress.setMessage("succes");
        ress.setData(seatService.findAllSeat());
        return ResponseEntity.ok().body(ress);
    }
}
