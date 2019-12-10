package dyve.aoc.day.day7;

import dyve.aoc.day.day6.Planet;
import dyve.aoc.input.InputReader;

import java.util.*;
import java.util.stream.Collectors;

public class Part1 {


    public static void main(String[] args) throws Exception {
        Part1 instance = new Part1();
        instance.execute(InputReader.readInput("7"));
        //instance.execute(List.of("3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0"));
        //instance.execute(List.of("3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0"));
        //instance.execute(List.of("3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0"));
    }

    protected void execute(List<String> programText) throws Exception{
        List<String> raw = Arrays.asList(programText.get(0).split(","));
        int maxThrust = 0;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++) {
                if(j == i)
                    continue;
                for(int k = 0; k < 5; k++) {
                    if(k == j || k == i)
                        continue;
                    for(int l = 0; l < 5; l++) {
                        if(l == k || l == j || l == i)
                            continue;
                        for(int m = 0; m < 5; m++) {
                            if(m == l || m == k || m == j || m == i)
                                continue;
                            System.out.printf("%d,%d,%d,%d,%d\n", i, j, k, l, m);
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
                            A.input = new Channel();
                            link(A, B);
                            link(B, C);
                            link(C, D);
                            link(D, E);
                            E.output = new Channel();
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
                                Thread.sleep(1);
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
