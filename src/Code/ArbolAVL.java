package Code;

import java.io.Serializable;

/**
 * Clase Arbol AVL que recibe una instancia Comparable y la agrega al arbol. La
 * clase es fenerica, se puede decir que se le puede agregar cualquier clase al
 * valor mientras implemente a Comparable. Redefiniendo el metodo
 * compareTo(Object o) y toString() funciona.
 * 
 * @author Malab
 */
public class ArbolAVL implements Serializable {
    
	private NodoAVL Root;

	public void insert(Comparable x) {
		Root = insert(x, Root);
	}

	/**
	 * 
	 * @param Valor
	 * @param NodoAInsertar
	 * @return
	 */
	private NodoAVL insert(Comparable Valor, NodoAVL NodoAInsertar) {
		if (NodoAInsertar == null) {
			NodoAInsertar = new NodoAVL(Valor);
		}

		// If Valor es menor al Nodo que vamos a insertar
		else if (Valor.compareTo(NodoAInsertar.getValue()) < 0) {
			NodoAInsertar.LeftNode = insert(Valor, NodoAInsertar.LeftNode);
			if (height(NodoAInsertar.LeftNode) - height(NodoAInsertar.RightNode) == 2)
				if (Valor.compareTo(NodoAInsertar.LeftNode.Value) < 0)
					NodoAInsertar = rotateWithLeftChild(
							NodoAInsertar); /* Caso 1 */
				else
					NodoAInsertar = doubleWithLeftChild(
							NodoAInsertar); /* Caso 2 */
		}

		// If Valor es mayor al Nodo que vamos a insertar
		else if (Valor.compareTo(NodoAInsertar.Value) > 0) {
			NodoAInsertar.RightNode = insert(Valor, NodoAInsertar.RightNode);
			if (height(NodoAInsertar.RightNode) - height(NodoAInsertar.LeftNode) == 2)
				if (Valor.compareTo(NodoAInsertar.RightNode.Value) > 0)
					NodoAInsertar = rotateWithRightChild(
							NodoAInsertar); /* Caso 4 */
				else
					NodoAInsertar = doubleWithRightChild(
							NodoAInsertar); /* Caso 3 */
		}

		// If Valor es igual al Nodo que vamos a insertar
		else {
			casoIgual(Valor, NodoAInsertar);
		}
		NodoAInsertar.Height = max(height(NodoAInsertar.LeftNode), height(NodoAInsertar.RightNode)) + 1;
		return NodoAInsertar;
	}

	/**
	 * Compara dos numeros y devuelve el mayor
	 * 
	 * @param FirstNumber
	 * @param SecondNumber
	 * @return
	 */
	private static int max(int FirstNumber, int SecondNumber) {
		if (FirstNumber > SecondNumber)
			return FirstNumber;
		else
			return SecondNumber;
	}

	private static NodoAVL rotateWithLeftChild(NodoAVL k2) {
		NodoAVL k1 = k2.LeftNode;
		k2.LeftNode = k1.RightNode;
		k1.RightNode = k2;
		k2.Height = max(height(k2.LeftNode), height(k2.RightNode)) + 1;
		k1.Height = max(height(k1.LeftNode), k2.Height) + 1;
		return k1;
	}

	private static NodoAVL rotateWithRightChild(NodoAVL k1) {
		NodoAVL k2 = k1.RightNode;
		k1.RightNode = k2.LeftNode;
		k2.LeftNode = k1;
		k1.Height = max(height(k1.LeftNode), height(k1.RightNode)) + 1;
		k2.Height = max(height(k2.RightNode), k1.Height) + 1;
		return k2;
	}

	private static NodoAVL doubleWithLeftChild(NodoAVL k3) {
		k3.LeftNode = rotateWithRightChild(k3.LeftNode);
		return rotateWithLeftChild(k3);
	}

	private static NodoAVL doubleWithRightChild(NodoAVL k1) {
		k1.RightNode = rotateWithLeftChild(k1.RightNode);
		return rotateWithRightChild(k1);
	}

