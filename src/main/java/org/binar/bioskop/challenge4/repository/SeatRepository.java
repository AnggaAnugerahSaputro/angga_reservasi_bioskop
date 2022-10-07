package org.binar.bioskop.challenge4.repository;

import org.binar.bioskop.challenge4.entity.SeatComposite;
import org.binar.bioskop.challenge4.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<SeatEntity, SeatComposite> {
}
