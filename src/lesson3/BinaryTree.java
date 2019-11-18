package lesson3;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    private static class Node<T> {
        T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        }
        else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        }
        else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    public int height() {
        return height(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    private int height(Node<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     *Трудоемкость - O(n), n - height()
     * Ресурсоемкость - О(1)
     */
    @Override
    public boolean remove(Object o) {
        if (o == null || !contains(o)) return false;
        Node<T> node = new Node<>((T) o);
        root = removePr(root,(T) o);
        size--;
        return true;
    }

    private Node<T> removePr (Node<T> node, T value) {
        if (node == null) return null;
        if (value.compareTo(node.value) < 0) node.left = removePr(node.left, value);
        if (value.compareTo(node.value) > 0) node.right = removePr(node.right, value);
        if (value.compareTo(node.value) == 0 ) node = removeIn(node);
        return node;
    }

    private Node <T> removeIn (Node <T> node) {
        if (node.left == null) return  node.right;
        if (node.right == null) return  node.left;
        Node<T> mainNode = new Node<T>(minNode(node.right).value);
        mainNode.left = node.left;
        mainNode.right = node.right;
        node = mainNode;
        node.right = removePr(node.right, node.value);
        return node;
    }

    private Node<T> minNode (Node<T> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        }
        else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        }
        else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }


    public class BinaryTreeIterator implements Iterator<T> {
        private Node<T> current;
        private Stack<Node<T>> nodes;

        private BinaryTreeIterator() {
            nodes = new Stack<>();
            current = root;
            while (current != null){
                nodes.push(current);
                current = current.left;
            }
        }

        /**
         * Проверка наличия следующего элемента
         * Средняя
         *
         *  *
         * Трудоемкость - O(n), n - height()
         * Ресурсоемкость - О(1)
         */
        @Override
        public boolean hasNext() {
           return nodes.size() > 0;
        }

        /**
         * Поиск следующего элемента
         * Средняя
         *
         * Трудоемкость - O(n), n - height()
         * Ресурсоемкость - О(1)
         */
        @Override
        public T next() {
            current = nodes.pop();
            Node<T> node = current;
            if (node == null) throw new NotImplementedError();
            if (node.right != null) {
                node = node.right;
                while (node != null) {
                    nodes.push(node);
                    node = node.left;
                }
            }
            return current.value;


        }

        /**
         * Удаление следующего элемента
         * Сложная
         * Трудоемкость - O(n), n - height()
         *  Ресурсоемкость - О(1)
         */
        @Override
        public void remove() {
            if (current != null) root = removePr(root, current.value);
            else  throw new NotImplementedError();
            size--;
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Очень сложная
     */
    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        SortedSet<T> set = new TreeSet<>();
        if (fromElement.compareTo(toElement) <= 0) return subSetPr(this.root, fromElement, toElement, set);
        else return set;
    }

    private SortedSet<T> subSetPr (Node<T> node, T fromElement, T toElement, SortedSet set){
        T root = node.value;
        int fromEl = fromElement.compareTo(root);
        int toEl = toElement.compareTo(root);
        if (fromEl <= 0 && toEl >= 0 ) {
            set.add(root);
            if (node.left != null) subSetPr(node.left, fromElement, toElement, set);
            if (node.right!= null) subSetPr(node.right, fromElement,toElement,set);
        }
        if (toEl < 0 && node.left != null) subSetPr(node.left, fromElement, toElement, set);
        if (fromEl > 0 && node.right != null) subSetPr(node.right, fromElement, toElement, set);

        return set;
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        // TODO
        throw new NotImplementedError();
    }

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        return subSet(fromElement, last());
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
