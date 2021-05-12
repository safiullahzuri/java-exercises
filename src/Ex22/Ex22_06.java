package Ex22;

public class Ex22_06 {


    public static void main(String[] args) {
        int[] c = new int[6];
        for (int i=40; i< 46; i++){
            c[i-40] = fib(i);
        }

        long startTime = System.currentTimeMillis();
        for (int i=1;i<c.length; i++){
            gcd(c[i], c[i-1]);
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Computed in "+(endTime-startTime)+" milliseconds using pure gcd.");

        long startTime2 = System.currentTimeMillis();
        for (int i=1;i<c.length; i++){
            euclidGcd(c[i], c[i-1]);
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("Computed in "+(endTime2-startTime2)+" milliseconds using euclid gcd.");

    }

    public static int fib(int n){
        int f0 =1;
        int f1 = 1;
        int f2 = 1;
        if (n == 0)return 0;
        else if (n==1) return 1;
        else if (n== 2) return 2;

        for (int i=3; i<= n; i++){
            f0 = f1;
            f1= f2;
            f2 = f0 + f1;
        }
        return f2;
    }

    public static int euclidGcd(int x1, int x2){
        if (x1%x2 == 0){
            return x2;
        }else{
            return euclidGcd(x2, x1%x2);
        }
    }

    public static int gcd(int x1, int x2){
        int gcd = x2;
        while (x1%gcd !=0 || x2%gcd!=0){
            gcd--;
        }
        return gcd;
    }
}
