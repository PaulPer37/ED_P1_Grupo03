/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.ed_p1_grupo03;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author DHAMAR
 */
public class LinkedList<E> implements List<E>{
    private NodeList<E> header;
    private NodeList<E> last;
    //private int size = 0;
    
    public LinkedList(){
        this.header=null;
        this.last=null;
    }
    public NodeList<E> getHeader() {
        return header;
    }

    public void setHeader(NodeList<E> header) {
        this.header = header;
    }

    public NodeList<E> getLast() {
        return last;
    }

    public void setLast(NodeList<E> last) {
        this.last = last;
    }

    @Override
    public boolean addFirst(E e) { //dado un elemento generico
        //size++;
        if(e!=null) {
            NodeList<E> newNode=new NodeList<>(e); // constructor crea un nodo aislado
            newNode.setNext(header); //El siguiente de ese nuevo nodo es Header
            this.setHeader(newNode); //actualizar el header de la lista
            return true;
        } else {
            return false;
        }
    }
    @Override
    public boolean addLast(E e) {
        //size++;
        if(e!=null){
            NodeList<E> newNode = new NodeList<>(e);
            if(last !=null){
                last.setNext(newNode);
            }
            last=newNode;
            
            if(header == null){
                header = newNode;
            }
            return true;
        } else {
            return false;
        }  
    }
    
    private NodeList<E> getPrevious(NodeList<E> node){
    
        if (node == null || header == null) {
        return null;  // No tiene sentido buscar en una lista vacía o para un nodo null
    }
        NodeList<E> previous = null;
        NodeList<E> n;
        
        for(n = this.header; n!=node  ; n= n.getNext()){
            previous = n;
        }
        return previous;
    }
    
    private void recorrerHaciAtras(){
        NodeList<E> n;
        //complejidad cuadratica
        for (n = last; n != header; n = this.getPrevious(n)){
            System.out.println(n);
        }
    }
    
    @Override
    public void removeFirst() {
       if(header != null){
           NodeList<E> nodo = header;
           setHeader(nodo.getNext());
           nodo.setNext(null);
           
       }

    }

    @Override
   public E removeLast() {
    if (last == null) {
        // La lista está vacía, no hay nada que eliminar
        return null;
    }

    if (header == last) {
        // Si solo hay un elemento en la lista, eliminarlo
        E content = last.getContent();
        header = null;
        last = null;
        return content;
    }

    // Obtener el nodo anterior al último
    NodeList<E> previous = getPrevious(last);

    // Guardar el contenido del último nodo
    E content = last.getContent();

    // Actualizar last para que apunte al nodo anterior
    last = previous;

    // Establecer el siguiente del nodo anterior a null
    last.setNext(null);

    return content;
    }

    @Override
    public int size() {
        // return size; // O(1)
        // primera parte declaro variables a iterar
        // segunda parte condicion logica
        // tercera parte instrucciones
        //for (int i=0, j=0; i < 10 && j >8; i++){
        //for (NodeList<E> n = header; n ! = null ; n = n.getNext()){
        
        // Complejidad lineal O(n) a pesar de que no es tan buena como O(1) evita que tenga que darle mantenimiento a size
        int size = 0;
        NodeList<E> n; //declaro un nodo viajero
        //declaro un nodo viajero, mientras sea diferente de null, se mueve al siguiente nodo
        
        for (n = header ; n!= null ; n = n.getNext( )) {
            size++;
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return header == null;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E remove(int index) {
         if (header == null) {
        // La lista está vacía, no hay nada que eliminar
        return null;
        }
        // Caso especial: Eliminar el primer elemento
        if (index == 0) {
            E content = header.getContent();
            header = header.getNext();
            return content;
        }
        NodeList<E> current = header;
        int currentIndex = 0;
        // Avanzar hasta el nodo anterior al índice que queremos eliminar
        while (current != null && currentIndex < index - 1) {
            current = current.getNext();
            currentIndex++;
        }
        // Si current es null o no encontramos el índice válido, retornar null o manejar según necesites
        if (current == null || current.getNext() == null) {
            return null;
        }
        // Guardar el contenido del nodo que se va a eliminar
        E content = current.getNext().getContent();
        // Eliminar el nodo actualizando los enlaces
        current.setNext(current.getNext().getNext());
        return content;
    }

    @Override
    public E get(int index) {
     
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }

        NodeList<E> current = header;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getContent();
    
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public String toString() {
        String s="";
        for (NodeList<E> n=header; n!=null; n=n.getNext()) {
            s+=n.getContent()+", ";
        }
        return s;
    }
    
    public Iterator<E> iterator(){
        Iterator<E> it=new Iterator<E>() {
                NodeList<E> cursor = header;
                
            @Override
            public boolean hasNext() {
                return cursor != null;
            }

            @Override
            public E next() {
                E e = cursor.getContent();
                cursor = cursor.getNext();
                return e;
            }
        };
        
        
        return it;
    }    
    public void remove(E value) {
        if (header.getContent().equals(value)) {
            header = header.getNext();
            return;
        }

        NodeList<E> nodo = header;
        while (nodo.getNext() != null) {
            if (nodo.getNext().getContent().equals(value)) {
                nodo.setNext(nodo.getNext().getNext());
                return;
            }
            nodo = nodo.getNext();
        }
    }
    /*

    @Override
    public E find(Comparator comp, E elemento) {
        if(elemento == null){
            return null;
        }
        NodeList<E> cursor = header; 
        while(cursor!=null){
            if(comp.compare(cursor.getContent(), elemento)==0){
                return cursor.getContent();   
            }else{
                cursor = cursor.getNext();
            }
        }
        return null;
        
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        List.super.forEach(action); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Spliterator<E> spliterator() {
        return List.super.spliterator(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    public LinkedList<E> findAll(Comparator comp, LinkedList<E>  lista){
        LinkedList<E> repetidos = new LinkedList<E>();
        for(NodeList<E> n1 = this.header; n1!=null;n1 = n1.getNext()){
            
            for(NodeList<E> n2 = lista.header; n2!=null; n2 = n2.getNext()){
                if(comp.compare(n1.getContent(), n2.getContent())==0){
                    repetidos.addFirst(n2.getContent());
                    
                }
               
            }
  
        }
        return repetidos;
    }
*/
    LinkedList sublist(int start, int end) {
        if (start < 0 || end < start) {
            throw new IllegalArgumentException("Índices de inicio o fin inválidos");
        }

        LinkedList sublist = new LinkedList();
        NodeList current = header;
        int currentIndex = 0;

        while (current != null && currentIndex <= end) {
            if (currentIndex >= start && currentIndex <= end) {
                sublist.addLast(current.getContent());
            }
            current = current.getNext();
            currentIndex++;
        }

        return sublist;
    }
    public void sort(Comparator<? super E> comparator) {
        if (header == null || header == last) {
            return;
        }

        boolean swapped;
        NodeList<E> left;
        NodeList<E> right = null;

        do {
            swapped = false;
            left = header;

            while (left.getNext() != right) {
                if (comparator.compare(left.getContent(), left.getNext().getContent()) > 0) {
                    // Swap nodes
                    E temp = left.getContent();
                    left.setContent(left.getNext().getContent());
                    left.getNext().setContent(temp);
                    swapped = true;
                }
                left = left.getNext();
            }
            right = left;
        } while (swapped);
    }
    public LinkedList<E> copy() {
        LinkedList<E> newList = new LinkedList<>();
        NodeList<E> current = this.header;

        while (current != null) {
            newList.addLast(current.getContent());
            current = current.getNext();
        }

        return newList;
    }
}
