package ca.sheridancollege.parikhmo.repositories;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import ca.sheridancollege.parikhmo.beans.Ticket;
import lombok.AllArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class TicketRepository {

    private NamedParameterJdbcTemplate jdbc;

    public void addTicket(Ticket ticket) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "INSERT INTO Ticket (ticketHolder, ticketPrice, seatNumber, eventLocation, eventDate, eventTime, guestName) " +
                       "VALUES (:ticketHolder, :ticketPrice, :seatNumber, :eventLocation, :eventDate, :eventTime , :guestName)";
        
        parameters.addValue("ticketHolder", ticket.getTicketHolder());
        parameters.addValue("ticketPrice", ticket.getTicketPrice());
        parameters.addValue("seatNumber", ticket.getSeatNumber());
        parameters.addValue("eventLocation", ticket.getEventLocation());
        parameters.addValue("eventDate", ticket.getEventDate());
        parameters.addValue("eventTime", ticket.getEventTime());
        parameters.addValue("guestName", ticket.getGuestName()); 
        
        jdbc.update(query, parameters);
    }
    public ArrayList<Ticket> getTickets() {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM Ticket";
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);

        for (Map<String, Object> row : rows) {
            Ticket ticket = new Ticket();
            
            ticket.setId((int) row.get("id"));
            ticket.setTicketHolder((String) row.get("ticketHolder"));
            ticket.setTicketPrice((double) row.get("ticketPrice"));
            ticket.setSeatNumber((String) row.get("seatNumber"));
            ticket.setEventLocation((String) row.get("eventLocation"));
            ticket.setEventDate((String) row.get("eventDate"));
            ticket.setEventTime((String) row.get("eventTime"));
            
            tickets.add(ticket);
        }
        return tickets;
    }

    public Ticket getTicketById(int id) {
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "SELECT * FROM Ticket WHERE id = :id";
        parameters.addValue("id", id);
        
        List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
        for (Map<String, Object> row : rows) {
            Ticket ticket = new Ticket();
            
            ticket.setId((int) row.get("id"));
            ticket.setTicketHolder((String) row.get("ticketHolder"));
            ticket.setTicketPrice((double) row.get("ticketPrice"));
            ticket.setSeatNumber((String) row.get("seatNumber"));
            ticket.setEventLocation((String) row.get("eventLocation"));
            ticket.setEventDate((String) row.get("eventDate"));
            ticket.setEventTime((String) row.get("eventTime"));
            
            tickets.add(ticket);
        }
        
        if (tickets.size() > 0) {
            return tickets.get(0);
        } else {
            return null;
        }
    }


    public void editTicket(Ticket ticket) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "UPDATE Ticket SET ticketHolder = :ticketHolder, ticketPrice = :ticketPrice, " +
                       "seatNumber = :seatNumber, eventLocation = :eventLocation, " +
                       "eventDate = :eventDate, eventTime = :eventTime WHERE id = :id";
        
        parameters.addValue("id", ticket.getId());
        parameters.addValue("ticketHolder", ticket.getTicketHolder());
        parameters.addValue("ticketPrice", ticket.getTicketPrice());
        parameters.addValue("seatNumber", ticket.getSeatNumber());
        parameters.addValue("eventLocation", ticket.getEventLocation());
        parameters.addValue("eventDate", ticket.getEventDate());
        parameters.addValue("eventTime", ticket.getEventTime());
        
        jdbc.update(query, parameters);
    }

    public void deleteTicketById(int id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String query = "DELETE FROM Ticket WHERE id = :id";
        parameters.addValue("id", id);
        
        jdbc.update(query, parameters);
    }
	
    public ArrayList<Ticket> getTickets2(){
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		String query = "Select * from Ticket";
		ArrayList<Ticket> tickets = (ArrayList<Ticket>)jdbc.query(query, params, new BeanPropertyRowMapper<Ticket>(Ticket.class));
				
				
		return tickets;
	}
	
    public ArrayList<Ticket> getGuestTickets() {  
	    MapSqlParameterSource params = new MapSqlParameterSource();  
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();  
	    String guestName = authentication.getName();
	    String query = "SELECT * FROM Ticket WHERE guestName = :guestName";  
	    params.addValue("guestName", guestName);   

	    ArrayList<Ticket> tickets = (ArrayList<Ticket>) jdbc.query(query, params, new BeanPropertyRowMapper<>(Ticket.class));  
	    return tickets;  
	}  
}






