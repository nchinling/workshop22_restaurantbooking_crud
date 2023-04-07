package sg.ed.nus.iss.workshop22_restaurantbooking.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.ed.nus.iss.workshop22_restaurantbooking.model.Booking;
import sg.ed.nus.iss.workshop22_restaurantbooking.service.BookingService;

@RestController
@RequestMapping(path = "/restaurant", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookingsRestController {
    
    @Autowired
    private BookingService bookingsvc;
    
    @GetMapping(path = "/bookings")
    public ResponseEntity<String> getAllBookings(@RequestParam(required=false, defaultValue ="0") String offset,
    @RequestParam(required=false, defaultValue ="10") String limit) throws NumberFormatException, IOException{

        List<Booking> bookings = bookingsvc.findAll(Integer.parseInt(offset), 
                                    Integer.parseInt(limit));

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (Booking b : bookings){
            arrayBuilder.add(b.toJson());
        }

        //array is used as there is a list of customers
        JsonArray result = arrayBuilder.build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());
        
    }


    @GetMapping("/namebooking")
    public ResponseEntity<String> getBookingByName(@RequestParam String q){
        
        List<Booking> bookings = bookingsvc.findByName(q);

        //array is used as there might be more than one of each name..
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (Booking b : bookings){
            arrayBuilder.add(b.toJson());
        }

        JsonArray result = arrayBuilder.build();

        if (bookings.isEmpty())
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{error_msg: record for booking "+ q +" not found :)}");
                    // .body("{'error_code' : " + HttpStatus.NOT_FOUND+"'}");


        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());
        
    }

        
        // JsonObject result;
        // try{
        //     Booking booking = bookingsvc.findByName(name); 
          
        //     JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        //     objectBuilder.add("booking", booking.toJson());
        //     result = objectBuilder.build();
        // } catch(Exception e){
        //     return ResponseEntity
        //         .status(HttpStatus.NOT_FOUND)
        //         .contentType(MediaType.APPLICATION_JSON)
        //         .body("{error msg : record for booking " + name + " not found}");
        // }

        // return ResponseEntity
        // .status(HttpStatus.OK)
        // .contentType(MediaType.APPLICATION_JSON)
        // .body(result.toString());

            
        // JsonObject result;
        // try{
        //     Booking booking = bookingsvc.findByName(name); 
          
        //     JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        //     objectBuilder.add("booking", booking.toJson());
        //     result = objectBuilder.build();
        // } catch(Exception e){
        //     return ResponseEntity
        //         .status(HttpStatus.NOT_FOUND)
        //         .contentType(MediaType.APPLICATION_JSON)
        //         .body("{error msg : record for booking " + name + " not found}");
        // }

        // return ResponseEntity
        // .status(HttpStatus.OK)
        // .contentType(MediaType.APPLICATION_JSON)
        // .body(result.toString());
    
        @GetMapping("/emailbooking")
        public ResponseEntity<String> getBookingByEmail(@RequestParam String q){
            
            List<Booking> bookings = bookingsvc.findByEmail(q);
    
            //array is used as there might be more than one of each name..
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
    
            for (Booking b : bookings){
                arrayBuilder.add(b.toJson());
            }
    
            JsonArray result = arrayBuilder.build();
    
            if (bookings.isEmpty())
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{error_msg: record for booking "+ q +" not found :)}");
                        // .body("{'error_code' : " + HttpStatus.NOT_FOUND+"'}");
    
    
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(result.toString());
            
        }
        

    



}
