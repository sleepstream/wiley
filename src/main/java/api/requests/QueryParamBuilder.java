package api.requests;

import java.util.HashMap;

public class QueryParamBuilder {

    public HashMap<String, Object> params = new HashMap<>();

    public QueryParamBuilder search(String param) {
        params.put("term", param);
        return this;
    }

    public QueryParamBuilder delay(String param) {
        params.put("delay", param);
        return this;
    }
}
