package com.niit.jukebox.DAOtest;

import com.niit.jukebox.DAO.SongDAO;
import com.niit.jukebox.model.Songs;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class SongDAOTest {
    SongDAO songDAO;//class - obj//creating the obj of class that should be tested
    @Before//This annotation executes statements before each testcase is executed
    public void setUp(){
        songDAO=new SongDAO();
    }
    @After//This annotation executes statements after each testcase is executed
    public void tearDown(){
        songDAO=null;
    }
    @Test//this implies that below method is a test case
    public void testForGetAllSongs() throws SQLException {
        ArrayList<Songs> songsArrayList=songDAO.getAllSongs();//actual op-it is the op of what method returns when a call is made to it and
        //parameters are passed
        Assert.assertNotNull(songsArrayList);
    }
    @Test
    public void checkInsertSong() throws SQLException {
        boolean songs = songDAO.insertSongs(null);
        Assert.assertEquals(false,songs);
    }
    @Test
//    assertThrows(Class<T> expectedThrowable, ThrowingRunnable runnable)
    //The Throwable class is the superclass of all errors and exceptions in the Java language
//    Asserts that runnable throws an exception of type expectedThrowable when executed.
    public void checkInsertSongNullPointer() throws NullPointerException, SQLException {
        boolean songs = songDAO.insertSongs(null);
        //()-> for method call
        Assert.assertThrows(Throwable.class,() -> checkInsertSongNullPointer());
    }
}
