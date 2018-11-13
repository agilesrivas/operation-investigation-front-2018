package com.example.tp.repositories;

import com.example.tp.models.P;
import com.example.tp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface P_Repository extends JpaRepository<P,Long> {
    @Query(value="SELECT * FROM model_p q WHERE q.id_product = :id",nativeQuery = true)
    public P getByProduct(@Param("id")Long id);
}
