package sg.ed.nus.iss.workshop22_restaurantbooking.repository;


public class DBQueries {
    public static final String SELECT_ALL_BOOKINGS="select * from bookings limit ?, ?";
    public static final String SELECT_BOOKING_BY_NAME ="select * from bookings where name like ?";
    public static final String SELECT_BOOKING_BY_EMAIL ="select * from bookings where email_address = ?";
}
