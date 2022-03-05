package com.jasmine.employeedirectory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class EmployeeDetailsActivity extends Activity {

    ImageView ivImg;
    TextView tvName, tvUserName, tvEmail, tvAddress, tvPhone, tvWebsite,
            tvCompanyDetails, tvStreet, tvSuite, tvCity, tvZipCode, tvGeo, tvLat, tvLong, tvCompanyName, tvCatchPhrase, tvBs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_details);

        ivImg = (ImageView) findViewById(R.id.ivImg);
        tvName = (TextView) findViewById(R.id.tvName);
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        tvStreet = (TextView) findViewById(R.id.tvStreet);
        tvSuite = (TextView) findViewById(R.id.tvSuite);
        tvCity = (TextView) findViewById(R.id.tvCity);
        tvZipCode = (TextView) findViewById(R.id.tvZipCode);
        tvGeo = (TextView) findViewById(R.id.tvGeo);
        tvLat = (TextView) findViewById(R.id.tvLat);
        tvLong = (TextView) findViewById(R.id.tvLong);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvWebsite = (TextView) findViewById(R.id.tvWebsite);
        tvCompanyDetails = (TextView) findViewById(R.id.tvCompanyDetails);
        tvCompanyName = (TextView) findViewById(R.id.tvCompanyName);
        tvCatchPhrase = (TextView) findViewById(R.id.tvCatchPhrase);
        tvBs = (TextView) findViewById(R.id.tvBs);

        tvName.setTypeface(null, Typeface.BOLD);
        tvUserName.setTypeface(null, Typeface.BOLD);
        tvEmail.setTypeface(null, Typeface.BOLD);
        tvAddress.setTypeface(null, Typeface.BOLD);
        tvStreet.setTypeface(null, Typeface.BOLD);
        tvSuite.setTypeface(null, Typeface.BOLD);
        tvCity.setTypeface(null, Typeface.BOLD);
        tvZipCode.setTypeface(null, Typeface.BOLD);
        tvGeo.setTypeface(null, Typeface.BOLD);
        tvLat.setTypeface(null, Typeface.BOLD);
        tvLong.setTypeface(null, Typeface.BOLD);
        tvPhone.setTypeface(null, Typeface.BOLD);
        tvWebsite.setTypeface(null, Typeface.BOLD);
        tvCompanyDetails.setTypeface(null, Typeface.BOLD);
        tvCompanyName.setTypeface(null, Typeface.BOLD);
        tvCatchPhrase.setTypeface(null, Typeface.BOLD);
        tvBs.setTypeface(null, Typeface.BOLD);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            //Toast.makeText(getApplicationContext(), bundle.getString("name"), Toast.LENGTH_LONG).show();
            tvName.setText("Name: " + bundle.getString("name"));
            tvUserName.setText("Username: " + bundle.getString("username"));
            tvEmail.setText("Email: " + bundle.getString("email"));
            Picasso.get().load(bundle.getString("profile_image")).placeholder(R.drawable.ic_launcher_background).
                    error(R.drawable.ic_launcher_background).into(ivImg);
            tvStreet.setText("Street: " + bundle.getString("street"));
            tvSuite.setText("Suite: " + bundle.getString("suite"));
            tvCity.setText("City: " + bundle.getString("city"));
            tvZipCode.setText("Zip code: " + bundle.getString("zipcode"));
            tvLat.setText("Latitude: " + bundle.getString("lat"));
            tvLong.setText("Longitude: " + bundle.getString("lng"));
            tvPhone.setText("Phone: " + bundle.getString("phone"));
            tvWebsite.setText("Website: " + bundle.getString("website"));
            tvCompanyName.setText("Name: " + bundle.getString("companyName"));
            tvCatchPhrase.setText("Catch phrase: " + bundle.getString("catchPhrase"));
            tvBs.setText("BS: " + bundle.getString("bs"));
        }
    }

}
