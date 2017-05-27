package Code;

import java.io.Serializable;

/**
 * 
 * @author Malab
 */
public class NodoAVL implements Comparable, Serializable {
	public Comparable Value;
	public NodoAVL LeftNode;
	public NodoAVL RightNode;
	public int Height;

	/**
	 * 
	 * @param pValue
	 */
	public NodoAVL(Comparable pValue) {
		this.Value = pValue;
		this.LeftNode = null;
		this.RightNode = null;

	}

	/**
	 * 
	 * @param pValue
	 * @param pLeft
	 * @param pRight
	 */
	public NodoAVL(Comparable pValue, NodoAVL pLeft, NodoAVL pRight) {
		this.Value = pValue;
		this.LeftNode = pLeft;
		this.RightNode = pRight;
		this.Height = 0;
	}

	@Override
	public int compareTo(Object o) {
		return Value.compareTo(o);
	}

	public int compareTo(Comparable o) {
		return Value.compareTo(o);
	}

	public int compareTo(NodoAVL o) {
		return Value.compareTo(o.getValue());
	}

	public Comparable getValue() {
		return Value;
	}

	public void setValue(Comparable Value) {
		this.Value = Value;
	}

}