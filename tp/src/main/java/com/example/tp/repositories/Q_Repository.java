package com.example.tp.repositories;

import com.example.tp.models.Product;
import com.example.tp.models.Q;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Q_Repository extends JpaRepository<Q,Long> {

    @Query(value="SELECT * FROM model_q q WHERE q.id_product = :id",nativeQuery = true)
    public Q getByProduct(@Param("id")Long id);
}
