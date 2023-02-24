package com.example.demo.Repositories;

import com.example.demo.Entities.Ouders;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OudersRep extends CrudRepository<Ouders, Integer> {

    @Query(value = "SELECT id FROM ouders where LOWER(naam1) = LOWER(:ouder);", nativeQuery = true)
    Integer getOid(@Param("ouder") String ouder);

    @Transactional
    @Modifying
    @Query(value = "update ouders SET visible = 't' where LOWER(naam1) = LOWER(:ouder);", nativeQuery = true)
    void setvisibleTrue(@Param("ouder") String ouder);

    @Transactional
    @Modifying
    @Query(value = "update ouders SET naam2 = (:aanhang) where LOWER(naam1) = LOWER(:ouder);", nativeQuery = true)
    void setAanhang(@Param("ouder") String ouder, @Param("aanhang") String aanhang);

}
