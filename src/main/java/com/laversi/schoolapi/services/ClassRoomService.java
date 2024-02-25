package com.laversi.schoolapi.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laversi.schoolapi.model.classRoomDTO.ClassRoomDTOConverter;
import com.laversi.schoolapi.model.classRoomDTO.ClassRoomGetDTO;
import com.laversi.schoolapi.model.classRoomDTO.ClassRoomPostDTO;
import com.laversi.schoolapi.model.classRoomDTO.ClassRoomPutDTO;
import com.laversi.schoolapi.model.classRoomEntity.ClassRoomEntity;
import com.laversi.schoolapi.repositories.ClassRepository;

@Service
public class ClassRoomService {

    @Autowired
    private ClassRepository classRoomRepository;

    @Autowired
    private ClassRoomDTOConverter classRoomDTOConverter;

    public List<ClassRoomGetDTO> getAllClassRoom() {
        List<ClassRoomEntity> people = classRoomRepository.findAll();
        return people.stream()
                .map(classRoomEntity -> classRoomDTOConverter.convertToGetDto(classRoomEntity))
                .collect(Collectors.toList());
    }

        public ClassRoomGetDTO getClassRoomById(int id) {
        Optional<ClassRoomEntity> optionalClassRoom = classRoomRepository.findById(id);
        if (optionalClassRoom.isEmpty()) {
            throw new RuntimeException("ClassRoom not found");
        }

        ClassRoomEntity classRoomEntity = optionalClassRoom.get();
        return classRoomDTOConverter.convertToGetDto(classRoomEntity);
    }

    public ClassRoomPostDTO createClassRoom(ClassRoomPostDTO classRoomDto) {
        ClassRoomEntity classRoomEntity = classRoomDTOConverter.convertToEntity(classRoomDto);
        ClassRoomEntity savedClassRoom = classRoomRepository.save(classRoomEntity);
        return classRoomDTOConverter.convertToDto(savedClassRoom);
    }

    public ClassRoomPutDTO updateClassRoom(int id, ClassRoomPutDTO classRoomDto) {
        Optional<ClassRoomEntity> optionalClassRoom = classRoomRepository.findById(id);

        ClassRoomEntity existingClassRoom = optionalClassRoom.get();
        classRoomDTOConverter.updateEntityFromPutDto(existingClassRoom, classRoomDto);
    
        ClassRoomEntity updatedClassRoom = classRoomRepository.save(existingClassRoom);
        return classRoomDTOConverter.convertToPutDto(updatedClassRoom);
    }

    public void deleteClassRoom(int id) {
        classRoomRepository.deleteById(id);
    }
}
