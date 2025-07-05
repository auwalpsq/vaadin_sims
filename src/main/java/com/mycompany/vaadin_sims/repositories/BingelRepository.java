/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.vaadin_sims.repositories;

import com.mycompany.vaadin_sims.entities.Bingel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Auwal Usman
 */
public interface BingelRepository extends JpaRepository<Bingel, Long>{
    
    @Query( "select b from Bingel b where (lower(b.firstName) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(b.lastName) like lower(concat('%', :searchTerm, '%'))) and b.type = :type"
          )
    List<Bingel> search(@Param("searchTerm") String searchTerm, @Param("type") String type);
    
    List<Bingel> findByType(String type);
}
