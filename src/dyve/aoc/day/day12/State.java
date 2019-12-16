package dyve.aoc.day.day12;

import static java.lang.Integer.compare;

public class State {

    int a1, a2, a3, a4, v1, v2, v3, v4;

    public State step(){
        State state = new State();
        state.v1 = v1 + compare(a2, a1) + compare(a3, a1) + compare(a4, a1);
        state.v2 = v2 + compare(a1, a2) + compare(a3, a2) + compare(a4, a2);
        state.v3 = v3 + compare(a1, a3) + compare(a2, a3) + compare(a4, a3);
        state.v4 = v4 + compare(a1, a4) + compare(a2, a4) + compare(a3, a4);

        state.a1 = a1 + state.v1;
        state.a2 = a2 + state.v2;
        state.a3 = a3 + state.v3;
        state.a4 = a4 + state.v4;
        return state;
    }

    @Override
    public String toString() {
        return "" + a1 + a2 + a3 + a4 + v1 + v2 + v3 + v4;
    }
}
