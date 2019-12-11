package dyve.aoc.day.day11;

public enum Direction {
    UP{
        @Override
        public Direction turn(int value) {
            return value == 0 ? LEFT : RIGHT;
        }
    },
    RIGHT{
        @Override
        public Direction turn(int value) {
            return value == 0 ? UP : DOWN;
        }
    },
    DOWN{
        @Override
        public Direction turn(int value) {
            return value == 0 ? RIGHT : LEFT;
        }
    },
    LEFT{
        @Override
        public Direction turn(int value) {
            return value == 0 ? DOWN : UP;
        }
    };

    public abstract Direction turn(int value);
}
