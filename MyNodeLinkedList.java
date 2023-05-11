public class MyNodeLinkedList{
    protected GraphNode headNode;
    protected int sizeLinkedLGN;

    public void insertMyNode(GraphNode node){
        node.nextNode = this.headNode;
        if (this.headNode != null) {
            this.headNode.prevNode = node;
        }
        this.headNode = node;
        node.prevNode = null;
        sizeLinkedLGN ++;
    }

    public void deleteMyNode(GraphNode node) {
        if (node.prevNode != null) {
            node.prevNode.nextNode = node.nextNode;
        } else {
            this.headNode = node.nextNode;
        }
        if (node.nextNode != null) {
            node.nextNode.prevNode = node.prevNode;
        }
        sizeLinkedLGN --;
    }



    public void insertMyNodeQueue(GraphNode node){
        node.nextInQueue = this.headNode;
        if (this.headNode != null) {
            this.headNode.prevInQueue = node;
        }
        this.headNode = node;
        node.prevInQueue = null;
        this.sizeLinkedLGN ++;
    }

    public void insertMyNodeAfterQueue(GraphNode x, GraphNode y){
        if (y.nextInQueue != null){
            y.nextInQueue.prevInQueue = x;
        }
        x.nextInQueue = y.nextInQueue;
        x.prevInQueue = y;
        y.nextInQueue = x;
        sizeLinkedLGN ++;
    }


    public void deleteMyNodeQueue(GraphNode node) {
        if (node.prevInQueue != null) {
            node.prevInQueue.nextInQueue = node.nextInQueue;
        } else {
            this.headNode = node.nextInQueue;
        }
        if (node.nextInQueue != null) {
            node.nextInQueue.prevInQueue = node.prevInQueue;
        }
        this.sizeLinkedLGN --;
    }
}