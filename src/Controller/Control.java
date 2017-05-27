/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Code.*;
import Ui.*;
import java.awt.Image;
import java.io.BufferedWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Bryan H.O.
 */
public class Control {

	private File Album = new File("Album.txt");
	private File Data = new File("Data.txt");
	private ImageManager ImgManager;
	private ArrayList<Imagen> Imagenes;
	private ArrayList<Pares> Save = new ArrayList();
	private ArrayList<Tag> Tags;
	private ArbolAVL Arbol;
	private Ui ui;
	private Hilo hilo;

	/**
	 * Esta es procesarURL
	 */

	/**
	 * Default Constructor
	 * 
	 * @param
	 * @throws IOException
	 */
	public Control() {

		this.Arbol = new ArbolAVL();
		this.hilo = new Hilo();
		hilo.setControlador(this);
		if (!Data.exists()) {
			try {
				Data.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ImgManager = new ImageManager();
		FileManager GetSave = new FileManager();
		ArrayList<String> temp = GetSave.getLines(Data);
		int index = 0;
		while (temp.size() != index) {
			String linea = temp.get(index);
			String[] splited = linea.split("\\s+");
			Pares par = new Pares(splited[0], splited[1]);
			Save.add(par);
			index++;
		}
//		System.out.println(Save.get(0).getLeft());
	}

        /**
         * Inicializa la Ui.
         */
	public void iniciarUi() {
		this.ui = new Ui();
		this.ui.setControlador(this);
		if (this.ui == null)
			System.out.println("Ui nula otra");
		this.ui.setVisible(true);
	}

	/**
	 * 
	 * @param File
	 * @throws IOException
	 */
	public void guardar(File File, String name) throws IOException {

		Pares par = new Pares(name, File.getPath());
		Save.add(par);
		FileManager save = new FileManager();
		File.createNewFile();
		save.escribirArchivo(File, Arbol);
		actualizarIndex();

	}

        /**
         * Reinicia el Hilo.
         */
	public void iniciarHilo() {
		if (!(hilo == null))
			hilo.setContinuar(false);
		this.hilo = null;
		this.hilo = new Hilo();
		this.hilo.setControlador(this);
		hilo.start();
	}

        /**
         * Actualiza el indice de los guardados.
         * @throws IOException 
         */
	public void actualizarIndex() throws IOException {
		System.out.println(Save);
		QuickSort sort = new QuickSort();
		Save = sort.quicksort(Save);
		Data.delete();
		Data.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(Data.getPath()));

		for (Pares line : Save) {
			bw.write(line.getLeft() + " " + line.getRight());
			bw.newLine();
			bw.flush();
		}

		bw.close();
	}

        /**
         * Depura el arbol, elimina los Tags que no tienen punteros a imagen.
         * @param Array
         * @param nodo 
         */
        public void depurarArbol(ArrayList<String> Array, NodoAVL nodo){
            if (nodo != null) {
                
			// De esta manera lo recorremos InOrden
			depurarArbol(Array, nodo.RightNode);

			Tag tag = (Tag) nodo.getValue();
                        
                        if(tag.getFotos().isEmpty() ){
                            this.Arbol.remove(nodo.getValue());
                            System.out.println("Balanceado: " + this.Arbol.balanceOfTree(nodo));
                        }
                        
			// Para las imagenes en el Tag del nodo actual...
                        Iterator<Imagen> iter = tag.getFotos().iterator();
			while (iter.hasNext()) {
                            Imagen imagen = iter.next();
				
				// Si la imagen no ha sido procesada...
				if (!(Array.contains(imagen.getURL()))) {

					Array.add(imagen.getURL());
				}
                                
				// Si la imagen ya habia sido procesada,
                                // la eliminamos de la lista del Tag
				else {
                                        iter.remove();
					System.out.println("Eliminar imagen");
				}                     
			}
                        // Si la lista de fotos del tag no esta vacia...
                        if(tag.getFotos().isEmpty() ){
                            this.Arbol.remove(nodo.getValue());
                            
                        }

                        // Finalmente va al nodo derecho
                        depurarArbol(Array, nodo.LeftNode);
		}
		
        }
        
	/**
	 * Re-instancia las Imagenes, Tags, el Save y el Arbol.
	 */
	private void reiniciarInformacion() {
		Imagenes = null;
		Imagenes = new ArrayList();
		Tags = null;
		Tags = new ArrayList();
		Save = null;
		Arbol = null;
		Arbol = new ArbolAVL();

	}

	/**
	 * Dependiendo del tipo de archivo ingresado, se decide que metodo seguir
	 * para obtener la inaformacion. Se da por hecho que ya ha sido validado el
	 * archivo ya que al principio de este metodo reinica todo.
	 * 
	 * @param Tipo
	 */
	public void procesarURL(TipoArchivo Tipo) {
		reiniciarInformacion();
		if (Tipo == Tipo.TXT) {
			procesarURL_TXT();
		}
	}

	/**
	 * Lee un archivo .txt y lo analiza para obtener de EL una lista de URL que
	 * seran usados para crear el Array de Imagenes (con una lista de instancias
	 * de tipo Imagen) y el Array de Tags(con una lista de instancias de tipo
	 * Tag).
	 */
	public void procesarURL_TXT() {
		ArrayList<String> ULRs = new FileManager().getLines(Album);
		int index = 0;
		Imagenes = new ArrayList();
		Tags = new ArrayList();
		while (index != ULRs.size()) {
			try {
				ArrayList info = getImageInfo(ULRs.get(index));

				System.out.println((String) info.get(3));
				System.out.println((String) info.get(0));
				System.out.println((String) info.get(1));
				System.out.println((String) info.get(2));

				Imagen img = new Imagen();
				img.setDescripcion((String) info.get(3));
				img.setURL(ULRs.get(index));
				Imagenes.add(img);

				Tags.add(new Tag((String) info.get(0), img));
				Tags.add(new Tag((String) info.get(1), img));
				Tags.add(new Tag((String) info.get(2), img));
			} finally {
				index++;
			}
		}

	}

	/**
	 * Toma el ArrayList de Tags y los agrega al Arbol para que ahi sean
	 * procesados.
	 */
	public void procesarTags() {
		for (Tag tag : Tags) {
			this.Arbol.insert(tag);
		}
                
                ArrayList<String> Array = new ArrayList();
                depurarArbol(Array, this.Arbol.getRoot());
                
	}

	/**
	 * Recibe un archivo y lo guarda como atributo del Controlador.
	 * 
	 * @param File
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void abrir(String nombre) throws ClassNotFoundException, IOException {
		BinarySearch search = new BinarySearch();
		int pos = search.busquedaBinaria(Save, nombre);
		if (pos != -1) {

			FileManager open = new FileManager();
			File file = new File((String) Save.get(pos).getRight());
			Arbol = (ArbolAVL) open.leerArchivo(file);
			hilo.start();
		}
	}

	

	public void limpiarPantalla() {
		System.out.println("Limpiar Pantalla");
	}

        /**
         * Usa el API de Microsoft y devuelve en un ArrayList la informacion
         * de la imagen.
         * Indices:
         *      0, 1, 2 :   Tags
         *      3:          Descripcion
         * @param URL
         * @return 
         */
	public ArrayList getImageInfo(String URL) {
		try {
			return Pictureinfo.getInfo(URL);
		} catch (StringIndexOutOfBoundsException e) {
			ArrayList<String> error = new ArrayList();
			error.add("Error");
			error.add("Error");
			error.add("Error");
			error.add("Error");
			return error;
		}
	}

        
        public File getAlbum() {
		return Album;
	}

	public void setAlbum(File album) {
		Album = album;
	}

	public Image cargarImagen(String URL) {
		return ImgManager.imageFromWebURL(URL);
	}

	public void nextImage() {
		System.out.println("Next...");
	}
        
	public ArbolAVL getArbol() {
		return Arbol;
	}

	public void setArbol(ArbolAVL Arbol) {
		this.Arbol = Arbol;
	}

	public Ui getUi() {
		return ui;
	}

	public void setUi(Ui ui) {
		this.ui = ui;
	}

	public Hilo getHilo() {
		return hilo;
	}

	public void setHilo(Hilo hilo) {
		this.hilo = hilo;
	}

        
	public File getData() {
		return Data;
	}

	public void setData(File data) {
		Data = data;
	}

	public ImageManager getImgManager() {
		return ImgManager;
	}

	public void setImgManager(ImageManager imgManager) {
		ImgManager = imgManager;
	}

	public ArrayList<Imagen> getImagenes() {
		return Imagenes;
	}

	public void setImagenes(ArrayList<Imagen> imagenes) {
		Imagenes = imagenes;
	}

	public ArrayList<Pares> getSave() {
		return Save;
	}

	public void setSave(ArrayList<Pares> save) {
		Save = save;
	}

	public ArrayList<Tag> getTags() {
		return Tags;
	}

	public void setTags(ArrayList<Tag> tags) {
		Tags = tags;
	}

        
}
