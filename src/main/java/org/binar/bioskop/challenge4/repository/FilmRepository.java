package org.binar.bioskop.challenge4.repository;

import org.binar.bioskop.challenge4.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository <FilmEntity, String> {
}
