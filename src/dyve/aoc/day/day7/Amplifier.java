package dyve.aoc.day.day7;

import java.util.List;

public class Amplifier implements Runnable {

    String name;

    List<String> raw;

    int phase;

    Channel output;

    Channel input;

    public Amplifier(String name, List<String> raw, int phase){
        this.raw = raw;
        this.phase = phase;
        this.name = name;
    }

    @Override
    public void run() {
        Program p = new Program(raw);
        p.execute(this);
    }

    public int getInput(){
        //System.out.println("Amplifier " + name + " getInput");
        while(input.transit.isEmpty()){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Integer result = input.transit.poll();
        //System.out.println("Amplifier " + name + " gotInput : " + result);
        return result;
    }

    public void sendOutput(int i){
        //System.out.println("Amplifier " + name + " sendOutput : " + i);
        output.transit.offer(i);
    }

}
