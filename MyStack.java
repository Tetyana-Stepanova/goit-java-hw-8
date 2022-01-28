import java.util.Arrays;

public class MyStack<T> {
    private static final int INIT_SIZE = 10;

    private Object[] stack;
    private int index;

    public MyStack() {
        stack = new Object[INIT_SIZE];
    }

    public void push(T value) {
        resizeIfNeed();

        stack[index] = value;
        index++;
    }

    private void resizeIfNeed() {
        if (index == stack.length) {

            System.out.println("Resize happened, index: " + index + ", data.length: " + stack.length);
            int newSize = stack.length * 2;
            Object[] newData = new Object[newSize];
            System.arraycopy(stack, 0, newData, 0, stack.length);
            stack = newData;
        }
    }

    public int size() {
        return index;
    }

    public void clear(){
        index = 0;
        stack = new Object[INIT_SIZE];
    }

    public T peek(){
        return (T)stack[index-1];
    }

    public T pop(){
        T firstStackElement = (T) stack[index-1];
        System.arraycopy(stack, 0, stack,0,index-1);
        index--;
        return firstStackElement;
    }

    public T remove(int i){
        if(i>=0 && i< index) {
            int k=index-i;
            T removeElement = (T) stack[k];
            System.arraycopy(stack, k + 1, stack, k, stack.length - 1 - k);
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
        for (int i = index-1; i >= 0; i--) {
            result.add(stack[i].toString());
        }

        return "[" + result + "]";
    }
}

class MyStackTests {
    public static void main(String[] args) {
        MyStack<String> stack = new MyStack<>();

        stack.push("Zero");
        stack.push("One");
        stack.push("Two");
        stack.push("Three");
        stack.push("Four");
        System.out.println("stack = " + stack);
        System.out.println("stack.size() = " + stack.size());

        System.out.println("firstStackElement = " + stack.peek());

        System.out.println("removeElement(2) = " + stack.remove(2));
        System.out.println("stack after remove = " + stack);
        System.out.println("stack.size() = " + stack.size());

        System.out.println("stack.pop() = " + stack.pop());
        System.out.println("stack without popElement = " + stack);
        System.out.println("stack.size() = " + stack.size());

        stack.clear();
        System.out.println("stack.clear = " + stack);
        System.out.println("stack.size() = " + stack.size());

    }
}
