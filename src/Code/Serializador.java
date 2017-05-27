package Code;

import Controller.*;
import Ui.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

/**
 * --------- Convertir esta clase a estatica para ------------ --------- usar
 * los metodos sin instanciarla ------------
 */
public class Serializador {

	// private static final long serialVersionUID = 8799656478674716638L;

	public byte[] Serializador(Object obj) throws IOException {
		try (ByteArrayOutputStream b = new ByteArrayOutputStream()) {
			try (ObjectOutputStream o = new ObjectOutputStream(b)) {
				o.writeObject(obj);
			}
			return b.toByteArray();
		}
	}

	public Object deseerializador(byte[] bytes) throws IOException, ClassNotFoundException {
		try (ByteArrayInputStream b = new ByteArrayInputStream(bytes)) {
			try (ObjectInputStream o = new ObjectInputStream(b)) {
				return o.readObject();
			}
		}
	}

}