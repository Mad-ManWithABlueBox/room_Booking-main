package com.example.demo.Repository;

import com.example.demo.Model.Booking;
import com.example.demo.Request.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{

    List<Booking> findByRoomIDAndDateOfBooking(int roomID, Date date);

    List<Booking> findByUserID(int userID);

    List<Booking> findByUserIDAndDateOfBookingGreaterThanEqualOrderByDateOfBookingAsc(int userID, Date currentDate);

    List<Booking> findByRoomID(int roomID);
}
