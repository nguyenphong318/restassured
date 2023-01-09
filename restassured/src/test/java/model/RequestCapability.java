package model;

import io.restassured.http.Header;

import java.util.logging.Handler;

public interface RequestCapability {

    Header defaultHeader = new Header("Content-Type", "application/json; charset=UTF-8");
}
