public class MyEdgeLinkedList{
    protected GraphEdge headEdge;
    protected int sizeLinkedLGE;

    public void insertEdge(GraphEdge edge){
        edge.nextEdge = this.headEdge;
        if (this.headEdge != null) {
            this.headEdge.prevEdge = edge;
        }
        this.headEdge = edge;
        edge.prevEdge = null;
        sizeLinkedLGE++;
    }

    public void deleteEdge(GraphEdge edge) {
        if (edge.prevEdge != null) {
            edge.prevEdge.nextEdge = edge.nextEdge;
        } else {
            this.headEdge = edge.nextEdge;
        }
        if (edge.nextEdge != null) {
            edge.nextEdge.prevEdge = edge.prevEdge;
        }
        sizeLinkedLGE--;
    }

    public void insertEdgeInGraph(GraphEdge edge){
        edge.nextEdgeInGraph = this.headEdge;
        if (this.headEdge != null) {
            this.headEdge.prevEdgeInGraph = edge;
        }
        this.headEdge = edge;
        edge.prevEdgeInGraph = null;
        sizeLinkedLGE++;
    }

    public void deleteEdgeInGraph(GraphEdge edge) {
        if (edge.prevEdgeInGraph != null) {
            edge.prevEdgeInGraph.nextEdgeInGraph = edge.nextEdgeInGraph;
        } else {
            this.headEdge = edge.nextEdgeInGraph;
        }
        if (edge.nextEdgeInGraph != null) {
            edge.nextEdgeInGraph.prevEdgeInGraph = edge.prevEdgeInGraph;
        }
        sizeLinkedLGE--;
    }
}
