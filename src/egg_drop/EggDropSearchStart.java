package egg_drop;

public class EggDropSearchStart {
    private int searchStart;
    private int interval;
    public EggDropSearchStart(int searchStart, int interval) {
        this.searchStart = searchStart;
        this.interval = interval;
    }
    public int getSearchStart() {
        return searchStart;
    }

    public int getInterval() {
        return interval;
    }
}
