public class GraphNode {
    //Node's attributes in Graph
    protected int nodeKey;
    protected MyEdgeLinkedList myEdgeLinkedList;
    protected GraphNode nextNode;
    protected GraphNode prevNode;
    protected int inDegree;
    protected int outDegree;

    //Node's attributes in Queue
    protected GraphNode nextInQueue;
    protected GraphNode prevInQueue;

    //Node's attributes in tree
    protected GraphNode leftChild;
    protected GraphNode rightSibling;
    protected GraphNode parent;
    protected Color color;
    protected int currentPositionInTree;

    //Node's attributes in Bfs
    protected int distance;

    //Node's attributes in Dfs
    protected int discoveryTime;
    protected int refractionTime;
    protected int indexNodeByRefractionTime;
    protected MyEdgeLinkedList myTransposeEdgeLinkedList;



    public GraphNode(int nodeKey) {
        this.nodeKey = nodeKey;
        myEdgeLinkedList = new MyEdgeLinkedList();
        myTransposeEdgeLinkedList = new MyEdgeLinkedList();
        this.currentPositionInTree = -1;
    }

    public int getOutDegree() {
        return this.outDegree;
    }

    public int getInDegree() {
        return this.inDegree;
    }

    public int getKey() {
        return this.nodeKey;
    }

}
