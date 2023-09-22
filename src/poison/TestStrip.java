package poison;

import java.util.*;

public class TestStrip {
    private int id;
    private NavigableMap<Integer, List<Bottle>> bottles;
    private static final int DAYS_FOR_RESULT = 7;
    public TestStrip(int id) {
        this.id = id;
        bottles = new TreeMap<>();
    }
    public void addBottle(Bottle bottle, int day) {
        if(!bottles.containsKey(day)) {
            List<Bottle> todaysBottles = new LinkedList<>();
            todaysBottles.add(bottle);
            bottles.put(day, todaysBottles);
        } else {
            List<Bottle> todaysBottles = bottles.get(day);
            todaysBottles.add(bottle);
        }
    }
    public boolean hasPoison(int currentDay) {
        int testDay = currentDay - DAYS_FOR_RESULT;
        if(testDay < 0) {
            return false;
        }
        NavigableMap<Integer, List<Bottle>> subMapOfBottles =
                bottles.subMap(
                        0,
                        true,
                        testDay,
                        true);
        for(Map.Entry<Integer, List<Bottle>> day : subMapOfBottles.entrySet()) {
            List<Bottle> bottlesFromThisDay = day.getValue();
            if(hasPoison(bottlesFromThisDay)) {
                return true;
            }
        }
        return false;
    }
    private static boolean hasPoison(List<Bottle> bottles) {
        for(Bottle bottle : bottles) {
            if(bottle.isPoisoned()) {
                return true;
            }
        }
        return false;
    }
    public int getId() {
        return id;
    }

}
