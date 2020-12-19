public class DemoArr {
    public static void main(String[] args) {
        ArrSwitch<Integer> arr=new ArrSwitch<Integer>(1,2,3,4,5);
        arr.swap(0,1);
        arr.showValue();
    }
}
