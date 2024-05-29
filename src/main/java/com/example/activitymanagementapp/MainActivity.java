package com.example.activitymanagementapp;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTask;
    private Spinner spinnerPriority, spinnerStatus;
    private Button buttonAddTask, buttonViewTasks, buttonDeleteTask;
    private TextView textViewSelectedTask;
    private ListView listViewTasks;
    private ArrayAdapter<String> adapter;
    private ArrayList<Pair<String, String>> tasks;
    private DatePicker datePicker;
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonViewTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tasks.isEmpty()) {
                    String selectedTask = getFirstTask();
                    String selectedPriorityStatus = tasks.get(0).second;
                    textViewSelectedTask.setText("Selected Task: " + selectedTask + "\nPriority and Status: " + selectedPriorityStatus);
                }
            }

            private String getFirstTask() {
                return null;
            }
        });

        editTextTask = findViewById(R.id.editTextTask);
        spinnerPriority = findViewById(R.id.spinnerPriority);
        spinnerStatus = findViewById(R.id.spinnerStatus);
        buttonAddTask = findViewById(R.id.buttonAddTask);
        buttonViewTasks = findViewById(R.id.buttonViewTasks);
        buttonDeleteTask = findViewById(R.id.buttonDeleteTask);
        textViewSelectedTask = findViewById(R.id.textViewSelectedTask);
        listViewTasks = findViewById(R.id.listViewTasks);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);

        tasks = new ArrayList<>();
        //adapter = new ArrayAdapter<>((Context) this, android.R.layout.simple_list_item_1, (Integer) tasks.stream().map(Pair::first).collect(Collectors.toList()));
        listViewTasks.setAdapter(adapter);

        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = editTextTask.getText().toString().trim();
                String priority = spinnerPriority.getSelectedItem().toString();
                String status = spinnerStatus.getSelectedItem().toString();
                if (!task.isEmpty()) {
                    tasks.add(new Pair<>(task, priority + " - " + status));
                    adapter.notifyDataSetChanged();
                    editTextTask.setText("");
                }
            }
        });

        buttonViewTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedPosition = listViewTasks.getSelectedItemPosition();
                if (selectedPosition != AdapterView.INVALID_POSITION) {
                    String selectedTask = tasks.get(selectedPosition).first;
                    String selectedPriorityStatus = tasks.get(selectedPosition).second;
                    textViewSelectedTask.setText("Selected Task: " + selectedTask + "\nPriority and Status: " + selectedPriorityStatus);
                }
            }
        });

        buttonDeleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedPosition = listViewTasks.getSelectedItemPosition();
                if (selectedPosition != AdapterView.INVALID_POSITION) {
                    tasks.remove(selectedPosition);
                    adapter.notifyDataSetChanged();
                    textViewSelectedTask.setText("");
                }
            }
        });
    }
}