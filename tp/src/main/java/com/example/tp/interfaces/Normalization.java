package com.example.tp.interfaces;

import com.example.tp.models.Normaliza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface Normalization extends JpaRepository<Normaliza,Long> {
    @Query(value="SELECT * FROM normalizaciones n WHERE n.e_z = :e_z limit 1",nativeQuery = true)
    public Normaliza getByZ(@Param("e_z")Double e_z);
}
