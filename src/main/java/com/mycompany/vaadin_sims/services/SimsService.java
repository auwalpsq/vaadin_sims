/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vaadin_sims.services;

import com.mycompany.vaadin_sims.entities.Bingel;
import com.mycompany.vaadin_sims.entities.Goddo;
import com.mycompany.vaadin_sims.entities.Parent;
import java.util.List;
import org.springframework.stereotype.Service;
import com.mycompany.vaadin_sims.repositories.BingelRepository;
import com.mycompany.vaadin_sims.repositories.GoddoRepository;
import com.mycompany.vaadin_sims.repositories.ParentRepository;
import java.util.Optional;

/**
 *
 * @author Auwal Usman
 */
@Service
public class SimsService {
    private BingelRepository bingelRepository;
    private GoddoRepository goddoRepository;
    private ParentRepository parentRepository;
    
    public SimsService(BingelRepository bingelRepository, GoddoRepository goddoRepository, ParentRepository parentRepository){
        this.bingelRepository = bingelRepository;
        this.goddoRepository = goddoRepository;
        this.parentRepository = parentRepository;
    }
    public List<Bingel> findAllBikkoi(String filter, String type){
        if(filter == null || filter.isEmpty()){
            return findBingelByType(type);
        }else{
            return bingelRepository.search(filter, type);
        }
    }
    public void saveBingel(Bingel bingel){
        bingelRepository.save(bingel);
    }
    public void saveGoddo(Goddo goddo){
        if(goddo != null){
            goddoRepository.save(goddo);
        }
    }
    public void saveParent(Parent parent){
        if(parent != null){
            parentRepository.save(parent);
        }
    }
    public void deleteBingel(Bingel bingel){
        if(bingel != null)
            bingelRepository.delete(bingel);
    }
    public Optional<Bingel> findBingelById(Long id){return bingelRepository.findById(id);}
    public List<Bingel> findBingelByType(String type){return bingelRepository.findByType(type);}
    
}
