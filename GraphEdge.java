public class GraphEdge{
    protected GraphEdge nextEdge;
    protected GraphEdge prevEdge;
    protected GraphNode from;
    protected GraphNode to;

    protected GraphEdge nextEdgeInGraph;
    protected GraphEdge prevEdgeInGraph;

    public GraphEdge(GraphNode from, GraphNode to) {
        this.from = from;
        this.to = to;
    }
}
