package org.binar.bioskop.challenge4.service.implement;

import org.binar.bioskop.challenge4.entity.SeatEntity;
import org.binar.bioskop.challenge4.repository.SeatRepository;
import org.binar.bioskop.challenge4.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {


    @Autowired
    SeatRepository seatRepository;


    @Override
    public List<SeatEntity> findAllSeat() {
        return seatRepository.findAll();
    }
}
