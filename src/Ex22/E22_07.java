package Ex22;

public class E22_07 {

    public static void main(String[] args) {

        double[][] s = new double[10][2];
        Point[] points = new Point[10];
        for(int i = 0; i < s.length; i++) {
            s[i][0] = (i+i)*2+(i*i);
            s[i][1] = (i+i)*3+(i*i);
            points[i] = new Point(s[i][0], s[i][1]);
        }
        long s1 = System.currentTimeMillis();
        System.out.println(Pair.bruteForceClosestPair(points, 0, points.length-1));
        long e1 = System.currentTimeMillis();
        System.out.println("Done in milliseconds using brute force=>"+(e1-s1));
        long s2 = System.currentTimeMillis();
        System.out.println(Pair.getClosestPair(s));
        long e2 = System.currentTimeMillis();
        System.out.println("Done in milliseconds using recursion=>"+(e2-s2));

        long s3 = System.currentTimeMillis();
        System.out.println(Pair.myBruteForceClosestPairs(points));
        long e3 = System.currentTimeMillis();
        System.out.println("Done in milliseconds using my own brute force=>"+(e3-s3));

        long s4 = System.currentTimeMillis();
        System.out.println(Pair.getClosestPair2(points));
        long e4 = System.currentTimeMillis();
        System.out.println("Done in milliseconds using my own brute force=>"+(e4-s4));

        long s5 = System.currentTimeMillis();
        System.out.println(Pair2.getClosestPair(points));
        long e5 = System.currentTimeMillis();
        System.out.println("Done in milliseconds using my own written =>"+(e5-s5));


    }
}
