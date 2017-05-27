/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Code.*;
import Ui.*;
import javax.swing.SwingUtilities;
//import Ui.*;
      

/**
 *
 * @author Malab
 */
public class Principal {

    public static Control MyControl = new Control();

    public Principal() {
        MyControl = new Control();
        MyControl.iniciarUi();
        
        
    }

    
    
    
    
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new Principal();
        });
        /*
        
        ArbolAVL arbolAVL = new ArbolAVL();
        String[] Descripcion = {"Descripcion1", "Descripcion2", "Descripcion3",
                 "Descripcion4","Descripcion5","Descripcion6","Descripcion7",
                 "Descripcion8","Descripcion9",
                "Descripcion10","Descripcion11","Descripcion12","Descripcion13","Descripcion14",
                "Descripcion15","Descripcion16"};
        
        String[] URL = {"URL1", "URL2", "URL3",
                 "URL4","URL5","URL6","URL7","URL8","URL9",
                "URL10","URL11","URL12","URL13","URL14",
                "URL15","URL16"};
        
        String[] Tags = {"Cheese", "Pepperoni", "Black Olives",
                 "Alejandro","Mujer","Vestida","Mae","Caminando","Paisaje",
                "Verde","Sentados","Cargando","Mujer","Peinandose",
                "Azul","Mujer"};
        int i = 0;
        for (String s : Tags){
            Tag elemento1 = new Tag(s);
            elemento1.agregarFoto(Descripcion[i],URL[i]);
            arbolAVL.insert(elemento1);
            i++;
        }
        


        
        arbolAVL.imprimirPorNiveles();
        System.out.println("\n");
        arbolAVL.imprimirXaltura();
        System.out.println("\n");
        arbolAVL.imprimir();
        
        Tag.recorrerInOrden(arbolAVL.getRoot());
        
        
        System.out.println("\n");

        
        
        
        
*/
    }

    
    
}
