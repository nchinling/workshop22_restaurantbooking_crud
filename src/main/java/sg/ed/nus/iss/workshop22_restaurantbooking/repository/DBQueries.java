package sg.ed.nus.iss.workshop22_restaurantbooking.repository;


public class DBQueries {
    public static final String SELECT_ALL_BOOKINGS="select * from bookings limit ?, ?";
    public static final String SELECT_BOOKING_BY_NAME ="select * from bookings where name like ?";
    public static final String SELECT_BOOKING_BY_EMAIL ="select * from bookings where email_address = ?";
    public static final String INSERT_NEW_BOOKING = "insert into bookings (name,email_address,phone_number,reservation_date,comments) VALUES (?,?,?,?,?)";
    public static final String UPDATE_BOOKING_BY_EMAIL = "update bookings set name= ?, phone_number = ?, reservation_date = ?, comments = ?  where email_address = ?";
    public static final String GET_BOOKING_COUNT = "select count(*) from bookings";
}
