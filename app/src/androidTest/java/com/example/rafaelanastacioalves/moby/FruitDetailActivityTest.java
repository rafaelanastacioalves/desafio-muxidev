package com.example.rafaelanastacioalves.moby;

import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.rafaelanastacioalves.moby.fruitdetailing.FruitDetailFragment;
import com.example.rafaelanastacioalves.moby.fruitdetailing.FruitDetailActivity;
import com.example.rafaelanastacioalves.moby.util.RestServiceTestHelper;
import com.example.rafaelanastacioalves.moby.vo.Fruit;

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
import static org.hamcrest.CoreMatchers.allOf;


@RunWith(AndroidJUnit4.class)
public class FruitDetailActivityTest {
    @Rule
    public ActivityTestRule<FruitDetailActivity> tripPackageDetailActivityTestRule = new ActivityTestRule<FruitDetailActivity>(FruitDetailActivity.class, true, false);
    private String fruitListOkResponse = "fruit_list_ok_response.json";
    private MockWebServer server;


    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start(1234);
        InstrumentationRegistry.registerInstance(InstrumentationRegistry.getInstrumentation(),new Bundle());
        server.url("/").toString();
    }

    @Test
    public void shouldShowTripPackageDetailSuccess() throws IOException {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(
                        InstrumentationRegistry.getInstrumentation().getContext()
                        , fruitListOkResponse)
                )
        );

        Intent intent = new Intent();
        Fruit fruit = new Fruit();
        fruit.setImage("https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Red_Apple.jpg/265px-Red_Apple.jpg");
        fruit.setName("Apple");
        fruit.setPrice(35);
        intent.putExtra(FruitDetailFragment.ARG_FRUIT_OBJECT, fruit);

        tripPackageDetailActivityTestRule.launchActivity(intent);
        onView(allOf(withId(R.id.fruit_name_text_view), withText("Apple"))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fruit_original_price_text_view), withText("35.0"))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fruit_converted_price_text_view), withText("70.0"))).check(matches(isDisplayed()));

    }


    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }
}
