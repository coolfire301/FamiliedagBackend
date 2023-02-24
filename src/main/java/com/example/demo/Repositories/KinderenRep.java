package com.example.demo.Repositories;

import com.example.demo.Entities.Kinderen;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface KinderenRep extends CrudRepository<Kinderen, Integer> {


    @Query(value = "SELECT id FROM kinderen where LOWER(naam1) = LOWER(:neef);", nativeQuery = true)
    Integer getOid(@Param("neef") String ouder);

    @Query(value = "SELECT * FROM kinderen where oid = (:oid);", nativeQuery = true)
    Kinderen getKinderenRow(@Param("oid") Integer oid);

    @Query(value = "SELECT COUNT(*) FROM kinderen where LOWER(naam1)=LOWER(:naam1) OR LOWER(naam2)=LOWER(:naam1) OR LOWER(naam2)=(:naam2) OR LOWER(naam1)=LOWER(:naam2) AND oid = (:oid);", nativeQuery = true)
    Integer checkofbestaan(@Param("naam1") String naam1, @Param("naam2") String naam2, @Param("oid") Integer oid);

    @Query(value = "SELECT id FROM kinderen where LOWER(naam1)=LOWER(:naam1) OR LOWER(naam2)=LOWER(:naam1) OR LOWER(naam2)=(:naam2) OR LOWER(naam1)=LOWER(:naam2) AND oid = (:oid);", nativeQuery = true)
    Integer getId(@Param("naam1") String naam1, @Param("naam2") String naam2, @Param("oid") Integer oid);

    @Transactional
    @Modifying
    @Query(value = "update kinderen SET naam1 = (:naam1), naam2 = (:naam2) WHERE id=(:id);", nativeQuery = true)
    void Update_kinderen(@Param("naam1") String naam1, @Param("naam2") String naam2, @Param("id") Integer id);

}
