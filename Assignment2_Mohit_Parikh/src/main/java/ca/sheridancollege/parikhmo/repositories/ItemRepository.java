package ca.sheridancollege.parikhmo.repositories;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import org.springframework.stereotype.Repository;

import ca.sheridancollege.parikhmo.beans.Item;
import lombok.AllArgsConstructor;


@Repository
@AllArgsConstructor
public class ItemRepository {

	private NamedParameterJdbcTemplate jdbc;
	
	public void addItem(Item item ) {
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    String query = "INSERT INTO item (name, price, category, powerlevel, material , weight, stockStatus) " +
	               "VALUES (:name, :price, :category, :powerlevel, :material, :weight, :stockStatus)";
	    
	    parameters.addValue("name", item.getName());
	    parameters.addValue("price", item.getPrice());
	    parameters.addValue("category", item.getCategory());
	    parameters.addValue("powerlevel", item.getPowerlevel());
	    parameters.addValue("material", item.getMaterial());
	    parameters.addValue("weight", item.getWeight());
	    parameters.addValue("stockStatus", item.getStockStatus());
	    jdbc.update(query, parameters);
	}

	public ArrayList<Item> getItems() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "Select * from item";
		ArrayList<Item> Items = new ArrayList<Item>();
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for (Map<String, Object> row : rows) {
		Item d = new Item();
		
		d.setId((int)(row.get("id")));
		d.setName((String)(row.get("name")));
		d.setPrice((double)(row.get("price")));
		d.setCategory((String)(row.get("category")));
		d.setPowerlevel((int)(row.get("powerlevel")));
		d.setMaterial((String)(row.get("material")));
		d.setWeight((double)(row.get("weight")));
		d.setStockStatus((String)(row.get("stockStatus")));
		
		Items.add(d);
		}
		return Items;
	}
	public ArrayList<Item> getItem2() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String q = "Select * from item";
		ArrayList<Item> Items =
		(ArrayList<Item>)jdbc.query(q, parameters,
		new BeanPropertyRowMapper<Item>(Item.class));
		return Items;
	}
	
	public Item getItemById (int id){
		ArrayList<Item> Items = new ArrayList<Item>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "Select * from item where id = :me";
		parameters.addValue("me", id);
		
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for (Map<String, Object> row : rows) 
		{
			Item d = new Item();
			
			
			d.setId((int)(row.get("id")));
			d.setName((String)(row.get("name")));
			d.setPrice((double)(row.get("price")));
			d.setCategory((String)(row.get("category")));
			d.setPowerlevel((int)(row.get("powerlevel")));
			d.setMaterial((String)(row.get("material")));
			d.setWeight((double)(row.get("weight")));
			d.setStockStatus((String)(row.get("stockStatus")));
		
			
			
			Items.add(d);
		}
		
		if(Items.size()>0) {
			return Items.get(0);
		}else {
			return null;
		}
	}
	
	public void editItem(Item item) {
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    String query = "UPDATE item SET name = :name, price = :price, category = :category, " +
                "powerlevel = :powerlevel, material = :material, weight = :weight, " +
                "stockStatus = :stockStatus WHERE id = :me";
	    

	    parameters.addValue("me", item.getId());
	    parameters.addValue("name", item.getName());
	    parameters.addValue("price", item.getPrice());
	    parameters.addValue("category", item.getCategory());
	    parameters.addValue("powerlevel", item.getPowerlevel());
	    parameters.addValue("material", item.getMaterial());
	    parameters.addValue("weight", item.getWeight());
	    parameters.addValue("stockStatus", item.getStockStatus());
	    jdbc.update(query, parameters);
	}
	
	public void deleteItemById(int id) {
	    MapSqlParameterSource parameters = new MapSqlParameterSource();
	    String query = "DELETE FROM item WHERE id = :id";
	    parameters.addValue("id", id);
	    jdbc.update(query, parameters);
	}

	public long countTotalItems() {
	    String query = "SELECT COUNT(*) FROM item";
	    return jdbc.queryForObject(query, new MapSqlParameterSource(), Long.class);
	}

	public double calculateAveragePrice() {
	    String query = "SELECT AVG(price) FROM item";
	    return jdbc.queryForObject(query, new MapSqlParameterSource(), Double.class);
	}

	public double calculateTotalStockValue() {
	    String query = "SELECT SUM(price * weight) FROM item WHERE stockStatus = 'In Stock'";
	    return jdbc.queryForObject(query, new MapSqlParameterSource(), Double.class);
	}

	public String getTopSellingGadget() {
	   
	    String query = "SELECT name FROM item WHERE stockStatus = 'In Stock' LIMIT 1"; 
	    return jdbc.queryForObject(query, new MapSqlParameterSource(), String.class);
	}

	public String getMostExpensiveItem() {
	    String query = "SELECT name FROM item ORDER BY price DESC LIMIT 1";
	    return jdbc.queryForObject(query, new MapSqlParameterSource(), String.class);
	}

   
}
