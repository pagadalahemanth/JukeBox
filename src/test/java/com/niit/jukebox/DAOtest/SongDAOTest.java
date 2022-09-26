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
    SongDAO songDAO;//class - obj
    @Before
    public void setUp(){
        songDAO=new SongDAO();
    }
    @After
    public void tearDown(){
        songDAO=null;
    }
    @Test
    public void testForGetAllSongs() throws SQLException {
        ArrayList<Songs> songsArrayList=songDAO.getAllSongs();
        Assert.assertNotNull(songsArrayList);
    }
}
