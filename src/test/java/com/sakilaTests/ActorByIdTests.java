package com.sakilaTests;

import org.testng.annotations.BeforeMethod;

public class ActorByIdTests extends SetUpTests {

    private String id = "5";

    @BeforeMethod
    public void pageSetUp() {
        driver.get("http://localhost:5173/actor/" + id);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
