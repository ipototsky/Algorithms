package week5.assignment;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class KdTree {
    private Node root;
    private int size;

    public KdTree() {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D point2D) {
        if (point2D == null) throw new IllegalArgumentException();
        if (root == null) {
            size++;
            root = new Node(point2D, true);
        } else {
            insert(root, point2D, true);
        }
    }

    private Node insert(Node node, Point2D point2D, boolean vertical) {
        if (node == null) {
            size++;
            return new Node(point2D, vertical);
        } else {
            if ((vertical && point2D.x() < node.point.x() || (!vertical && point2D.y() < node.point.y()))) {
                node.left = insert(node.left, point2D, !vertical);
            } else {
                node.right = insert(node.right, point2D, !vertical);
            }
        }
        return node;
    }

    public boolean contains(Point2D point2D) {
        if (point2D == null) throw new IllegalArgumentException();
        return contains(root, point2D, true);
    }

    private boolean contains(Node node, Point2D point2D, boolean vertical) {
        if (node == null) return false;
        if (node.point.equals(point2D)) return true;

        if (vertical && point2D.x() < node.point.x() || !vertical && point2D.y() < node.point.y()) {
            return contains(node.left, point2D, !vertical);
        } else {
            return contains(node.right, point2D, !vertical);
        }
    }

    public void draw() {
        draw(root, new RectHV(0, 0, 1, 1));
    }

    private void draw(Node node, RectHV rectHV) {
        if (node == null) return;

        StdDraw.setPenColor(Color.black);
        StdDraw.setPenRadius(0.015);
        node.point.draw();

        StdDraw.setPenRadius();
        if (node.vertical) {
            StdDraw.setPenColor(Color.red);
            StdDraw.line(node.point.x(), rectHV.ymin(), node.point.x(), rectHV.ymax());

            draw(node.left, new RectHV(rectHV.xmin(), rectHV.ymin(), node.point.x(), rectHV.ymax()));
            draw(node.right, new RectHV(node.point.x(), rectHV.ymin(), rectHV.xmax(), rectHV.ymax()));
        } else {
            StdDraw.setPenColor(Color.blue);
            StdDraw.line(rectHV.xmin(), node.point.y(), rectHV.xmax(), node.point.y());

            draw(node.left, new RectHV(rectHV.xmin(), rectHV.ymin(), rectHV.xmax(), node.point.y()));
            draw(node.right, new RectHV(rectHV.xmin(), node.point.y(), rectHV.xmax(), rectHV.ymax()));
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        List<Point2D> result = new ArrayList<>();
        range(root, rect, result);
        return result;
    }

    private void range(Node node, RectHV rect, List<Point2D> result) {
        if (node == null) return;
        if (rect.contains(node.point)) result.add(node.point);

        if ((node.vertical && rect.xmin() < node.point.x()) || (!node.vertical && rect.ymin() < node.point.y())) {
            range(node.left, rect, result);
        }
        if (node.vertical && rect.xmax() > node.point.x() || (!node.vertical && rect.ymax() > node.point.y())) {
            range(node.right, rect, result);
        }
    }

    public Point2D nearest(Point2D queryPoint) {
        if (queryPoint == null) throw new IllegalArgumentException();
        return nearest(root, queryPoint, root.point);
    }

    private Point2D nearest(Node node, Point2D query, Point2D nearest) {
        if (node == null) return nearest;
        if (node.point.distanceSquaredTo(query) < nearest.distanceSquaredTo(query)) {
            nearest = node.point;
        }
        if ((node.vertical && query.x() < node.point.x()) || (!node.vertical && query.y() < node.point.y())) {
            nearest = nearest(node.left, query, nearest);
            if (checkOtherSide(query, node, nearest)) {
                nearest = nearest(node.right, query, nearest);
            }
        } else {
            nearest = nearest(node.right, query, nearest);
            if (checkOtherSide(query, node, nearest)) {
                nearest = nearest(node.left, query, nearest);
            }
        }
        return nearest;
    }

    private boolean checkOtherSide(Point2D query, Node node, Point2D nearest) {
        double distanceToRect = node.vertical ?
                Math.pow(query.x() - node.point.x(), 2) :
                Math.pow(query.y() - node.point.y(), 2);
        return distanceToRect < query.distanceSquaredTo(nearest);
    }

    private class Node {
        private Point2D point;
        private Node left, right;
        private boolean vertical;

        public Node(Point2D point, boolean vertical) {
            this.point = point;
            this.vertical = vertical;
        }
    }

    public static void main(String[] args) {
        KdTree kdTree = new KdTree();
//        StdDraw.enableDoubleBuffering();

        kdTree.insert(new Point2D(0.7, 0.2)); // A
        kdTree.insert(new Point2D(0.5, 0.4)); // B
        kdTree.insert(new Point2D(0.2, 0.3)); // C

        kdTree.insert(new Point2D(0.4, 0.7));
        kdTree.insert(new Point2D(0.9, 0.6));
//        kdTree.insert(new Point2D(0.4, 0.7));

        System.out.println(kdTree);
        System.out.println(kdTree.contains(new Point2D(0.2, 0.3)));

        kdTree.draw();
        System.out.println(kdTree.contains(new Point2D(0.2, 0.4)));

        RectHV searchRect = new RectHV(0.1, 0.1, 0.75, 0.75);
//        searchRect.draw();

        Iterable<Point2D> result = kdTree.range(searchRect);
        System.out.println(result);

        Point2D queryPoint = new Point2D(0.1, 0.5);
        queryPoint.draw();

        Point2D nearest = kdTree.nearest(queryPoint);
        System.out.println(nearest);

    }
}