	private static int height(NodoAVL Nodo) {
		if (Nodo == null) {
			return -1;
		} else {
			return Nodo.Height;
		}
	}

	/*
	 * Obtiene la altura del arbol AVL
	 */
	public int calcularAltura() {
		return calcularAltura(Root);
	}

	private int calcularAltura(NodoAVL actual) {
		if (actual == null)
			return -1;
		else
			return 1 + Math.max(calcularAltura(actual.LeftNode), calcularAltura(actual.RightNode));
	}

	public NodoAVL getRoot() {
		return Root;
	}

	public void setRoot(NodoAVL Root) {
		this.Root = Root;
	}

	public void casoIgual(Comparable Valor, NodoAVL NodoAInsertar) {
		((Tag) NodoAInsertar.getValue()). // Casteamos el Valor del Nodo a Tag
				getFotos(). // Obtenemos las fotos
				add(((Tag) Valor). // Agregamos...
						getFotos(). // la foto que viene con el Valor...
						get(0) // casteandolo a Tag.
		);

		System.out.println("\n\n Agregaremos una foto repetida...");
		// System.out.println(((Tag)Valor).getFotos().get(0));
		System.out.println("Para el tag: " + ((Tag) NodoAInsertar.getValue()).getTag());
		System.out.println(
				"Cantidad de fotos actuales en la lista: " + ((Tag) NodoAInsertar.getValue()).getFotos().size());

		System.out.println("Caso igual");
	}
        
