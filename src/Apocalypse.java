import java.util.Random;

public class Apocalypse {
    public static double getNumberOfBoysTheoretical(long numberOfFamilies, int numberOfChildrenPerFamily) {
        double percentageOfBoys = 0;
        for(int numberOfBoys = 0; numberOfBoys < numberOfChildrenPerFamily; numberOfBoys++) {
            percentageOfBoys += (Math.pow(0.5, numberOfBoys + 1) * numberOfBoys);
        }
        return numberOfFamilies * percentageOfBoys;
    }
    public static long getNumberOfBoysExperimental(long numberOfFamilies, int numberOfChildrenPerFamily) {
        long numberOfBoys = 0;
        for(int i = 0; i < numberOfFamilies; i++) {
            numberOfBoys += runFamily(numberOfChildrenPerFamily);
        }
        return numberOfBoys;
    }
    private static int runFamily(int numberOfChildrenPerFamily) {
        Random random = new Random();
        int numberOfBoys = 0;
        for(int i = 0; i < numberOfChildrenPerFamily; i++) {
            boolean female = random.nextBoolean();
            if(female) {
                return numberOfBoys;
            }
            numberOfBoys++;
        }
        return numberOfBoys;
    }
    public static void main(String[] args) {
        long numberOfFamilies = 100000;
        int numberOfChildrenPerFamily = 15;
        double numberOfBoysTheoretical = getNumberOfBoysTheoretical(numberOfFamilies, numberOfChildrenPerFamily);
        printResult("Theoretical", numberOfFamilies, numberOfBoysTheoretical);
        long numberOfBoysExperimental = getNumberOfBoysExperimental(numberOfFamilies, numberOfChildrenPerFamily);
        printResult("Experimental", numberOfFamilies, numberOfBoysExperimental);
    }
    private static void printResult(String type, long numberOfFamilies, double numberOfBoys) {
        StringBuilder stringBuilder = new StringBuilder(type);
        stringBuilder.append(" number of boys from ");
        stringBuilder.append(numberOfFamilies);
        stringBuilder.append(" families: ");
        stringBuilder.append(numberOfBoys);
        System.out.println(stringBuilder);
    }
}
