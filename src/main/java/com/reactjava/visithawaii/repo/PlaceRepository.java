package com.reactjava.visithawaii.repo;

import com.reactjava.visithawaii.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findByAvailable(boolean available);
    List<Place> findByName(String name);
    List<Place> findByIsland(String island);
    // Intégrer une méthode de rechercher par mot(s) contenu(s) dans la description
}
