

package com.mvvm.rabin.architecture.mad.todoapp.util;

import java.util.concurrent.Executor;

import androidx.annotation.NonNull;

/**
 * Allow instant execution of tasks.
 */
public class SingleExecutors extends AppExecutors {
    private static Executor instant = new Executor() {
        @Override
        public void execute(@NonNull Runnable command) {
            command.run();
        }
    };

    public SingleExecutors() {
        super(instant, instant, instant);
    }
}
