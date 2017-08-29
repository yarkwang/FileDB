package com.example.weiwang7.filedb;

/**
 *  Created by wei.wang7 on 2017/8/18.
 */

class FileRecord {
    private String file_name, file_extension, md5, position, length, size, keyword;

    void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    void setFile_extension(String file_extension) {
        this.file_extension = file_extension;
    }

    void setMd5(String md5) {
        this.md5 = md5;
    }

    void setPosition(String position) {
        this.position = position;
    }

    void setLength(String length) {
        this.length = length;
    }

    void setSize(String size) {
        this.size = size;
    }

    void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    String getFile_name() {
        return file_name;
    }

    String getFile_extension() {
        return file_extension;
    }

    String getMd5() {
        return md5;
    }

    String getPosition() {
        return position;
    }

    String getLength() {
        return length;
    }

    String getSize() {
        return size;
    }

    String getKeyword() {
        return keyword;
    }
}
