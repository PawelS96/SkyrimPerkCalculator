package com.pawels96.skyrimperkcalculator.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.pawels96.skyrimperkcalculator.domain.Build;
import com.pawels96.skyrimperkcalculator.domain.IPerk;
import com.pawels96.skyrimperkcalculator.domain.PerkSystem;
import com.pawels96.skyrimperkcalculator.domain.enums.EMainSkill;
import com.pawels96.skyrimperkcalculator.domain.skills_vokrii.Vok_Destruction;
import com.pawels96.skyrimperkcalculator.domain.skills_vokrii.Vok_HeavyArmor;
import com.pawels96.skyrimperkcalculator.domain.skills_vokrii.Vok_Smithing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.pawels96.skyrimperkcalculator.domain.PerkSystem.VOKRII;
import static com.pawels96.skyrimperkcalculator.presentation.Utils.DEFAULT_BUILD_NAME;

public class OldDatabase extends SQLiteOpenHelper {

    private static final String DBNAME = "DB";

    private static final int DBVER = 4;

    private Repository repoForMigration;

    public OldDatabase(Context context, Repository repoForMigration) {
        super(context, DBNAME, null, DBVER);
        this.repoForMigration = repoForMigration;
        getWritableDatabase();
    }

    public void clear() {

        SQLiteDatabase db = getWritableDatabase();

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
                int index = cursor.getColumnIndex(p.toString());

                if (index == -1){
                    Log.d("oldDatabase_getB", p.toString());
                    continue;
                }

                int state = cursor.getInt(index);
                build.getSkill(s).get(p).setState(state);
            }
        }

        build.setName(cursor.getString(cursor.getColumnIndex("name")));

        String description = cursor.getString(cursor.getColumnIndex("description"));

        if (description == null) description = "";
        build.setDescription(description);

        return build;
    }

    private List<Build> getAllBuilds(PerkSystem perkSystem, SQLiteDatabase db) {

        List<Build> builds = new ArrayList<>();
        String table = getTable(perkSystem);

        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + table, null);

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

    public List<Build> getAllBuilds(PerkSystem perkSystem) {
        return getAllBuilds(perkSystem, getReadableDatabase());
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

       /* for (PerkSystem p : PerkSystem.values()) {
            createBuildsTable(p, db);
            addDefaultBuild(p, db);
        }*/
    }

    private void addDefaultBuild(PerkSystem system, SQLiteDatabase db) {
        Build build = Build.Companion.create(system, 0);
        build.setName(DEFAULT_BUILD_NAME + "_FromOldDB");
        insertBuild(getTable(system), processBuild(build), db);
    }

    private static String getTable(PerkSystem p) {
        return p.toString().toLowerCase() + "_build";
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int old, int newVersion) {

        if (old < 2) {
            createBuildsTable(VOKRII, database);
            addDefaultBuild(VOKRII, database);
        }

        if (old < 3)
            updateDB(database);

        if (old < 4) {
            updateVokriiPerks(database);
            repoForMigration.insert(getDataForMigration(database));
        }
    }

    private void updateVokriiPerks(SQLiteDatabase db) {

        String table = getTable(VOKRII);

        Map<String, IPerk> oldAndNewPerkNames = new HashMap<>();

        oldAndNewPerkNames.put("VOK_DES_MAGES_FURY", Vok_Destruction.VOK_DES_HELLSTORM);
        oldAndNewPerkNames.put("VOK_HAR_OFF_BALANCE", Vok_HeavyArmor.VOK_HAR_IMMOVABLE_OBJECT);
        oldAndNewPerkNames.put("VOK_SMT_BASIC_SMITHING", Vok_Smithing.VOK_SMT_STEEL_SMITHING);
        oldAndNewPerkNames.put("VOK_SMT_MERIC_SMITHING", Vok_Smithing.VOK_SMT_ELVEN_SMITHING);
        oldAndNewPerkNames.put("VOK_SMT_ENGRAVED_SMITHING", Vok_Smithing.VOK_SMT_ORCISH_SMITHING);
        oldAndNewPerkNames.put("VOK_SMT_PRIMAL_SMITHING", Vok_Smithing.VOK_SMT_ADVANCED_ARMORS);
        oldAndNewPerkNames.put("VOK_SMT_CRYSTALLINE_SMITHING", Vok_Smithing.VOK_SMT_GLASS_SMITHING);
        oldAndNewPerkNames.put("VOK_SMT_EXOTIC_SMITHING", Vok_Smithing.VOK_SMT_EBONY_SMITHING);

        for (IPerk p : oldAndNewPerkNames.values()) {
            db.execSQL("ALTER TABLE " + table + " ADD " + p.toString() + " INTEGER DEFAULT 0");
        }

        List<String> vokriiBuildNames = new ArrayList<>();

        Cursor nameCursor = db.rawQuery("SELECT name FROM " + table, null);

        if (nameCursor != null && nameCursor.moveToFirst()) {
            do {
                vokriiBuildNames.add(nameCursor.getString(nameCursor.getColumnIndex("name")));
            }
            while (nameCursor.moveToNext());
        }

        if (nameCursor != null)
            nameCursor.close();

        for (String name : vokriiBuildNames) {

            ContentValues values = new ContentValues();

            for (Map.Entry<String, IPerk> e : oldAndNewPerkNames.entrySet()) {

                String oldColumn = e.getKey();
                String newColumn = e.getValue().toString();

                Cursor cursor = db.rawQuery("SELECT " + oldColumn + " FROM " + table + " WHERE name='" + name + "'", null);

                if (cursor != null && cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(oldColumn);

                    if (index != -1) {
                        int savedPerkState = cursor.getInt(index);
                        int newState = Math.min(savedPerkState, e.getValue().getPerkInfo().getSkillLevel().length);
                        values.put(newColumn, newState);
                    }
                }

                if (cursor != null)
                    cursor.close();
            }

            db.update(table, values, "name='" + name + "'", null);
        }
    }

    private List<Build> getDataForMigration(SQLiteDatabase database) {

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
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

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