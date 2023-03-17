package com.example.test17_test

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// 데이터베이스(스키마) 만드는 역할
class DBHelper(context: Context): SQLiteOpenHelper(context, "testdb2", null, 1) {
    // 테이블 만드는 과정
    // 해당 디비의 내용은 파일로 저장
    // data -> data -> 해당 프로젝트 명 -> databases 에 있음
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table USER (" +
                "_id integer primary key autoincrement," +
                "name not null," +
                "age not null)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}