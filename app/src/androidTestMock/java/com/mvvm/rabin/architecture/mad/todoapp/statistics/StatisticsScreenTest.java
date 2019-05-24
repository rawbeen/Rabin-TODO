package com.mvvm.rabin.architecture.mad.todoapp.statistics;

import android.app.Application;
import android.content.Intent;

import com.mvvm.rabin.architecture.mad.todoapp.R;
import com.mvvm.rabin.architecture.mad.todoapp.ViewModelFactory;
import com.mvvm.rabin.architecture.mad.todoapp.data.Task;
import com.mvvm.rabin.architecture.mad.todoapp.data.source.TasksRepository;
import com.mvvm.rabin.architecture.mad.todoapp.util.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

/**
 * Tests for the statistics screen.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class StatisticsScreenTest {

    /**
     * {@link ActivityTestRule} is a JUnit {@link Rule @Rule} to launch your activity under test.
     *
     * <p>
     * Rules are interceptors which are executed for each test method and are important building
     * blocks of Junit tests.
     */
    @Rule
    public ActivityTestRule<StatisticsActivity> mStatisticsActivityTestRule =
            new ActivityTestRule<>(StatisticsActivity.class, false, false);

    @Before
    public void prepareRepository() throws Throwable {
        mStatisticsActivityTestRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TasksRepository repo = ViewModelFactory.getInstance(
                        (Application) ApplicationProvider.getApplicationContext())
                        .getTasksRepository();

                repo.deleteAllTasks();
                repo.saveTask(new Task("St1", "", false));
                repo.saveTask(new Task("St2", "", true));
            }
        });

        Intent intent = new Intent(ApplicationProvider.getApplicationContext(),
                StatisticsActivity.class);

        mStatisticsActivityTestRule.launchActivity(intent);
    }

    /**
     * Prepare your test fixture for this test. In this case we register an IdlingResources with
     * Espresso. IdlingResource resource is a great way to tell Espresso when your app is in an
     * idle state. This helps Espresso to synchronize your test actions, which makes tests
     * significantly more reliable.
     */
    @Before
    public void registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
    }

    /**
     * Unregister your Idling Resource so it can be garbage collected and does not leak any memory.
     */
    @After
    public void unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
    }

    @Test
    public void Tasks_ShowsNonEmptyMessage() {
        // Check that the active and completed tasks text is displayed
        String expectedActiveTaskText = String.format(ApplicationProvider.getApplicationContext()
                .getString(R.string.statistics_active_tasks), 1);
        onView(withText(containsString(expectedActiveTaskText))).check(matches(isDisplayed()));
        String expectedCompletedTaskText = String.format(ApplicationProvider.getApplicationContext()
                .getString(R.string.statistics_completed_tasks), 1);
        onView(withText(containsString(expectedCompletedTaskText))).check(matches(isDisplayed()));
    }
}
