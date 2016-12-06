package com.archana.weathertest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    EditText write;

    public String getGetText() {
        return getText;
    }

    public void setGetText(String getText) {
        this.getText = getText;
    }

    private String getText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchable);
        button = (Button) findViewById(R.id.button);
        write = (EditText) findViewById(R.id.write);
        button.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        EditText modifText = (EditText) findViewById(R.id.write);
        String getText = modifText.getText().toString();
        setGetText(getText);
        System.out.println(getText);

        switch(v.getId()){
            case R.id.button:
                Intent intent = new Intent(SearchActivity.this, CityActivity.class);
                intent.putExtra("cityname", getText);
                SearchActivity.this.startActivity(intent);
                break;
        }
    }
}
