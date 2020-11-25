public class Main {

    static final int SIZE=10000000;
    static final int HALF=SIZE/2;
    static final int FILLER=1;
    static final int PART_ONE=0;
    static final int PART_TWO=1;

    public static void main(String[] args) throws InterruptedException {

        System.out.printf("method1: %d\n", doMethod1());
        System.out.printf("method2: %d\n", doMethod2());
    }

    private static long doMethod1() {
        float[] arr = new float[SIZE];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long finish = System.currentTimeMillis();
        return finish - start;
    }

    private static long doMethod2() throws InterruptedException{
        float[] a=new float[SIZE];
        for (int i = 0; i < a.length; i++) {
            a[i] = 1;
        }
        long start1=System.currentTimeMillis();

        float[][] a1=new float[][]{new float[HALF],new float[HALF]};
        System.arraycopy(a,0,a1[PART_ONE],0,HALF);
        System.arraycopy(a,HALF,a1[PART_TWO],0,HALF);

        Thread thread = new Thread(()-> {
            for (int i = 0; i < a1[PART_TWO].length; i++) {
                a1[PART_TWO][i] = (float) (a1[PART_TWO][i] * Math.sin(0.2f + (i+HALF) / 5) * Math.cos(0.2f + (i+HALF) / 5) * Math.cos(0.4f + (i+HALF) / 2));
            }
        });
        thread.start();
        for (int i = 0; i < a1[PART_ONE].length; i++) {
            a1[PART_ONE][i] = (float) (a1[PART_ONE][i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        thread.join();

        System.arraycopy(a1[PART_ONE],0,a,0,HALF);
        System.arraycopy(a1[PART_TWO],0,a,HALF,HALF);
        long finish1=System.currentTimeMillis();
        return finish1-start1;
    }
}
