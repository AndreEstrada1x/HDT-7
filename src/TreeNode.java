/**
 * Represents a node in a binary tree.
 *
 * @param <K> the type of the key
 * @param <V> the type of the value
 */
public class TreeNode<K, V> {
    
    private K key;
    private V value;
    private TreeNode<K,V> left;
    private TreeNode<K,V> right;
    private TreeNode<K,V> parent;

    /**
     * Constructs a new TreeNode with the specified key and value.
     *
     * @param key   the key of the node
     * @param value the value of the node
     */
    public TreeNode(K key, V value){
        this.key = key;
        this.value = value;
        parent = null;
        left = null;
        right = null;
    }

    /**
     * Returns the key of the node.
     *
     * @return the key of the node
     */
    public K getKey() {
        return key;
    }

    /**
     * Sets the key of the node.
     *
     * @param key the key to set
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * Returns the value of the node.
     *
     * @return the value of the node
     */
    public V getValue() {
        return value;
    }

    /**
     * Sets the value of the node.
     *
     * @param value the value to set
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Returns the left child of the node.
     *
     * @return the left child of the node
     */
    public TreeNode<K, V> getLeft() {
        return left;
    }

    /**
     * Sets the left child of the node.
     *
     * @param left the left child to set
     */
    public void setLeft(TreeNode<K, V> left) {
        this.left = left;
    }

    /**
     * Returns the right child of the node.
     *
     * @return the right child of the node
     */
    public TreeNode<K, V> getRight() {
        return right;
    }

    /**
     * Sets the right child of the node.
     *
     * @param right the right child to set
     */
    public void setRight(TreeNode<K, V> right) {
        this.right = right;
    }

    /**
     * Returns the parent of the node.
     *
     * @return the parent of the node
     */
    public TreeNode<K, V> getParent() {
        return parent;
    }

    /**
     * Sets the parent of the node.
     *
     * @param parent the parent to set
     */
    public void setParent(TreeNode<K, V> parent) {
        this.parent = parent;
    }
}
