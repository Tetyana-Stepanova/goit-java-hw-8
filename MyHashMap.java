import java.util.Objects;
public class MyHashMap <K,V>{

    private final int DEFAULT_INITIAL_CAPACITY = 16;
    private final float DEFAULT_LOAD_FACTOR = 0.75f;
    Node[] table = new Node[DEFAULT_INITIAL_CAPACITY];
    private  int size;


    static class Node{
       int hash;
       Object key;
       Object value;
       Node next;

        Node(int hash,Object key,Object value,Node next){
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public int hash(Object key){
        int h=31;
        return (key == null) ? 0 : (h =h*17 + key.hashCode());
    }

    public int indexFor(int hashValue,int length){
        return hashValue % length;
    }

    public int size() {
        return size;
    }

    public void clear(){
        table = new Node[DEFAULT_INITIAL_CAPACITY];
        size= 0;
    }


    public void addEntry(Object key,Object value,int hashValue,int i){
        // Если согласованная длина массива превышена, расширяем емкость
        if(++size >= table.length * DEFAULT_LOAD_FACTOR){
            Node[] newTable = new Node[table.length *2];
            transfer(table,newTable);
            table = newTable;
        }
        // получить данные в i
        Node eNode = table[i];
        // Добавить узел, указать узел рядом с предыдущим узлом
        table[i] = new Node(hashValue,key,value,eNode);
    }

    public void transfer (Node [] src, Node [] newTable) {// src ссылается на старый массив Node
        int newCapacity = newTable.length;
        for (int j = 0; j <src.length; j ++) {// пройти старый массив
            Node e = src [j]; // Получить каждый элемент старого массива
            if (e != null) {
                src [j] = null; // Освободить ссылку на объект старого массива
                do {
                    Node next = e.next;
                    int i = indexFor (e.hash, newCapacity); // Пересчитать положение каждого элемента в массиве
                    e.next = newTable [i]; // Mark [1]
                    newTable [i] = e; // Поместить элемент в массив
                    e = next; // Доступ к элементам в следующей цепочке ввода
                } while (e != null);
            }
        }
    }



    public Object put(Object key, Object value) {
        // Рассчитать хеш-значение ключа
        int hashValue = hash(key);
        // Рассчитать место, где он должен храниться
        int i = indexFor(hashValue,table.length);
        // Если в i есть данные и ключ тот же, перезаписать
        for(Node node = table[i];node != null; node = node.next){
            Object k;
            if(node.hash == hashValue && ((k = node.key)==key||key.equals(k))){
                Object oldValue = node.value;
                node.value = value;
                return  oldValue;
            }
        }
        // Если в позиции i нет данных или есть данные в позиции i, но ключ - это новый ключ, добавьте узел
        addEntry(key,value,hashValue,i);
        return null;
    }

    public Object get(Object key) {
        // Вычисляем значение хеша на основе хеш-кода объекта
        int hashValue = hash(key);
        // По значению хеша и длине связанного списка получаем индекс позиции вставки
        int i = indexFor(hashValue,table.length);
        for(Node node = table[i];node != null;node = node.next){
            if(node.key.equals(key) && hashValue == node.hash){
                return node.value;
            }
        }
        return null;
    }

    public boolean remove(Object key){
        int i = indexFor(hash(key), table.length);
        Node node = table[i];
        if(node == null){
            return  false;
        }
        if (node.next == null){
            node = null;
            size--;
            return true;
        }
        for(node = table[i];node != null;node = node.next){
            if(node.key.equals(key) && hash(key) == node.hash){
                node= null;
                size--;
                return true;
            }
        }
        return  false;
    }
}



class MyHashMapTests{
    public static void main(String[] args) {
        MyHashMap<String,Integer> hashMap= new MyHashMap<>();
        hashMap.put("Tanya", 39);
        hashMap.put("Arina", 11);
        hashMap.put("Tymur", 9);
        hashMap.put("Yuri", 39);

        System.out.println("hashMap.get(\"Tanya\") = " + hashMap.get("Tanya"));
        System.out.println("hashMap.get(\"Arina\") = " + hashMap.get("Arina"));
        System.out.println("hashMap.get(\"Tymur\") = " + hashMap.get("Tymur"));
        System.out.println("hashMap.get(\"Yuri\") = " + hashMap.get("Yuri"));
        System.out.println("hashMap.size() = " + hashMap.size());
        hashMap.put("Toma", 65);
        System.out.println("hashMap.get(\"Toma\") = " + hashMap.get("Toma"));
        System.out.println("hashMap.size() = " + hashMap.size());

        hashMap.put("Alexandr", 70);
        System.out.println("hashMap.get(\"Alexandr\") = " + hashMap.get("Alexandr"));
        System.out.println("hashMap.size() = " + hashMap.size());

        System.out.println("hashMap.remove(\"Toma\") = " + hashMap.remove("Toma"));
        System.out.println("hashMap.sizeAfterRemove() = " + hashMap.size());


        hashMap.clear();
        System.out.println("hashMapClear.size() = " + hashMap.size());
    }
}
