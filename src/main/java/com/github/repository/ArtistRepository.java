package com.github.repository;

import com.github.entity.Artist;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist, Long> {

    Artist getArtistByEventsId(Long id);

}
