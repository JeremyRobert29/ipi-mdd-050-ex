package com.ipiecoles.java.eval.mdd050.controller;

import com.ipiecoles.java.eval.mdd050.model.Artist;
import com.ipiecoles.java.eval.mdd050.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;
    //Afficher un artiste
    @RequestMapping(
            method = RequestMethod.GET,
            value ="/{id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Artist findById(@PathVariable(value ="id")Long id){
        return artistRepository.findOne(id);
    }
    //Recherche par nom
    @RequestMapping(
            method = RequestMethod.GET,
            value ="",
            params = "name",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<Artist> findByName(@RequestParam("name") String name,
                                   @RequestParam("page")Integer page,
                                   @RequestParam("size")Integer size,
                                   @RequestParam("sortDirection")String sortDirection,
                                   @RequestParam("sortProperty")String sortProperty
    ){
        Pageable pageable = new PageRequest(page, size, Sort.Direction.fromString(sortDirection),
                sortProperty);
        return artistRepository.findByNameContainingIgnoreCase(name, pageable);
    }
    //Liste d'artist
    @RequestMapping(
            method = RequestMethod.GET,
            value = "",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public Page<Artist> findAll(
            @RequestParam("page")Integer page,
            @RequestParam("size")Integer size,
            @RequestParam("sortDirection")String sortDirection,
            @RequestParam("sortProperty")String sortProperty){
        Pageable pageable = new PageRequest(page, size, Sort.Direction.fromString(sortDirection),
                sortProperty);
        return artistRepository.findAll(pageable);
    }
    //Creation
    @RequestMapping(
            method = RequestMethod.POST,
            value = "",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Artist createArtist (@RequestBody Artist artist) {
        return this.artistRepository.save(artist);
    }
    //Mise à jour
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/{id}")
    public Artist updateArtist(
            @PathVariable("id")Long id,
            @RequestBody Artist artist){
        return this.artistRepository.save(artist);
    }
    //Suppression
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/{id}"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable("id") Long id){
        this.artistRepository.delete(id);
    }





}
