package poison;

public class Bottle {

    private int id;
    private boolean poisoned;
    public Bottle(int id) {
        this.id = id;
        poisoned = false;
    }
    public int getId() {
        return id;
    }
    public boolean isPoisoned() {
        return poisoned;
    }
    public void setPoisoned(boolean poisoned) {
        this.poisoned = poisoned;
    }

}
