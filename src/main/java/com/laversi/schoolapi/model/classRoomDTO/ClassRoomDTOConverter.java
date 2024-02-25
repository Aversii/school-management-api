package com.laversi.schoolapi.model.classRoomDTO;

import org.springframework.stereotype.Service;

import com.laversi.schoolapi.model.classRoomEntity.ClassRoomEntity;


@Service
public class ClassRoomDTOConverter {

    public ClassRoomEntity convertToEntity(ClassRoomPostDTO ClassRoomDto) {
        return new ClassRoomEntity(
                ClassRoomDto.name()
        );
    }

    public ClassRoomPostDTO convertToDto(ClassRoomEntity ClassRoomEntity) {
        return new ClassRoomPostDTO(
                ClassRoomEntity.getId(),
                ClassRoomEntity.getName());
    }

     public ClassRoomPutDTO convertToPutDto(ClassRoomEntity ClassRoomEntity) {
        return new ClassRoomPutDTO(
                ClassRoomEntity.getName());
    }

    public ClassRoomGetDTO convertToGetDto(ClassRoomEntity ClassRoomEntity) {
        return new ClassRoomGetDTO(
                ClassRoomEntity.getId(),
                ClassRoomEntity.getName());
    }
    public void updateEntityFromPutDto(ClassRoomEntity existingClassRoom, ClassRoomPutDTO ClassRoomDto) {
        existingClassRoom.setName(ClassRoomDto.name() != null ? ClassRoomDto.name() : existingClassRoom.getName());
    }
    
    
}
