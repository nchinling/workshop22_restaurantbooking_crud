package sg.ed.nus.iss.workshop22_restaurantbooking.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.ed.nus.iss.workshop22_restaurantbooking.model.Booking;
import static sg.ed.nus.iss.workshop22_restaurantbooking.repository.DBQueries.*;

@Repository
public class BookingRepo {
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public List<Booking> findAll(int offset, int limit){
        
        List<Booking> bookings = new ArrayList<Booking>();
        SqlRowSet rs =  jdbcTemplate.queryForRowSet(SELECT_ALL_BOOKINGS, offset,
                                                     limit);
        while(rs.next()){
            bookings.add(Booking.create(rs));
        }

        // List<Customer> customers = jdbcTemplate.query(SELECT_ALL_CUSTOMERS, 
        // new CustomerRowMapper() , new Object[]{offset, limit});

        return bookings;
    }


    public List<Booking> findByName(String name){
        List<Booking> bookings = new ArrayList<Booking>();

        SqlRowSet rs = null;
        //This approach allows for wildcards in the name
        // rs =  jdbcTemplate.queryForRowSet(SELECT_RSVP_BY_NAME, new Object[]{"%"+name+ "%"});
        rs =  jdbcTemplate.queryForRowSet(SELECT_BOOKING_BY_NAME, "%"+name+"%");
        
        //this approach doesn't allow for wildcards
        // rs =  jdbcTemplate.queryForRowSet(SELECT_RSVP_BY_NAME, name);

        while(rs.next()){
            bookings.add(Booking.create(rs));
        }
        return bookings;

    }

    public List<Booking> findByEmail(String email){
        List<Booking> bookings = new ArrayList<Booking>();

        SqlRowSet rs = null;
        //This approach allows for wildcards in the name
        // rs =  jdbcTemplate.queryForRowSet(SELECT_RSVP_BY_NAME, new Object[]{"%"+name+ "%"});
        rs =  jdbcTemplate.queryForRowSet(SELECT_BOOKING_BY_EMAIL, email);
        
        //this approach doesn't allow for wildcards
        // rs =  jdbcTemplate.queryForRowSet(SELECT_RSVP_BY_NAME, name);

        while(rs.next()){
            bookings.add(Booking.create(rs));
        }
        return bookings;

    }

}
