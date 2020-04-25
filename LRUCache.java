import java.util.HashMap;
import java.util.Map;

class DLNode {
    public int data, key;
    public DLNode next,prev;

    public DLNode(int key, int data) {
        this.data = data;
        this.key = key;
    }


}
class LRUCache {

    Map<Integer, DLNode> dlNodeKeyMap;
    DLNode front = null;
    DLNode root = null;
    int capacity;
    int currSize = 0, query = 0;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        dlNodeKeyMap = new HashMap(capacity);
    }

    public int get(int key) {
        if (!dlNodeKeyMap.containsKey(key)) {
            return -1;
        }
        //System.out.println("GETTING:" + key);
        DLNode curr = dlNodeKeyMap.get(key);
        remove(curr);
        addToFront(curr);
        return curr.data;
    }

    private void remove(DLNode curr) {
        if (curr.key == root.key) {
            root = root.next;
            if (root != null) {
                root.prev = null;
            } else {
                front = null;
            }
        } else if (curr.next != null & curr.prev != null) {
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
        }
    }

    private void addToFront(DLNode curr) {
        if (front == null) {
            front = curr;
            root = curr;
            dlNodeKeyMap.put(curr.key, curr);
        } else if(front.key != curr.key) {
            curr.prev = front;
            front.next = curr;
            front = curr;
            front.next = null;
            if (!dlNodeKeyMap.containsKey(curr.key)) {
                dlNodeKeyMap.put(curr.key, curr);
            }
        }
    }

    public void put(int key, int value) {
        DLNode curr = new DLNode(key, value);
        if (dlNodeKeyMap.containsKey(key)) {
            //System.out.println("DUPLICATE");
            curr = dlNodeKeyMap.get(key);
            remove(curr);
            curr.data = value;
        } else {
            if (dlNodeKeyMap.size() == capacity && root != null) {
                dlNodeKeyMap.remove(root.key);
                //System.out.println("Removing" + root.data);
                root = root.next;
                if (root != null) {
                    root.prev = null;
                } else {
                    front = null;
                }
            }
        }
        addToFront(curr);
    }

    public static void main(String[] args) {
       LRUCache lruCache = new LRUCache(2);
       lruCache.put(2, 1);
       lruCache.put(1, 1);
       lruCache.put(2, 3);
       lruCache.put(4, 1);
       System.out.println(lruCache.get(1));
       System.out.println(lruCache.get(2));
    }
}
