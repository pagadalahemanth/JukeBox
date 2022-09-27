package com.niit.jukebox.service;

import com.niit.jukebox.DAO.SongDAO;
import com.niit.jukebox.model.Songs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class SongService {
    ArrayList<Songs> songsArrayList;
    SongDAO songDAO;

    public SongService() {
        songDAO = new SongDAO();
    }

    private boolean checkSong(ArrayList<Songs> songsArrayList, String songname) {
        boolean result = false;
        for (Songs songs : songsArrayList) {
            if (songs.getSongName().equals(songname)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean addSong(Songs songs, ArrayList<Songs> songsArrayList) throws SQLException {
        boolean result = false;
//        Songs checkSong =songDAO.getSong(songs.getSongName());
//        if (checkSong == null) {
//            result=songDAO.insertSongs(songs);
//        }
        if (checkSong(songsArrayList, songs.getSongName()) == false) {
             songDAO.insertSongs(songs);
            result = true;
        }
        return result;
    }

    public void displaySongs() throws SQLException {
        ArrayList<Songs> songsArrayList;
        songsArrayList = songDAO.getAllSongs();
        if (songsArrayList == null) {
            System.out.println("Empty");
        } else {
            System.out.format("%10s\t%30s\t%30s\t%30s\t%30s\t%20s\t", "songId", "songName", "albumName", "artist", "genre", "duration");
            Iterator<Songs> songsIterator = songsArrayList.iterator();
            while (songsIterator.hasNext()) {
                System.out.println(songsIterator.next());
            }
        }
    }
    public Songs getASongByName(ArrayList<Songs> songsList, String songName) {
        Songs selectedSong = null;
        if(songsList.isEmpty()==false) {
            selectedSong = new Songs();
            for (Songs songs : songsList) {
                if (songs.getSongName().equals(songName)) {
                    selectedSong.getSongName();
                }else
                    return null;
            }
        }
        return selectedSong;
    }
    public boolean addSongs(Songs songs, ArrayList<Songs> songsArrayList) throws SQLException {
        boolean result = false;
        if (getASongByName(songsArrayList, songs.getSongName()) == null) {
            result = songDAO.insertSongs(songs);
            result = true;
        }
        return result;
    }

    public ArrayList<Songs> getSongsByAlbumName(String albumName, ArrayList<Songs> songList) {
        ArrayList<Songs> songsByAlbumName = null;
        if (songList.isEmpty() == false && albumName != null) {
            songsByAlbumName = new ArrayList<>();
            for (Songs songs : songList) {
                if (songs.getAlbumName().equals(albumName))
                    songsByAlbumName.add(songs);
            }
        }
        return songsByAlbumName;
    }

    public ArrayList<Songs> getSongsByArtistName(String artistName, ArrayList<Songs> songList) {
        ArrayList<Songs> songsByAlbumName = null;
        if (songList.isEmpty() == false && artistName != null) {
            songsByAlbumName = new ArrayList<>();
            for (Songs songs : songList) {
                if (songs.getArtist().contains(artistName))
                    songsByAlbumName.add(songs);
            }
        }
        return songsByAlbumName;
    }

    public ArrayList<Songs> getSongsByGenre(String genre, ArrayList<Songs> songList) {
        ArrayList<Songs> songsByGenre = null;
        if (songList.isEmpty() == false && genre != null) {
            songsByGenre = new ArrayList<>();
            for (Songs songs : songList) {
                if (songs.getGenre().contains(genre))
                    songsByGenre.add(songs);
            }
        }
        return songsByGenre;
    }
}