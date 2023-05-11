public class DynamicGraph {
    MyNodeLinkedList myNodeLinkedList;
    MyEdgeLinkedList myEdgeLinkedListInGraph;

    protected int numberOfNodesDG;
    protected int numberOfEdgesDG;

    //Dfs
    protected GraphNode[] dfsXi;
    protected GraphNode[] dfsXiSortedByRT;
    protected int time = 0;

    public DynamicGraph() {
        myNodeLinkedList = new MyNodeLinkedList();
        myEdgeLinkedListInGraph = new MyEdgeLinkedList();
    }

    public GraphNode insertNode(int nodeKey) {
        GraphNode node = new GraphNode(nodeKey);
        myNodeLinkedList.insertMyNode(node);
        this.numberOfNodesDG++;
        return node;
    }

    public void deleteNode(GraphNode node) {
        if (node.inDegree == 0 && node.outDegree == 0) {
            myNodeLinkedList.deleteMyNode(node);
            this.numberOfNodesDG--;
        }
    }

    public GraphEdge insertEdge(GraphNode from, GraphNode to) {
        GraphEdge edge = new GraphEdge(from, to);
        from.outDegree++;
        to.inDegree++;
        from.myEdgeLinkedList.insertEdge(edge);
        this.myEdgeLinkedListInGraph.insertEdgeInGraph(edge);
        this.numberOfEdgesDG++;
        return edge;
    }

    public void deleteEdge(GraphEdge edge) {
        edge.from.myEdgeLinkedList.deleteEdge(edge);
        edge.from.outDegree = edge.from.outDegree - 1;
        edge.to.inDegree = edge.to.inDegree - 1;
        this.numberOfEdgesDG--;
        this.myEdgeLinkedListInGraph.deleteEdgeInGraph(edge);
    }


    public RootedTree bfs(GraphNode source){
        MyQueue queue = new MyQueue();
        bfsInitialization(source, queue);
        RootedTree rootedTree = new RootedTree();
        rootedTree.setRoot(source);
        while (queue.linkedListOfQueue.sizeLinkedLGN != 0) {
            rootedTree.numberKnodesInTree++;
            GraphNode node = queue.dequeue();
            node.currentPositionInTree = rootedTree.positionOfTheNodesInTree++;
            GraphEdge currentEdge = node.myEdgeLinkedList.headEdge;
            boolean isFirstChild = true;
            boolean isRightSibling = false;
            GraphNode tempNode = null;
            for (int i = 0; i < node.myEdgeLinkedList.sizeLinkedLGE; i++) {
                if (currentEdge.to.color == Color.White) {
                    currentEdge.to.color = Color.Grey;
                    currentEdge.to.distance = node.distance + 1;
                    currentEdge.to.parent = node;

                    if (isRightSibling){
                        tempNode.rightSibling = currentEdge.to;
                    }
                    if (currentEdge.to != null && !isRightSibling){
                        currentEdge.from.leftChild  = currentEdge.to;
                        isRightSibling = true;
                    }
                    tempNode = currentEdge.to;
                    queue.enqueue(currentEdge.to);
                }
                currentEdge = currentEdge.nextEdge;
            }
            node.color = Color.Black;
        }
        return rootedTree;
    }

    public void bfsInitialization(GraphNode source, MyQueue queue) {
        GraphNode currentNode = this.myNodeLinkedList.headNode;
        while (currentNode != null) {
            currentNode.color = Color.White;
            currentNode.distance = Integer.MAX_VALUE;
            currentNode.parent = null;
            currentNode.leftChild = null;
            currentNode.rightSibling = null;
            currentNode.currentPositionInTree = -1;
            currentNode.nextInQueue = null;
            currentNode.prevInQueue = null;
            currentNode.myTransposeEdgeLinkedList = new MyEdgeLinkedList();
            currentNode = currentNode.nextNode;
        }
        source.color = Color.Grey;
        source.distance = 0;
        source.parent = null;
        queue.enqueue(source);
    }


    public void dfs1() {
        this.dfsXi = new GraphNode[2 * this.numberOfNodesDG + 1];
        GraphNode currentNode = this.myNodeLinkedList.headNode;
        while (currentNode != null) {
            currentNode.color = Color.White;
            currentNode.parent = null;
            currentNode.leftChild = null;
            currentNode.rightSibling = null;
            currentNode.refractionTime = 0;
            currentNode.discoveryTime = 0;
            currentNode.currentPositionInTree = -1;
            currentNode.indexNodeByRefractionTime = 0;
            currentNode.nextInQueue = null;
            currentNode.prevInQueue = null;
            currentNode.myTransposeEdgeLinkedList = new MyEdgeLinkedList();
            currentNode = currentNode.nextNode;
        }
        currentNode = this.myNodeLinkedList.headNode;
        while (currentNode != null) {
            if (currentNode.color == Color.White) {
                dfsVisit1(currentNode);
            }
            currentNode = currentNode.nextNode;
        }

        int index = (this.time / 2);
        dfsXiSortedByRT = new GraphNode[index];
        for (int i = 0; i <= this.time; i++) {
            if (this.dfsXi[i] != null) {
                index--;
                dfsXiSortedByRT[index] = this.dfsXi[i];
                dfsXiSortedByRT[index].indexNodeByRefractionTime = index;
            }
        }
        this.time = 0;
    }

    public void dfsVisit1(GraphNode node) {
        time++;
        node.discoveryTime = time;
        node.color = Color.Grey;
        GraphEdge currentEdge = node.myEdgeLinkedList.headEdge;
        for (int i = 0; i < node.myEdgeLinkedList.sizeLinkedLGE; i++) {
            if (currentEdge.to.color == Color.White) {
                currentEdge.to.parent = node;
                dfsVisit1(currentEdge.to);
            }
            currentEdge = currentEdge.nextEdge;
        }
        node.color = Color.Black;
        time++;
        node.refractionTime = time;
        this.dfsXi[time] = node;
    }

    public void transposeGraph() {
        GraphEdge currentEdge = this.myEdgeLinkedListInGraph.headEdge;
        GraphEdge[] arrEdges = new GraphEdge[this.numberOfEdgesDG];
        for (int i = 0; i < this.numberOfEdgesDG; i++) {
            arrEdges[i] = currentEdge;
            currentEdge = currentEdge.nextEdgeInGraph;
        }
        for (int i = arrEdges.length - 1; i >= 0; i--) {
            GraphNode from = arrEdges[i].to;
            GraphNode to = arrEdges[i].from;
            GraphEdge tempEdge = new GraphEdge(from, to);
            from.myTransposeEdgeLinkedList.insertEdge(tempEdge);
        }
    }


    public RootedTree dfs2(){
        RootedTree sccTree = new RootedTree();
        GraphNode virtualNode = new GraphNode(0);
        sccTree.setRoot(virtualNode);
        GraphNode currentNode = this.myNodeLinkedList.headNode;
        while (currentNode != null) {
            currentNode.color = Color.White;
            currentNode = currentNode.nextNode;
        }
        currentNode = dfsXiSortedByRT[0];
        if (currentNode != null){
            virtualNode.leftChild = currentNode;
            sccTree.numberKnodesInTree++;
        }
        GraphNode tempNode1 = null;
        boolean isRightSibling = false;
        for (int i = 0; i < this.dfsXiSortedByRT.length; i++) {
            if (dfsXiSortedByRT[i] != null && dfsXiSortedByRT[i].color == Color.White) {
                if (tempNode1 != null){
                    tempNode1.rightSibling = dfsXiSortedByRT[i];
                }
                dfsXiSortedByRT[i].parent = virtualNode;
                dfsVisit2(sccTree, dfsXiSortedByRT[i]);
                isRightSibling = true;
            }
            if (isRightSibling){
                tempNode1 = dfsXiSortedByRT[i];
                isRightSibling = false;
            }
            sccTree.numberKnodesInTree++;
        }
        this.time = 0;
        return sccTree;
    }

        public void dfsVisit2(RootedTree rootedTree, GraphNode node) {
            time++;
            node.discoveryTime = time;
            node.color = Color.Grey;
            boolean isRightSibling = false;
            GraphNode tempNode = null;
            GraphEdge currentEdge = node.myTransposeEdgeLinkedList.headEdge;
            for (int i = 0; i < node.myTransposeEdgeLinkedList.sizeLinkedLGE; i++) {
                if (currentEdge.to.color == Color.White) {
                    currentEdge.to.parent = node;
                    if (isRightSibling){
                        tempNode.rightSibling = currentEdge.to;
                    }
                    if (currentEdge.to != null && !isRightSibling){
                        currentEdge.from.leftChild  = currentEdge.to;
                        isRightSibling = true;
                    }
                    tempNode = currentEdge.to;
                    dfsVisit2(rootedTree, currentEdge.to);
                }
                currentEdge = currentEdge.nextEdge;
            }
            node.color = Color.Black;
            time++;
            node.refractionTime = time;
        }

    public RootedTree scc(){
        dfs1();
        transposeGraph();
        return dfs2();
    }
}
