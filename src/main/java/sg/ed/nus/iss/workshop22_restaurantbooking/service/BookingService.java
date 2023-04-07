package sg.ed.nus.iss.workshop22_restaurantbooking.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.ed.nus.iss.workshop22_restaurantbooking.model.Booking;
import sg.ed.nus.iss.workshop22_restaurantbooking.repository.BookingRepo;

@Service
public class BookingService {
    
    @Autowired
    private BookingRepo bookingrepo;
    
    public List<Booking> findAll(int offset, int limit) throws IOException{
        return bookingrepo.findAll(offset, limit);
    }
}
