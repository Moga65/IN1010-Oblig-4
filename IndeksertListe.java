class IndeksertListe <E> extends Lenkeliste<E> {
    
    public void leggTil(int pos, E x) {
        if (pos < 0 || pos > stoerrelse()) {
            throw new UgyldigListeindeks(pos);
        }
        Node ny_node = new Node(x);
        if (stoerrelse == 0) {
            foerst = ny_node;
            sist = ny_node;
        } else if (pos == 0) {
            ny_node.neste = foerst;
            foerst = ny_node;
        } else if (pos == stoerrelse()) {
            sist.neste = ny_node;
            sist = ny_node;
        } else {
            Node pointer = foerst;
            for (int i = 0; i < pos - 1; i++) {
                pointer = pointer.neste;
            }
            ny_node.neste = pointer.neste;
            pointer.neste = ny_node;
        }
        stoerrelse++;
    }
    public void sett(int pos, E x) {
        if (pos < 0 || pos >= stoerrelse()) {
            throw new UgyldigListeindeks(pos);
        }
        Node node_2 = foerst;
        for (int i = 0; i < pos; i++) {
            node_2 = node_2.neste;
        }
            node_2.info = x;
        }

    public E hent(int pos) {
        if (pos < 0 || pos >= stoerrelse()) {
            throw new UgyldigListeindeks(pos);
        }
        if (foerst == null){
            return null;
        }
        Node ny_node = foerst;
        for (int i = 0; i < pos; i++) {
            ny_node = ny_node.neste;
        }
        return ny_node.info;
    }

    public E fjern(int pos) {
        if (pos < 0 || pos >= stoerrelse()) {
            throw new UgyldigListeindeks(pos);
        }
       
        if (foerst == null) {
            return null;
        }
        Node ny_node_4 = foerst; 
        if (pos == 0) {
            foerst = foerst.neste;
            stoerrelse--;
            return ny_node_4.info;
        }
        Node forrige_node = null;
        for (int i = 0; i < pos; i++) {
            forrige_node = ny_node_4;
            ny_node_4 = ny_node_4.neste;
        }
        if (ny_node_4 == sist) {
            sist = forrige_node;
        }
        forrige_node.neste = ny_node_4.neste;
        stoerrelse--;
        return ny_node_4.info;
        }       
    }