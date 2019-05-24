package com.mvvm.rabin.architecture.mad.todoapp.taskdetail;


import android.view.View;

/**
 * Listener used with data binding to process user actions.
 */
public interface TaskDetailUserActionsListener {

    void onCompleteChanged(View v);
}
