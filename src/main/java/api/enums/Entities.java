package api.enums;

public enum Entities {

    SEARCH(Endpoints.SEARCH),
    DELAY(Endpoints.DELAY),
    PNG(Endpoints.PNG);

    public Endpoints endpoint;

    Entities(Endpoints endpoint) {
        this.endpoint = endpoint;
    }
}
