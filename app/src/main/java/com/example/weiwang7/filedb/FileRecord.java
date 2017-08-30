package com.example.weiwang7.filedb;

/**
 *  Created by wei.wang7 on 2017/8/18.
 */

class FileRecord {
    private String file_name, file_extension, md5, position, length, size, keyword;

    String getFile_name() {
        return file_name;
    }

    void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    String getFile_extension() {
        return file_extension;
    }

    void setFile_extension(String file_extension) {
        this.file_extension = file_extension;
    }

    String getMd5() {
        return md5;
    }

    void setMd5(String md5) {
        this.md5 = md5;
    }

    String getPosition() {
        return position;
    }

    void setPosition(String position) {
        this.position = position;
    }

    String getLength() {
        return length;
    }

    void setLength(String length) {
        this.length = length;
    }

    String getSize() {
        return size;
    }

    void setSize(String size) {
        this.size = size;
    }

    String getKeyword() {
        return keyword;
    }

    void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return file_name + ", " + position + ", " + keyword;
    }
}
