package com.ipiecoles.java.eval.mdd050.service;

import com.ipiecoles.java.eval.mdd050.model.Artist;
import com.ipiecoles.java.eval.mdd050.repository.AlbumRepository;
import com.ipiecoles.java.eval.mdd050.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private AlbumRepository albumRepository;

    public Page<Artist> findAllArtists(Integer page, Integer size, String sortProperty, String sortDirection) {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.fromString(sortDirection),sortProperty));
        Pageable pageable = new PageRequest(page,size,sort);
        return artistRepository.findAll(pageable);
    }

    public Artist findById(Long id) {
        Artist artist = this.artistRepository.findOne(id);
        if(artist == null){
            throw new EntityNotFoundException("Impossible de trouver l'artiste d'identifiant " + id);
        }
        return artist;
    }

    public Long countAllArtists() {
        return artistRepository.count();
    }

    public Artist creerArtiste(Artist artist) {
        return artistRepository.save(artist);
    }

    public void deleteArtist(Long id) {
        artistRepository.delete(id);
    }

    public Artist updateArtiste(Long id, Artist artist) {
        return artistRepository.save(artist);
    }

    public Page<Artist> findByNameLikeIgnoreCase(String name, Integer page, Integer size, String sortProperty, String sortDirection) {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.fromString(sortDirection),sortProperty));
        Pageable pageable = new PageRequest(page,size,sort);
        return artistRepository.findByNameContainingIgnoreCase(name, pageable);
    }
}
