
package Code;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Bryan H.O.
 */

public class Tag implements Comparable, Serializable {

	private String Tag;
	private ArrayList<Imagen> Fotos;

	/**
	 * Default Constructor
	 * 
	 * @param
	 */
	public Tag() {
		this.Tag = "A";
		Fotos = new ArrayList();
	}

	public Tag(String pTag) {

		this.Tag = pTag;
		Fotos = new ArrayList();

	}

	public Tag(String pTag, Imagen img) {

		this.Tag = pTag;
		Fotos = new ArrayList();
		Fotos.add(img);

	}

	public Tag(String pTag, ArrayList<Imagen> pFotos) {

		this.Tag = pTag;
		this.Fotos = pFotos;

	}

	/**
	 * Compara el String ingresado contra el Tag de la instancia.
	 * 
	 * @param Comparar
	 * @return
	 */
	public int compareTo(Tag Comparar) {
		return Comparar.getTag().compareTo(this.getTag());
	}

	/**
	 * Compara un String contra el Tag de esta instacia.
	 * 
	 * @param Comparar
	 * @return
	 */
	public int compareTo(String Comparar) {
		return Comparar.compareTo(Tag);
	}

	/**
	 * Metodo que toma el Object, lo castea a tipo Tag y lo compara contra el
	 * tag de la instancia. Feoooooooooooooooooooooooooooooooooooooo
	 * 
	 * @param Comparar
	 * @return
	 */

	public int compareTo(NodoAVL Comparar) {
		return Comparar.getValue().compareTo(this);
	}

	@Override
	public int compareTo(Object Comparar) {
		try {
			NodoAVL Comp = (NodoAVL) Comparar;
			return this.compareTo(Comp.getValue());
		} catch (Exception e) {
			;
		}
		try {
			Tag Comp = (Tag) Comparar;
			return this.compareTo(Comp);
		} catch (Exception e) {
			;
		}
		try {
			String Comp = (String) Comparar;
			return this.getTag().compareTo(Comp);
		} catch (Exception e) {
			;
		}
		return 0;

	}

	public void agregarFoto(String pDescripcion, String pURL) {
		this.Fotos.add(new Imagen(pDescripcion, pURL));
	}

	public void agregarFoto(Imagen image) {
		this.Fotos.add(image);
	}

	public String getTag() {
		return Tag;
	}

	public void setTag(String Tag) {
		this.Tag = Tag;
	}

	public ArrayList<Imagen> getFotos() {
		return Fotos;
	}

	public void setFotos(ArrayList<Imagen> Fotos) {
		this.Fotos = Fotos;
	}

	@Override
	public String toString() {
		return this.getTag();
	}

	public static ArrayList<Imagen> recorrerInOrden(NodoAVL nodo) {
		ArrayList<Imagen> Array = new ArrayList();
		recorrerInOrden(Array, nodo);
		return Array;
	}

	private static void recorrerInOrden(ArrayList<Imagen> Array, NodoAVL nodo) {
		if (nodo != null) {
			recorrerInOrden(Array, nodo.RightNode);

			Tag tag = (Tag) nodo.getValue();
			for (Imagen imagen : tag.Fotos) {
				for (Imagen enlistado : tag.Fotos) {
					if (enlistado.getURL().equals(imagen.getURL())) {
						System.out.println("No Repetido!");
						System.out.println("    " + imagen.toString());
						;
					} else {
						Array.add(imagen);
						System.out.println(imagen.toString());
						System.out.println("Repetido!");
						break;
					}
				}
			}
			recorrerInOrden(Array, nodo.LeftNode);
		}
	}
}
