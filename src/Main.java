public class Main {

    static final int SIZE=10000000;
    static final int HALF=SIZE/2;

    public static void main(String[] args) {

        float[] arr=new float[SIZE];
        float[] a1=new float[SIZE];
        float[] a2=new float[SIZE];

        for(int i=0;i<arr.length;i++){
            arr[i]=1;
        }
        long start=System.currentTimeMillis();
        for(int i=0;i<arr.length;i++){
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long finish=System.currentTimeMillis();
        System.out.println(finish-start);

        System.arraycopy(arr,0,a1,0,HALF);
        System.arraycopy(arr,HALF,a2,0,HALF);

        long start1=System.currentTimeMillis();
        for(int i=0;i<a1.length;i++){
            a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            a2[i] = (float)(a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        System.arraycopy(a1,0,arr,0,HALF);
        System.arraycopy(a2,HALF,arr,HALF,HALF);

        long finish1=System.currentTimeMillis();
        System.out.println(finish1-start1);

    }
}
