/**
 * Represents a binary tree data structure.
 * The tree is composed of nodes, where each node contains a key-value pair.
 * The keys must be comparable.
 *
 * @param <K> the type of the keys in the tree (must implement Comparable)
 * @param <V> the type of the values in the tree
 */
public class BinaryTree<K extends Comparable<K>, V> {

    private TreeNode<K, V> root;

    public BinaryTree() {
        this.root = null;
    }

    public void insert(K key, V value) {
        TreeNode<K, V> newNode = new TreeNode<>(key, value);
        if (isEmpty()) {
            root = newNode;
        } else {
            insertRec(root, newNode);
        }
    }

    private void insertRec(TreeNode<K, V> node, TreeNode<K, V> newNode) {
        if (newNode.getKey().compareTo(node.getKey()) < 0) {
            if (node.getLeft() == null) {
                node.setLeft(newNode);
                newNode.setParent(node);
            } else {
                insertRec(node.getLeft(), newNode);
            }
        } else {
            if (node.getRight() == null) {
                node.setRight(newNode);
                newNode.setParent(node);
            } else {
                insertRec(node.getRight(), newNode);
            }
        }
    }

    public boolean search(K key) {
        return searchRec(root, key);
    }

    private boolean searchRec(TreeNode<K, V> node, K key) {
        if (node == null) {
            return false;
        }

        if (key.compareTo(node.getKey()) == 0) {
            return true;
        } else if (key.compareTo(node.getKey()) < 0) {
            return searchRec(node.getLeft(), key);
        } else {
            return searchRec(node.getRight(), key);
        }
    }

    public V get(K key) {
        return getRec(root, key);
    }

    private V getRec(TreeNode<K, V> node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.getKey()) == 0) {
            return node.getValue();
        } else if (key.compareTo(node.getKey()) < 0) {
            return getRec(node.getLeft(), key);
        } else {
            return getRec(node.getRight(), key);
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

}
