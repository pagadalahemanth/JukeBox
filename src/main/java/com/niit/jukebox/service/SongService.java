package com.niit.jukebox.service;

import com.niit.jukebox.DAO.SongDAO;
import com.niit.jukebox.model.Songs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class SongService {
    ArrayList<Songs> songsArrayList;
    SongDAO songDAO;

    public SongService(){
        songDAO = new SongDAO();
    }
    public boolean addSong(Songs songs) throws SQLException {
        boolean result = false;
        Songs checkSong =songDAO.getSong(songs.getSongName());
//        if (checkSong == null) {
//            result=songDAO.insertSongs(songs);
//        }
        if(checkSong(songs.getSongName())==false){
            result =songDAO.insertSongs(songs);
        }
        return result;
    }


    private boolean checkSong(String songname){
        if(songsArrayList!=null){
            return true;
        }else{
            return false;
        }
    }

    public void displaySongs() throws SQLException {
        songsArrayList = songDAO.getAllSongs();
        if(songsArrayList==null){
            System.out.println("Empty");
        }else{
            Iterator<Songs> songsIterator =songsArrayList.iterator();
            while(songsIterator.hasNext()){
                System.out.println(songsIterator.next());
            }
        }
    }
}
