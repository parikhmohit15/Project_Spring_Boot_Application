package ca.sheridancollege.parikhmo.beans;


import java.sql.Date;
import java.sql.Time;
import java.util.Objects;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Ticket {
	
	
	private int id;
    private String ticketHolder;
    private double ticketPrice;
    private String seatNumber;
    private String eventLocation;
    private String eventDate;
    private String eventTime;
    private String guestName;

}






