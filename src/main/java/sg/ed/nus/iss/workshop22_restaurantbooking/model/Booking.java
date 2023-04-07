package sg.ed.nus.iss.workshop22_restaurantbooking.model;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonValue;

public class Booking {
    
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private DateTime reservationDate;
    private String comments;

    //for aggregations
    private Integer totalCount;

    public Booking() {
    }

    public Booking(Integer id, String name, String email, String phone, DateTime reservationDate, String comments,
            Integer totalCount) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.reservationDate = reservationDate;
        this.comments = comments;
        this.totalCount = totalCount;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public DateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(DateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "Booking [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", reservationDate="
                + reservationDate + ", comments=" + comments + ", totalCount=" + totalCount + "]";
    }

    public static Booking create(SqlRowSet rs){
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


    public JsonValue toJson(){
        DateTimeFormatter formatter = DateTimeFormat.forPattern(" 'Date:' yyyy-MM-dd 'Time:' HH:mm:ss");
        return Json.createObjectBuilder()
            .add("id", getId())
            .add("Name", getName())
            .add("Email", getEmail())
            .add("Phone Number", getPhone())
            // .add("Reservation", getReservationDate().toString())
            .add("Reservation", formatter.print(getReservationDate()))
            .add("Comments", getComments())
            .build();
    }

 
}


