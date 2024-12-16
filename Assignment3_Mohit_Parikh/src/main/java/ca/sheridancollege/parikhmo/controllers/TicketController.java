//package ca.sheridancollege.parikhmo.controllers;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import ca.sheridancollege.parikhmo.beans.Ticket;
//import ca.sheridancollege.parikhmo.beans.User;
//import ca.sheridancollege.parikhmo.repositories.SecurityRepository;
//import ca.sheridancollege.parikhmo.repositories.TicketRepository;
//import lombok.AllArgsConstructor;
//
//@AllArgsConstructor
//@Controller
//public class TicketController {
//
//    TicketRepository TicketRepo;
//    SecurityRepository secRepo;
//    NamedParameterJdbcTemplate jdbc;
//
//    
//    @GetMapping("/")
//    public String home() {
//        return "Index.html";
//    }
//
//    @GetMapping("/add")
//    public String showAddTicketForm(Model model, @AuthenticationPrincipal UserDetails userDetails) {
//        model.addAttribute("ticket", new Ticket());
//        List<User> guests = secRepo.getUsersByRole("ROLE_GUEST");
//        model.addAttribute("users", guests);
//        return "AddTicket.html";
//    }
//
//    @PostMapping("/add")
//    public String addTicket(@ModelAttribute Ticket ticket) {
//        TicketRepo.addTicket(ticket);
//        return "redirect:/add";
//    }
//
//    @GetMapping("/view")
//    public String viewTicket(Authentication authentication, Model model) {
//        String username = authentication.getName();
//        ArrayList<String> roles = new ArrayList<>();
//        for (GrantedAuthority ga : authentication.getAuthorities()) {
//            roles.add(ga.getAuthority());
//        }
//
//        ArrayList<Ticket> tickets = new ArrayList<>();
//        if (roles.contains("ROLE_GUEST")) {
//            tickets.addAll(TicketRepo.getGuestTickets());
//            model.addAttribute("showSummary", true);
//            double subtotal = 0.0;
//            for (Ticket ticket : tickets) {
//                subtotal += ticket.getTicketPrice();
//            }
//
//            double tax = subtotal * 0.13;
//            double total = subtotal + tax;
//
//            model.addAttribute("subtotal", subtotal);
//            model.addAttribute("tax", tax);
//            model.addAttribute("total", total);
//        } else {
//            tickets.addAll(TicketRepo.getTickets2());
//            model.addAttribute("showSummary", false);
//        }
//        model.addAttribute("ticketList", tickets);
//        return "ViewTicket.html";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String edit(@PathVariable int id, Model model) {
//        Ticket ticket = TicketRepo.getTicketById(id);
//        model.addAttribute("ticket ", ticket);
//
//        return "edit.html";
//    }
//
//    @PostMapping("/edit")
//    public String processEdit(Model model, @ModelAttribute Ticket ticket) {
//        TicketRepo.editTicket(ticket);
//        return "redirect:/view";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable int id, Model model) {
//        TicketRepo.deleteTicketById(id);
//        return "redirect:/view";
//    }
//
//    @GetMapping("/vender")
//    public String VenderRoot() {
//        return "/Vender/Home.html";
//    }
//
//    @GetMapping("/guest")
//    public String guestRoot() {
//        return "/Guest/Home.html";
//    }
//}


package ca.sheridancollege.parikhmo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.parikhmo.beans.Ticket;
import ca.sheridancollege.parikhmo.beans.User;
import ca.sheridancollege.parikhmo.repositories.SecurityRepository;
import ca.sheridancollege.parikhmo.repositories.TicketRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class TicketController {

    TicketRepository ticketRepo;
    SecurityRepository secRepo;
    NamedParameterJdbcTemplate jdbc;

    @GetMapping("/")
    public String home() {
        return "Index.html";
    }

    @GetMapping("/add")
    public String showAddTicketForm(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("ticket", new Ticket());
        List<User> guests = secRepo.getUsersByRole("ROLE_GUEST");
        model.addAttribute("users", guests);
        return "AddTicket.html";
    }

    @PostMapping("/add")
    public String addTicket(@ModelAttribute Ticket ticket) {
        ticketRepo.addTicket(ticket);
        return "redirect:/view";
    }

    @GetMapping("/view")
    public String viewTicket(Authentication authentication, Model model) {
        String username = authentication.getName();
        ArrayList<String> roles = new ArrayList<>();
        for (GrantedAuthority ga : authentication.getAuthorities()) {
            roles.add(ga.getAuthority());
        }

        ArrayList<Ticket> tickets = new ArrayList<>();
        if (roles.contains("ROLE_GUEST")) {
            tickets.addAll(ticketRepo.getGuestTickets());
            model.addAttribute("showSummary", true);
            double subtotal = tickets.stream().mapToDouble(Ticket::getTicketPrice).sum();
            double tax = subtotal * 0.13;
            double total = subtotal + tax;

            model.addAttribute("subtotal", subtotal);
            model.addAttribute("tax", tax);
            model.addAttribute("total", total);
        } else {
            tickets.addAll(ticketRepo.getTickets2());
            model.addAttribute("showSummary", false);
        }
        model.addAttribute("ticketList", tickets);
        return "ViewTicket.html";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Ticket ticket = ticketRepo.getTicketById(id);
        model.addAttribute("ticket", ticket);
        return "edit.html";
    }

    @PostMapping("/edit")
    public String processEdit(Model model, @ModelAttribute Ticket ticket) {
        ticketRepo.editTicket(ticket);
        return "redirect:/view";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        ticketRepo.deleteTicketById(id);
        return "redirect:/view";
    }
    @GetMapping("/vender")
	public String venderRoot() {
		return "/Vender/Home.html";
				
	}
	
	@GetMapping("/guest")
	public String guestRoot() {
		return "/Guest/Home.html";			
	}
}


