package com.github.controller;

import com.github.dto.ArtistDto;
import com.github.dto.StatusDto;
import com.github.metamodel.Roles;
import com.github.service.IArtistService;
import com.github.service.IItemsSecurityExpressions;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("artist-management")
public class ArtistController {

    private final IArtistService iArtistService;

    private final IItemsSecurityExpressions iItemsSecurityExpressions;

    @PostMapping("{id}")
    @Secured(Roles.ADMIN)
    public ResponseEntity<StatusDto> createArtistCard(@PathVariable("id") Long userId,
                                                      @RequestBody ArtistDto artistDto) {

        iArtistService.createArtist(userId, artistDto);

        return ResponseEntity.ok(new StatusDto()
                .setStatusId(HttpStatus.OK.value())
                .setMessage("Success"));
    }

    @GetMapping("{id}")
    @PreAuthorize("permitAll")
    public ResponseEntity<ArtistDto> readArtist(@PathVariable("id") Long artistId) {

        ArtistDto currentArtist = iArtistService.getArtist(artistId);

        return ResponseEntity.ok(currentArtist);
    }

    @PutMapping("{id}")
    @PreAuthorize("@itemsSecurityExpressions.isUserOwnedArtistCard(#artistId, authentication)")
    public ResponseEntity<ArtistDto> updateArtist(@PathVariable("id") Long artistId,
                                                  @RequestBody ArtistDto artistDto) {
        ArtistDto currentArtist = iArtistService.updateArtist(artistId, artistDto);

        return ResponseEntity.ok(currentArtist);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("@itemsSecurityExpressions.isUserOwnedArtistCard(#artistId, authentication)")
    public ResponseEntity<StatusDto> deleteArtist(@PathVariable("id") Long artistId) {
        iArtistService.deleteArtist(artistId);

        return ResponseEntity.ok(new StatusDto()
                .setStatusId(HttpStatus.OK.value())
                .setMessage("Success"));
    }

    @PreAuthorize("permitAll")
    @GetMapping("by-event/{id}")
    public ResponseEntity<ArtistDto> getArtistByEventId(@PathVariable("id") Long eventId) {
        ArtistDto currentArtist = iArtistService.getArtistByEventId(eventId);

        return ResponseEntity.ok(currentArtist);
    }

}
