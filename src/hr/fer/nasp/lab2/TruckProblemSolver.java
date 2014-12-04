package hr.fer.nasp.lab2;

import java.util.ArrayList;

public class TruckProblemSolver {
	private ArrayList<Item> allItems;
	private int maxLoad;

	public TruckProblemSolver(ArrayList<Item> allItems, int maxLoad) {
		this.allItems = allItems;
		this.maxLoad = maxLoad;
	}

	public ArrayList<SubItem> findOptimal() {
		ArrayItem[][] array = new ArrayItem[maxLoad][numOfElements()];

		for (int i = 0; i < maxLoad; i++) {
			int j = 0;
			for (Item item : allItems) {
				for (SubItem subItem : item.subCategory) {

					array[i][j] = getMaxValue(array, i + 1, j, subItem);
					j++;
				}
			}
		}
		return getOptimalItemsFromArray(array);
	}

	private ArrayItem getMaxValue(ArrayItem[][] array, int i, int j,
			SubItem item) {
		Item category = getCategory(item);
		int index = category.subCategory.indexOf(item);

		ArrayItem[] potentialMax = new ArrayItem[index + 2];

		for (int k = 0; k < index; k++) {
			int value = getValue(array, i, j - k - 1, item);
			potentialMax[k] = new ArrayItem(value, i, j - k - 1);
		}

		int value = getValue(array, i, j - index - 1, item);
		potentialMax[index] = new ArrayItem(value, i, j - index - 1);

		value = getValue(array, i - item.volume, j - index - 1, item)
				+ getValue(item, i);
		potentialMax[index + 1] = new ArrayItem(value, i, j - index - 1);

		return findMax(potentialMax, index + 2);
	}

	private int getValue(ArrayItem[][] array, int i, int j, SubItem item) {
		if (i < 1 || j < 0) {
			return 0;
		}
		return array[i - 1][j].value;
	}

	private int getValue(SubItem item, int volume) {
		if (item.volume > volume)
			return 0;
		return item.value;
	}

	private ArrayItem findMax(ArrayItem[] items, int size) {
		if (size < 0)
			return null;
		int max = 0;
		for (int i = 1; i < size; i++) {
			if (items[i].value > items[max].value)
				max = i;
		}
		return items[max];
	}

	private Item getCategory(SubItem subItem) {
		for (Item item : allItems) {
			if (item.subCategory.contains(subItem)) {
				return item;
			}
		}
		return null;
	}

	private ArrayList<SubItem> getOptimalItemsFromArray(ArrayItem[][] array) {
		int i = maxLoad - 1, j = numOfElements() - 1;
		ArrayList<SubItem> optimalItems = new ArrayList<SubItem>();		
		
		while (i > 0 && j >= 0) {
			if (j > 0 && array[i][j].value != array[i][j - 1].value){
				optimalItems.add(getSubItem(i, j));
			} else if (j == 0 && array[i][j].value != 0){
				optimalItems.add(getSubItem(i, j));
			}
			int tmp_i = array[i][j].i;
			j = array[i][j].j;
			i = tmp_i - 1;
		}

		return optimalItems;
	}

	private SubItem getSubItem(int i, int j){
		int k = 0;
		for (Item item : allItems){
			for (SubItem subItem : item.subCategory){
				k++;
				if (k == j + 1)
					return subItem;
			}
		}		
		return null;
	}
	
	private int numOfElements() {
		int i = 0;
		for (Item item : allItems) {
			i += item.subCategory.size();
		}
		return i;
	}
}
