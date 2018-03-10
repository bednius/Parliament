package main;

public class Data {
    private Politician[] politicians;
    private String nextUrl;
    private String lastUrl;

    public Data(Politician[] politicians) {
        this.politicians = politicians;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public String getLastUrl() {
        return lastUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

    public void setLastUrl(String lastUrl) {
        this.lastUrl = lastUrl;
    }

    public Politician[] getPoliticians() {
        return politicians;
    }
}
