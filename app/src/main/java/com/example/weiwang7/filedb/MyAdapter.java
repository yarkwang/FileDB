package com.example.weiwang7.filedb;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 *  Created by wei.wang7 on 2017/8/18.
 */

class MyAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<FileRecord> files;
    private static LayoutInflater inflater=null;

    MyAdapter(Context context, ArrayList<FileRecord> files){
        this.context=context;
        this.files=files;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){
        return files.size();
    }

    @Override
    public Object getItem(int position){
        return position;
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null)
            convertView=inflater.inflate(R.layout.layout_grid_item, null);

        TextView fileNameTextView=(TextView)convertView.findViewById(R.id.tv_file_name);
        TextView fileExtensionTextView=(TextView)convertView.findViewById(R.id.tv_file_extension);
        TextView md5TextView=(TextView)convertView.findViewById(R.id.tv_md5);
        TextView positionTextView=(TextView)convertView.findViewById(R.id.tv_postion);
        TextView lengthTextView=(TextView)convertView.findViewById(R.id.tv_length);
        TextView sizeTextView=(TextView)convertView.findViewById(R.id.tv_size);
        TextView keywordTextView=(TextView)convertView.findViewById(R.id.tv_keyword);

        FileRecord fr=new FileRecord();
        fr=files.get(position);
        fileNameTextView.setText("File name:  " + fr.getFile_name());
        fileExtensionTextView.setText("File extension:  " + fr.getFile_extension());
        md5TextView.setText("MD5:  " + fr.getMd5());
        positionTextView.setText("Position:  " + fr.getPosition());
        lengthTextView.setText("Length:  " + fr.getLength());
        sizeTextView.setText("Size:  " + fr.getSize());
        keywordTextView.setText("Keyword:  " + fr.getKeyword());
        return convertView;
    }
}
