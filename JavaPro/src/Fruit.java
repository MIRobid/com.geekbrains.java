//Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;

public class Fruit {
    float weight;

    public Fruit(float f){
        this.weight=f;
    }

    public float getWeight(){
        return weight;
    }
}

class Apple extends Fruit{
    public Apple() {
        super(1.0f);
    }

}

class Orange extends Fruit{
    public Orange(){
        super(1.5f);
    }
}
