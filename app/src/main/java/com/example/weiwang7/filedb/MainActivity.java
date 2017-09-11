package com.example.weiwang7.filedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        copyDatabase();

        ListView listView = (ListView) findViewById(R.id.lv_file);

        DatabaseHelper databaseHelper=new DatabaseHelper(MainActivity.this);
        ArrayList<FileRecord> files;

        files = databaseHelper.getAllFiles();
        adapter=new MyAdapter(MainActivity.this, files);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
//                String fileName=((TextView)view.findViewById(R.id.tv_file_name)).getText().toString().substring(11);
//                String md5=((TextView)view.findViewById(R.id.tv_md5)).getText().toString().substring(5);
//                Toast.makeText(getApplicationContext(), fileName+"_"+md5, Toast.LENGTH_SHORT).show();
                String url = ((TextView) view.findViewById(R.id.tv_url)).getText().toString().substring(5);
                Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), Thumbnail.class);
                intent.putExtra(Intent.EXTRA_TEXT, url);
                startActivity(intent);
                //startActivity(new Intent(getApplicationContext(), Thumbnail.class));
            }
        });

        EditText myFilter=(EditText)findViewById(R.id.myFilter);
        myFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id=item.getItemId();
//        if(id == R.id.action_settings){
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    private void copyDatabase() {
        String package_name= getApplicationContext().getPackageName();
        String DB_PATH= getApplicationContext().getFilesDir().getPath() + "data/"+package_name+"/databases/";
        String DB_NAME="file_db.db";
        try{
            InputStream inputStream=getResources().getAssets().open(DB_NAME);

            File dbFile=new File(DB_PATH);
            if(dbFile.mkdirs()){
                Log.d( null, "new data folder is created");
            }

            String outputFileName=DB_PATH + DB_NAME;
            OutputStream outputStream=new FileOutputStream(outputFileName);

            byte[] buffer=new byte[1024];
            int length;

            while((length=inputStream.read(buffer))>0){
                outputStream.write(buffer, 0, length);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
