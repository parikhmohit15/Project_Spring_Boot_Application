package ca.sheridancollege.parikhmo.controller;

import java.util.ArrayList;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.parikhmo.beans.Item;
import ca.sheridancollege.parikhmo.repositories.ItemRepository; 
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ItemController {

     ItemRepository itemRepo; 
    NamedParameterJdbcTemplate jdbc;

    @GetMapping("/")
    public String home() {
        return "root.html";
    }
  
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("item", new Item());
        return "addItem.html";
    }

    @PostMapping("/add")
    public String process(@ModelAttribute Item item) {
        itemRepo.addItem(item);
        return "redirect:/add"; 
    }

    @GetMapping("/view")
    public String view(Model model) {
        ArrayList<Item> items = itemRepo.getItems(); 
        model.addAttribute("items", items); 
        return "viewItems.html";
    }
    @GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model model) {
		Item item = itemRepo.getItemById(id);
		model.addAttribute("item", item); 
		
		return "editItem.html";
	}
	
	@PostMapping("/edit")
	public String processEdit(Model model, @ModelAttribute Item item) {
	    itemRepo.editItem(item);  
	    return "redirect:/view";			
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, Model model) {
		itemRepo.deleteItemById(id);  
		return "redirect:/view";  
	}
	@GetMapping("/stats")
    public String stats(Model model) {
        long totalItems = itemRepo.getItems().size(); 
        double averagePrice = itemRepo.getItems().stream().mapToDouble(Item::getPrice).average().orElse(0.0); 
        double totalStockValue = itemRepo.calculateTotalStockValue(); 
        String topSellingGadget = itemRepo.getTopSellingGadget(); 
        String mostExpensiveItem = itemRepo.getMostExpensiveItem(); 
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("averagePrice", averagePrice);
        model.addAttribute("totalStockValue", totalStockValue);
        model.addAttribute("topSellingGadget", topSellingGadget);
        model.addAttribute("mostExpensiveItem", mostExpensiveItem);
        
        return "stats.html"; 
    }
	}
	


