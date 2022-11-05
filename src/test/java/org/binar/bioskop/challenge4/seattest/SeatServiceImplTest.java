package org.binar.bioskop.challenge4.seattest;

import org.binar.bioskop.challenge4.datatest.DataFilmTest;
import org.binar.bioskop.challenge4.entity.FilmEntity;
import org.binar.bioskop.challenge4.entity.SeatEntity;
import org.binar.bioskop.challenge4.repository.SeatRepository;
import org.binar.bioskop.challenge4.service.implement.SeatServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class SeatServiceImplTest {

    @InjectMocks
    SeatServiceImpl seatService;

    @Mock
    SeatRepository seatRepository;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testGetAllSeats() {
        List<SeatEntity> seatEntities = DataFilmTest.getSeat();
        Mockito.when(seatRepository.findAll()).thenReturn(seatEntities);

        var actualValue = seatService.findAllSeat();
        Assertions.assertEquals(actualValue.size(), 1);
        Assertions.assertEquals(actualValue.get(0).getStatus_seat(), true);
    }
}
