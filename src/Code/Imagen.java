package Code;

import java.io.Serializable;

public class Imagen implements Serializable {
	private String Descripcion;
	private String URL;

	public Imagen(String Descripcion, String ULR) {
		this.Descripcion = Descripcion;
		this.URL = ULR;
	}

	public Imagen() {

	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.Descripcion = descripcion;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uLR) {
		URL = uLR;
	}

	@Override
	public String toString() {
		return URL + " - " + Descripcion;
	}
}
