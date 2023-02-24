package com.example.demo.Repositories;

import com.example.demo.Entities.Kleinkinderen;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface KleinkinderenRep extends CrudRepository<Kleinkinderen, Integer> {


    @Query(value = "SELECT COUNT(*) FROM kleinkinderen where LOWER(naam)=LOWER(:naam) AND oid = (:oid);", nativeQuery = true)
    Integer checkofbestaan(@Param("naam") String naam, @Param("oid") Integer oid);

    @Transactional
    @Modifying
    @Query(value = "update kleinkinderen SET naam = (:naam) WHERE oid=(:oid) AND LOWER(naam) = LOWER(:naam);", nativeQuery = true)
    void Update_kleinkinderen(@Param("naam") String naam, @Param("oid") Integer oid);

}
