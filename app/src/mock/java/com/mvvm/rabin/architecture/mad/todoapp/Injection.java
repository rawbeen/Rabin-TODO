

package com.mvvm.rabin.architecture.mad.todoapp;

import android.content.Context;

import com.mvvm.rabin.architecture.mad.todoapp.data.FakeTasksRemoteDataSource;
import com.mvvm.rabin.architecture.mad.todoapp.data.source.TasksDataSource;
import com.mvvm.rabin.architecture.mad.todoapp.data.source.TasksRepository;
import com.mvvm.rabin.architecture.mad.todoapp.data.source.local.TasksLocalDataSource;
import com.mvvm.rabin.architecture.mad.todoapp.data.source.local.ToDoDatabase;
import com.mvvm.rabin.architecture.mad.todoapp.util.AppExecutors;

import androidx.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Enables injection of mock implementations for
 * {@link TasksDataSource} at compile time. This is useful for testing, since it allows us to use
 * a fake instance of the class to isolate the dependencies and run a test hermetically.
 */
public class Injection {

    public static TasksRepository provideTasksRepository(@NonNull Context context) {
        checkNotNull(context);
        ToDoDatabase database = ToDoDatabase.getInstance(context);
        return TasksRepository.getInstance(FakeTasksRemoteDataSource.getInstance(),
                TasksLocalDataSource.getInstance(new AppExecutors(),
                        database.taskDao()));
    }
}
