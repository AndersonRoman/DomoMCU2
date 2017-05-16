package com.andersonroman.domomcu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RadioButton;

public class HabitacionesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String username="Anderson", correo="ander-353@hotmail.com";
    Intent intent;

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    RadioButton rHab1, rHab2,rHab3;
    ImageView iHabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habitaciones);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rHab1= (RadioButton) findViewById(R.id.rHab1);
        rHab2= (RadioButton) findViewById(R.id.rHab2);
        rHab3=(RadioButton) findViewById(R.id.rHab3);
        iHabs=(ImageView)findViewById(R.id.iHabs);

        this.setTitle("Estado de Habitaciones");

        prefs=getSharedPreferences("MisPreferencias",MODE_PRIVATE);
        editor=prefs.edit();

        Bundle extras=getIntent().getExtras();
        username=extras.getString("username");
        correo=extras.getString("correo");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void click(android.view.View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rHab1:
                if (checked) {
                    rHab2.setChecked(false);
                    rHab3.setChecked(false);
                    iHabs.setImageResource(R.drawable.hab1);
                }
                break;
            case R.id.rHab2:
                if (checked) {
                    rHab1.setChecked(false);
                    rHab3.setChecked(false);
                    iHabs.setImageResource(R.drawable.hab2);
                }
                break;
            case R.id.rHab3:
                if (checked) {
                    rHab2.setChecked(false);
                    rHab1.setChecked(false);
                    iHabs.setImageResource(R.drawable.hab3);
                }
                break;
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.configuraciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            intent= new Intent(HabitacionesActivity.this,ConfiguracionesActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("correo",correo);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_iluminacion) {
            intent= new Intent(HabitacionesActivity.this,IluminacionActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("correo",correo);
            startActivity(intent);
            finish();
            // Handle the camera action
        } else if (id == R.id.nav_principal) {
            intent= new Intent(HabitacionesActivity.this,MainActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("correo",correo);
            startActivity(intent);
            finish();

        }
        else if (id == R.id.nav_notificaciones) {
            intent= new Intent(HabitacionesActivity.this,NotificacionesActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("correo",correo);
            startActivity(intent);
            finish();

        }
        else if (id == R.id.nav_contactenos) {
            intent= new Intent(HabitacionesActivity.this,ContactenosActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("correo",correo);
            startActivity(intent);
            finish();

        }else if (id == R.id.nav_indicadores) {
            intent= new Intent(HabitacionesActivity.this,SensoresActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("correo",correo);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_actuadores) {
            intent= new Intent(HabitacionesActivity.this,ActuadoresActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("correo",correo);
            startActivity(intent);
            finish();

        }else if (id == R.id.nav_cerrar) {
            intent=new Intent(HabitacionesActivity.this,LoginActivity.class);
            editor.putInt("login",-1);
            editor.commit();
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
