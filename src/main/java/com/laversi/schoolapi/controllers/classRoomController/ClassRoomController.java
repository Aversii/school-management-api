package com.laversi.schoolapi.controllers.classRoomController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.laversi.schoolapi.model.classRoomDTO.*;
import com.laversi.schoolapi.services.ClassRoomService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/classroom")
public class ClassRoomController {

    @Autowired
    private ClassRoomService classRoomService;

    @GetMapping
    public ResponseEntity<List<ClassRoomGetDTO>> getAllClassRoom() {
        try {
            List<ClassRoomGetDTO> classRoom = classRoomService.getAllClassRoom();
            return ResponseEntity.ok(classRoom);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassRoomGetDTO> getClassRoomById(@PathVariable int id) {
        try {
            ClassRoomGetDTO classRoom = classRoomService.getClassRoomById(id);
            return ResponseEntity.ok(classRoom);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ClassRoomPostDTO> postClassRoom(@Valid @RequestBody ClassRoomPostDTO classRoom) {
        try {
            ClassRoomPostDTO savedClassRoom = classRoomService.createClassRoom(classRoom);
            return ResponseEntity.ok(savedClassRoom);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassRoomPutDTO> updateClassRoom(@PathVariable int id, @RequestBody ClassRoomPutDTO classRoom) {
        try {
            ClassRoomPutDTO updatedClassRoom = classRoomService.updateClassRoom(id, classRoom);
            return ResponseEntity.ok(updatedClassRoom);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassRoom(@PathVariable int id) {
        try {
            classRoomService.deleteClassRoom(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}