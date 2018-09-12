package tech.valery.presentation.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import tech.valery.presentation.model.Room;
import tech.valery.presentation.service.PresentationService;
import tech.valery.presentation.service.RoomService;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Collection;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("api/rooms")
public class RoomRestController {

    @Autowired
    PresentationService presentationService;

    @Autowired
    RoomService roomService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Collection<Room>> getRooms(){

        Collection<Room> rooms = this.roomService.findAll();
        if(rooms.isEmpty()){
            return new ResponseEntity<Collection<Room>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Collection<Room>>(rooms,HttpStatus.OK);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Room> addRoom(@RequestBody @Valid Room room, BindingResult bindingResult,
                                        UriComponentsBuilder ucBuilder){

        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors() || (room == null)) {
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<Room>(headers, HttpStatus.BAD_REQUEST);
        }
        this.roomService.save(room);
        headers.setLocation(ucBuilder.path("/api/rooms/{id}").buildAndExpand(room.getId()).toUri());
        return new ResponseEntity<Room>(room, headers, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Room> bulkRoomUpdate(){
        return new ResponseEntity<Room>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping("")
    public ResponseEntity<Room> deleteRooms(){
        return new ResponseEntity<Room>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping(value = "/{roomId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Room> getRoom(@PathVariable("roomId") Long roomId){
        Room room = roomService.findById(roomId);
        if(room == null){
            return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Room>(room, HttpStatus.OK);
    }

    @PostMapping("/{roomId}")
    public ResponseEntity<Room> getError(@PathVariable("roomId") Long roomId){
        return new ResponseEntity<Room>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping(value = "/{roomId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Room> updateRoom(@PathVariable("roomId") Long roomId,
                                           @RequestBody @Valid Room room, BindingResult bindingResult,
                                           UriComponentsBuilder ucBuilder){
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors() || (room == null)) {
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<Room>(headers, HttpStatus.BAD_REQUEST);
        }

        Room currentRoom = this.roomService.findById(roomId);
        if(currentRoom == null){
            return new ResponseEntity<Room>(HttpStatus.NOT_FOUND);
        }
        currentRoom.setNumber(room.getNumber());
        // todo: refactor - there'is no need in this line. hibernate automatically does updates of pers objects
        this.roomService.save(currentRoom);

        return new ResponseEntity<Room>(currentRoom, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(value = "/{roomId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> deleteRoom(@PathVariable("roomId") Long roomId){
        Room room = this.roomService.findById(roomId);
        if(room == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        this.roomService.delete(room);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

    }
}
