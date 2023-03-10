package com.niit.jukebox.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistContentDAO {
    public static boolean addSongsToPlaylist(int songId, int playlistId) throws SQLException {
        //we have to insert into playlistcontent table
        //Prepared Statement is used to establish connection to pass queries at run time

        PreparedStatement insertSongs = JukeBoxConnection.getJukeBoxConnection().prepareStatement("insert into playlistcontent(playlistid,songid) values(?,?);");
        insertSongs.setInt(1,playlistId);
        insertSongs.setInt(2,songId);
        int result = insertSongs.executeUpdate();
        System.out.println(result);
        return result>0?true:false;
    }

    public ArrayList<Integer> viewSongsInPlaylist(int playlistId) throws SQLException {
        ArrayList<Integer> arrayList = null;
        PreparedStatement viewSongs = JukeBoxConnection.getJukeBoxConnection().prepareStatement("select * from playlistcontent where playlistid=?");
        viewSongs.setInt(1,playlistId);
        //Sets the designated parameter to the given Java int value
        ResultSet resultSet = viewSongs.executeQuery();//executeQuery is used for DQL commands like SEL.
        if(resultSet.isBeforeFirst()){
            arrayList = new ArrayList<>();//to store songs in playlist
            while(resultSet.next())
                arrayList.add(resultSet.getInt(2));//if 1 it will display fields of table
        }
        return arrayList;
    }
}