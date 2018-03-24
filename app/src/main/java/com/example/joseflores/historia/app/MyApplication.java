package com.example.joseflores.historia.app;

import android.app.Application;

import com.example.joseflores.historia.modelos.Bloque;
import com.example.joseflores.historia.modelos.Tema;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by JoseFlores on 13/03/2018.
 */

public class MyApplication extends Application {

    //public static AtomicInteger ContenidoID = new AtomicInteger();
    public static AtomicInteger TemasID = new AtomicInteger();
    public static AtomicInteger BloquesID = new AtomicInteger();

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(getApplicationContext());
        setUpRealmConfig();

        Realm realm = Realm.getDefaultInstance();
        //ContenidoID = getIdByTable(realm, Contenido.class);
        TemasID = getIdByTable(realm, Tema.class);
        BloquesID = getIdByTable(realm, Bloque.class);
        realm.close();
    }

    private void setUpRealmConfig() {
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass) {
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0) ? new AtomicInteger(results.max("id").intValue()) : new AtomicInteger();
    }
}
