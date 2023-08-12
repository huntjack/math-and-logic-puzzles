import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Poison {
    private static final int NUMBER_OF_BOTTLES = 1000;
    private static final int NUMBER_OF_TEST_STRIPS = 10;
    private List<Bottle> bottles;
    private List<TestStrip> testStrips;
    private int day;
    public Poison() {
        bottles = new ArrayList<>(NUMBER_OF_BOTTLES);
        testStrips = new ArrayList<>(NUMBER_OF_TEST_STRIPS);
        day = 0;
    }
    public void initialize(int poisonedBottleId) {
        if(poisonedBottleId <= 0 || poisonedBottleId > NUMBER_OF_BOTTLES) {
            throw new IllegalArgumentException();
        }
        for(int i = 1; i <= NUMBER_OF_BOTTLES; i++) {
            Bottle bottle = new Bottle(i);
            bottles.add(bottle);
        }
        for(int i = 0; i < NUMBER_OF_TEST_STRIPS; i++) {
            TestStrip testStrip = new TestStrip(i);
            testStrips.add(testStrip);
        }
        Bottle poisonedBottle =
                bottles.get(poisonedBottleId - 1);
        poisonedBottle.setPoisoned(true);
    }
    public void testBottles() {
        for(Bottle bottle : bottles) {
            mapBottleToBinary(bottle);
        }
    }
    private void mapBottleToBinary(Bottle bottle) {
        int id = bottle.getId();
        for(int i = 0; i < NUMBER_OF_TEST_STRIPS; i++) {
            if(getBit(id, i)) {
                TestStrip testStrip = testStrips.get(i);
                testStrip.addBottle(bottle, day);
            }
        }
    }
    private static boolean getBit(int number, int i) {
        return (number & (1 << i)) != 0;
    }
    public Optional<Integer> getResult() {
        int poisonedBottleId = 0;
        for(int i = 0; i < NUMBER_OF_TEST_STRIPS; i++) {
            TestStrip testStrip = testStrips.get(i);
            if(testStrip.hasPoison(day)) {
                poisonedBottleId = setBit(poisonedBottleId, i);
            }
        }
        if(poisonedBottleId == 0) {
            return Optional.empty();
        } else {
            return Optional.of(poisonedBottleId);
        }
    }
    private static int setBit(int number, int i) {
        return number | (1 << i);
    }
    public void incrementDay() {
        day++;
    }
    public static void main(String[] args) {
        Poison poison = new Poison();
        poison.initialize(1000);
        poison.testBottles();
        for(int i = 0; i <= 7; i++) {
            Optional<Integer> optional = poison.getResult();
            if(optional.isPresent()) {
                System.out.println("Poisoned Bottle: " + optional.get());
            }
            poison.incrementDay();
        }
    }
}
