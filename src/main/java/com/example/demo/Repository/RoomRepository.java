package com.example.demo.Repository;

import com.example.demo.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer>{
    boolean existsByRoomName(String roomName);
    Room findByRoomID(int roomID);
    boolean existsByRoomID(int roomID);
    List<Room> findByRoomCapacityGreaterThanEqual(int roomCapacity);
}
