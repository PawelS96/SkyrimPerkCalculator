package com.pawels96.skyrimperkcalculator.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pawels96.skyrimperkcalculator.domain.Build;
import com.pawels96.skyrimperkcalculator.domain.IPerk;
import com.pawels96.skyrimperkcalculator.domain.PerkSystem;
import com.pawels96.skyrimperkcalculator.domain.enums.EMainSkill;

import java.util.ArrayList;
import java.util.List;

import static com.pawels96.skyrimperkcalculator.domain.PerkSystem.VOKRII;
import static com.pawels96.skyrimperkcalculator.presentation.Utils.DEFAULT_BUILD_NAME;

public class OldDatabase extends SQLiteOpenHelper {

    //TODO remove test code

    private static final String DBNAME = "DB";
    private static final String DBNAME_TEST = "DB_test";

    private static final int DBVER = 4;

    private Repository repoForMigration;

    private SQLiteDatabase db;

    public OldDatabase(Context context, Repository repoForMigration) {
        super(context, DBNAME, null, DBVER);
        this.repoForMigration = repoForMigration;
        db = getWritableDatabase();
    }

    public void clear() {

        for (PerkSystem ps : PerkSystem.values()) {
            String table = getTable(ps);
            db.execSQL("DELETE FROM " + table);
        }
    }

    private ContentValues processBuild(Build build) {

        ContentValues values = new ContentValues();

        for (EMainSkill s : EMainSkill.values()) {
            for (IPerk p : build.getSkill(s).getPerks().keySet()) {
                values.put(p.toString(), build.getSkill(s).get(p).getState());
            }
        }

        values.put("name", build.getName());
        values.put("description", build.getDescription());

        return values;
    }

    private boolean insertBuild(String table, ContentValues values, SQLiteDatabase db) {

        long result = db.insert(table, null, values);
        return result != -1;
    }

    public boolean saveBuild(Build build) {
        String table = getTable(build.getSystem());
        return insertBuild(table, processBuild(build), getWritableDatabase());
    }

    private Build getBuild(Cursor cursor, PerkSystem system) {

        Build build = Build.Companion.create(system, 0);

        for (EMainSkill s : EMainSkill.values()) {
            for (IPerk p : build.getSkill(s).getPerks().keySet()) {
                int state = cursor.getInt(cursor.getColumnIndex(p.toString()));
                build.getSkill(s).get(p).setState(state);
            }
        }

        build.setName(cursor.getString(cursor.getColumnIndex("name")));

        String description = cursor.getString(cursor.getColumnIndex("description"));

        if (description == null) description = "";
        build.setDescription(description);

        return build;
    }

