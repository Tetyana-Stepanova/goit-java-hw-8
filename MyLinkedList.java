import java.util.*;
public class Node<T> {
    private T value;
    private Node<T> next;
    private Node<T> previous;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }
}

public class MyLinkedList<T> {

    private Node<T> head;

    public void add(T item) {
        Node<T> node = new Node<>();
        node.setValue(item);

        if (head == null) {
            head = node;
        } else {
            Node<T> last = head;
            while (last.getNext() != null) {
                last = last.getNext();
            }

            last.setNext(node);
            node.setPrevious(last);
        }
    }

    public int size() {
        if (head == null) {
            return 0;
        }

        int count = 1;
        Node<T> last = head;
        while (last.getNext() != null) {
            last = last.getNext();
            count++;
        }

        return count;
    }

    public T get(int index) {
        if (head == null) {
            throw new IndexOutOfBoundsException("List is empty");
        }

        Node<T> search = head;
        for (int i = 0; i < index; i++) {
            search = search.getNext();

            if (search == null) {
                throw new IndexOutOfBoundsException("Invalid index: " + index + ", Size: " + size());
            }
        }

        return search.getValue();
    }

    public void clear() {
        head = null;
    }

    public T remove(int index) {
        if (head == null) {
            throw new IndexOutOfBoundsException("List is empty");
        }
        Node<T> result = new Node<>();
        Node<T> removeElement = head;
        for (int i = 0; i < index; i++) {
            removeElement = removeElement.getNext();
            result = removeElement;
        }

        Node<T> prevRemoveElement = head;
        for(int i = 0; i < index-1; i++){
            prevRemoveElement = prevRemoveElement.getNext();
        }

        Node<T> nextRemoveElement = head;
        for(int i = 0; i < index+1; i++){
            nextRemoveElement = nextRemoveElement.getNext();
        }
        prevRemoveElement.setNext(nextRemoveElement);
        nextRemoveElement.setPrevious(prevRemoveElement);
        removeElement = head;
        return result.getValue();
    }
}

public class MyLinkedListTests {
        public static void main(String[] args) {
            MyLinkedList<String> list = new MyLinkedList<>();

            System.out.println("list.size() = " + list.size());

            list.add("Alfa");
            System.out.println("list.size() = " + list.size());

            list.add("Beta");
            System.out.println("list.size() = " + list.size());

            list.add("Gamma");
            System.out.println("list.size() = " + list.size());

            System.out.println("list.get(0) = " + list.get(0));
            System.out.println("list.get(1) = " + list.get(1));
            System.out.println("list.get(2) = " + list.get(2));

            System.out.println("removeElement = " + list.remove(1));
            System.out.println("list.size() = " + list.size());

            list.clear();
            System.out.println("cleanlist.size() = " + list.size());
        }
    }
