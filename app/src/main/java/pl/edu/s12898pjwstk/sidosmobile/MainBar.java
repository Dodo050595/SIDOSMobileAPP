package pl.edu.s12898pjwstk.sidosmobile;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Calendar;

import HelperClasses.DatabaseHelper;
import HelperClasses.HelperMethods;
import HelperClasses.Utils;
import Models.UserTokens;
import ViewModels.CreateEventsTabViewModels;
import ViewModels.KonieViewModels;
import ViewModels.PracownikViewModels;
import ViewModels.TaskDisplayOnTab;
import ViewModels.VetRequestViewModels;

public class MainBar extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ProgressDialog progressDialog;
    public DatabaseHelper db;

    FragmentManager fragmen = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = HelperMethods.getDatabaseObject(getApplicationContext());





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        View hView =  navigationView.getHeaderView(0);
        TextView txView = (TextView) hView.findViewById(R.id.UserNameTItle);
        if(Utils.UserTokenCls != null) {
            Utils.UserTokenCls.UpdateListRoles();
            txView.setText(Utils.UserTokenCls.getUserName());

            if(!HelperMethods.IsInRole(Utils.CanAddVetRequest,Utils.UserTokenCls.getUserRolesList())){
                navigationView.getMenu().getItem(3).setVisible(false);
                fab.setVisibility(View.GONE);

            }else{
                fab.setVisibility(View.VISIBLE);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), addnewRquest.class);
                        startActivity(intent);
                    }
                });

            }

            if(!HelperMethods.IsInRole(Utils.CanSeeTasks,Utils.UserTokenCls.getUserRolesList())){
                navigationView.getMenu().getItem(5).setVisible(false);
            }

            boolean alarmUp = (PendingIntent.getBroadcast(getApplicationContext(), 0,
                    new Intent(getApplicationContext(), SenderReciever.class),
                    PendingIntent.FLAG_NO_CREATE) != null);

            if(!alarmUp) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 13);
                calendar.set(Calendar.MINUTE, 38);
                Intent intent = new Intent(getApplicationContext(), SenderReciever.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmManager.INTERVAL_DAY, pendingIntent);
            }

        }else{
            fab.setVisibility(View.GONE);
            txView.setText("Gość");
            navigationView.getMenu().getItem(3).setVisible(false);
            navigationView.getMenu().getItem(4).setVisible(false);
            navigationView.getMenu().getItem(5).setVisible(false);
            navigationView.getMenu().getItem(6).setTitle("Zaloguj");
        }

        fragmen.beginTransaction().replace(
                R.id.mainpage,new MainPageFragment())
                .commit();
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
        //getMenuInflater().inflate(R.menu.main_bar, menu);


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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
       // android.app.FragmentManager fragmen = getFragmentManager();

        if (id == R.id.Pracownicy) {
            fragmen.beginTransaction().replace(
                    R.id.mainpage,new PracownikViewModels())
                    .commit();
        } else if (id == R.id.Konie) {
            fragmen.beginTransaction().replace(
                    R.id.mainpage,new KonieViewModels())
                    .commit();
        }else if (id == R.id.Ogolne) {
            fragmen.beginTransaction().replace(
                    R.id.mainpage,new MainPageFragment())
                    .commit();
        }else if (id == R.id.ZapytaniaWeterynarza) {
            fragmen.beginTransaction().replace(
                    R.id.mainpage,new VetRequestViewModels())
                    .commit();
        }else if(id == R.id.Grafik) {
            fragmen.beginTransaction().replace(
                    R.id.mainpage, new CreateEventsTabViewModels())
                    .commit();
        }else if(id == R.id.Zadania) {
            progressDialog = ProgressDialog.show(this, "",
                    "Ładowanie. Proszę czekać...", true);
            fragmen.beginTransaction().replace(
                    R.id.mainpage, new TaskDisplayOnTab())
                    .commit();
            progressDialog.dismiss();

        }else if(id == R.id.Wyloguj){

            if(Utils.UserTokenCls != null){
               db.deleteData(Utils.UserTokenCls);
            }

                Utils.UserTokenCls = null;
                Intent intent = new Intent(getApplicationContext(), loginuseractivity.class);
                MainBar.this.startActivity(intent);
                MainBar.this.finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
