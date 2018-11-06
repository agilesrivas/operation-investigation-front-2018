package com.example.tp.interfaces;

import com.example.tp.models.Normaliza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Normalization extends JpaRepository<Normaliza,Long> {
}
