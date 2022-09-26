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

    private boolean checkSong(ArrayList<Songs> songsArrayList,String songname) {
        boolean result = false;
        for (Songs songs : songsArrayList) {
            if (songs.getSongName().equals(songname)) {
                result = true;
                break;
            }
        }
        return result;
    }
    public boolean addSong(Songs songs) throws SQLException {
        boolean result = false;
//        Songs checkSong =songDAO.getSong(songs.getSongName());
//        if (checkSong == null) {
//            result=songDAO.insertSongs(songs);
//        }
        if(checkSong(songsArrayList,songs.getSongName())==false){
            result =songDAO.insertSongs(songs);
            result=true;
        }
        return result;
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
