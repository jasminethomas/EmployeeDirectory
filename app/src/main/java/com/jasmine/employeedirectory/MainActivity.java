package com.jasmine.employeedirectory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jasmine.employeedirectory.Adapter.EmployeeDirectoryAdapter;
import com.jasmine.employeedirectory.DB.DatabaseHandler;
import com.jasmine.employeedirectory.model.EmployeeModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String JSON_URL = "http://www.mocky.io/v2/5d565297300000680030a986";
    // creating variables for ui components.
    private RecyclerView employeeRV;
    private ProgressBar progressBar;
    // variable for adapter class and array list
    private EmployeeDirectoryAdapter adapter;
    private ArrayList<EmployeeModel> employeeModelArrayList;
    //private ArrayList<EmployeeModel> employeeList;
    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing our variables.
        employeeRV = findViewById(R.id.rvDirectoryList);
        progressBar = findViewById(R.id.progressBar);
        dbHandler = new DatabaseHandler(this);
        //employeeList = new ArrayList<>();
        employeeModelArrayList = new ArrayList<>();

        // calling method to build recycler view.
        buildRecyclerView();
    }

    // calling on create option menu
    // layout to inflate our menu file.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // below line is to get our inflater
        MenuInflater inflater = getMenuInflater();

        // inside inflater we are inflating our menu file.
        inflater.inflate(R.menu.search_menu, menu);

        // below line is to get our menu item.
        MenuItem searchItem = menu.findItem(R.id.actionSearch);

        // getting search view of our item.
        SearchView searchView = (SearchView) searchItem.getActionView();

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });
        return true;
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<EmployeeModel> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (EmployeeModel item : employeeModelArrayList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getEmployeeName().toLowerCase().contains(text.toLowerCase())) {
                // if the name is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            } else if (item.getEmployeeEmail().toLowerCase().contains(text.toLowerCase())) {
                // if the email is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapter.filterList(filteredlist);
        }
    }

    private void buildRecyclerView() {

        progressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        int count = dbHandler.getEmployeesCount();
        Log.d("inside buildRecyclerView--count-->", String.valueOf(count));
        if (count != 0) {
            Log.d("inside buildRecyclerView--->", "not zero");
            //Data already exists
            //Get data from DB as set to recyclerView
            employeeModelArrayList = dbHandler.getAllEmployees();
            setDataToRecyclerView();
        } else {
            Log.d("inside buildRecyclerView--->", " zero");
            //Call API to get response
            getEmployeeDataFromAPI();
        }

    }

    public void getEmployeeDataFromAPI() {

        Log.d("inside getEmployeeDataFromAPI--->", " calling api");
        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        //progressBar.setVisibility(View.INVISIBLE);

                        try {
                            //here we are getting the json array
                            JSONArray jsonArray = new JSONArray(response);

                            //now looping through all the elements of the json array
                            for (int i = 0; i < jsonArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                try {
                                    // below line we are creating a new array list
                                    Log.d("inside getEmployeeDataFromAPI--jsonObject->", String.valueOf(jsonObject));
                                    EmployeeModel employee = new EmployeeModel();
                                    employee.setEmployeeId(jsonObject.getInt("id"));
                                    employee.setEmployeeName(jsonObject.getString("name"));
                                    employee.setEmployeeUserName(jsonObject.getString("username"));
                                    employee.setEmployeeEmail(jsonObject.getString("email"));
                                    employee.setEmployeeProfileImage(jsonObject.getString("profile_image"));

                                    JSONObject addressObj = jsonObject.getJSONObject("address");
                                    employee.setEmployeeStreet(addressObj.getString("street"));
                                    employee.setEmployeeSuite(addressObj.getString("suite"));
                                    employee.setEmployeeCity(addressObj.getString("city"));
                                    employee.setEmployeeZipCode(addressObj.getString("zipcode"));

                                    JSONObject geoObj = addressObj.getJSONObject("geo");
                                    employee.setEmployeeLatitude(geoObj.getString("lat"));
                                    employee.setEmployeeLongitude(geoObj.getString("lng"));

                                    if (!jsonObject.isNull("company") && jsonObject.getString("company").trim().length() != 0) {
                                        JSONObject companyObj = jsonObject.getJSONObject("company");

                                        employee.setEmployeeCompanyName(companyObj.getString("name"));
                                        employee.setEmployeeCompanyCatchPhrase(companyObj.getString("catchPhrase"));
                                        employee.setEmployeeCompanyBs(companyObj.getString("bs"));
                                    }
                                    employee.setEmployeePhone(jsonObject.getString("phone"));
                                    employee.setEmployeeWebsite(jsonObject.getString("website"));

                                    employeeModelArrayList.add(employee);
                                    //Inserting to DB
                                    dbHandler.addEmployee(employee);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            setDataToRecyclerView();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occur
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);

    }

    public void setDataToRecyclerView() {
        Log.v("array length", String.valueOf(employeeModelArrayList.size()));
        // initializing our adapter class.
        adapter = new EmployeeDirectoryAdapter(employeeModelArrayList, MainActivity.this, new ItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                String name = employeeModelArrayList.get(position).getEmployeeName();
                // do what ever you want to do with it
                Toast.makeText(getApplicationContext(), employeeModelArrayList.get(position).getEmployeeEmail(), Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity.this, EmployeeDetailsActivity.class);
                i.putExtra("name", employeeModelArrayList.get(position).getEmployeeName());
                i.putExtra("username", employeeModelArrayList.get(position).getEmployeeUserName());
                i.putExtra("email", employeeModelArrayList.get(position).getEmployeeEmail());
                i.putExtra("profile_image", employeeModelArrayList.get(position).getEmployeeProfileImage());
                i.putExtra("street", employeeModelArrayList.get(position).getEmployeeStreet());
                i.putExtra("suite", employeeModelArrayList.get(position).getEmployeeSuite());
                i.putExtra("city", employeeModelArrayList.get(position).getEmployeeCity());
                i.putExtra("zipcode", employeeModelArrayList.get(position).getEmployeeZipCode());
                i.putExtra("lat", employeeModelArrayList.get(position).getEmployeeLatitude());
                i.putExtra("lng", employeeModelArrayList.get(position).getEmployeeLongitude());
                i.putExtra("phone", employeeModelArrayList.get(position).getEmployeePhone());
                i.putExtra("website", employeeModelArrayList.get(position).getEmployeeWebsite());
                i.putExtra("companyName", employeeModelArrayList.get(position).getEmployeeCompanyName());
                i.putExtra("catchPhrase", employeeModelArrayList.get(position).getEmployeeCompanyCatchPhrase());
                i.putExtra("bs", employeeModelArrayList.get(position).getEmployeeCompanyBs());
                startActivity(i);

            }
        });
        employeeRV.setAdapter(adapter);
        // adding layout manager to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
        employeeRV.setHasFixedSize(true);

        // setting layout manager
        // to our recycler view.
        employeeRV.setLayoutManager(manager);

        // setting adapter to
        // our recycler view.
        employeeRV.setAdapter(adapter);
        progressBar.setVisibility(View.INVISIBLE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

    }


}