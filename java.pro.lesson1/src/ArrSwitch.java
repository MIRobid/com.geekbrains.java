//Написать метод, который меняет два элемента массива местами
// (массив может быть любого ссылочного типа);

public class ArrSwitch<T> {
    private T temp;
    private T[] arr;

    public ArrSwitch(T...arr){
        this.arr=arr;
    }

    public T getArr(int i){
        return arr[i];
    }

    public void setArr(int i, T v) {
        arr[i]=v;
    }

    public void showValue(){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }

    public ArrSwitch<T> swap(int index1, int index2){
        temp=this.getArr(index1);
        this.setArr(index1,this.getArr(index2));
        this.setArr(index2,temp);
        return this;
    }

}
