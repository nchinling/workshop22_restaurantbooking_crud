package sg.ed.nus.iss.workshop22_restaurantbooking.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;
import org.springframework.jdbc.core.RowMapper;

import sg.ed.nus.iss.workshop22_restaurantbooking.model.Booking;


public class BookingRowMapper implements RowMapper<Booking>{
    
    @Override
    public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {

        Booking booking = new Booking();

        booking.setId(rs.getInt("id"));
        booking.setName(rs.getString("name"));
        booking.setEmail(rs.getString("email_address"));
        booking.setPhone(rs.getString("phone_number"));
        // booking.setReservationDate(new DateTime(DateTime.parse(rs.getString("reservation_date")))); 
        booking.setReservationDate(new DateTime(rs.getString("reservation_date")));
       
        booking.setComments(rs.getString("comments"));
     
        return booking;

    }
}
