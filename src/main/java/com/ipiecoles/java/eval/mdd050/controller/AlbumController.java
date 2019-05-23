package com.ipiecoles.java.eval.mdd050.controller;


import com.ipiecoles.java.eval.mdd050.model.Album;
import com.ipiecoles.java.eval.mdd050.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/albums")
public class AlbumController {
    @Autowired
    private AlbumRepository albumRepository;
    //Creation
    @RequestMapping(
            method = RequestMethod.POST,
            value = "",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Album createAlbum (@RequestBody Album album) {
        return this.albumRepository.save(album);
    }

    //Suppression
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/{id}"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable("id") Long id){
        this.albumRepository.delete(id);
    }
}