    public List<Build> getAllBuilds(PerkSystem perkSystem) {

        List<Build> builds = new ArrayList<>();

       // SQLiteDatabase db = getReadableDatabase();
        String table = getTable(perkSystem);

        Cursor cursor = db.rawQuery("SELECT * FROM " + table, null);

        if (cursor.moveToFirst()) {
            do {

                builds.add(getBuild(cursor, perkSystem));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
       // db.close();

        return builds;
    }

    private void createBuildsTable(PerkSystem perkSystem, SQLiteDatabase db) {

        StringBuilder builder = new StringBuilder();

        for (EMainSkill s : EMainSkill.values())
            for (IPerk p : s.getPerks(perkSystem))
                builder.append(p.toString()).append(" INTEGER,");

        String columns = builder.substring(0, builder.lastIndexOf(",") - 1);
        String tableName = perkSystem.toString().toLowerCase() + "_build";

        String createSongTable = "CREATE TABLE " + tableName + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR," +
                "description VARCHAR," +
                columns + ")";

        db.execSQL(createSongTable);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("OldDatabase", "Create");

        for (PerkSystem p : PerkSystem.values()) {
            createBuildsTable(p, db);
            addDefaultBuild(p, db);
        }
    }

    private void addDefaultBuild(PerkSystem system, SQLiteDatabase db) {
        Build build = Build.Companion.create(system, 0);

        build.setName(DEFAULT_BUILD_NAME + "_FromOldDB");

      //  build.getSkills().values().forEach(skill -> {
      //      skill.getPerkList().forEach(perk -> perk.setState(perk.getMaxState()));
      //  });


        insertBuild(getTable(system), processBuild(build), db);
    }

    private static String getTable(PerkSystem p) {
        return p.toString().toLowerCase() + "_build";
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int old, int newVersion) {

        Log.d("OldDatabase", "Upgrade");

        if (old < 2) {
            createBuildsTable(VOKRII, db);
            addDefaultBuild(VOKRII, db);
        }

        if (old < 3)
            updateDB(db);

        if (old < 4) {
            repoForMigration.insert(getDataForMigration(database));
        //    migrateFromSQLiteToRoom(this, repoForMigration);
            Log.d("OldDatabase", "Upgrade");
        }
    }

    private List<Build> getDataForMigration(SQLiteDatabase database){

        List<Build> builds = new ArrayList<>();

        for (PerkSystem ps : PerkSystem.values()) {

            String table = getTable(ps);

            Cursor cursor = database.rawQuery("SELECT * FROM " + table, null);

            if (cursor.moveToFirst()) {
                do {
                    builds.add(getBuild(cursor, ps));
                }
                while (cursor.moveToNext());
            }
            cursor.close();
        }

        return builds;
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {    }

    private void updateDB(SQLiteDatabase db) {

        for (PerkSystem p : PerkSystem.values())
            updateColumns(db, p);
    }

    private void updateColumns(SQLiteDatabase db, PerkSystem system) {

        Cursor cursor = db.rawQuery("SELECT * FROM " + getTable(system), null);

        for (EMainSkill s : EMainSkill.values()) {

            for (IPerk p : s.getPerks(system)) {

                boolean exists = cursor.getColumnIndex(p.toString()) != -1;

                if (!exists)
                    db.execSQL("ALTER TABLE " + getTable(system) + " ADD COLUMN " + p.toString() + " INTEGER DEFAULT 0");

            }
        }
        cursor.close();
    }


   /* public Build getByName(String name, PerkSystem system) {

        //SQLiteDatabase db = getReadableDatabase();
        String table = getTable(system);
        Build build = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + table + " WHERE name='" + name + "'", null);

        if (cursor.moveToFirst()) {
            build = getBuild(cursor, system);
        }

        cursor.close();
       // db.close();

        if (build == null) {

            List<Build> allBuilds = getAllBuilds(system);

            if (allBuilds.isEmpty()) {
                build = Build.Companion.create(system, 0);
                build.setName(DEFAULT_BUILD_NAME);

                if (isNameAvailable(DEFAULT_BUILD_NAME, system)) {
                    saveBuild(build);
                }
            } else
                build = allBuilds.get(0);
        }

        return build;
    }*/
/* public boolean renameBuild(Build build, String newName) {

        String table = getTable(build.getSystem());

        try {
            String sql = "UPDATE " + table + " SET name='" + newName + "' WHERE name='" + build.getName() + "'";
            db.execSQL(sql);
            return true;
        } catch (Exception e) {
            return false;
        } //finally {
          //  DB.close();
       // }
    }*/

    /*public boolean deleteBuild(Build build) {

        SQLiteDatabase DB = getWritableDatabase();
        String table = getTable(build.getSystem());

        try {
            DB.execSQL("DELETE FROM " + table + " WHERE name='" + build.getName() + "'");
            return true;

        } catch (SQLiteException e) {
            return false;
        } //finally {
         //   DB.close();
      //  }
    }*/

   /* public void updateBuild(Build build) {

        SQLiteDatabase db = getWritableDatabase();
        String table = getTable(build.getSystem());
        try {
            db.update(table, processBuild(build), "name='" + build.getName() + "'", null);
        } catch (SQLiteException ignored) {
        } //finally {
         //   db.close();
        //}
    }*/


   /* public boolean isEmpty() {

        int records = 0;

        for (PerkSystem ps : PerkSystem.values()) {
            records += tableSize(ps);
        }

        return records == 0;
    }*/

   /* private long tableSize(PerkSystem system) {
        String table = getTable(system);
        return DatabaseUtils.queryNumEntries(db, table);
    }*/

    /* public boolean isNameAvailable(String name, PerkSystem system) {
         String table = getTable(system);

         boolean result = true;

         try {
             Cursor cursor = db.rawQuery("SELECT name FROM " + table + " WHERE name='" + name + "'", null);

             if (cursor != null && cursor.moveToFirst())
                 result = false;

             if (cursor != null)
                 cursor.close();

         } catch (SQLiteException e) {
             result = false;
         }

         return result;
     }*/

}