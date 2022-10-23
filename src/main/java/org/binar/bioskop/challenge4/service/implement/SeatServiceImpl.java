package org.binar.bioskop.challenge4.service.implement;

import lombok.extern.slf4j.Slf4j;
import org.binar.bioskop.challenge4.entity.SeatEntity;
import org.binar.bioskop.challenge4.repository.SeatRepository;
import org.binar.bioskop.challenge4.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SeatServiceImpl implements SeatService {


    @Autowired
    SeatRepository seatRepository;


    @Override
    public List<SeatEntity> findAllSeat() {
        log.info("Showing seat from database");
        return seatRepository.findAll();
    }
}
