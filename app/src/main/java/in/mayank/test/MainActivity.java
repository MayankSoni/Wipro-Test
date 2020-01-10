package in.mayank.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;

import in.mayank.test.adapter.BindAdapter;
import in.mayank.test.model.Datam;
import in.mayank.test.network.APIClient;
import in.mayank.test.network.APIInterface;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private APIInterface apiInterface;
    private boolean responseFailed = false;
    private SwipeRefreshLayout swipeContainer;
    private BindAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();
        setData();
    }

    private void setData() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
        final Call<ResponseBody> call1 = apiInterface.getNetworkData();
        call1.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    swipeContainer.setRefreshing(false);

                    try {
                        String res = response.body().string();

                        try {
                            Gson gson = new Gson();
                            Reader reader = new StringReader(res);
                            Datam datam = gson.fromJson(reader, Datam.class);

                            if (datam.getRows().size() > 0) {
                                adapter = new BindAdapter(MainActivity.this, datam.getRows());
                                recyclerView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(MainActivity.this, "No Data Found.", Toast.LENGTH_SHORT).show();
                            }

                        } catch (IllegalStateException | JsonSyntaxException exception) {
                            responseFailed = true;
                        }

                        if (responseFailed) {
                            Toast.makeText(MainActivity.this, "No Data Found.", Toast.LENGTH_SHORT).show();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Unable to get response from server.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Init() {
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setData();
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorPrimaryDark);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
    }
}
