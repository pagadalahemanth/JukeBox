package com.niit.jukebox.service;

import com.niit.jukebox.DAO.JukeBoxException;
import com.niit.jukebox.DAO.PlaylistContentDAO;
import com.niit.jukebox.model.Songs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;


public class PlaylistContentService {
    PlaylistContentDAO  playlistContentDAO;
    PlaylistContentDAO playListContentDAO=new PlaylistContentDAO();
    public boolean addSongsToPlaylistContent(ArrayList<Songs> songsArrayList, Hashtable<String,Integer> playlist,String songName,String playlistName) throws JukeBoxException, SQLException
    {
        int playlistId;
        boolean result;//boolean def false
        if (songName == null && playlistName == null) {
            throw new JukeBoxException("Provide all details");
        }else{
            playlistId= playlist.get(playlistName);
            int songId = 0;
            for (Songs song : songsArrayList) {
                if (song.getSongName().equalsIgnoreCase(songName)) {
                    songId = song.getSongId();
                    //result = true;
                    break;
                }
            }
            if (songId==0)
            { throw new JukeBoxException("Song is not present");}
            else if (playlistId == 0) {
                throw new JukeBoxException("Playlist is not present");
            } else {

                result=PlaylistContentDAO.addSongsToPlaylist(songId,playlistId);
            }
        }
        return result;
    }
    public boolean addSongsByAlbumNameToPlaylistContent(ArrayList<Songs> songsArrayList,Hashtable<String,Integer> playlist,String albumName,String playlistName) throws Exception
    {
        boolean result=false;
        if(songsArrayList.isEmpty() || playlist.isEmpty() || albumName==null || playlistName==null){
            throw new JukeBoxException("provide all values");
        }else {
            int playlistId = playlist.get(playlistName);
            ArrayList<Integer> arrayList = new ArrayList<>();
            for(Songs songs:songsArrayList){
                if(songs.getAlbumName().equals(albumName)){
                    //arrayList.add(songs.getSongId());
                    result=arrayList.add(songs.getSongId());
                }
            }
            if(playlistId==0)
                throw new JukeBoxException("playlist not present");
            else if (arrayList.isEmpty())
                throw new JukeBoxException("song not present");
            else
                for(int id:arrayList){
                    playlistContentDAO.addSongsToPlaylist(id,playlistId);
                }
        }
        return result;
    }
    public ArrayList<Songs> getSongsFromplaylistContent(String playlistName,Hashtable<String,Integer> playlist,ArrayList<Songs> songsArrayList) throws Exception
    {
        ArrayList<Integer> songIdList;
        ArrayList<Songs> songlist;
        //
        if(playlistName==null || playlist.isEmpty() || songsArrayList.isEmpty() )
            throw new JukeBoxException("Please provide all the details");
        else{
            int playlistId=playlist.get(playlistName);
            if(playlistId==0) throw new JukeBoxException("Playlist id cannot be zero");
            else {
                songIdList = playListContentDAO.viewSongsInPlaylist(playlistId);
            }
            if(songIdList.isEmpty()==false)
            {
                songlist=new ArrayList<>();
                for(int id:songIdList){
                    for(Songs song:songsArrayList){
                        if(song.getSongId()==id){
                            songlist.add(song);
                        }
                    }
                }
            }
            else
                throw new JukeBoxException("Playlist is empty");
        }
        return songlist;
    }
}
