package dyve.aoc.day.day7;

import dyve.aoc.input.InputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part2 {


    public static void main(String[] args) throws Exception {
        Part2 instance = new Part2();
        instance.execute(InputReader.readInput("7"));
        //instance.execute(List.of("3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5"));
        //instance.execute(List.of("3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10"));
    }

    protected void execute(List<String> programText) throws Exception {
        List<String> raw = Arrays.asList(programText.get(0).split(","));
        int maxThrust = 0;
        for(int i = 5; i < 10; i++){
            for(int j = 5; j < 10; j++) {
                if(j == i)
                    continue;
                for(int k = 5; k < 10; k++) {
                    if(k == j || k == i)
                        continue;
                    for(int l = 5; l < 10; l++) {
                        if(l == k || l == j || l == i)
                            continue;
                        for(int m = 5; m < 10; m++) {
                            if(m == l || m == k || m == j || m == i)
                                continue;
                            //System.out.printf("%d,%d,%d,%d,%d\n", i, j, k, l, m);
                            Amplifier A = new Amplifier("A", new ArrayList<>(raw), i);
                            Thread tA = new Thread(A);
                            Amplifier B = new Amplifier("B", new ArrayList<>(raw), j);
                            Thread tB = new Thread(B);
                            Amplifier C = new Amplifier("C", new ArrayList<>(raw), k);
                            Thread tC = new Thread(C);
                            Amplifier D = new Amplifier("D", new ArrayList<>(raw), l);
                            Thread tD = new Thread(D);
                            Amplifier E = new Amplifier("E", new ArrayList<>(raw), m);
                            Thread tE = new Thread(E);
                            link(A, B);
                            link(B, C);
                            link(C, D);
                            link(D, E);
                            link(E, A);
                            A.input.transit.offer(i);
                            A.input.transit.offer(0);
                            B.input.transit.offer(j);
                            C.input.transit.offer(k);
                            D.input.transit.offer(l);
                            E.input.transit.offer(m);
                            tA.start();
                            tB.start();
                            tC.start();
                            tD.start();
                            tE.start();
                            while(tA.isAlive() || tB.isAlive() || tC.isAlive() || tD.isAlive() || tE.isAlive()){
                                Thread.sleep(10);
                                //System.out.println("" + tA.isAlive() + tB.isAlive() + tC.isAlive() + tD.isAlive() + tE.isAlive());
                            }
                            maxThrust = Math.max(maxThrust, E.output.transit.peek());
                        }
                    }
                }
            }
        }
        System.out.println(maxThrust);
    }

    public void link(Amplifier a1, Amplifier a2){
        Channel c = new Channel();
        a1.output = c;
        a2.input = c;
    }
}
