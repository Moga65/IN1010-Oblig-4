public class Prioritetskoe<E extends Comparable<E>> extends Lenkeliste<E> {
    public void leggTil(E x) {
        Node ny_node = new Node(x);
        if (stoerrelse == 0) {
            foerst = ny_node;
            sist = ny_node;
        } else if (ny_node.info.compareTo(foerst.info) < 0) {
            ny_node.neste = foerst;
            foerst = ny_node;
        } else {
            Node hjelpe_peker = foerst;
            while (hjelpe_peker.neste != null && ny_node.info.compareTo(hjelpe_peker.neste.info) > 0) {
                hjelpe_peker = hjelpe_peker.neste;
            }
            ny_node.neste = hjelpe_peker.neste;
            hjelpe_peker.neste = ny_node;
            if (ny_node.neste == null) {
                sist = ny_node;
            }
        }
        stoerrelse++;
    }
}