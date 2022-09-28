package com.niit.jukebox.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

public class PlaylistDAO {

    public boolean createPlaylist(String playlistName) throws SQLException {
        if(playlistName==null){
            return false;
        }
        else {
            //we have to insert into playlist table
            PreparedStatement insertplaylist = JukeBoxConnection.getJukeBoxConnection().prepareStatement("insert into playlist (playlistname) values(?)");
            insertplaylist.setString(1, playlistName);
            int result = insertplaylist.executeUpdate();
            return result > 0 ? true : false;
        }
    }
    public Hashtable<String,Integer> viewAllPlaylists() throws SQLException {
        Hashtable<String,Integer> playlist = null;
        Statement selectstatement = JukeBoxConnection.getJukeBoxConnection().createStatement();
        ResultSet resultSet = selectstatement.executeQuery("select * from playlist");
        if(resultSet.isBeforeFirst()){
            playlist=new Hashtable<>();
            while(resultSet.next()){
                playlist.put(resultSet.getString(2),resultSet.getInt(1));
            }
        }
        return playlist;
    }
}
