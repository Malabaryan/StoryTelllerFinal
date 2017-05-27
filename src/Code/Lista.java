package Code;

import java.io.Serializable;
import java.lang.Object;
import java.util.*;

public class Lista implements Serializable {
	private LinkedList<Object> lista = null;

	public Lista() {
		lista = new LinkedList<Object>();
	}

	public int size() {
		return lista.size();
	}

	private List getElements() {
		return lista;
	}

	public void removeAll() {
		while (!lista.isEmpty()) {
			lista.removeFirst();
		}
	}

	public boolean exist(Object element) {
		return lista.contains(element);
	}

	public Object get(int index) {
		return lista.get(index);
	}

	public void add(Object elemento) {
		lista.add(elemento);
	}

	public ListIterator<Object> listIterator() {
		return lista.listIterator();
	}

	public boolean remove(Object elemento) {
		return lista.remove(elemento);
	}

	public Object remove(int index) {
		return lista.remove(index);
	}

	public Object removeFirst() {
		return lista.removeFirst();
	}

	public Object removeLast() {
		return lista.removeLast();
	}

	public void copy(Lista fuente) {
		ListIterator<Object> iterador = fuente.listIterator();
		while (iterador.hasNext()) {
			Object elemento = (Object) iterador.next();
			lista.add(elemento);
		}
	}

	public boolean isEmpty() {
		return lista.isEmpty();
	}

	public static void main(String[] args) {
		Lista aux = new Lista();
		Lista prueba = new Lista();
		for (int i = 0; i < 10; i++) {
			prueba.add(i);
		}
		prueba.remove(4);
		if (prueba.exist(9)) {
			System.out.println("El elemento " + Integer.toString(9) + " existe");
		}
		ListIterator<Object> iterador = prueba.listIterator();
		while (iterador.hasNext()) {
			Object valor = (Object) iterador.next();
			System.out.println(Integer.toString((Integer) valor));
		}
		int i = 0;
		int total = prueba.size();
		System.out.println("Tamaño : " + Integer.toString(total));
		while (i != total) {
			Object valor = (Object) prueba.get(i);
			System.out.println(valor.toString());
			i++;
		}

	}
}
