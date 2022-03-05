package com.jasmine.employeedirectory.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jasmine.employeedirectory.model.EmployeeModel;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "EmployeeDirectoryDB";
    private static final String TABLE_EMPLOYEES = "employees";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_USER_NAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_STREET = "street";
    private static final String KEY_SUITE = "suite";
    private static final String KEY_CITY = "city";
    private static final String KEY_ZIP_CODE = "zipcode";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_WEBSITE = "website";
    private static final String KEY_LAT = "lat";
    private static final String KEY_LONG = "lng";
    private static final String KEY_COMPANY_NAME = "companyname";
    private static final String KEY_CATCH_PHRASE = "catchphrase";
    private static final String KEY_BS = "bs";
    private static final String KEY_PROFILE_IMAGE = "profile_image";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EMPLOYEE_TABLE = "CREATE TABLE " + TABLE_EMPLOYEES + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_USER_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_PROFILE_IMAGE + " TEXT,"
                + KEY_STREET + " TEXT,"
                + KEY_SUITE + " TEXT,"
                + KEY_CITY + " TEXT,"
                + KEY_ZIP_CODE + " TEXT,"
                + KEY_LAT + " TEXT,"
                + KEY_LONG + " TEXT,"
                + KEY_COMPANY_NAME + " TEXT,"
                + KEY_CATCH_PHRASE + " TEXT,"
                + KEY_BS + " TEXT,"
                + KEY_PHONE + " TEXT,"
                + KEY_WEBSITE + " TEXT" + ")";
        db.execSQL(CREATE_EMPLOYEE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEES);

        // Create tables again
        onCreate(db);
    }

    // code to add the new employee
    public void addEmployee(EmployeeModel employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, employee.getEmployeeId()); //  Id
        values.put(KEY_NAME, employee.getEmployeeName()); //  Name
        values.put(KEY_USER_NAME, employee.getEmployeeUserName()); //  Username
        values.put(KEY_EMAIL, employee.getEmployeeEmail()); //  Email
        values.put(KEY_PROFILE_IMAGE, employee.getEmployeeProfileImage()); //  Profile image
        values.put(KEY_STREET, employee.getEmployeeStreet()); //  Street
        values.put(KEY_SUITE, employee.getEmployeeSuite()); //  Suite
        values.put(KEY_CITY, employee.getEmployeeCity()); //  City
        values.put(KEY_ZIP_CODE, employee.getEmployeeZipCode()); //  Zip
        values.put(KEY_LAT, employee.getEmployeeLatitude()); //  Latitude
        values.put(KEY_LONG, employee.getEmployeeLongitude()); //  Longitude
        values.put(KEY_COMPANY_NAME, employee.getEmployeeCompanyName()); //  company name
        values.put(KEY_CATCH_PHRASE, employee.getEmployeeCompanyCatchPhrase()); //  catch phrase
        values.put(KEY_BS, employee.getEmployeeCompanyBs()); //  bs
        values.put(KEY_PHONE, employee.getEmployeePhone()); //  Phone
        values.put(KEY_WEBSITE, employee.getEmployeeWebsite()); //  Website

        // Inserting Row
        db.insert(TABLE_EMPLOYEES, null, values);
        db.close(); // Closing database connection
    }

    // code to get all employees
    public ArrayList<EmployeeModel> getAllEmployees() {
        ArrayList<EmployeeModel> employeeList = new ArrayList<EmployeeModel>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EMPLOYEES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                EmployeeModel employee = new EmployeeModel();
                employee.setEmployeeId(Integer.parseInt(cursor.getString(0)));
                employee.setEmployeeName(cursor.getString(1));
                employee.setEmployeeUserName(cursor.getString(2));
                employee.setEmployeeEmail(cursor.getString(3));
                employee.setEmployeeProfileImage(cursor.getString(4));
                employee.setEmployeeStreet(cursor.getString(5));
                employee.setEmployeeSuite(cursor.getString(6));
                employee.setEmployeeCity(cursor.getString(7));
                employee.setEmployeeZipCode(cursor.getString(8));
                employee.setEmployeeLatitude(cursor.getString(9));
                employee.setEmployeeLongitude(cursor.getString(10));
                employee.setEmployeeCompanyName(cursor.getString(11));
                employee.setEmployeeCompanyCatchPhrase(cursor.getString(12));
                employee.setEmployeeCompanyBs(cursor.getString(13));
                employee.setEmployeePhone(cursor.getString(14));
                employee.setEmployeeWebsite(cursor.getString(15));

                // Adding employee to list
                employeeList.add(employee);
            } while (cursor.moveToNext());
        }

        // return employee list
        return employeeList;
    }

    // Getting Employee Count
    public int getEmployeesCount() {
        int count = 0;
        String countQuery = "SELECT  * FROM " + TABLE_EMPLOYEES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        try {
            if (cursor.moveToFirst()) {
                count = cursor.getCount();
            }
            return count;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

}
