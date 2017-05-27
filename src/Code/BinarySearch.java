package Code;

import java.util.ArrayList;

public class BinarySearch {

	public int busquedaBinaria(ArrayList<Pares> Lista, String dato) {
		if (Lista.isEmpty()) {
			return -1;
		}
		int n = Lista.size();
		int centro, inf = 0, sup = n - 1;
		while (inf <= sup) {
			centro = (sup + inf) / 2;
			String centroS = (String) Lista.get(centro).getLeft();
			if (centroS.compareTo(dato) == 0)
				return centro;
			else if (dato.compareTo(centroS) < 0) {
				sup = centro - 1;
			} else {
				inf = centro + 1;
			}
		}
		return -1;
	}

}