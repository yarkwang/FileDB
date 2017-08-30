package com.example.weiwang7.filedb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

/**
 *  Created by wei.wang7 on 2017/8/18.
 */

class MyAdapter extends BaseAdapter implements Filterable {
    private static LayoutInflater inflater = null;
    private final Context context;
    private final ArrayList<FileRecord> storedFiles;
    private ArrayList<FileRecord> files;

    MyAdapter(Context context, ArrayList<FileRecord> files){
        this.context=context;
        this.files=files;
        this.storedFiles = new ArrayList<>(files);
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
            convertView = inflater.inflate(R.layout.layout_grid_item, parent, false);

        TextView fileNameTextView = convertView.findViewById(R.id.tv_file_name);
        TextView fileExtensionTextView = convertView.findViewById(R.id.tv_file_extension);
        TextView md5TextView = convertView.findViewById(R.id.tv_md5);
        TextView positionTextView = convertView.findViewById(R.id.tv_position);
        TextView lengthTextView = convertView.findViewById(R.id.tv_length);
        TextView sizeTextView = convertView.findViewById(R.id.tv_size);
        TextView keywordTextView = convertView.findViewById(R.id.tv_keyword);

        FileRecord fr = files.get(position);
        fileNameTextView.setText(context.getString(R.string.label_fileName, fr.getFile_name()));
        fileExtensionTextView.setText(context.getString(R.string.label_fileExtension, fr.getFile_extension()));
        md5TextView.setText(context.getString(R.string.label_md5, fr.getMd5()));
        positionTextView.setText(context.getString(R.string.label_position, fr.getPosition()));
        lengthTextView.setText(context.getString(R.string.label_length, fr.getLength()));
        sizeTextView.setText(context.getString(R.string.label_size, fr.getSize()));
        keywordTextView.setText(context.getString(R.string.label_keyword, fr.getKeyword()));
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                files = (ArrayList<FileRecord>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                if (constraint == null || constraint.length() == 0) {
                    results.values = storedFiles;
                    results.count = storedFiles.size();
                } else {
                    ArrayList<FileRecord> filteredFiles = new ArrayList<>();

                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < storedFiles.size(); i++) {
                        FileRecord fileRecord = storedFiles.get(i);
                        if (fileRecord.toString().toLowerCase().contains(constraint)) {
                            filteredFiles.add(fileRecord);
                        }
                    }

                    results.count = filteredFiles.size();
                    results.values = filteredFiles;
                }

                return results;
            }
        };
    }
}
