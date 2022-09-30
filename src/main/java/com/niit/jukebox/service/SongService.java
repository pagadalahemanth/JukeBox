package com.niit.jukebox.service;

import com.niit.jukebox.DAO.JukeBoxException;
import com.niit.jukebox.DAO.SongDAO;
import com.niit.jukebox.model.Songs;

import java.security.spec.ECField;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class SongService {
    ArrayList<Songs> songsArrayList;
    SongDAO songDAO;

    public SongService() {
        songDAO = new SongDAO();
    }

    private boolean checkSong(ArrayList<Songs> songsArrayList, String songname) throws Exception {
       // System.out.println(songname);//printing
        boolean result = false;
        if(songname==null || songsArrayList.isEmpty()){
            throw new Exception("provide all values");
        }
        else {
            for (Songs songs : songsArrayList) {
                //System.out.println("inside for loop");
                System.out.println(songs.getSongName());
                if (songs.getSongName().equals(songname)) {
                    //System.out.println("inside if");
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
    public boolean addSong(Songs songs, ArrayList<Songs> songsArrayList) throws SQLException,Exception
    {
        boolean result = false;
        if(songs==null || songsArrayList.isEmpty()){
            throw new Exception("provide all values");
        }
        else {
            if (!checkSong(songsArrayList, songs.getSongName())) {//checksong returns true if it contains given songname
                //System.out.println("check song");
                songDAO.insertSongs(songs);
                result = true;
            }
            System.out.println(songs.getSongName()+"  Song added");
            return result;
        }
    }
//    {
//        boolean result=false;
//        if(checkSong(songsArrayList,songs.getSongName())==false){
//            songDAO.insertSongs(songs);
//            result=true;
//        }else result=false;
//        songsArrayList.add(songs);
//        return result;
//    }




    public void displaySongs(ArrayList<Songs> songsArrayList) throws SQLException, Exception {
        //songsArrayList = songDAO.getAllSongs();
        if (songsArrayList == null) {
            throw new JukeBoxException("arraylist is null");
        } else {
            System.out.format("%10s\t%30s\t%30s\t%30s\t%30s\t%20s\t", "songId", "songName", "albumName", "artist", "genre", "duration");
            //Iterator<Songs> songsIterator = songsArrayList.iterator();
            for(Songs songs:songsArrayList){
                System.out.println(songs);
            }
//            while (songsIterator.hasNext()) {
//                System.out.println(songsIterator.next());
//            }
        }
    }
    public Songs getASongByName(ArrayList<Songs> songsList, String songName) throws Exception ,SQLException
    {
        Songs selectedSong = null;
        if(songsList.isEmpty() && songName==null){
            throw new Exception("Please provide all values");
        }else{
            for(Songs list:songsList){
                if(list.getSongName().contains(songName)){
                    selectedSong=list;
                    break;
                }
            }
        }
        return selectedSong;
    }
//    public boolean addSongs(Songs songs) throws Exception,SQLException {
//        boolean result=false;
//        Songs songs1 = songDAO.getSong(songs.getSongName());
//        if(songs1==null){
//            songDAO.insertSongs(songs);
//            result=true;
//        }
//        System.out.println("Song added");
//        return result;
//    }


    public ArrayList<Songs> getSongsByAlbumName(String albumName, ArrayList<Songs> songList) throws Exception {
        if(albumName==null || songList.isEmpty()){
            throw new Exception("Provide all values");
        }
        else {
            ArrayList<Songs> songsByAlbumName = null;
            if (songList.isEmpty() == false && albumName != null) {
                songsByAlbumName = new ArrayList<>();
                for (Songs songs : songList) {
                    if (songs.getAlbumName().equals(albumName))
                        songsByAlbumName.add(songs);
                }
            }
            System.out.println("song got by album");
            return songsByAlbumName;
        }
    }

    public ArrayList<Songs> getSongsByArtistName(String artistName, ArrayList<Songs> songList) throws Exception {
        if(artistName==null || songList.isEmpty()){
            throw new Exception("provide all values");
        }
        else {
            ArrayList<Songs> songsByAlbumName = null;
            if (songList.isEmpty() == false && artistName != null) {
                songsByAlbumName = new ArrayList<>();
                for (Songs songs : songList) {
                    if (songs.getArtist().contains(artistName))
                        System.out.println(songsByAlbumName.add(songs));
                }
            }
            System.out.println("song found by artist");
            return songsByAlbumName;
        }
    }

    public ArrayList<Songs> getSongsByGenre(String genre, ArrayList<Songs> songList) throws Exception {
        if(genre==null || songList.isEmpty()==true){//.isEmpty-Returns true if this list contains no elements.
            throw new Exception("provide all values");
        }
        else {
            ArrayList<Songs> songsByGenre = null;
            if (songList.isEmpty() == false && genre != null) {
                songsByGenre = new ArrayList<>();
                for (Songs songs : songList) {
                    if (songs.getGenre().contains(genre))
                        songsByGenre.add(songs);
                }
            }
            System.out.println("song found by genre");
            return songsByGenre;
        }
    }
}