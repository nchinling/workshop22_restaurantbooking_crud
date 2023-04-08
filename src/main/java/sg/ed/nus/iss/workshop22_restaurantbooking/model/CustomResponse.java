package sg.ed.nus.iss.workshop22_restaurantbooking.model;

import jakarta.json.JsonObject;

public class CustomResponse {
    private String message;
    private JsonObject jsonObject;

    public CustomResponse(String message, JsonObject jsonObject) {
        this.message = message;
        this.jsonObject = jsonObject;
    }

    public String getMessage() {
        return message;
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }
}

