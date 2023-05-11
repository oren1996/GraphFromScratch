public class MyQueue extends MyNodeLinkedList{
    protected MyNodeLinkedList linkedListOfQueue;
    protected GraphNode tail;

    public MyQueue(){
        this.linkedListOfQueue = new MyNodeLinkedList();
        this.tail = null;
    }
    public void enqueue(GraphNode node){
        if (this.tail != null){
            this.linkedListOfQueue.insertMyNodeAfterQueue(node, this.tail);
        }
        else {
            linkedListOfQueue.insertMyNodeQueue(node);
        }
        this.tail = node;
    }

    public GraphNode dequeue(){
        if (this.linkedListOfQueue.headNode == null){
            System.out.println("ERROR");
        }
        GraphNode node = this.linkedListOfQueue.headNode;
        this.linkedListOfQueue.deleteMyNodeQueue(this.linkedListOfQueue.headNode);
        if (this.linkedListOfQueue.headNode == null){
            this.tail =  null;
        }
        return node;
    }
}
