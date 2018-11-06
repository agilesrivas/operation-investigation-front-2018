package com.example.tp.repositories;


import com.example.tp.models.DetailSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRepository extends JpaRepository<DetailSale,Long> {
}
