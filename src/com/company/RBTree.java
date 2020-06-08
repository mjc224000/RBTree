package com.company;

enum Color {BLACK, RED}

public class RBTree<T extends Comparable<T>> {
    @Override
    public String toString() {
        return "RBTree{" +
                "root=" + root +
                '}';
    }

    public boolean isLeft(RBNode node) {
        return node.father.left == node;
    }

    public void inorder() {
        inorder(root);
    }

    private void inorder(RBNode<T> node) {
        if (node == null)
            return;
        inorder(node.left);
        System.out.println(node.value);
        inorder(node.right);
    }

    public RBNode<T> rotateRight(RBNode<T> node) {
        RBNode<T> left = node.left;
        if (node == root) {
            root = left;
            left.father = null;
            left.color = Color.BLACK;
        } else {
            RBNode<T> p = node.father;
            if (isLeft(node))
                p.left = left;
            else
                p.right = left;
            left.father = p;
        }
        RBNode<T> lr = left.right;
        node.left = lr;
        if (lr != null)
            lr.father = node;
        left.right = node;
        node.father = left;
        return left;
    }

    public RBNode<T> rotateLeft(RBNode<T> node) {
        RBNode<T> r = node.right;
        if (node == root) {
            root = r;
            r.father = null;
            r.color = Color.BLACK;

        } else {
            RBNode<T> p = node.father;
            if (isLeft(node))
                p.left = r;
            else
                p.right = r;
            r.father = p;
        }
        RBNode<T> rl = r.left;
        node.right = rl;
        if (rl != null)
            rl.father = node;
        r.left = node;
        node.father = r;
        return r;

    }

    public class RBNode<T> {
        RBNode<T> left;
        RBNode<T> right;
        RBNode<T> father;
        T value;
        Color color = Color.RED;

        RBNode(T v) {
            value = v;
        }

        @Override
        public String toString() {
            return " {" +
                    "'left':" + left +
                    ", 'right':" + right +
                    ", 'value':" + "'" + value + "'" +
                    ", 'color':" + "'" + color + "'" +
                    '}';
        }
    }

    RBNode<T> root;

    private RBNode<T> getUncle(RBNode<T> node) {
        RBNode<T> p = node.father;
        RBNode<T> pp = p.father;

        return pp.left == p ? pp.right : pp.left;
    }


    public void insert(T value) {

        RBNode<T> n = new RBNode(value);
        if (root == null) {
            root = n;
            n.color = Color.BLACK;
            return;
        }
        RBNode<T> cur = root;
        while (true) {
            if (n.value.compareTo(cur.value) < 0) {
                if (cur.left == null) {
                    cur.left = n;
                    n.father = cur;
                    break;
                } else
                    cur = cur.left;
            } else {
                if (cur.right == null) {
                    cur.right = n;
                    n.father = cur;
                    break;
                } else
                    cur = cur.right;
            }
        }
        cur = n;
        while (cur.color == Color.RED && cur.father != null && cur.father.color == Color.RED) {
            if (cur == root) {
                cur.color = Color.BLACK;
                return;
            }
            // System.out.println(cur.value);
            if (cur == null)
                return;
            RBNode<T> p = cur.father;
            RBNode<T> uncle = getUncle(cur);
            /**
             * 4.1 u存在,并且为红
             */
            if (uncle != null && uncle.color == Color.RED) {
                uncle.color = Color.BLACK;
                p.color = Color.BLACK;
                p.father.color = Color.RED;
                cur = p.father;
                if (cur == root) {
                    cur.color = Color.BLACK;
                    return;
                }

                continue;
            }
            /**
             * 4.2 u不存在，或者u为黑，插入节点的父节点是祖父节点的右子节点
             */
            if ((uncle == null || uncle.color == Color.BLACK )&& !isLeft(p)) {
                /**
                 * 4.2.1 插入节点为父亲节点的右子节点
                 */
                if (!isLeft(cur)) {
                    RBNode pp = p.father;
                    pp.color = Color.RED;
                    p.color = Color.BLACK;
                    cur = rotateLeft(cur.father.father);
                    continue;
                } else {
                    /**
                     * 4.2.2插入节点是父亲节点的左子节点
                     */

                }
            }
            /**
             * 4.3 u 不存在，u为黑，插入节点的父亲节点是祖父节点的左子节点
             */
            if ((uncle == null || uncle.color == Color.BLACK) && isLeft(p)) {
                cur.father.color = Color.BLACK;
                cur.father.father.color = Color.RED;
                cur = rotateRight(cur.father.father);
                continue;
            }

        }

    }
}
