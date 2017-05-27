package Code;

import java.io.Serializable;
import java.util.ArrayList;

public class Nodo<T> implements Serializable {
	private T Value;
	private ArrayList<Nodo<T>> Ramas;

	public Nodo() {
		Ramas = new ArrayList<Nodo<T>>();
	}

	public void addChild(T pValue) {

	}

	public void addChild(Nodo<T> pNodo) {

	}

	public Nodo<T> getNodeChild(int pIndex) {
		return null;
	}

	public T getNodeValue(int pIndex) {
		return null;
	}

	public T getValue() {
		return Value;
	}

	public void setValue(T Value) {
		this.Value = Value;
	}

	public ArrayList<Nodo<T>> getRamas() {
		return Ramas;
	}

	public void setRamas(ArrayList<Nodo<T>> Ramas) {
		this.Ramas = Ramas;
	}

}
