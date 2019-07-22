package com.stackroute;

import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;

public class ActionListerRowSet implements RowSetListener {
    @Override
    public void rowSetChanged(RowSetEvent event) {

    }

    @Override
    public void rowChanged(RowSetEvent event) {

    }

    @Override
    public void cursorMoved(RowSetEvent event) {
        System.out.println("Cursor Moved");

    }
}
