package com.example.rafaelanastacioalves.moby;

import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.rafaelanastacioalves.moby.entitymainlisting.FruitListActivity;
import com.example.rafaelanastacioalves.moby.util.RestServiceTestHelper;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;



@RunWith(AndroidJUnit4.class)
public class FruitListActivityTest {

    @Rule
    public ActivityTestRule<FruitListActivity> mainActivityActivityTestRule = new ActivityTestRule<FruitListActivity>(FruitListActivity.class, true, false);
    private String fileNameFruitListOKResponse = "fruit_list_ok_response.json";
    private MockWebServer server;

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start(1234);
        InstrumentationRegistry.registerInstance(InstrumentationRegistry.getInstrumentation(),new Bundle());
        server.url("/").toString();


    }

    @Test
    public void shouldShowTripPackageSuccess() throws IOException {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(
                        InstrumentationRegistry.getInstrumentation().getContext()
                        , fileNameFruitListOKResponse)
                )
        );



        Intent intent = new Intent();

        mainActivityActivityTestRule.launchActivity(intent);

        onView(CoreMatchers.allOf(withId(R.id.fruit_name_text_view), withText("Apple"))).check(matches(isDisplayed()));
        onView(CoreMatchers.allOf(withId(R.id.fruit_price), withText("35.0"))).check(matches(isDisplayed()));

    }


    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }
}
