package omabralimited.myinvoices.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import omabralimited.myinvoices.InvoiceInfo;

public class InvoiceDbHelper extends SQLiteOpenHelper {


    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME = "Voter.db";
    private SQLiteDatabase database;
    public InvoiceDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        database = db;
        db.execSQL(InvoiceDbContract.User.SQL_CREATE_TABLE);
        db.execSQL(InvoiceDbContract.Invoices.SQL_CREATE_TABLE);

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(InvoiceDbContract.User.SQL_DELETE_TABLE);
        db.execSQL(InvoiceDbContract.Invoices.SQL_DELETE_TABLE);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    public boolean insertUser(String name, String country, String email, String phoneNumber, String profPic, String password
             ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(InvoiceDbContract.User.COLUMN_NAME_NAME, name);
        contentValues.put(InvoiceDbContract.User.COLUMN_NAME_PROFILE_PICTURE, profPic);
        contentValues.put(InvoiceDbContract.User.COLUMN_NAME_EMAIL, email);
        contentValues.put(InvoiceDbContract.User.COLUMN_NAME_PHONENUMBER, phoneNumber);
        contentValues.put(InvoiceDbContract.User.COLUMN_NAME_PASSWORD, password);
        contentValues.put(InvoiceDbContract.User.COLUMN_NAME_COUNTRY, country);
        db.insert(InvoiceDbContract.User.TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean updatePerson(Integer id, String name, String country, String email, String phoneNumber, String profPic, String password
            ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(InvoiceDbContract.User.COLUMN_NAME_NAME, name);
        contentValues.put(InvoiceDbContract.User.COLUMN_NAME_PROFILE_PICTURE, profPic);
        contentValues.put(InvoiceDbContract.User.COLUMN_NAME_EMAIL, email);
        contentValues.put(InvoiceDbContract.User.COLUMN_NAME_PHONENUMBER,phoneNumber);
        contentValues.put(InvoiceDbContract.User.COLUMN_NAME_PASSWORD, password);
        contentValues.put(InvoiceDbContract.User.COLUMN_NAME_COUNTRY, country);
        db.update(InvoiceDbContract.User.TABLE_NAME, contentValues, InvoiceDbContract.User.COLUMN_NAME_USER_ID + " = ? ", new String[]{Integer.toString(id)});
        return true;
    }
    public Cursor getPerson(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + InvoiceDbContract.User.TABLE_NAME + " WHERE " +
                InvoiceDbContract.User.COLUMN_NAME_EMAIL + "=?", new String[] { email } );
        return res;
    }
    public Cursor getAllPersons() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + InvoiceDbContract.User.TABLE_NAME, null);
        return res;
    }
    public String getSingleEntry(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + InvoiceDbContract.User.TABLE_NAME + " WHERE " +
                InvoiceDbContract.User.COLUMN_NAME_EMAIL + "=?", new String[]{email});
        if (cursor.getCount() < 1) {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex(InvoiceDbContract.User.COLUMN_NAME_PASSWORD));
        cursor.close();
        return password;
    }
    public boolean insertInvoice(String title, String profPic, String date, String type, String location, String shopname, String comment) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(InvoiceDbContract.Invoices.COLUMN_NAME_INVOICE_TITLE,title);
        contentValues.put(InvoiceDbContract.Invoices.COLUMN_NAME_PICTURE, profPic);
        contentValues.put(InvoiceDbContract.Invoices.COLUMN_NAME__INVOICE_DATE, date);
        contentValues.put(InvoiceDbContract.Invoices.COLUMN_NAME_INVOICE_TYPE, type);
        contentValues.put(InvoiceDbContract.Invoices.COLUMN_NAME_LOCATION, location);
        contentValues.put(InvoiceDbContract.Invoices.COLUMN_SHOP_NAME, shopname);
        contentValues.put(InvoiceDbContract.Invoices.COLUMN_COMMENT, comment);
        System.out.println(contentValues);

        db.insert(InvoiceDbContract.Invoices.TABLE_NAME, null, contentValues);
        return true;
    }
    public ArrayList<InvoiceInfo> getAllInvoices() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<InvoiceInfo> arrInvoices = new ArrayList<InvoiceInfo>();
        InvoiceInfo invoiceInfo=null;
        Cursor res = db.rawQuery("SELECT * FROM " + InvoiceDbContract.Invoices.TABLE_NAME, null);
       res.moveToFirst();
        while (res.isAfterLast() == false)
        {
        String title = res.getString(res.getColumnIndex(InvoiceDbContract.Invoices.COLUMN_NAME_INVOICE_TITLE));
        String picture = res.getString(res.getColumnIndex(InvoiceDbContract.Invoices.COLUMN_NAME_PICTURE));
        String date = res.getString(res.getColumnIndex(InvoiceDbContract.Invoices.COLUMN_NAME__INVOICE_DATE));
        String type = res.getString(res.getColumnIndex(InvoiceDbContract.Invoices.COLUMN_NAME_INVOICE_TYPE));
        String location = res.getString(res.getColumnIndex(InvoiceDbContract.Invoices.COLUMN_NAME_LOCATION));
        String shopName = res.getString(res.getColumnIndex(InvoiceDbContract.Invoices.COLUMN_SHOP_NAME));
        String comment = res.getString(res.getColumnIndex(InvoiceDbContract.Invoices.COLUMN_COMMENT));
        //String timeOfReg = res.getString(res.getColumnIndex(InvoiceDbContract.Invoices.COLUMN_NAME_TIME_OF_REG));
        String id = res.getString(res.getColumnIndex(InvoiceDbContract.Invoices._ID));

        InvoiceInfo invoice  = new InvoiceInfo();
        invoice.setTitle(title);
        invoice.setDate(date);
        invoice.setProfilePic(picture);
        invoice.setType(type);
        invoice.setLocation(location);
        invoice.setShopName(shopName);
        invoice.setId(id);
        invoice.setComment(comment);
        if (invoice != null)
        {

            arrInvoices.add(invoice);

              }
           res.moveToNext();
        }
        res.close();
        return arrInvoices;
    }
   public Cursor getInvoice(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + InvoiceDbContract.Invoices.TABLE_NAME + " WHERE " +
                InvoiceDbContract.Invoices._ID + "=?", new String[]{Integer.toString(id)});

        return res;
    }

    public boolean updateInvoice(Integer id, String title, String profPic, String date, String type, String location, String shopname, String comment) {
        SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();
      contentValues.put(InvoiceDbContract.Invoices.COLUMN_NAME_INVOICE_TITLE,title);
      contentValues.put(InvoiceDbContract.Invoices.COLUMN_NAME_PICTURE, profPic);
      contentValues.put(InvoiceDbContract.Invoices.COLUMN_NAME__INVOICE_DATE, date);
      contentValues.put(InvoiceDbContract.Invoices.COLUMN_NAME_INVOICE_TYPE, type);
      contentValues.put(InvoiceDbContract.Invoices.COLUMN_NAME_LOCATION, location);
      contentValues.put(InvoiceDbContract.Invoices.COLUMN_SHOP_NAME, shopname);
      contentValues.put(InvoiceDbContract.Invoices.COLUMN_COMMENT, comment);

        db.update(InvoiceDbContract.Invoices.TABLE_NAME, contentValues, InvoiceDbContract.Invoices._ID  + " = ? ", new String[]{Integer.toString(id)} );
        return true;
    }

}
