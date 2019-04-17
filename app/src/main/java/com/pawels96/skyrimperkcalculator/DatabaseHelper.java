package com.pawelsobieszczuk.skyrimperkcalculatorforordinator;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.IPerk;
import com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.PerkSystem;
import com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.SkillEnum;
import com.pawelsobieszczuk.skyrimperkcalculatorforordinator.models.Build;

import java.util.ArrayList;
import java.util.List;

import static com.pawelsobieszczuk.skyrimperkcalculatorforordinator.Utils.DEFAULT_BUILD_NAME;
import static com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.PerkSystem.ORDINATOR;
import static com.pawelsobieszczuk.skyrimperkcalculatorforordinator.enums.PerkSystem.VANILLA;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DBNAME = "DB";
    private static final int DBVER = 1;

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, DBVER);
    }

    public boolean isNameAvailable(String name) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT name FROM ordinator_build WHERE name='" + name + "'", null);

        boolean result = true;

        if (cursor.moveToFirst())
            result = false;

        cursor.close();
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

    public boolean deleteBuild(String name) {

        SQLiteDatabase DB = getWritableDatabase();

        try {
            DB.execSQL("DELETE FROM ordinator_build WHERE name='" + name + "'");
            return true;

        } catch (SQLiteException e) {
            Log.d("ERROR", e.getMessage());
            return false;
        } finally {
            DB.close();
        }
    }

    public void updateBuild(Build build) {
        SQLiteDatabase db = getWritableDatabase();
        String table = getTable(build.getPerkSystem());
        db.update(table, processBuild(build), "name='" + build.getName() + "'", null);
        db.close();
    }

    public List<Build> getAllBuilds(PerkSystem perkSystem) {

       // Log.d("PERKSYSTEM", perkSystem.toString());

        List<Build> builds = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String table = getTable(perkSystem);

        Cursor cursor = db.rawQuery("SELECT * FROM " + table, null);

        if (cursor.moveToFirst()) {

            do {

                Build build = new Build(perkSystem);

                for (SkillEnum s : SkillEnum.values()) {
                    for (IPerk p : build.getSkill(s).getPerks().keySet()) {
                        int state = cursor.getInt(cursor.getColumnIndex(p.toString()));
                        build.getSkill(s).get(p).setState(state);
                    }
                }

                build.setName(cursor.getString(cursor.getColumnIndex("name")));
                build.setDescription(cursor.getString(cursor.getColumnIndex("description")));

                builds.add(build);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return builds;
    }

    private void createBuildsTable(PerkSystem perkSystem, SQLiteDatabase db){

        StringBuilder builder = new StringBuilder();

        for (SkillEnum s : SkillEnum.values()) {
            for (IPerk p : s.getPerks(perkSystem)) {
                builder.append(p.toString()).append(" INTEGER,");
            }
        }

        String columns = builder.substring(0, builder.lastIndexOf(",") - 1);
        String tableName = perkSystem.toString().toLowerCase() + "_build";

        String createSongTable = "CREATE TABLE " + tableName + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR," +
                "description VARCHAR," +
                columns + ")";
        try {
            db.execSQL(createSongTable);
        } catch (SQLiteException e) {
            Log.d("DB_ERROR", e.getMessage());
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        createBuildsTable(VANILLA, db);
        createBuildsTable(ORDINATOR, db);

        Build build = new Build(ORDINATOR);
        build.setName(DEFAULT_BUILD_NAME);
        insertBuild(getTable(ORDINATOR), processBuild(build), db, false);

        Build build2 = new Build(VANILLA);
        build2.setName(DEFAULT_BUILD_NAME);
        insertBuild(getTable(VANILLA), processBuild(build2), db, false);
    }

    private static String getTable(PerkSystem p){
       return p.toString().toLowerCase() + "_build";
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

   /* public void update(String build, String perk, int state) {

        SQLiteDatabase db = getWritableDatabase();
        String sql = "UPDATE build SET " + perk + "=" + state + " WHERE skillEnum='" + build + "'";

        try {
            db.execSQL(sql);
        } catch (SQLiteException e) {
            Log.d("ERROR", e.getMessage());
        }
        db.close();

    }*/

    /*
    public Build loadBuild(String name) {

        Build build = null;

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM build WHERE name='" + name + "'", null);

        if (cursor.moveToFirst()) {

            build = new Build();

            do {
                for (SkillEnum s : SkillEnum.values()) {
                    for (IPerk p : build.getSkill(s).getPerks().keySet()) {
                        int state = cursor.getInt(cursor.getColumnIndex(p.toString()));
                        build.getSkill(s).get(p).setState(state);
                    }
                }
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return build;
    }
*/


}








