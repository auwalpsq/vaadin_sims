/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.vaadin_sims.repositories;

import com.mycompany.vaadin_sims.entities.Bingel;
import com.mycompany.vaadin_sims.entities.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Auwal Usman
 */
public interface ParentRepository extends JpaRepository<Parent, Long>{
    Parent findByBingel(Bingel bingel);
}
