package hr.fer.nasp.lab2;

import java.util.ArrayList;

public class Item {
	public String name;
	public ArrayList<SubItem> subCategory;
	
	public Item(String name, ArrayList<SubItem> subCategory){
		this.name = name; 
		this.subCategory = subCategory;
	}
}
