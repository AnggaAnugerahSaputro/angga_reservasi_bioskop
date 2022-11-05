package org.binar.bioskop.challenge4.filmtest;

import org.binar.bioskop.challenge4.datatest.DataFilmTest;
import org.binar.bioskop.challenge4.entity.FilmEntity;
import org.binar.bioskop.challenge4.repository.FilmRepository;
import org.binar.bioskop.challenge4.service.implement.FilmServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class FilmServiceImplTest {

    @InjectMocks
    FilmServiceImpl filmService;

    @Mock
    FilmRepository filmRepository;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Get Film Not Found")
    void testGetFilmNotFound() {
        Exception e = Assertions.assertThrows(Exception.class, () -> {
            Mockito.verify(filmService.findById("not found"));
        });
        Assertions.assertNotNull(e.getMessage());
    }

    @Test
    @DisplayName("Get All Film Success")
    void testGetAllFilmSuccess() {
        List<FilmEntity> filmEntities = DataFilmTest.getDataFilm();
        Mockito.when(filmRepository.findAll()).thenReturn(filmEntities);

        var actualValue = filmService.findAll();
        Assertions.assertEquals(actualValue.size(), 1);
        Assertions.assertEquals(actualValue.get(0).getFilmName(), "OnePiece");

    }

    @Test
    @DisplayName("Create Film Success")
    void testCreateFilm() {
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

        Mockito.when(filmRepository.save(film)).thenReturn(film);
        Assertions.assertEquals(film, filmService.create(film));
    }

    @Test
    @DisplayName("Update Film Success")
    void testUpdateFilm() {
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
        Mockito.when(filmRepository.findById("1")).thenReturn(Optional.of(film));
        Mockito.when(filmRepository.saveAndFlush(film)).thenReturn(film);

        var actualValue = filmService.update("1", film);
        Assertions.assertEquals(actualValue.getFilmCode(), "1");
        Assertions.assertEquals(actualValue.getFilmName(), "OnePiece");
        Assertions.assertNotNull(actualValue);
    }

    @Test
    @DisplayName("Delete Film Success")
    void testDeleteFilm() {
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
        Mockito.when(filmRepository.findById("1")).thenReturn(Optional.of(film));
        Mockito.doNothing().when(filmRepository).deleteById("1");
        filmService.delete("1");
        Mockito.verify(filmRepository).deleteById("1");
    }

    @Test
    @DisplayName("Get Film By Id")
    void testFindById() {
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
        Mockito.when(filmRepository.findById("1")).thenReturn(Optional.of(film));
    }

    @Test
    @DisplayName("Get Film Now Playing")
    void testGetFilmNow() {
        List<FilmEntity> filmEntities = DataFilmTest.getDataFilm();
        Mockito.when(filmRepository.findAll()).thenReturn(filmEntities);

        var actualValue = filmService.getFilmNow();
        Assertions.assertEquals(actualValue.size(), 1);
        Assertions.assertEquals(actualValue.get(0).getShowStatus(), true);

    }

    @Test
    @DisplayName("Get Find Schedule")
    void testGetFindSchedule() {
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
        Mockito.when(filmRepository.findById("1")).thenReturn(Optional.of(film));
        var actualValue = filmService.getFindSchedule("1");
        Assertions.assertEquals(actualValue.getFilmCode(),"1");
        Assertions.assertEquals(actualValue.getShowStatus(), true);

    }

    @Test
    @DisplayName("Set Schedule Film")
    void testSetSchedules() {
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

        Mockito.when(filmRepository.findById("1")).thenReturn(Optional.of(film));
        Mockito.when(filmRepository.save(film)).thenReturn(film);
        Assertions.assertEquals(film, filmService.create(film));
    }
}
