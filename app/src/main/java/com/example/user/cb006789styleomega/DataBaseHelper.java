package com.example.user.cb006789styleomega;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 9/18/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_Version = 1;
    private static final String DATABASE_NAME = "user.db";

    //user table

    private static final String TABLE_NAME = "user";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_PASS = "pass";
    private static final String COLUMN_EMAIL = "email";
    SQLiteDatabase sqLiteDatabase;
    private static final String CREATE_USER = " create table user ( id integer primary key autoincrement , name text, " + " uname text,pass text,email text); ";

    private Context context;


    private String CREATEITEMTABLE = "CREATE TABLE " + itemtablename + "("
            + itemid + " INTEGER PRIMARY KEY AUTOINCREMENT," + itemname + " TEXT,"
            + itemType + " TEXT," + itemPrice + " TEXT," + picURL + " TEXT"
            + ")";
    private String DROPITEMTABLE = "DROP TABLE IF EXISTS " + itemtablename;


    private String CREATE_CHECKOUTS_TABLE = "CREATE TABLE " + cartTable + "("
            + cartid + " INTEGER PRIMARY KEY AUTOINCREMENT," + cartItemid + " TEXT,"
            + cartname + " TEXT," + cartusrnam + " TEXT," + cartprice + " TEXT"
            +")";
    private String DROP_CHECKOUTS_TABLE = "DROP TABLE IF EXISTS " + cartTable;


    private String CREATE_CREDITCARDTABLE="CREATE TABLE " + creditcardtable+ "("
            + tablecreditid + " INTEGER PRIMARY KEY AUTOINCREMENT," + creditcardno + " TEXT,"
            + creditcardowner + " TEXT," + expirydate+ " TEXT," + creditcardver + " TEXT,"
            +shipaddress+ " TEXT"+")";
    private String DROP_CREDITCARDTABLE="DROP TABLE IF EXISTS"+creditcardtable;

    public void insertcreditcard(CreditCardDet card) {
        sqLiteDatabase = this.getWritableDatabase();//inorder to insert anything to the database we need to make it writable
        ContentValues values = new ContentValues();
        values.put(creditcardno, card.getCreditcadno());
        values.put(creditcardowner, card.getCreditowner());
        values.put(expirydate, card.getExpirydate());
        values.put(creditcardver, card.getCardverification());
        values.put(shipaddress,card.getShippingaddress());
        sqLiteDatabase.insert(creditcardtable, null, values);
        sqLiteDatabase.close();
    }



    private static String creditcardtable ="CreditCardDet_Table";
    private static String tablecreditid ="creditid";
    private static String creditcardno ="credcardno";
    private static String creditcardowner ="creditcardowner";
    private static String expirydate ="expirydate";
    private static String creditcardver="cardverifi";
    private static String shipaddress="shipaddress";


    private static String TABLE_NAME2 = "InquiryTable";
    private static String InqAdid = "Inqid";
    private static String Inqname = "Inqname";
    private static String InqAdname = "InqAdname";
    private static String InquiryDetails = "InqDetails";


    private String CREATE_INQUIRY_TABLE = "CREATE TABLE " + TABLE_NAME2 + "("
            + InqAdid + " INTEGER PRIMARY KEY AUTOINCREMENT," + InqAdname + " TEXT,"
            +  Inqname + " TEXT,"+InquiryDetails + " TEXT"
            + ")";


    private String DROP_INQUIRY_ADVERTISMENT_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME2;


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER);
        sqLiteDatabase.execSQL(CREATEITEMTABLE);
        sqLiteDatabase.execSQL(CREATE_CHECKOUTS_TABLE);
        sqLiteDatabase.execSQL(CREATE_CREDITCARDTABLE);
        sqLiteDatabase.execSQL(CREATE_INQUIRY_TABLE);
        this.sqLiteDatabase = sqLiteDatabase;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = " DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.execSQL(DROPITEMTABLE);
        sqLiteDatabase.execSQL(DROP_CHECKOUTS_TABLE);
        sqLiteDatabase.execSQL(DROP_CREDITCARDTABLE);
        sqLiteDatabase.execSQL(DROP_INQUIRY_ADVERTISMENT_TABLE);
        this.onCreate(sqLiteDatabase);

    }


    public void insertUser(UserDetails user) {
        sqLiteDatabase = this.getWritableDatabase();//inorder to insert anything to the database we need to make it writable
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getFullname());
        values.put(COLUMN_UNAME, user.getUsername());
        values.put(COLUMN_PASS, user.getPassword());
        values.put(COLUMN_EMAIL, user.getEmail());
        sqLiteDatabase.insert(TABLE_NAME, null, values);
        sqLiteDatabase.close();
    }


    public String searchPass(String uname) {
        sqLiteDatabase = this.getReadableDatabase();
        String query = " select uname, pass from " + TABLE_NAME;


        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String a, b;
        b = " Not found ";
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                if (a.equals(uname)) {
                    b = cursor.getString(1);
                    break;
                }

            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public boolean updatepass(String newpass, String username)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PASS,newpass);
        db.update(TABLE_NAME, contentValues, "uname = ?",new String[] { username });
        return true;
    }
    public String searchemail(String uname) {
        sqLiteDatabase = this.getReadableDatabase();
        String query = " select uname, email from " + TABLE_NAME;


        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        String a, b;
        b = " Not found ";
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                if (a.equals(uname)) {
                    b = cursor.getString(1);
                    break;
                }

            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public boolean updateemail(String newemail, String username)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PASS,newemail);
        db.update(TABLE_NAME, contentValues, "uname = ?",new String[] { username });
        return true;
    }









    public boolean CreateItemFROMITEMOBJECT(Items items) {
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(itemname, items.getItemname());
            values.put(itemType, items.getItemType());
            values.put(itemPrice, items.getItemPrice());
            values.put(picURL, items.getPicURL());
            long rows = sqLiteDatabase.insert(itemtablename, null, values);
            sqLiteDatabase.close();
            if (rows > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }



    private static String itemtablename = "Item_Table";

    private static String itemid = "itemid";
    private static String itemname = "itemname ";
    private static String itemType = "itemType";
    private static String itemPrice = "itemPrice";
    private static String picURL = "PicURL";


    public List<Items> AllItemsRetrieve(String itemType) {
        List<Items> advertisments = new ArrayList<Items>();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + itemtablename, null);
        if (cursor.moveToFirst()) {
            do {
                String dbCat = cursor.getString(cursor.getColumnIndex("itemType"));
                if (dbCat.equalsIgnoreCase(itemType)) {
                    Items items = new Items();
                    items.setId(cursor.getInt(0));
                    items.setItemname(cursor.getString(1));
                    items.setItemType(cursor.getString(2));
                    items.setItemPrice(cursor.getString(3));
                    items.setPicURL(cursor.getString(4));
                    advertisments.add(items);
                }
            } while (cursor.moveToNext());
            sqLiteDatabase.close();
            return advertisments;
        } else {
            return null;
        }
    }

    public List<Items> SearchItemByItemName(String searchid) {
        //SQLiteDatabase db = this.getReadableDatabase();
        List<Items> itemsBean = new ArrayList<Items>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = itemname;
        String[] selectionArgs = {searchid};
        Cursor cursor = db.query(itemtablename, null, selection,
                selectionArgs, null, null, null);
        if(cursor.moveToFirst()){
            do{
                Items items = new Items();
                items.setId(cursor.getInt(0));
                items.setItemname(cursor.getString(1));
                items.setItemType(cursor.getString(2));
                items.setItemPrice(cursor.getString(3));
                items.setPicURL(cursor.getString(4));
                itemsBean.add(items);
            }while(cursor.moveToNext());
        }
        db.close();
        return itemsBean;
    }


    public boolean createCart(Cart cart) {
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(cartItemid, cart.getItemid());
            values.put(cartname, cart.getItemname());
            values.put(cartusrnam, cart.getUsername());
            values.put(cartprice, cart.getItemprice());

            long rows = sqLiteDatabase.insert(cartTable, null, values);
            sqLiteDatabase.close();
            if (rows > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }


    public List<Cart> cartlist(String username) {
        //SQLiteDatabase db = this.getReadableDatabase();

        List<Cart> checkouts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = cartusrnam + " = ?";
        String[] selectionArgs = {username};
        Cursor cursor = db.query(cartTable, null, selection,
                selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Cart adds = new Cart();
                adds.setCheckId(cursor.getInt(0));
                adds.setItemid(cursor.getInt(1));
                adds.setItemname(cursor.getString(2));
                adds.setUsername(cursor.getString(3));
                adds.setItemprice(cursor.getDouble(4));

                checkouts.add(adds);
            } while (cursor.moveToNext());
        }
        db.close();
        return checkouts;
    }


    public boolean cartDel(int id) {

        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();

            int rows = sqLiteDatabase.delete(cartTable, cartid + " = ? ", new String[]{
                    String.valueOf(id)});
            sqLiteDatabase.close();
            return rows > 0;
        } catch (Exception ex) {
            return false;
        }
    }




    public boolean AdInquiryCreate(Inquiry inquiries) {
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(InquiryDetails, inquiries.getInqDetails());
            //values.put(Inqname,inquiries.getProdName());
            values.put(InqAdname, inquiries.getUsername());
            long rows = sqLiteDatabase.insert(TABLE_NAME2, null, values);
            sqLiteDatabase.close();
            if(rows > 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    //this is for administrator
    public boolean AdInquiryDelete(int id)
    {

        try{
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            int rows = sqLiteDatabase.delete(TABLE_NAME2,InqAdid + " = ? ",new String[] {
                    String.valueOf(id) });
            sqLiteDatabase.close();
            return rows > 0;
        }catch (Exception ex){
            return false;
        }
    }




    public List<Inquiry> InquiriesfindAll() {
        List<Inquiry> inquiriesList = new ArrayList<Inquiry>();
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME2, null);
        // Cursor  cursor = sqLiteDatabase.rawQuery("select * from "+TABLE_USER+" where " + COLUMN_USER_PASSWORD+ "=" + email + "", null);
        if (cursor.moveToFirst()) {
            do {

                Inquiry adds = new Inquiry();
                adds.setInquiryId(0);
                adds.setUsername(cursor.getString(1));
                adds.setProdName(cursor.getString(2));
                adds.setInqDetails(cursor.getString(3));
                inquiriesList.add(adds);
            } while (cursor.moveToNext());
            sqLiteDatabase.close();
            return inquiriesList;
        } else {
            return null;
        }
    }


    private static String cartTable = "cartTable";
    private static String cartid = "cartid";
    private static String cartItemid = "cartItemid";
    private static String cartname = "cartname";
    private static String cartusrnam = "cartusrnam";
    private static String cartprice = "cartprice";



    public int cartcounttotal(String username) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        //Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(adPrice) FROM CheckoutsTable WHERE username = thanuka;", null);
        int amount;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(cartprice) FROM cartTable WHERE cartusrnam = '" + username + "';", null);
        if (cursor.moveToFirst()) {
            int total = cursor.getInt(0);
            return total;
        } else {
            amount = 0;
            return amount;
        }
        //cursor.close();


    }
}

