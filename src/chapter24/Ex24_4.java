package chapter24;

public class Ex24_4 {

    public static void main(String[] args) {
        GenericStack<Integer> stack = new GenericStack<>();

        int primes = 0;
        int number = 2;
        while (primes<100){
            if (isPrime(number)){
                primes++;
                stack.push(number);
            }
            number++;
        }

        while (!stack.isEmpty()){
            System.out.printf("%d\t", stack.pop());
        }

    }

    private static boolean isPrime(int n){
        for (int i=2; i<=Math.sqrt(n); i++){
            if (n%i == 0){
                return false;
            }
        }
        return true;
    }


}
