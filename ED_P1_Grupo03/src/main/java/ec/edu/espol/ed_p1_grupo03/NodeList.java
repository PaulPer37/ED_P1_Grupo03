/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.ed_p1_grupo03;

/**
 *
 * @author DHAMAR
 */
public class NodeList<E> {
    private E content;
    private NodeList<E> next;

    public NodeList(E content) {
        this.content = content;
        this.next = null;
    }

    public E getContent() {
        return content;
    }

    public NodeList<E> getNext() {
        return next;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public void setNext(NodeList<E> next) {
        this.next = next;
    }
}
