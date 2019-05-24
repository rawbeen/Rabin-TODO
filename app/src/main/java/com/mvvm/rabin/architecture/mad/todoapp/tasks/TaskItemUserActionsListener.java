package com.mvvm.rabin.architecture.mad.todoapp.tasks;


import android.view.View;

import com.mvvm.rabin.architecture.mad.todoapp.data.Task;

/**
 * Listener used with data binding to process user actions.
 */
public interface TaskItemUserActionsListener {
    void onCompleteChanged(Task task, View v);

    void onTaskClicked(Task task);
}
