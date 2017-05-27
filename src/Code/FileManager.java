package Code;

import Controller.*;
import Ui.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * --------- Convertir esta clase a estatica para ------------ --------- usar
 * los metodos sin instanciarla ------------
 */
public class FileManager {

	public Object leerArchivo(File Archivo) throws IOException, ClassNotFoundException {
		Path path = Paths.get(Archivo.getPath());
		byte[] data = Files.readAllBytes(path);
		Serializador tool = new Serializador();
		return tool.deseerializador(data);
	}

	public void escribirArchivo(File Archivo, Object obj) throws IOException {
		FileOutputStream out = new FileOutputStream(Archivo);
		Path file = Paths.get(Archivo.getPath());
		Serializador tool = new Serializador();
		byte[] data = tool.Serializador(obj);
		Files.write(file, data);

		out.write(data);
	}

	public ArrayList getLines(File Archivo) {
		ArrayList lineas = new ArrayList();
		try {
			BufferedReader b = new BufferedReader(new FileReader(Archivo));
			String readLine = "";
			while ((readLine = b.readLine()) != null) {
				lineas.add(readLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return lineas;

	}

}
