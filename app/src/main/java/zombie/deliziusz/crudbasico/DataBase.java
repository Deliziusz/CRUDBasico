package zombie.deliziusz.crudbasico;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {

    private final String SCRIPT_DB = "CREATE TABLE IF NOT EXISTS contactos (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "_usuario TEXT NOT NULL," +
            "_email TEXT NOT NULL," +
            "_telefono TEXT NOT NULL," +
            "_fechaNacimiento DATE NOT NULL" +
            ");";

    public static final String[] COLUMNS_NAME_CONTACTO =
            {"_id", "_usuario", "_email", "_telefono", "_fechaNacimiento"};

    public static final String TABLE_NAME_CONTACTOS = "contactos";

    public DataBase(@Nullable Context context) {
        super(context, "MyDB", null, 1);
    }

    public ContentValues valores(Contacto contacto){
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMNS_NAME_CONTACTO[1], contacto.getUsuario());
        contentValues.put(COLUMNS_NAME_CONTACTO[2], contacto.getEmail());
        contentValues.put(COLUMNS_NAME_CONTACTO[3], contacto.getTelefono());
        contentValues.put(COLUMNS_NAME_CONTACTO[4], contacto.getFechaNacimiento());

        return contentValues;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SCRIPT_DB);
        sqLiteDatabase.insert(TABLE_NAME_CONTACTOS, null,
                valores(new Contacto(0,"Karla","deliziusz@gmail.com",
                "4171230045","1997/09/17")));
        sqLiteDatabase.insert(TABLE_NAME_CONTACTOS, null,
                valores(new Contacto(0,"Yazmin","yaz@gmail.com",
                        "4612590717","1997/09/17")));

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CONTACTOS);
        onCreate(sqLiteDatabase);
    }
}
