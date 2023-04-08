package sg.ed.nus.iss.workshop22_restaurantbooking.repository;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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


    public Booking insertUpdateBooking(Booking booking){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        List<Booking> existingBookings = findByEmail(booking.getEmail());
        // Booking existingbooking = getBookingByEmail(booking.getEmail());
        
        if(existingBookings.isEmpty()){
    
            //insert record
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(INSERT_NEW_BOOKING, Statement.RETURN_GENERATED_KEYS);
    
                //name,email_address,phone_number,reservation_date,comments
                statement.setString(1, booking.getName());
                statement.setString(2, booking.getEmail());
                statement.setString(3,booking.getPhone());
        
                statement.setTimestamp(4, new Timestamp(booking.getReservationDate().toDateTime().getMillis()));
                statement.setString(5, booking.getComments());
                return statement;
            }, keyHolder);
    
            BigInteger primaryKey = (BigInteger) keyHolder.getKey();
    
            booking.setId(primaryKey.intValue());

           
        }

        else{
                //update existing record
                Booking existingBooking = existingBookings.get(0);
                existingBooking.setName(booking.getName());
                existingBooking.setEmail(booking.getEmail());
                existingBooking.setPhone(booking.getPhone());
                existingBooking.setReservationDate(booking.getReservationDate());
                existingBooking.setComments(booking.getComments());
    
                boolean isUpdated = updateBookingSuccess(existingBooking);
                
                if(isUpdated){
                    booking.setId(existingBooking.getId());
                }
    
        }
    
        return booking;
        
    }

    private boolean updateBookingSuccess(Booking existingBooking) {
        return jdbcTemplate.update(UPDATE_BOOKING_BY_EMAIL, 
                existingBooking.getName(),    
                existingBooking.getPhone(), 
                new Timestamp(existingBooking.getReservationDate().toDateTime().getMillis()), 
                existingBooking.getComments(),
                existingBooking.getEmail())>0;
        }

    public Booking updateBooking(Booking booking, String email){

            //update record
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection.prepareStatement(UPDATE_BOOKING_BY_EMAIL);
    
                //name,email_address,phone_number,reservation_date,comments
                statement.setString(1, booking.getName());
                statement.setString(2,booking.getPhone());
        
                statement.setTimestamp(3, new Timestamp(booking.getReservationDate().toDateTime().getMillis()));
                statement.setString(4, booking.getComments());
                statement.setString(5, email);
                return statement;
            });

            return booking;
        
    }


    public Integer countBooking(){
        int counter = 0;

        SqlRowSet rs = null;
        rs =  jdbcTemplate.queryForRowSet(GET_BOOKING_COUNT);
        
        while(rs.next()){
            counter = rs.getInt(1);
        }

        System.out.println(">>>>>>>: " + counter);
        return counter;
    }
    

}
