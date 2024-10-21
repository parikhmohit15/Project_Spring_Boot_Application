package ca.sheridancollege.parikhmo.beans;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {


	private int id;
	private String name;
    private double  price;            
    private String category;     
    private int powerlevel;       
    private String material;      
    private double weight;        
    private String stockStatus;
}
