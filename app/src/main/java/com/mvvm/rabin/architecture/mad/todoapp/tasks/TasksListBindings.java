package com.mvvm.rabin.architecture.mad.todoapp.tasks;

import androidx.databinding.BindingAdapter;
import android.widget.ListView;

import com.mvvm.rabin.architecture.mad.todoapp.data.Task;

import java.util.List;

/**
 * Contains {@link BindingAdapter}s for the {@link Task} list.
 */
public class TasksListBindings {

    @SuppressWarnings("unchecked")
    @BindingAdapter("app:items")
    public static void setItems(ListView listView, List<Task> items) {
        TasksAdapter adapter = (TasksAdapter) listView.getAdapter();
        if (adapter != null)
        {
            adapter.replaceData(items);
        }
    }
}
