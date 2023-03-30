public  class Stabel <E> extends Lenkeliste<E> {
    @Override
    public void leggTil(E x){
        Node node_1 = new Node(x);
        node_1.neste = foerst; 
        foerst = node_1;
        stoerrelse++;
    }
}
