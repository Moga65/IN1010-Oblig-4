import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class Lenkeliste<E> implements Liste<E>{
    Node foerst = null;
    Node sist = null;
    int stoerrelse = 0; 
    
    public class Node {
        Node neste = null;
        Node forrige = null;
        E info;

        public Node(E info) {
            this.info = info;
        }

        public int compareTo(Lenkeliste<E>.Node hjelpe_peker) {
            return 0;
        }

        
    }

    class ListeIterator implements Iterator <E>{
        private Node denne;

        public ListeIterator(){
            denne = foerst;
        }
        
        @Override 
        public boolean hasNext(){
            if(denne == null){
                return false;
            }
            else{
                return true;
            }
        }

        @Override
        public E next() {
            if (!hasNext()){
                return denne.info;
            }
            E neste_2E = denne.info;
            denne = denne.neste;
            return neste_2E;
        }
    }
    
    public Iterator<E> iterator(){
        return new ListeIterator();
    }
    
    public int stoerrelse() {
        return stoerrelse;
    }
    
    public void leggTil(E x) {
        Node ny_node = new Node(x);
        if (stoerrelse == 0) {
            foerst = ny_node;
            sist = ny_node;
        } else {
            sist.neste = ny_node;
            sist = ny_node;
        }
        stoerrelse++;
    }
    
    public E hent() {
        if (foerst == null) {
            return null;
        }
        return foerst.info;
    }

    public E fjern() throws UgyldigListeindeks {
        if (stoerrelse == 0) {
            throw new UgyldigListeindeks(0);
        }
        E data = foerst.info;
        if (stoerrelse == 1) {
            foerst = null;
            sist = null;
        } else {
            Node node = foerst;
            foerst = node.neste;
        }
        stoerrelse--;
        return data;
    }

    public String toString() {
        String skriver_ut = "";
        Node string = foerst;
        while (string != null) {
            skriver_ut += string.info + " ";
            string = string.neste;
        }
        return skriver_ut;
    }


}