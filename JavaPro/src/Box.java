//Класс Box, в который можно складывать фрукты.
// Коробки условно сортируются по типу фрукта,
// поэтому в одну коробку нельзя сложить и яблоки, и апельсины;

//Для хранения фруктов внутри коробки можно использовать ArrayList;

//Сделать метод getWeight(),
// который высчитывает вес коробки,
// зная вес одного фрукта и их количество:
// вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);

//Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той, которую подадут в compare() в качестве параметра. true – если их массы равны, false в противоположном случае. Можно сравнивать коробки с яблоками и апельсинами;

//Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую. Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами. Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;

//Не забываем про метод добавления фрукта в коробку.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> list;

    public Box() {this.list=new ArrayList();}
    public Box(T...fruits){
        this.list=new ArrayList(Arrays.asList(fruits));
    }

    /*
    //мой изначальный код

    ArrayList<Box<? super Apple>> appleBox = new ArrayList<Box<? super Apple>>();
    ArrayList<Box<? super Orange>> orangeBox = new ArrayList<Box<? super Orange>>();

    public void addApple(Box<? super Apple> a){
        appleBox.add(a);
    }

    public void addOrange(Box<? super Orange> o){
        orangeBox.add(o);
    }

     */

    public float getWeight(){
        float w=0.0f;
        for(T f:list){
            w+=f.getWeight();
        }
        return w;
    }

    public boolean compare(Box<?> another){
        return Math.abs(this.getWeight() - another.getWeight()) < 0.0001;
    }

    public void combine(Box<? super T> another){
        if(another == this){
            return;
        }

        another.list.addAll(this.list);
        this.list.clear();
    }

    public void add(T fruit){
        list.add(fruit);
    }
}
