package com.tacoloy.signup.repository;

import com.tacoloy.signup.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {
    Booking findByrestaurantName (String restaurantName);

}
