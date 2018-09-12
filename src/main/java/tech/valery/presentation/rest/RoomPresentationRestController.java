package tech.valery.presentation.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import tech.valery.presentation.model.Presentation;
import tech.valery.presentation.service.PresentationService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("api/rooms/{roomId}/presentations")
public class RoomPresentationRestController {

    @Autowired
    PresentationService presentationService;

    @GetMapping("")
    public ResponseEntity<Collection<Presentation>> presentationsInRoom(@PathVariable("roomId") Long roomId) {
        Collection<Presentation> presentations = presentationService.findAllByRoomId(roomId);

        if (presentations.isEmpty()) {
            return new ResponseEntity<Collection<Presentation>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<Presentation>>(presentations, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Presentation> addPresentation(@RequestBody @Valid Presentation presentation,
                                                        BindingResult bindingResult,
                                                        UriComponentsBuilder ucBuilder) {

        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if(bindingResult.hasErrors() || (presentation == null)){
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<Presentation>(headers, HttpStatus.BAD_REQUEST);
        }

        //todo: refactor where to put the business logic?
        // if there is an intersections in the intervals of the presentations
        if(presentationService.countAllIntersected(presentation) > 0){

            return new ResponseEntity<Presentation>(headers, HttpStatus.BAD_REQUEST);
        }

        this.presentationService.save(presentation);
        headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(presentation.getId()).toUri());
        return new ResponseEntity<Presentation>(presentation, headers, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Presentation> presentationsBulkUpdate() {
        return new ResponseEntity<Presentation>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("")
    public ResponseEntity<Presentation> deletePresentationsForRoom() {
        return new ResponseEntity<Presentation>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping(value = "/{presentationId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Presentation> presentationDetails(@PathVariable("presentationId") Long presentationId) {
        Presentation presentation = presentationService.findOne(presentationId);
        if (presentation == null) {
            return new ResponseEntity<Presentation>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Presentation>(presentation, HttpStatus.OK);
    }

    @PutMapping("/{presentationId}")
    public ResponseEntity<Presentation> updatePresentation(@PathVariable("presentationId") Long presentationId,
                                                           @RequestBody @Valid Presentation presentation,
                                                           BindingResult bindingResult,
                                                           UriComponentsBuilder ucBuilder) {

        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors() || (presentation == null)) {
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<Presentation>(headers, HttpStatus.BAD_REQUEST);
        }

        Presentation currentPresentation = this.presentationService.findOne(presentationId);
        if (currentPresentation == null) {
            return new ResponseEntity<Presentation>(HttpStatus.NOT_FOUND);
        }

        currentPresentation.setRoom(presentation.getRoom());
        currentPresentation.setPerftime(presentation.getPerftime());
        currentPresentation.setTopic(presentation.getTopic());
        this.presentationService.save(currentPresentation);

        return new ResponseEntity<Presentation>(currentPresentation, HttpStatus.OK);
    }

    @DeleteMapping("/{presentationId}")
    public ResponseEntity<Void> deletePresentation(@PathVariable("presentationId") Long presentationId) {
        Presentation presentation = presentationService.findOne(presentationId);
        if (presentation == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        presentationService.delete(presentationId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
