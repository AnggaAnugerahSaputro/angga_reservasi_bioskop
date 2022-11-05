package org.binar.bioskop.challenge4.datatest;

import org.binar.bioskop.challenge4.entity.FilmEntity;
import org.binar.bioskop.challenge4.entity.SeatEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataFilmTest {

    public static List<FilmEntity> getDataFilm() {
        List<FilmEntity> listFilm = new ArrayList<>();
        FilmEntity film = new FilmEntity();
        film.setFilmCode("1");
        film.setFilmName("OnePiece");
        film.setShowStatus(true);
        film.setGenre("Comedy, Adventure");
        film.setDescription("Film Anime dari Japan");
        film.setDuration(260);
        film.setCountry("Japanese");
        film.setLanguage("Sub Indonesia");
        film.setStartDate(LocalDate.now());
        film.setEndDate(LocalDate.now());
        listFilm.add(film);
        return listFilm;
    }

    public static List<SeatEntity> getSeat(){
        List<SeatEntity> listSeat = new ArrayList<>();
        SeatEntity seat = new SeatEntity();
        seat.getSeat_id();
        seat.setStatus_seat(true);
        listSeat.add(seat);
        return listSeat;
    }
}
