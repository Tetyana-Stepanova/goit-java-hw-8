import java.util.StringJoiner;
import java.util.Arrays;

class MyArrayList<T> {
    private static final int INIT_SIZE = 8;

    private Object[] data;
    private int index;
    private T removeElement;

    public MyArrayList() {
        data = new Object[INIT_SIZE];
    }

    public void add(T value) {
        resizeIfNeed();

        data[index] = value;
        index++;
    }

    private void resizeIfNeed() {
        if (index == data.length) {

            System.out.println("Resize happened, index: " + index + ", data.length: " + data.length);
            int newSize = data.length * 2;
            Object[] newData = new Object[newSize];
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }
    }

    public T get(int i) {
        if(i>=0 && i< index) {
            return (T) data[i];
        }
        else {
            System.out.println("Wrong index");
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public int size() {
        return index;
    }

    public void clear(){
        index = 0;
        data = new Object[INIT_SIZE];
    }

    public T remove(int i){
        if(i>=0 && i< index) {
            T removeElement = (T) data[i];
            System.arraycopy(data, i + 1, data, i, data.length - 1 - i);
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
            result.add(data[i].toString());
        }

        return "[" + result + "]";
    }
}

class MyArrayListTests {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();

        list.add("Yuri");
        list.add("Igor");
        list.add("Tanya");
        list.add("Timur");
        list.add("Arina");
        System.out.println("list = " + list);
        for(int i=0; i< list.size(); i++){
            System.out.println("list.get("+i+") =" + list.get(i));
        }
        System.out.println("list.size() = " + list.size());


        System.out.println("removeElement = " + list.remove(1));

        for(int i=0; i< list.size(); i++){
            System.out.println("list.get("+i+") =" + list.get(i));
        }
        System.out.println("list.size() = " + list.size());


        list.clear();
        System.out.println("list.clear = " + list);

    }
}
