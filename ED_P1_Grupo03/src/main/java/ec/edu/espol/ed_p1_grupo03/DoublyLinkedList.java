/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.ed_p1_grupo03;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author RUCO HOUSE
 */
public class DoublyLinkedList<E> implements List<E> {

    private DoublyNodeList<E> header;
    private DoublyNodeList<E> last;

    public DoublyLinkedList() {
        this.header = null;
        this.last = null;
    }

    public boolean isEmpty() {
        return header == null && last == null;
    }

    public int size() {
        int count = 0;
        DoublyNodeList<E> p = header;
        while (p != null) {
            count++;
            p = p.getNext();
        }
        return count;
    }

    public DoublyNodeList<E> getHeader() {
        return header;
    }

    public void setHeader(DoublyNodeList<E> header) {
        this.header = header;
    }

    public DoublyNodeList<E> getLast() {
        return last;
    }

    public void setLast(DoublyNodeList<E> last) {
        this.last = last;
    }

    public boolean addFirst(E e) {
        if (e != null) {
            DoublyNodeList<E> newNode = new DoublyNodeList<>(e);
            newNode.setNext(header);
            header.setPrevious(newNode);
            this.setHeader(newNode);
            return true;
        }
        return false;
    }

    public boolean addLast(E e) {
        if (e != null) {
            DoublyNodeList<E> newNode = new DoublyNodeList<>(e);
            newNode.setPrevious(last);
            last.setNext(newNode);
            this.setLast(newNode);
            return true;
        }
        return false;
    }

    public boolean addAt(E e, int pos) {
        if (e != null && pos >= 0 && pos < this.size()) {
            DoublyNodeList<E> newNode = new DoublyNodeList<>(e);

            DoublyNodeList<E> p = header;

            for (int i = 0; i < pos; i++) {
                p = p.getNext();
            }
            newNode.setNext(p.getNext());
            p.setNext(newNode);

            newNode.setPrevious(p);
            newNode.getNext().setPrevious(newNode);

        }
        return false;
    }

    public E removeElement(int pos) {
        if (pos < 0 || pos >= this.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        DoublyNodeList<E> p = header;

        for (int i = 0; i < pos; i++) {
            p = p.getNext();
        }

        if (p == header) {
            // Removing the first element
            header = p.getNext();
            if (header != null) {
                header.setPrevious(null);
            } else {
                // List is now empty
                last = null;
            }
        } else if (p == last) {
            // Removing the last element
            last = p.getPrevious();
            if (last != null) {
                last.setNext(null);
            } else {
                // List is now empty
                header = null;
            }
        } else {
            // Removing an element in the middle
            p.getPrevious().setNext(p.getNext());
            p.getNext().setPrevious(p.getPrevious());
        }

        p.setNext(null);
        p.setPrevious(null);

        return p.getContent();
    }

    @Override
    public E removeLast() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E get(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            DoublyNodeList<E> current = header;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    return null;
                }
                E data = current.getContent();
                current = current.getNext();
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove operation is not supported.");
            }
        };
        return it;
    }

    @Override
    public void removeFirst() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
