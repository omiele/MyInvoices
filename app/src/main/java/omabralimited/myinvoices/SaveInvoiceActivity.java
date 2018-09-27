package omabralimited.myinvoices;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import omabralimited.myinvoices.databases.InvoiceDbHelper;

public class SaveInvoiceActivity extends AppCompatActivity {
  ImageView image;
  EditText etTitle,
          etshopName,
          etdob,
          ettype,
          etlocation,
          etcomment;
  Button bnRegisterVoter;
  Context c;
  Calendar myCalendar = Calendar.getInstance();
  InvoiceDbHelper dbHelper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_saveinvoice);


    c = this;

    ActionBar actionBar = getSupportActionBar();
    actionBar.hide();

    etTitle = (EditText) findViewById(R.id.etTitle);
    etshopName = (EditText) findViewById(R.id.etshopName);
    etdob = (EditText) findViewById(R.id.etdob);
    ettype = (AutoCompleteTextView) findViewById(R.id.ettype);
    etlocation = (EditText) findViewById(R.id.etlocation);
    etcomment = (EditText) findViewById(R.id.comment);

    bnRegisterVoter = (Button) findViewById(R.id.bnSaveInvoice);

    bnRegisterVoter.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        try {
          saveInvoice();
        } catch (ParseException e) {
          e.printStackTrace();
        }
      }
    });
    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

      @Override
      public void onDateSet(DatePicker view, int year, int monthOfYear,
                            int dayOfMonth) {
        // TODO Auto-generated method stub
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateLabel();
      }

    };

    etdob.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        // TODO Auto-generated method stub
        new DatePickerDialog(c, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
      }
    });
    String[] types = { "Clothes", "Home", "Stationary","Electronics"};
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, types);
    //Find TextView control
    AutoCompleteTextView acTextView = (AutoCompleteTextView) findViewById(R.id.ettype);
    //Set the number of characters the user must type before the drop down list is shown
    acTextView.setThreshold(1);
    //Set the adapter
    acTextView.setAdapter(adapter);
  }
  private void updateLabel() {
    String myFormat = "MM/dd/yy"; //In which you need put here
    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

    etdob.setText(sdf.format(myCalendar.getTime()));
  }

  private void saveInvoice() throws ParseException {

    String name = etTitle.getText().toString();
    String shopName = etshopName.getText().toString();
    String dob = etdob.getText().toString();
    String type = ettype.getText().toString();
    String location = etlocation.getText().toString();
    String Profpic = "picture";
    String comment = etcomment.getText().toString();

    d("Doing form validation");

    if(name.length() < 2 ){
      Toast.makeText(c, "Title too short", Toast.LENGTH_SHORT);
      return;
    }

    if(shopName.isEmpty()){
      Toast.makeText(c, "Shop name is required", Toast.LENGTH_SHORT);
      return;
    }
    if(dob.isEmpty() ){
      Toast.makeText(c, "Date is required", Toast.LENGTH_SHORT);
      return;
    }

   if(type.isEmpty()){
      Toast.makeText(c, "Please choose a type", Toast.LENGTH_SHORT );
      return;
    }
    d("Form validation passed");
//        Make the registration
    dbHelper = new InvoiceDbHelper(this);

    if(dbHelper.insertInvoice(name, Profpic, dob, type, location, shopName, comment)) {
      Toast.makeText(getApplicationContext(), "Invoice Saved", Toast.LENGTH_SHORT).show();
    }
    else{
      Toast.makeText(getApplicationContext(), "Could not save the invoice", Toast.LENGTH_SHORT).show();
    }
    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
    finish();
  }

  private void d(String s){
    try {
      Log.d("SaveInvoiceActivity", s);
    }catch(NullPointerException e){
      Log.d("SaveInvoiceActivity", e.getMessage());
    }
  }

}
