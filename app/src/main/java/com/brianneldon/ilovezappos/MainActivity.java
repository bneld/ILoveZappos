package com.brianneldon.ilovezappos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://api.zappos.com/";
    private ZapposService zapposService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Create and configure API client
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        zapposService = retrofit.create(ZapposService.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void onClickBtn(View view){
        Log.d("", "Button pressed.");
        EditText searchBar = (EditText) findViewById(R.id.main_search_bar);
        this.call(searchBar.getText().toString());
    }

    private void call(String query){

        Call<ResponseObject> call = zapposService.listResults(query);
        call.enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, retrofit2.Response<ResponseObject> response) {
                int statusCode = response.code();
                ResponseObject ro = response.body();
                Log.d("", "Product: " + ro.toString());
                if(!ro.getResults().isEmpty()) {
                    Log.d("", ro.getResults().get(0).toString());
                    ProductManager.setProducts(ro.getResults());
                    launchProductPage();
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(), "No results matched your search term.", Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                // Log error here since request failed
                Log.d("", "Request failed.");
                Toast toast = Toast.makeText(getApplicationContext(), "Failure to connect.", Toast.LENGTH_LONG);
                toast.show();
            }

        });
    }

    public void launchProductPage(){
        Intent intent = new Intent(this, ProductActivity.class);
        startActivity(intent);
    }


}
