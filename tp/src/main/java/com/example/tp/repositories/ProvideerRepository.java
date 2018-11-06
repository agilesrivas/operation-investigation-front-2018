package com.example.tp.repositories;


import com.example.tp.models.Provideer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvideerRepository extends JpaRepository<Provideer, Long> {
}
