package api.enums;

public enum Endpoints {
    //-----------------------endpoints for api v2----------------------//

    SEARCH("/search/autocomplete/comp_00001H9J"),
    DELAY("/delay"),
    PNG("/image/png");

    public String endpoint;

    Endpoints(String endpoint) {
        this.endpoint = endpoint;
    }
}
