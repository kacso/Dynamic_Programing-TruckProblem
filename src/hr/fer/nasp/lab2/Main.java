package hr.fer.nasp.lab2;

import java.util.ArrayList;

public class Main {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ArrayList<Item> items = new ArrayList<Item>();
		ArrayList<SubItem> subItems = new ArrayList<SubItem>();
		subItems.add(new SubItem("Ax", 10, 3));
		subItems.add(new SubItem("Bx", 5, 4));
		items.add(new Item("x", (ArrayList<SubItem>)subItems.clone()));
		
		subItems.clear();
		subItems.add(new SubItem("Ay", 10, 3));
		subItems.add(new SubItem("By", 5, 1));
		items.add(new Item("y", (ArrayList<SubItem>)subItems.clone()));
		
		subItems.clear();
		subItems.add(new SubItem("Az", 15, 3));
		items.add(new Item("z", (ArrayList<SubItem>)subItems.clone()));
		
		TruckProblemSolver solver = new TruckProblemSolver(items, 7);
		ArrayList<SubItem> optimal = solver.findOptimal();
		for (SubItem subItem : optimal){
			System.out.print(subItem.name + ", ");
			System.out.print(subItem.value + ", ");
			System.out.println(subItem.volume);
		}
	}
}
