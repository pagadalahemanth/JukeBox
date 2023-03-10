package com.niit.jukebox.DAO;

import com.niit.jukebox.model.Songs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SongDAO {
    public boolean insertSongs(Songs songs) throws SQLException,NullPointerException//we are inserting song details from java to sql using Statement
    {//Prepared Statement is used to establish connection to pass queries at run time
//        if(songs!=null){
//            return false;
//        }
//        else {}
            PreparedStatement insertStatement = JukeBoxConnection.getJukeBoxConnection().prepareStatement("insert into songs (songname,albumname,artist,genre,duration) values(?,?,?,?,?)");
            //songId is not necessary becoz it is auto_incremented
            //if u want to return songId use  .RETURN_GENERATED_KEYS
            //insertStatement.setInt(1,songs.getSongId());
            insertStatement.setString(1, songs.getSongName());//setString() converts into the String value
            //And then the driver converts this to an SQL varchar or char
            insertStatement.setString(2, songs.getAlbumName());
            insertStatement.setString(3, songs.getArtist());
            insertStatement.setString(4, songs.getGenre());
            insertStatement.setString(5, songs.getDuration());
            int result = insertStatement.executeUpdate();
            System.out.println(result);
//            ResultSet resultSet = insertStatement.getGeneratedKeys();//resultSet is used to display the data in the form of rows and columns
//            if (resultSet.next()) {
//                System.out.println(result);
//            }
            return result > 0 ? true : false;
    }
//To fetch a song from db
    public Songs getOneSong(String songName)throws SQLException
    {
        Songs songs=null;
        PreparedStatement fetchSong = JukeBoxConnection.getJukeBoxConnection().prepareStatement("select * from songs where songname=?");
        fetchSong.setString(1,songName);
        ResultSet resultSet = fetchSong.executeQuery();
        if(resultSet.next()){
            songs = new Songs(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6));
        }
        return songs;
    }

    //to fetch all songs
    public ArrayList<Songs> getAllSongs() throws SQLException{
        ArrayList<Songs> songsArrayList=null;
        Statement selectStatement=JukeBoxConnection.getJukeBoxConnection().createStatement();//establishes a connection to pass queries
        ResultSet resultSet= selectStatement.executeQuery("select * from songs");
        if(resultSet.isBeforeFirst()) {//if it is true db contains data...the cursor points to first line and that checks if data is present or not
            //if that is present we will create new arraylist to store.
            songsArrayList = new ArrayList<>();
            while(resultSet.next()){
                //.next() will not only checks the data it also fetch the data.
                songsArrayList.add(new Songs(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6)));
            }
        }
        System.out.println("*****************************************************************************************SONGS******************************************************************************");
        return songsArrayList;
    }
}
