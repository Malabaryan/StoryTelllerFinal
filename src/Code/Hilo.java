/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Code;

import Controller.Control;
import Ui.Ui;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bryan H.O.
 */
public class Hilo extends Thread implements KeyListener {

	public boolean Continuar;
	public Control Controlador;

	/**
	 * Default Constructor
	 * 
	 */
	public Hilo() {
		Continuar = true;
	}

        @Override
	public void run() {

		try {
			this.recorrerInOrden(this.Controlador.getArbol().getRoot());
		} catch (IOException ex) {
			Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void ONOFF() {
            Continuar = !Continuar;
	}

	public Control getControlador() {
		return Controlador;
	}

	public void setControlador(Control Controlador) {
		this.Controlador = Controlador;
	}

	public boolean isContinuar() {
		return Continuar;
	}

	public void setContinuar(boolean Continuar) {
		this.Continuar = Continuar;
	}

	public void recorrerInOrden(NodoAVL nodo) throws IOException {
		ArrayList<String> Array = new ArrayList();
		this.recorrerInOrden(Array, nodo);
	}

	private int recorrerInOrden(ArrayList<String> Array, NodoAVL nodo) throws IOException {
		if (nodo != null) {
			// De esta manera lo recorremos InOrden
			recorrerInOrden(Array, nodo.RightNode);

			Tag tag = (Tag) nodo.getValue();
			// Para las imagenes en el Tag del nodo actual...
			for (Imagen imagen : tag.getFotos()) {
				// Revisamos si aun se tiene que continuar...
				while(!Continuar) {
					try {Thread.sleep(500);} 
                                        catch (InterruptedException ex) {
						System.out.println("Biiip");}}
                                
				// Si la imagen no ha sido mostrada en pantalla...
				if (!(Array.contains(imagen.getURL()))) {
					System.out.println("No Repetido!");
					System.out.println("    " + imagen.toString());
					Array.add(imagen.getURL());

					// Esperamos 4 segundos...
					try {
						Thread.sleep(4000);
					} catch (InterruptedException ex) {
						System.out.println("Biiip");
					}
                                        // Revisamos si aun se tiene que continuar...
                                        while(!Continuar) {
					try {Thread.sleep(500);} 
                                        catch (InterruptedException ex) {
						System.out.println("Biiip");}}
                                

					System.out.println("Imagen a imprimir: " + imagen.getURL());
					if (this.Controlador.getUi() == null)
						System.out.println("Ui nula");
					try {
						// Pintamos la imagen en pantalla...
						this.Controlador.getUi().actualizarDescripcion(imagen.getDescripcion());
						this.Controlador.getUi().actualizarTag(((Tag)nodo.getValue()).getTag());
						this.Controlador.getUi().pintar(imagen.getURL());
					} catch (Exception e) {
						System.out.println("Error al obtener la imagen de la red y pintarla");
					}
					break;

				}
				// Si la imagen ya habia sido procesada, solo la omitimos
				else {

					if (Array == null)
						System.out.println("Array Nulo");
					if (imagen == null)
						System.out.println("Imagen null");

					System.out.println("Repetido: " + imagen.toString());

				}

			}
			recorrerInOrden(Array, nodo.LeftNode);
		}
		return 1;
	}

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.ONOFF();
        System.out.println("Una tecla ha sido Typeado");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.ONOFF();
        System.out.println("Una tecla ha sido Prsionada");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.ONOFF();
        System.out.println("Una tecla ha sido Soltada");
    }

}
