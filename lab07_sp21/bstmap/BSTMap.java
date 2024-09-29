package bstmap;

import java.util.Set;


public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class BSTNode {
        private K key;
        private V value;
        private BSTNode left;
        private BSTNode right;

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }

        public BSTNode(K key, V value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public void print() {

            System.out.print(key);
            System.out.println(value);
            if (right != null) {
                right.print();
            }
            if (left != null) {
                left.print();
            }
        }

        public BSTNode get(K key) {
            if (this == null) {
                return null;
            }
            if (this.key.compareTo(key) == 0) {
                return this;
            } else if (this.key.compareTo(key) > 0 && left != null) {
                return left.get(key);
            } else if (this.key.compareTo(key) < 0 && right != null) {
                return right.get(key);
            } else {
                return null;
            }
        }

        public BSTNode getMaxKey(K key) {
            if (right != null) {
                return right.getMaxKey(key);
            } else {
                return this;
            }
        }

        public BSTNode getMinKey(K key) {
            if (left != null) {
                return left.getMinKey(key);
            } else {
                return this;
            }
        }

        public BSTNode getMINParentKey(K key) {
            if (left.left != null) {
                return left.getMINParentKey(key);
            } else {
                return this;
            }
        }

        public BSTNode getMAXParentKey(K key) {
            if (right.right != null) {
                return right.getMINParentKey(key);
            } else {
                return this;
            }
        }


        public BSTNode getParent(BSTNode node) {
            if (this.right == node || this.left == node) {
                return this;
            } else if (node.key.compareTo(this.key) < 0 && this.left != null) {
                return left.getParent(node);
            } else if (node.key.compareTo(this.key) > 0 && this.right != null) {
                return right.getParent(node);
            }
            throw new RuntimeException();
        }
    }

    private BSTNode root;
    private int size;

    public BSTMap(K key, V value) {
        root = new BSTNode(key, value);
        size = 1;
    }

    public BSTMap() {
        root = null;
        size = 0;
    }

    public void clear() {
        root = null;
        size = 0;
    }


    public boolean containsKey(K key) {
        if (root == null) {
            return false;
        }
        return root.get(key) != null;
    }


    public V get(K key) {
        if (root == null) {
            return null;
        }
        return root.get(key).value;
    }


    public int size() {
        return size;
    }


    public void put(K key, V value) {
        if (root != null) {
            BSTNode lookup = root.get(key);
            if (lookup == null) {
                put(root, key, value);
            } else {
                lookup.value = value;
            }
        } else {
            root = new BSTNode(key, value);
        }
        size++;
    }

    private void put(BSTNode node, K key, V value) {
        if (node.key.compareTo(key) > 0) {
            if (node.left == null) {
                node.left = new BSTNode(key, value);
            } else {
                put(node.left, key, value);
            }
        } else {
            if (node.right == null) {
                node.right = new BSTNode(key, value);
            } else {
                put(node.right, key, value);
            }
        }
    }

    public V remove(K key) {
        BSTNode lookup = root.get(key);
        V re = lookup.value;
        if (lookup == null) {
            return null;
        }
        if (lookup == root) {
            if (lookup.left == null && lookup.right == null) {
                clear();
            } else if (lookup.left == null && lookup.right != null) {
                root = lookup.right;
                return re;
            } else if (lookup.left != null && lookup.right == null) {
                root = lookup.left;
                return re;
            } else {
                double t = Math.random();
                if (t < 0.5) {
                    BSTNode MAX_parent = lookup.left.getMAXParentKey(key);
                    BSTNode MAX = MAX_parent.right;
                    MAX_parent.right = MAX.left;
                    root.key = MAX.key;
                    root.value = MAX.value;
                    //root=new BSTNode(MAX.key,MAX.value,root.left,root.right);
                    return re;
                } else {
                    BSTNode MIN_parent = lookup.right.getMINParentKey(key);
                    BSTNode MIN = MIN_parent.left;
                    MIN_parent.left = MIN.right;
                    root.key = MIN.key;
                    root.value = MIN.value;
                    //root=new BSTNode(MIN.key,MIN.value,root.left,root.right);
                    return re;
                }
            }
        }

        BSTNode parent = this.root.getParent(lookup);
        if (lookup.left == null && lookup.right == null) {
            if (parent.key.compareTo(key) > 0) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (lookup.left == null && lookup.right != null) {
            if (parent.key.compareTo(key) > 0) {
                parent.left = lookup.right;
            } else {
                parent.right = lookup.right;
            }
        } else if (lookup.left != null && lookup.right == null) {
            if (parent.key.compareTo(key) > 0) {
                parent.left = lookup.left;
            } else {
                parent.right = lookup.left;
            }
        } else {
            double t = Math.random();
            if (t < 0.5) {
                BSTNode MAX_parent = lookup.left.getMAXParentKey(key);
                BSTNode MAX = MAX_parent.right;
                MAX_parent.right = MAX.left;
                if (parent.key.compareTo(key) > 0) {
                    parent.left = new BSTNode(MAX.key, MAX.value, lookup.right, lookup.left);
                    ;
                } else {
                    parent.right = new BSTNode(MAX.key, MAX.value, lookup.right, lookup.left);
                    ;
                }
            } else {
                BSTNode MIN_parent = lookup.right.getMINParentKey(key);
                BSTNode MIN = MIN_parent.left;
                MIN_parent.left = MIN.right;
                if (parent.key.compareTo(key) > 0) {
                    parent.left = new BSTNode(MIN.key, MIN.value, lookup.right, lookup.left);
                    ;
                } else {
                    parent.right = new BSTNode(MIN.key, MIN.value, lookup.right, lookup.left);
                    ;
                }
            }
        }

        size--;
        return re;
    }

    public void print() {
        root.print();
    }

    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }


    private class Iterator<K> implements java.util.Iterator {
        public boolean hasNext() {
            return false;
        }

        ;

        public K next() {
            return null;
        }

        ;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
