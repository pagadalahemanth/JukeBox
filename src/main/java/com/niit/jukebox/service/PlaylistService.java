package com.niit.jukebox.service;

import com.niit.jukebox.DAO.PlaylistContentDAO;
import com.niit.jukebox.DAO.PlaylistDAO;

import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Set;

public class PlaylistService {
    PlaylistContentDAO playlistContentDAO;
    PlaylistDAO playlistDAO;
    public PlaylistService(){
        playlistContentDAO=new PlaylistContentDAO();
        playlistDAO=new PlaylistDAO();
    }

    private boolean checkIfPlaylistPresent(String playlistName) throws SQLException {
//        Hashtable<String,Integer> checkPlaylist;
        boolean result = false;
        if(playlistDAO.viewAllPlaylists().containsKey(playlistName)){
            result=true;
        }
        return result;
    }
    public boolean addPlaylist(String playlistName, Hashtable<String,Integer> playlist) throws SQLException {
        boolean result = false;
        if(checkIfPlaylistPresent(playlistName)==false){
            playlistDAO.createPlaylist(playlistName);
            result=true;
        }
        return result;
    }

    public Hashtable<String,Integer> getAllPlaylists() throws SQLException {
        return playlistDAO.viewAllPlaylists();
    }
}
