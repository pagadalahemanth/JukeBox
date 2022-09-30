package com.niit.jukebox.DAOtest;

import com.niit.jukebox.DAO.PlaylistDAO;
import com.niit.jukebox.DAO.SongDAO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Hashtable;

public class PlaylistDAOTest {
    PlaylistDAO playlistDAO;
    @Before
    public void setUp(){
        playlistDAO=new PlaylistDAO();
    }
    @After//This annotation executes statements after each testcase is executed
    public void tearDown(){
        playlistDAO=null;
    }
    @Test
    public void negativeCheckViewAllPlaylists() throws SQLException{
        Hashtable<String,Integer> playlist = playlistDAO.viewAllPlaylists();
        Assert.assertNull(null);
    }
    @Test
    public void checkCreatePlaylist() throws SQLException{
        boolean playlist = playlistDAO.createPlaylist(null);
        Assert.assertEquals(false,playlist);
    }
    @Test
    public void checkViewAllPlaylists() throws SQLException{
        Hashtable<String,Integer> playlist = playlistDAO.viewAllPlaylists();
        Assert.assertNotNull(playlist);
        //assertNotNull(Object object)
        //Asserts that an object isn't null.
    }
}
