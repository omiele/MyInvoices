package omabralimited.myinvoices.databases;

import android.provider.BaseColumns;

public final class InvoiceDbContract {
    public static final String TEXT_TYPE = " TEXT";
    public static final String INTEGER_TYPE = " INTEGER";
    public static final String REAL_TYPE = " REAL";
    public static final String COMMA_SEP = ",";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public InvoiceDbContract(){

    }

    /* Inner class that defines the table contents */
    public static abstract class User implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_USER_ID = "userId";
        public static final String COLUMN_NAME_PROFILE_PICTURE = "profilePicture";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PHONENUMBER = "phoneNumber";
        public static final String COLUMN_NAME_PASSWORD= "password";
        public static final String COLUMN_NAME_COUNTRY= "country";


        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + User.TABLE_NAME + " (" +
                        User._ID + " INTEGER PRIMARY KEY," +
                        User.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                        User.COLUMN_NAME_EMAIL + TEXT_TYPE + COMMA_SEP +
                        User.COLUMN_NAME_PASSWORD + TEXT_TYPE + COMMA_SEP +
                        User.COLUMN_NAME_COUNTRY + TEXT_TYPE + COMMA_SEP +
                        User.COLUMN_NAME_PHONENUMBER + TEXT_TYPE + COMMA_SEP +
                        User.COLUMN_NAME_PROFILE_PICTURE + TEXT_TYPE + COMMA_SEP +
                        User.COLUMN_NAME_USER_ID + TEXT_TYPE+
                        " )";

        public static final String SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + User.TABLE_NAME;

        public static final String SQL_DELETE_ALL_RECORDS =
                "DELETE FROM " + User.TABLE_NAME;
    }

    public static abstract class Invoices implements BaseColumns {
        public static final String TABLE_NAME = "invoices";
        public static final String COLUMN_NAME_INVOICE_TITLE = "title";
        public static final String COLUMN_NAME__INVOICE_DATE = "date";
        public static final String COLUMN_NAME_INVOICE_TYPE = "type";
        public static final String COLUMN_NAME_PICTURE = "picture";
        public static final String COLUMN_NAME_LOCATION  = "location";
        public static final String COLUMN_SHOP_NAME  = "shopName";
        public static final String COLUMN_COMMENT  = "comment";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + Invoices.TABLE_NAME + " (" +
                        Invoices._ID + " INTEGER PRIMARY KEY," +
                        Invoices.COLUMN_NAME_INVOICE_TITLE                  + TEXT_TYPE     + COMMA_SEP +
                        Invoices.COLUMN_NAME__INVOICE_DATE                   + INTEGER_TYPE     + COMMA_SEP +
                        Invoices.COLUMN_NAME_INVOICE_TYPE    + TEXT_TYPE  + COMMA_SEP +
                        Invoices.COLUMN_NAME_PICTURE                    + TEXT_TYPE     + COMMA_SEP +
                        Invoices.COLUMN_NAME_LOCATION                   + TEXT_TYPE     + COMMA_SEP +
                        Invoices.COLUMN_SHOP_NAME                   + TEXT_TYPE     + COMMA_SEP +
                        Invoices.COLUMN_COMMENT                   + TEXT_TYPE+

                       " )";

        public static final String SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + Invoices.TABLE_NAME;

        public static final String SQL_DELETE_ALL_RECORDS =
                "DELETE FROM " + Invoices.TABLE_NAME;
    }    
    }