        /**
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         * 
         */
        
        
        /**
   * Determine if the tree is empty.
   * 
   * @return True if the tree is empty 
   */
  public boolean isEmpty(){
    return (Root == null);
  }
        
    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public Comparable findMin( )
    {
        if( isEmpty( ) ) return null;

        return findMin( Root ).getValue();
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public Comparable findMax( )
    {
        if( isEmpty( ) ) return null;
        return findMax( Root ).getValue();
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    private NodoAVL findMin(NodoAVL t)
    {
        if( t == null )
            return t;

        while( t.LeftNode != null )
            t = t.LeftNode;
        return t;
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the tree.
     * @return node containing the largest item.
     */
    private NodoAVL findMax( NodoAVL t )
    {
        if( t == null )
            return t;

        while( t.RightNode != null )
            t = t.RightNode;
        return t;
    }


// A version of remove from http://www.dreamincode.net/forums/topic/214510-working-example-of-avl-tree-remove-method/
// but it needs some attention and does not appear to be 100% correct

  /**
   * Remove from the tree. Nothing is done if x is not found.
   * @param x the item to remove.
   */
  public void remove( Comparable x ) {
      Root = remove(x, Root);
  }

  public NodoAVL remove(Comparable x, NodoAVL t) {
      if (t==null)    {
          System.out.println("Sorry but you're mistaken, " + t + " doesn't exist in this tree :)\n");
          return null;
      }
      System.out.println("Remove starts... " + t.getValue() + " and " + x);
  
      if (x.compareTo(t.getValue()) < 0 ) {
          t.LeftNode = remove(x,t.LeftNode);
          int l = t.LeftNode != null ? t.LeftNode.Height : 0;
  
          if((t.RightNode != null) && (t.RightNode.Height - l >= 2)) {
              int rightHeight = t.RightNode.RightNode != null ? t.RightNode.RightNode.Height : 0;
              int leftHeight = t.RightNode.LeftNode != null ? t.RightNode.LeftNode.Height : 0;
  
              if(rightHeight >= leftHeight)
                  t = rotateWithLeftChild(t);            
              else
                  t = doubleWithRightChild(t);
          }
      }
      else if (x.compareTo(t.getValue()) > 0) {
          t.RightNode = remove(x,t.RightNode);
          int r = t.RightNode != null ? t.RightNode.Height : 0;
          if((t.LeftNode != null) && (t.LeftNode.Height - r >= 2)) {
              int leftHeight = t.LeftNode.LeftNode != null ? t.LeftNode.LeftNode.Height : 0;
              int rightHeight = t.LeftNode.RightNode != null ? t.LeftNode.RightNode.Height : 0;
              if(leftHeight >= rightHeight)
                  t = rotateWithRightChild(t);               
              else
                  t = doubleWithLeftChild(t);
          }
      }
      /*
         Here, we have ended up when we are node which shall be removed. 
         Check if there is a LeftNode-hand node, if so pick out the largest getValue() out, and move down to the Root.
       */
      else if(t.LeftNode != null) {
          t.setValue(findMax(t.LeftNode).getValue()) ;
          remove(t.getValue(), t.LeftNode);
       
          if((t.RightNode != null) && (t.RightNode.Height - t.LeftNode.Height >= 2)) {
              int rightHeight = t.RightNode.RightNode != null ? t.RightNode.RightNode.Height : 0;
              int leftHeight = t.RightNode.LeftNode != null ? t.RightNode.LeftNode.Height : 0;
       
              if(rightHeight >= leftHeight)
                  t = rotateWithLeftChild(t);            
              else
                  t = doubleWithRightChild(t);
          }
      }
       
      else
          t = (t.LeftNode != null) ? t.LeftNode : t.RightNode;
       
      if(t != null) {
          int leftHeight = t.LeftNode != null ? t.LeftNode.Height : 0;
          int rightHeight = t.RightNode!= null ? t.RightNode.Height : 0;
          t.Height = Math.max(leftHeight,rightHeight) + 1;
      }
      return t;
  } //End of remove...

  /**
   * Search for an getValue() within the tree. 
   *
   * @param x Element to find
   * @param t Root of the tree
   * @return True if the getValue() is found, false otherwise
   */
  public boolean contains(Comparable x){
    return contains(x, Root); 
  }

  /**
   * Internal find method; search for an getValue() starting at the given node.
   *
   * @param x Element to find
   * @param t Root of the tree
   * @return True if the getValue() is found, false otherwise
   */
  protected boolean contains(Comparable x, NodoAVL t) {
    if (t == null){
      return false; // The node was not found

    } else if (x.compareTo(t.getValue()) < 0){
      return contains(x, t.LeftNode);
    } else if (x.compareTo(t.getValue()) > 0){
      return contains(x, t.RightNode); 
    }

    return true; // Can only reach here if node was found
  }
  
  /***********************************************************************/
  // Diagnostic functions for the tree
  public boolean balanceOfTree(NodoAVL current) {
    
    boolean balancedRight = true, balancedLeft = true;
    int leftHeight = 0, rightHeight = 0;
    
    if (current.RightNode != null) {
      balancedRight = balanceOfTree(current.RightNode);
      rightHeight = getDepth(current.RightNode);
    }
    
    if (current.LeftNode != null) {
      balancedLeft = balanceOfTree(current.LeftNode);
      leftHeight = getDepth(current.LeftNode);
    }
    
    return balancedLeft && balancedRight && Math.abs(leftHeight - rightHeight) < 2;
  }
  
  public int getDepth(NodoAVL n) {
    int leftHeight = 0, rightHeight = 0;
    
    if (n.RightNode != null)
      rightHeight = getDepth(n.RightNode);
    if (n.LeftNode != null)
      leftHeight = getDepth(n.LeftNode);
    
    return Math.max(rightHeight, leftHeight)+1;
  }
  
  public boolean checkOrderingOfTree(NodoAVL current) {
    if(current.LeftNode != null) {
      if(current.LeftNode.getValue().compareTo(current.getValue()) > 0)
        return false;
      else
        return checkOrderingOfTree(current.LeftNode);
    } else  if(current.RightNode != null) {
      if(current.RightNode.getValue().compareTo(current.getValue()) < 0)
        return false;
      else
        return checkOrderingOfTree(current.RightNode);
    } else if(current.LeftNode == null && current.RightNode == null)
      return true;
    
    return true;
  }
        
        
        
	// ########################################
	// ########################################
	// ############## ##################
	// ############# Borrar #################
	// ############## ##################
	// ########################################
	// ########################################
	// ########################################

	/*
	 * Imprime el arbol con el recorrido InOrden
	 */
	public void imprimir() {
		imprimir(Root);
	}

	private void imprimir(NodoAVL nodo) {
		if (nodo != null) {
			imprimir(nodo.RightNode);
			System.out.println("[" + nodo.Value + "]");
			imprimir(nodo.LeftNode);
		}
	}

	public void imprimirXaltura() {
		imprimirXaltura(Root);
	}

	/*
	 * Imprime cada nodo linea por linea. Recorriendo el arbol desde el Nodo
	 * más a la derecha hasta el nodo más a la izquierda, y dejando una
	 * identacion de varios espacios en blanco segun su altura en el arbol
	 */
	private void imprimirXaltura(NodoAVL nodo) {
		if (nodo != null) {
			imprimirXaltura(nodo.RightNode);
			System.out.println(replicate("        ", height(Root) - height(nodo)) + "[" + nodo.Value + "]\n");
			imprimirXaltura(nodo.LeftNode);
		}
	}

	/*
	 * Metodo estatico auxiliar que dada una cadena a y un enterto cnt replica o
	 * concatena esa cadena a, cnt veces
	 */
	private static String replicate(String a, int cnt) {
		String x = new String("");

		for (int i = 0; i < cnt; i++)
			x = x + a;
		return x;
	}

	// Imprime el arbol por niveles. Comienza por la raiz.
	public void imprimirPorNiveles() {
		imprimirPorNiveles(Root);
	}

	// Imprime el arbol por niveles.
	private void imprimirPorNiveles(NodoAVL nodo) {
		// Mediante la altura calcula el total de nodos posibles del árbol
		// Y crea una array cola con ese tamaño
		int max = 0;
		int nivel = calcularAltura();

		for (; nivel >= 0; nivel--)
			max += Math.pow(2, nivel);
		max++; // Suma 1 para no utilizar la posicion 0 del array

		NodoAVL cola[] = new NodoAVL[max];

		// Carga en la pos 1 el nodo raiz
		cola[1] = nodo;
		int x = 1;

		// Carga los demas elementos del arbol,
		// Carga null en izq y der si el nodo es null
		// i aumenta de a 2 por q carga en izq y der los hijos
		// x aumenta 1, que son los nodos raiz - padre
		for (int i = 2; i < max; i += 2, x++) {
			if (cola[x] == null) {
				cola[i] = null;
				cola[i + 1] = null;
			} else {
				cola[i] = cola[x].LeftNode;
				cola[i + 1] = cola[x].RightNode;
			}
		}
		nivel = 0;
		int cont = 0; // contador para cada nivel
		int cantidad = 1; // cantidad de nodos por nivel
		int ultimaPosicion = 1; // ultimaPosicion del nodo en la cola de cada
								// nivel

		// Cuando i es = a 2^nivel hay cambio de nivel
		// 2 ^ 0 = 1 que es el nodo raiz
		for (int i = 1; i < max; i++) {
			if (i == Math.pow(2, nivel)) {
				// Nodo raiz tiene nivel 1, por eso (nivel + 1)
				System.out.print("\n Nivel " + (nivel) + ": ");
				nivel++;
			}
			if (cola[i] != null) {
				System.out.print("[" + cola[i].Value + "]");
				cont++;
			}
			if (ultimaPosicion == i && cantidad == Math.pow(2, --nivel)) {
				if (cantidad == 1)
					System.out.print(" Cantidad de nodos: " + cont + " (raiz)");
				else
					System.out.print(" Cantidad de nodos: " + cont);
				cont = 0;
				cantidad *= 2;
				ultimaPosicion += (int) Math.pow(2, ++nivel);
			}
		}
	}
}