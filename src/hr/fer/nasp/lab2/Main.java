package hr.fer.nasp.lab2;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		ArrayList<Item> items = new ArrayList<Item>();
		ArrayList<SubItem> subItems = new ArrayList<SubItem>();
		subItems.add(new SubItem("A", 10, 3));
		subItems.add(new SubItem("B", 5, 4));
		items.add(new Item("x", subItems));
		
		TruckProblemSolver solver = new TruckProblemSolver(items, 7);
		ArrayList<SubItem> optimal = solver.findOptimal();
		for (SubItem subItem : optimal){
			System.out.println(subItem.name);
		}
	}
}
