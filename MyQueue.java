import java.util.Arrays;

public class MyQueue<T> {
    private static final int INIT_SIZE = 10;

    private Object[] queue;
    private int index;

    public MyQueue() {
        queue = new Object[INIT_SIZE];
    }

    public void add(T value) {
        resizeIfNeed();

        queue[index] = value;
        index++;
    }

    private void resizeIfNeed() {
        if (index == queue.length) {

            System.out.println("Resize happened, index: " + index + ", data.length: " + queue.length);
            int newSize = queue.length * 2;
            Object[] newData = new Object[newSize];
            System.arraycopy(queue, 0, newData, 0, queue.length);
            queue = newData;
        }
    }

    public int size() {
        return index;
    }

    public void clear(){
        index = 0;
        queue = new Object[INIT_SIZE];
    }

    public T peek(){
        return (T)queue[0];
    }

    public T poll(){
        T firstElement = (T) queue[0];
        System.arraycopy(queue, 1, queue,0,queue.length-1);
        index--;
        return firstElement;
    }

    public T remove(int i){
        if(i>=0 && i< index) {
            T removeElement = (T) queue[i];
            System.arraycopy(queue, i + 1, queue, i, queue.length - 1 - i);
            index--;
            return removeElement;
        }
        else{
            System.out.println("Wrong index");
            throw new ArrayIndexOutOfBoundsException();
        }
    }


    @Override
    public String toString() {
        StringJoiner result = new StringJoiner(", ");
        for (int i = 0; i < index; i++) {
            result.add(queue[i].toString());
        }

        return "[" + result + "]";
    }
}

class MyQueueTests {
    public static void main(String[] args) {
        MyQueue<String> queue = new MyQueue<>();

        queue.add("Zero");
        queue.add("One");
        queue.add("Two");
        queue.add("Three");
        queue.add("Four");
        System.out.println("queue = " + queue);
        System.out.println("queue.size() = " + queue.size());

        System.out.println("queue.peek() = " + queue.peek());

        System.out.println("removeElement = " + queue.remove(1));
        System.out.println("queue = " + queue);
        System.out.println("queue.size() = " + queue.size());

        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue = " + queue);
        System.out.println("queue.size() = " + queue.size());

        list.clear();
        System.out.println("queue.clear = " + queue);

    }
}
