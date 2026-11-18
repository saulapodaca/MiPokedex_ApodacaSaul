package apodaca.saul.mipokedex_compose_apodacasaul.model.data.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null ,DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createPokemons = "CREATE TABLE ${Contract.Pokemons.TABLE_NAME}(" +
                "${Contract.Pokemons.COLUMN_ID} INTEGER PRIMARY KEY," +
                "${Contract.Pokemons.COLUMN_NAME_NUMBER} INTEGER," +
                "${Contract.Pokemons.COLUMN_NAME_NAME} TEXT," +
                "${Contract.Pokemons.COLUMN_NAME_DESCRIPTION} TEXT," +
                "${Contract.Pokemons.COLUMN_NAME_TYPE} TEXT," +
                "${Contract.Pokemons.COLUMN_NAME_HEIGHT} REAL," +
                "${Contract.Pokemons.COLUMN_NAME_WEIGHT} REAL," +
                "${Contract.Pokemons.COLUMN_NAME_ABILITY} TEXT," +
                "${Contract.Pokemons.COLUMN_NAME_IMAGE} INTEGER)"
        db?.execSQL(createPokemons)
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        if (oldVersion < 2 && db != null) {
            db.beginTransaction()
            try {
                val tableName = Contract.Pokemons.TABLE_NAME
                val cId = Contract.Pokemons.COLUMN_ID
                val cNum = Contract.Pokemons.COLUMN_NAME_NUMBER
                val cName = Contract.Pokemons.COLUMN_NAME_NAME
                val cDesc = Contract.Pokemons.COLUMN_NAME_DESCRIPTION
                val cType = Contract.Pokemons.COLUMN_NAME_TYPE
                val cHeight = Contract.Pokemons.COLUMN_NAME_HEIGHT
                val cWeight = Contract.Pokemons.COLUMN_NAME_WEIGHT
                val cAbility = Contract.Pokemons.COLUMN_NAME_ABILITY
                val cImage = Contract.Pokemons.COLUMN_NAME_IMAGE
                val cFavorite = Contract.Pokemons.COLUMN_NAME_FAVORITE

                // 1. Create a new temporary table with 'BLOB' for the image column
                db.execSQL("""
                    CREATE TABLE ${tableName}_new (
                        $cId INTEGER PRIMARY KEY,
                        $cNum INTEGER,
                        $cName TEXT,
                        $cDesc TEXT,
                        $cType TEXT,
                        $cHeight REAL,
                        $cWeight REAL,
                        $cAbility TEXT,
                        $cImage BLOB DEFAULT NULL,
                        $cFavorite BOOLEAN DEFAULT 0
                    )
                """)

                // 2. Copy all past data over, but explicitly pass NULL for the image column
                db.execSQL("""
                    INSERT INTO ${tableName}_new (
                        $cId, $cNum, $cName, $cDesc, $cType, $cHeight, $cWeight, $cAbility, $cImage, $cFavorite
                    )
                    SELECT 
                        $cId, $cNum, $cName, $cDesc, $cType, $cHeight, $cWeight, $cAbility, NULL, $cFavorite
                    FROM $tableName
                """)

                // 3. Drop the old table
                db.execSQL("DROP TABLE $tableName")

                // 4. Rename the new table to the original table name
                db.execSQL("ALTER TABLE ${tableName}_new RENAME TO $tableName")

                // Commit the transaction successfully
                db.setTransactionSuccessful()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction()
            }
        }
    }


    companion object {
        private const val DATABASE_NAME = "pokedex.db"
        private const val DATABASE_VERSION = 1
    }
}