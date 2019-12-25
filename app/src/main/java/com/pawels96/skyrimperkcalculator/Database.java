package com.pawels96.skyrimperkcalculator;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.pawels96.skyrimperkcalculator.enums.IPerk;
import com.pawels96.skyrimperkcalculator.enums.PerkSystem;
import com.pawels96.skyrimperkcalculator.enums.SkillEnum;
import com.pawels96.skyrimperkcalculator.models.Build;

import java.util.ArrayList;
import java.util.List;

import static com.pawels96.skyrimperkcalculator.Utils.DEFAULT_BUILD_NAME;
import static com.pawels96.skyrimperkcalculator.enums.PerkSystem.VOKRII;

public class Database extends SQLiteOpenHelper {

    private static final String DBNAME = "DB";
    private static final int DBVER = 3;

    private static Database database;

    public static Database create(Context context) {
        if (database == null)
            database = new Database(context);

        return database;
    }

    public static Database get() {return database;}

    private Database(Context context) {
        super(context, DBNAME, null, DBVER);
    }

    public boolean isNameAvailable(String name, PerkSystem system) {
        SQLiteDatabase db = getReadableDatabase();
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

        db.close();

        return result;
    }

    private ContentValues processBuild(Build build) {

        ContentValues values = new ContentValues();

        for (SkillEnum s : SkillEnum.values()) {
            for (IPerk p : build.getSkill(s).getPerks().keySet()) {
                values.put(p.toString(), build.getSkill(s).get(p).getState());
            }
        }

        values.put("name", build.getName());

        if (build.getDescription() == null)
            values.putNull("description");
        else
            values.put("description", build.getDescription());

        return values;
    }

    private boolean insertBuild(String table, ContentValues values, SQLiteDatabase db, boolean close) {

        long result = db.insert(table, null, values);
        if (close)
            db.close();

        return result != -1;
    }

    public boolean saveBuild(Build build) {
        String table = getTable(build.getPerkSystem());
        return insertBuild(table, processBuild(build), getWritableDatabase(), true);
    }

    public boolean renameBuild(Build build, String newName) {

        SQLiteDatabase DB = getWritableDatabase();
        String table = getTable(build.getPerkSystem());

        try {
            String sql = "UPDATE " + table + " SET name='" + newName + "' WHERE name='" + build.getName() + "'";
            DB.execSQL(sql);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            DB.close();
        }
    }

    public boolean deleteBuild(Build build) {

        SQLiteDatabase DB = getWritableDatabase();
        String table = getTable(build.getPerkSystem());

        try {
            DB.execSQL("DELETE FROM " + table + " WHERE name='" + build.getName() + "'");
            return true;

        } catch (SQLiteException e) {
            return false;
        } finally {
            DB.close();
        }
    }

    public void updateBuild(Build build) {

        SQLiteDatabase db = getWritableDatabase();
        String table = getTable(build.getPerkSystem());
        try {
            db.update(table, processBuild(build), "name='" + build.getName() + "'", null);
        } catch (SQLiteException ignored) {
        } finally {
            db.close();
        }
    }

    private Build getBuild(Cursor cursor, PerkSystem system) {

        Build build = new Build(system);

        for (SkillEnum s : SkillEnum.values()) {
            for (IPerk p : build.getSkill(s).getPerks().keySet()) {
                int state = cursor.getInt(cursor.getColumnIndex(p.toString()));
                build.getSkill(s).get(p).setState(state);
            }
        }

        build.setName(cursor.getString(cursor.getColumnIndex("name")));
        build.setDescription(cursor.getString(cursor.getColumnIndex("description")));

        return build;
    }

    public Build getByName(String name, PerkSystem system){

        SQLiteDatabase db = getReadableDatabase();
        String table = getTable(system);
        Build build = null;

        Cursor cursor = db.rawQuery("SELECT * FROM " + table + " WHERE name='" + name + "'", null);

        if (cursor.moveToFirst()) {

               build = getBuild(cursor, system);

        }
        cursor.close();
        db.close();
        return build;

    }

    public List<Build> getAllBuilds(PerkSystem perkSystem) {

        List<Build> builds = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String table = getTable(perkSystem);

        Cursor cursor = db.rawQuery("SELECT * FROM " + table, null);

        if (cursor.moveToFirst()) {
            do {

                builds.add(getBuild(cursor, perkSystem));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return builds;
    }

    private void createBuildsTable(PerkSystem perkSystem, SQLiteDatabase db) {

        StringBuilder builder = new StringBuilder();

        for (SkillEnum s : SkillEnum.values())
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

        for (PerkSystem p : PerkSystem.values()) {
            createBuildsTable(p, db);
            addDefaultBuild(p, db);
        }
    }

    private void addDefaultBuild(PerkSystem system, SQLiteDatabase db) {
        Build build = new Build(system);
        build.setName(DEFAULT_BUILD_NAME);
        insertBuild(getTable(system), processBuild(build), db, false);
    }

    private static String getTable(PerkSystem p) {
        return p.toString().toLowerCase() + "_build";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        if (i == 1 && i1 == 2) {
            createBuildsTable(VOKRII, db);
            addDefaultBuild(VOKRII, db);
        } else if (i == 2 && i1 == 3)
            updateDB(db);

    }

    private void updateDB(SQLiteDatabase db) {

        for (PerkSystem p : PerkSystem.values())
            updateColumns(db, p);
    }

    private void updateColumns(SQLiteDatabase db, PerkSystem system) {

        Cursor cursor = db.rawQuery("SELECT * FROM " + getTable(system), null);

        for (SkillEnum s : SkillEnum.values()) {

            for (IPerk p : s.getPerks(system)) {

                boolean exists = cursor.getColumnIndex(p.toString()) != -1;

                if (!exists)
                    db.execSQL("ALTER TABLE " + getTable(system) + " ADD COLUMN " + p.toString() + " INTEGER DEFAULT 0");

            }
        }
        cursor.close();
    }

}