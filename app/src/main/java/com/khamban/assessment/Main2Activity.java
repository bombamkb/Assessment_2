package com.khamban.assessment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ListView dataViews;
    private MySQLConnect mySQLConnect;
    private List<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        getAssessment();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


//        dataViews.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,items2)); //ทดแทนกันโดยเรียกใช้ adt แทน



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
        getMenuInflater().inflate(R.menu.main2, menu);
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

        if (id == R.id.nav_open) {
            Intent home = new Intent(getApplicationContext(),Main2Activity.class);
            startActivity(home);

        }
        else if (id == R.id.nav_report) {
            Toast.makeText(this,"รายงานการทำแบบประเมิน",Toast.LENGTH_SHORT).show();
            list list_page = new list();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.main ,list_page).commit();
        }
        else if (id == R.id.nav_setting) {

        }
        else if (id == R.id.nav_logout) {
            logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void logout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        Intent login_page = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(login_page);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
    });
        AlertDialog alert = builder.create();
        alert.show();
}

    public void getAssessment(){
        String url = "http://10.80.39.17/TSP58/nursing/application/controllers/amis/Mobile/Android/sqlfile.php?status=0";
//        Toast.makeText(Main2Activity.this, "เข้าฟังก์ชัน", Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray("Assessment");
                    final String[] arr = new String[result.length()];
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject collectData = result.getJSONObject(i);
                        String evaName = collectData.getString("name");
//                        items.add(evaName);
                        arr[i] = evaName;

                    }
                    final ListView dataViews = (ListView) findViewById(R.id.Lis_2);
                    dataViews.setAdapter(new ArrayAdapter<String>(Main2Activity.this, android.R.layout.simple_list_item_1,arr));//ทดแทนกันโดยเรียกใช้ adt แทน
                    dataViews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                            int index = 2;
                            if(arr[position].toString().equals("แบบประเมินการจัดการเรียนการสอนภาคทฤษฎี และสิ่งสนับสนุนการเรียนรู้"))
                            {
                                index = 1;
                            }
                            Intent home = new Intent(getApplicationContext(),List_of_Subject_andTeacher.class);
                            home.putExtra("index", index);
//                                            Toast.makeText(Main2Activity.this, "Item id: " +id +"Position :"+arr[position].toString()+"index : "+index, Toast.LENGTH_SHORT).show();
                                            Subject_andTeac List = new Subject_andTeac(index);
                                            FragmentManager manager = getSupportFragmentManager();
                                            manager.beginTransaction().replace(R.id.main, List).commit();


                                    }

                    });
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
//                showAssessment(response);
//                Toast.makeText(Main2Activity.this, "เข้าลูป", Toast.LENGTH_SHORT).show();
//                Toast.makeText(Main2Activity.this, items.get(0), Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(Main2Activity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        }

        );
//
        RequestQueue requestQueue = Volley.newRequestQueue(Main2Activity.this.getApplicationContext());
        requestQueue.add(stringRequest);


//        return arr;
    }

}


