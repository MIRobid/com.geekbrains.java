//Написать метод, который преобразует массив в ArrayList;

import java.util.Arrays;
import java.util.List;

public class ArrayList<T> {
    public ArrayList(List<T> asList) {
    }

    public static <T> ArrayList<T> arrayList(T[] arr){
        return new ArrayList<>(Arrays.asList(arr));
    }
}
