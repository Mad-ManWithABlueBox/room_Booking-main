package com.example.demo.Service;

import com.example.demo.Model.Booking;
import com.example.demo.Model.Room;
import com.example.demo.Repository.BookingRepository;
import com.example.demo.Repository.RoomRepository;
import com.example.demo.Request.RoomAddRequest;
import com.example.demo.Request.RoomEditRequest;
import com.example.demo.Response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public void createRoom(RoomAddRequest roomRequest) {
        Room room = new Room();
        room.setRoomName(roomRequest.getRoomName());
        room.setRoomCapacity(roomRequest.getRoomCapacity());
        roomRepository.save(room);
    }
    public void editRoom(RoomEditRequest roomEditRequest) {
        Room room = roomRepository.findById(roomEditRequest.getRoomID()).orElse(null);
        if (room != null) {
            room.setRoomName(roomEditRequest.getRoomName());
            room.setRoomCapacity(roomEditRequest.getRoomCapacity());
            roomRepository.save(room);
        }
    }
    public boolean isRoomExists(int roomID) {
        return roomRepository.existsByRoomID(roomID);
    }

    public void deleteRoom(int roomID) {
        roomRepository.deleteById(roomID);
    }

    public boolean isRoomExistsName(String roomName) {
        return roomRepository.existsByRoomName(roomName);
    }

    public List<AllRoomResponse> getAllRoom() {
        return roomRepository.findAll().stream()
                .map(room -> new AllRoomResponse(room.getRoomID(), room.getRoomName(), room.getRoomCapacity()))
                .collect(Collectors.toList());
    }
    public List<TempRoomResponse> roomToTemp(List<Room> rooms, BookingRepository bookingRepository){
        List<TempRoomResponse> tempRoomResponses = new ArrayList<>();
        for(Room room:rooms){
            List<Booking> bookingList = bookingRepository.findByRoomID(room.getRoomID());
            TempRoomResponse roomResponse = new TempRoomResponse(room.getRoomID(),room.getRoomName(), room.getRoomCapacity(), getTempBookings(bookingList));
            tempRoomResponses.add(roomResponse);
        }
        return tempRoomResponses;
    }

    private List<TempDetailsBooking> getTempBookings(List<Booking> bookingList) {
        List<TempDetailsBooking> tempDetailsBookings = new ArrayList<>();
        for(Booking booking : bookingList){
            UserIDResponse user = new UserIDResponse(booking.getUserID());
            TempDetailsBooking detailsBooking = new TempDetailsBooking(booking.getBookingId(), user,booking.getDateOfBooking(), booking.getTimeFrom(), booking.getTimeTo(), booking.getPurpose());
            tempDetailsBookings.add(detailsBooking);
        }
        return tempDetailsBookings;
    }

}
