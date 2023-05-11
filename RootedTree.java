import java.io.DataOutputStream;
import java.io.IOException;

public class RootedTree {
    protected GraphNode root;
    protected int numberKnodesInTree;
    static protected int countToK;
    protected int positionOfTheNodesInTree;

    public RootedTree() {
    }

    public GraphNode getRoot() {
        return root;
    }

    public void setRoot(GraphNode root) {
        this.root = root;
    }

    public void printByLayer(DataOutputStream out) throws IOException {
        // Corner cases
        if (this.root == null)
            return;
        out.writeBytes(String.valueOf(root.nodeKey));
        if (this.root.leftChild == null)
            return;
        else {
            out.writeBytes("\n");
        }
        // Create two queue and enqueue root
        MyQueue queue1 = new MyQueue();
        MyQueue queue2 = new MyQueue();
        boolean weDidNotChangeQueue = false;
        GraphNode currentNode = this.root.leftChild;

        MyQueue currentQueue = queue1;
        MyQueue otherQueue = queue2;
        currentQueue.enqueue(currentNode);

        while (queue1.linkedListOfQueue.sizeLinkedLGN != 0 || queue2.linkedListOfQueue.sizeLinkedLGN != 0) {
            currentNode = currentQueue.dequeue();
            while (currentNode != null) {
                if (weDidNotChangeQueue){
                    out.writeBytes(",");
                }
                countToK++;
                out.writeBytes(String.valueOf(currentNode.nodeKey));
                if (currentNode.leftChild != null) {
                    otherQueue.enqueue(currentNode.leftChild);
                }
                currentNode = currentNode.rightSibling;
                weDidNotChangeQueue = true;
            }
            if (currentQueue.linkedListOfQueue.sizeLinkedLGN == 0){
                if (currentQueue == queue1){
                    currentQueue = queue2;
                    otherQueue = queue1;
                }
                else {
                    currentQueue = queue1;
                    otherQueue  = queue2;
                }
                if (countToK != numberKnodesInTree - 1){
                    out.writeBytes("\n");
                }
                weDidNotChangeQueue = false;
            }
        }
        countToK = 0;
    }

    public void preorderPrint(DataOutputStream out) throws IOException {
        GraphNode currentNode = this.root;
        preOderPrintRecursive(out, currentNode);
        countToK = 0;
    }

    public void preOderPrintRecursive(DataOutputStream out, GraphNode currentNode) throws IOException {
        if (currentNode == null){
            return;
        }
        else {
            out.writeBytes(String.valueOf(currentNode.nodeKey));
            countToK++;
            if (countToK != numberKnodesInTree){
                out.writeBytes(",");
            }
            for (int i = 0; i < numberKnodesInTree; i++) {
                preOderPrintRecursive(out,currentNode.leftChild);
                preOderPrintRecursive(out,currentNode.rightSibling);
                return;
            }
        }
    }
}
