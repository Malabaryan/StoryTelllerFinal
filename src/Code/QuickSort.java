package Code;

import java.awt.List;
import java.util.ArrayList;
import java.util.Random;

public class QuickSort {
	public ArrayList<Pares> quicksort(ArrayList<Pares> input) {

		if (input.size() <= 1) {
			return input;
		}

		int middle = (int) Math.ceil((double) input.size() / 2);
		Pares pivot = input.get(middle);

		ArrayList<Pares> less = new ArrayList<Pares>();
		ArrayList<Pares> greater = new ArrayList<Pares>();

		for (int i = 0; i < input.size(); i++) {
			String stringI = (String) input.get(i).getLeft();
			if (stringI.compareTo((String) pivot.getLeft()) <= 0) {
				if (i == middle) {
					continue;
				}
				less.add(input.get(i));
			} else {
				greater.add(input.get(i));
			}
		}

		return concatenate(quicksort(less), pivot, quicksort(greater));
	}

	private ArrayList<Pares> concatenate(ArrayList<Pares> less, Pares pivot, ArrayList<Pares> greater) {

		ArrayList<Pares> list = new ArrayList<Pares>();

		for (int i = 0; i < less.size(); i++) {
			list.add(less.get(i));
		}

		list.add(pivot);

		for (int i = 0; i < greater.size(); i++) {
			list.add(greater.get(i));
		}

		return list;
	}
}