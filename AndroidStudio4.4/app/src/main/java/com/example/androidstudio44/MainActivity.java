package com.example.androidstudio44;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ItemAdapter itemAdapter;
    Context thisContext;
    ListView myListView;
    TextView progressTextView;
    Map<String, Double> fruitsMap = new LinkedHashMap<String, Double>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        myListView = (ListView) findViewById(R.id.myListView);
        progressTextView = (TextView) findViewById(R.id.progressTextView);
        thisContext = this;

        progressTextView.setText("");
        Button btn = (Button) findViewById(R.id.getDataButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetData retrieveData = new GetData();
                retrieveData.execute("");
            }
        });

    } // END onCreate

    private class GetData extends AsyncTask<String, String, String> {

        String msg = "";

        @Override
        protected void onPreExecute() {
            progressTextView.setText("Connecting to database...");

        }

        @Override
        protected String doInBackground(String... Params) {

            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            try {

                conn  = DriverManager.getConnection(
                        DbStrings.DATABASE_URL,
                        DbStrings.USERNAME,
                        DbStrings.PASSWORD
                );

                stmt = conn.createStatement();
                String sql = "SELECT * from fruits";
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");

                    fruitsMap.put(name, price);
                }

                rs.close();
                stmt.close();
                conn.close();

                msg = "Process completed!";

            } catch (SQLException e) {
                msg = "\nSQLException: " + e.getMessage() +
                        "\nSQLState: " + e.getSQLState() +
                        "\nVendorError: " + e.getErrorCode();

            } finally {

                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else { msg = "Database is not connected!"; }

                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException sqlEx) { } // ignore
                }

                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException sqlEx) { } // ignore
                }
            }

            return null;
        } // END doInBackground

        @Override
        protected void onPostExecute(String msg) {
            super.onPostExecute(msg);
            progressTextView.setText(this.msg);

            if (fruitsMap.size() > 0) {
                itemAdapter = new ItemAdapter(thisContext, fruitsMap);
                myListView.setAdapter(itemAdapter);
            } // END if

        } // END onPostExecute

    } // END class GetData

} //END Main Activity
