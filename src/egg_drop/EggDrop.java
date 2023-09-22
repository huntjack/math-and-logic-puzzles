package egg_drop;

import java.util.Optional;

public class EggDrop {
    private int breakingPoint;
    private int dropCount;
    public int findBreakingPoint() {
        EggDropSearchStart searchStart = findSearchStart();
        Optional<Integer> optional = startSearch(searchStart);
        return optional
                .orElseThrow(IllegalStateException::new);
    }
    private EggDropSearchStart findSearchStart() {
        if(breakingPoint > 100 || breakingPoint < 1) {
            throw new IllegalStateException();
        }
        int interval = 14;
        int current = interval;
        while(current <= 100 && !breaksOnDrop(current)) {
            current += --interval;
        }
        int start = current - interval;
        return new EggDropSearchStart(start, interval);
    }
    private boolean breaksOnDrop(int floor) {
        dropCount++;
        return floor >= breakingPoint;
    }
    private Optional<Integer> startSearch(EggDropSearchStart searchStart) {
        int current = searchStart.getSearchStart();
        int stopPoint = current + searchStart.getInterval();
        while(current < stopPoint && current <= 100) {
            if(breaksOnDrop(++current)) {
                return Optional.of(current);
            }
        }
        return Optional.empty();
    }
    public EggDrop(int breakingPoint) {
        this.breakingPoint = breakingPoint;
        dropCount = 0;
    }
    public int getDropCount() {
        return dropCount;
    }
    public static void main(String[] args) {
        EggDrop eggDrop = new EggDrop(99);
        int result = eggDrop.findBreakingPoint();
        System.out.println("Breakpoint: " + result);
        System.out.println("drop count: " + eggDrop.getDropCount());
    }
}
