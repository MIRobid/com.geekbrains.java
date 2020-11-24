import java.util.Random;

public class Main {
    /*
    Задание 8.1
Приведите пример использование хеш-таблиц.
Список логин и паролей, сообщения в чате.

Задание 8.2
Приведите примеры ключей и коллизий.
Ключ - пароль, коллизия - один хеш на несколько паролей.

Задание 8.3
Приведите примеры популярных и эффективных хеш-функций.
Суммирование - переобразование текста в хеш.
Возведение в степень - может быть имя пользователей.
Квадратное переобразование - пароли.
Двойное хеширование - банковский счет.

Задание 8.4
На основе данных массива из задания 2.3 реализуйте хеш-таблицу с помощью открытой адресации,
а конкретнее метода линейного пробирования
Задание 8.5
Перестройте программный код задания 8.4 из алгоритма линейного пробирования в алгоритм двойного хеширования.
Сравните отличительные черты двух алгоритмов.


     */

    private int data;
    public Main(int data){this.data=data;}

    public int getKey() {return this.data;}

}

class HashTable{
    private Main[] hashArr;
    private int arrSize;
    private Main nonItem;

    public HashTable(int size){
        this.arrSize=size;
        hashArr=new Main[arrSize];
        nonItem=new Main(-1);
    }

    public void display() {
        for (int i=0;i<arrSize;i++){
            if(hashArr[i]!=null){
                System.out.println(hashArr[i].getKey());
            }else{
                System.out.println("***");
            }
        }
    }

    public int hashFunc(int key) {return key % arrSize;}
    /*
    public int hashFuncDouble(int key) {return 5-key%5;}
     */

    public void insert(Main item){
        int key=item.getKey();
        int hashVal=hashFunc(key);
        //int stepSize=hashFuncDouble(key);
        while (hashArr[hashVal]!=null && hashArr[hashVal].getKey()!=-1){
            ++hashVal;
            //hashVal+=stepSize;
            hashVal%=arrSize;
        }
        hashArr[hashVal]=item;
    }

    public Main delete(int key){
        int hashVal=hashFunc(key);
        //int stepSize = hashFuncDouble(key);
        while(hashArr[hashVal]!=null){
            if(hashArr[hashVal].getKey()==key){
                Main temp=hashArr[hashVal];
                hashArr[hashVal]=nonItem;
                return temp;
            }
            ++hashVal;
            //hashVal+=stepSize;
            hashVal%=arrSize;
        }
        return null;
    }

    public Main find(int key){
        int hashVal=hashFunc(key);
        //int stepSize=hashFuncDouble(key);
        while (hashArr[hashVal]!=null){
            if(hashArr[hashVal].getKey()==key){
                return hashArr[hashVal];
            }
            ++hashVal;
            //hashVal+=stepSize;
            hashVal%=arrSize;
        }
        return null;
    }

private int getPrime(int min){
        for(int i=min+1;true;i++)
            if(isPrime(i))
                return i;
}
private boolean isPrime(int n){
        for(int j=2;(j*j<=n);j++)
            if(n%j==0)
                return false;
            return true;
}


public static void hashFunc2(int k, int size){
    System.out.println(k%size);
}

    public static void main(String[] args) {
        Main aDataItem;
        int aKey;
        int size=66;

        HashTable hTable=new HashTable(size);
        Random rand=new Random();

        for(int j=0;j<15;j++){
            aKey=rand.nextInt(999);
            aDataItem=new Main(aKey);
            hTable.insert(aDataItem);
        }

        hTable.insert(new Main(999));
        hTable.insert(new Main(203));

        hTable.display();

        hashFunc2(999,66);
        System.out.println(hTable.find(999).getKey());

        hTable.delete(203);

    }
}