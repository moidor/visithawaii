package com.reactjava.visithawaii.controller;

import com.reactjava.visithawaii.model.Place;
import com.reactjava.visithawaii.repo.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class PlaceController {

    @Autowired
    PlaceRepository placeRepository;

    @GetMapping(path = "/places")
    public ResponseEntity<List<Place>> getAllPlaces(@RequestParam(required = false) String name) {
        try {
            List<Place> places = new ArrayList<Place>();

            if (name == null)
                places.addAll(placeRepository.findAll());
            else
                places.addAll(placeRepository.findByName(name));

            if (places.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            System.out.println("Getting all places...");
            return new ResponseEntity<>(places, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/unavailablePlaces")
    @Query(value = "SELECT COUNT(AVAILABLE) FROM PLACES WHERE AVAILABLE IS FALSE", nativeQuery = true)
    List<Place> getUnavailablePlaces() {
        return placeRepository.findAll();
    }

    @GetMapping("/places/{id}")
    public ResponseEntity<Place> getPlaceById(@PathVariable("id") long id) {
        Optional<Place> placeData = placeRepository.findById(id);

        return placeData.map(place -> new ResponseEntity<>(place, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/places")
    public ResponseEntity<Place> createPlace(@RequestBody Place place) {
        try {
            Place _place = placeRepository
                    .save(new Place(
                            place.getName(),
                            place.getIsland(),
                            place.getImage(),
                            place.getDescription(),
                            place.getLongDescription(),
                            place.getGallery(),
                            place.getPrice(),
                            place.getAvailable()));
            return new ResponseEntity<>(_place, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/places/{id}")
    public ResponseEntity<Place>
    updatePlace(@PathVariable("id") long id, @RequestBody Place place) {
        Optional<Place> placeData = placeRepository.findById(id);

        if (placeData.isPresent()) {
            Place _place = placeData.get();
            _place.setName(place.getName());
            _place.setIsland(place.getIsland());
            _place.setImage(place.getImage());
            _place.setDescription(place.getDescription());
            _place.setLongDescription(place.getLongDescription());
            _place.setGallery(place.getGallery());
            _place.setPrice(place.getPrice());
            _place.setAvailable(place.getAvailable());
            return new ResponseEntity<>(placeRepository.save(_place), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/places/{id}")
    public ResponseEntity<HttpStatus> deletePlace(@PathVariable("id") long id) {
        try {
            placeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/places")
    public ResponseEntity<HttpStatus> deleteAllPlaces() {
        try {
            placeRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/places/available")
    public ResponseEntity<List<Place>> findByAvailable() {
        try {
            List<Place> places = placeRepository.findByAvailable(true);

            if (places.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(places, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/places/name")
    public ResponseEntity<List<Place>> findByName(String name) {
        try {
            List<Place> places = placeRepository.findByName(name);

            if (places.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(places, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/places/unavailable")
    public Integer findByUnavailable() {
        System.out.println("Fetching unavailable places");
        try {
            List<Place> places = placeRepository.findByAvailable(false);
            if (places.isEmpty()) {
                return 0;
            }
            return places.size();
        } catch (Exception e) {
            return 0;
        }
    }
}
