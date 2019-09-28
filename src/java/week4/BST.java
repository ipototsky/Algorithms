package week4;

import week2.Queue;

public class BST<K extends Comparable<K>, V> {
    private Node root;

    public V get(K key) {
        Node node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if      (cmp < 0) node = node.left;
            else if (cmp > 0) node = node.right;
            else return node.value;
        }
        return null;
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    public int size() {
        return size(root);
    }

    public int rank(K key) {
        return rank(key, root);
    }

    private void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    public Iterable<K> keys() {
        Queue<K> queue = new Queue<>();
        inorder(root, queue);
        return queue;
    }

    private void inorder(Node node, Queue<K> queue) {
        if (node == null) return;
        inorder(node.left, queue);
        queue.enqueue(node.key);
        inorder(node.right, queue);
    }

    private int rank(K key, Node node) {
        if (node == null) return 0;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return rank(key, node.left);
        } else if (cmp > 0) {
            return 1 + size(node.left) + rank(key, node.right);
        } else {
            return size(node.left);
        }
    }

    private int size(Node node) {
        return node == null ? 0 : node.count;
    }

    private Node put(Node node, K key, V value) {
        if (node == null) return new Node(key, value, 1);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }


    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private int count;

        public Node(K key, V value, int count) {
            this.key = key;
            this.value = value;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        BST<String, Integer> bst = new BST<>();
        bst.put("S", 1);
        bst.put("E", 2);
        bst.put("X", 3);
        bst.put("A", 4);
        bst.put("R", 5);
        bst.put("C", 6);
        bst.put("H", 7);
        bst.put("M", 8);

        System.out.println(bst.get("A"));
    }
}
