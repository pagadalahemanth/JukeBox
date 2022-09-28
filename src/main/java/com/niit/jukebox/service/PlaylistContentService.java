package com.niit.jukebox.service;

import com.niit.jukebox.DAO.PlaylistContentDAO;
import com.niit.jukebox.model.Songs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public class PlaylistContentService {
    PlaylistContentDAO  playlistContentDAO;
    public boolean addSongsToPlaylistContent(ArrayList<Songs> songsArrayList, Hashtable<String,Integer> playlist,String songName,String playlistName) throws Exception
    {
        boolean result=false;
        if(songsArrayList.isEmpty() || playlist.isEmpty() || songName!=null || playlistName!=null){
            throw new Exception("Provide all values");//if any one of them misses values exception occurs
        }else {
            int playlistId = playlist.get(playlistName);
            int songId = 0;
            for(Songs songs:songsArrayList){
                if(songs.getSongName().equals(songName)){
                    songId = songs.getSongId();
                    break;
                }
            }
            if(playlistId==0){
                throw new Exception("playlist not present");
            } else if (songId==0) {
                throw new Exception("song not present");
            }else
                playlistContentDAO.addSongsToPlaylist(songId,playlistId);
            result=true;
        }

        return result;
    }
//    {
//        boolean result = false;
//        if(songName!=null && playlistName!=null){
//            int playlistId = playlist.get(playlistName);
//            int songId = 0;
//            Iterator<Songs> songsIterator = songsArrayList.listIterator();
//            while (songsIterator.hasNext()){
//                if(songId!=0){
//                    songId = songsIterator.next().getSongId();
//                }
//            }if(songId==0){
//                throw new Exception("song not present");
//            } else if (playlistId==0) {
//                throw new Exception("playlist not present");
//            }else
//                result=true;
//                return playlistContentDAO.addSongsToPlaylist(songId,playlistId);
//        }
//        return result;
//    }

    public boolean addSongsByAlbumNameToPlaylistContent(ArrayList<Songs> songsArrayList,Hashtable<String,Integer> playlist,String albumName,String playlistName) throws Exception
    {
        boolean result=false;
        if(songsArrayList.isEmpty() || playlist.isEmpty() || albumName!=null || playlistName!=null){
            throw new Exception("provide all values");
        }else {
            int playlistId = playlist.get(playlistName);
            ArrayList<Integer> arrayList = new ArrayList<>();
            for(Songs songs:songsArrayList){
                if(songs.getAlbumName().equals(albumName)){
                    arrayList.add(songs.getSongId());
                    result=true;
                }
            }
            if(playlistId==0)
                throw new Exception("playlist not present");
            else if (arrayList.isEmpty())
                throw new Exception("song not present");
            else
                for(int id:arrayList){
                    playlistContentDAO.addSongsToPlaylist(id,playlistId);
                }
        }
        return result;
    }
//    {
//        boolean result = false;
//        if(albumName!=null && playlistName!=null){
//            int albumId=0;
//            int playlistId = playlist.get(playlistName);
//            ArrayList<Songs> arrayList = new ArrayList<>();
//            Iterator<Songs> albumIterator = arrayList.listIterator();
//            while(albumIterator.hasNext()){
//                if(albumId!=0){
//                    albumId = Integer.parseInt(albumIterator.next().getAlbumName());
//                }
//                if(playlistId==0){
//                    throw new Exception("playlist not present");
//                } else if (albumId==0) {
//                    throw new Exception("album is not present");
//                }else
//                    result=true;
//            }
//        }
//    }
    public ArrayList<Songs> getSongsFromplaylistContent(String playlistName,Hashtable<String,Integer> playlist,ArrayList<Songs> songsArrayList) throws Exception {
        int playlistId = 0;
        if(playlistName!=null || playlist.isEmpty() || songsArrayList.isEmpty()){
            throw new Exception("provide all values");
        }else{
            playlistId = 0;
            if (playlistName != null){
                playlistId = playlist.get(playlistName);
            }else {
                ArrayList<Integer> songIdList = playlistContentDAO.viewSongsInPlaylist(playlistId);
                {
                    if(songIdList.isEmpty()==false){
                        songIdList=new ArrayList<>();
                        for(Songs songs1:songsArrayList){
                            if (songs1.getSongId() == playlistId){
                                songsArrayList.add(songs1);
                            }
                        }
                    }
                }
            }
        }
        return songsArrayList;
    }
//    {
////        ArrayList<Songs> songs = null;
//        int playlistId = 0;
//        if (playlistName != null) {
//            playlistId = playlist.get(playlistName);
//        } else {
//            ArrayList<Integer> songIdList = playlistContentDAO.viewSongsInPlaylist(playlistId);
//            {
//                if(songIdList.isEmpty()==false){
//                    songIdList=new ArrayList<>();
//                for(Songs songs1:songsArrayList) {
//                    if (songs1.getSongId() == playlistId) {
//                        songsArrayList.add(songs1);
//                    }
//                }
//                }
//            }
//        }
//        return songsArrayList;
//    }
}
