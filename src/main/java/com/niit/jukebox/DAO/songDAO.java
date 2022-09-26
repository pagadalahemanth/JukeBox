package com.niit.jukebox.DAO;

import com.niit.jukebox.model.Songs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class songDAO {
    public boolean insertSongs(Songs songs) throws SQLException
    {
        PreparedStatement insertStatement=JukeBoxConnection.getJukeBoxConnection().prepareStatement("insert into songs (songname,albumname,artist,genre,duration) values(?,?,?,?,?)");
        //songId is not necessary becoz it is auto_incremented
        //if u want to return id use  .RETURN_GENERATED_KEYS
        insertStatement.setString(2, songs.getSongName());
        insertStatement.setString(3, songs.getAlbumName());
        insertStatement.setString(4, songs.getArtist());
        insertStatement.setString(5, songs.getGenre());
        insertStatement.setString(6, songs.getDuration());
        int result = insertStatement.executeUpdate();
        return result>0?true:false;
    }
//To fetch song from db and store in ArrayList
    public Songs getSong(String songName)throws SQLException
    {
        Songs songs=null;
        PreparedStatement fetchSong = JukeBoxConnection.getJukeBoxConnection().prepareStatement("select * from songs where songname=?");
        fetchSong.setString(2,songName);
        ResultSet resultSet = fetchSong.executeQuery();
        if(resultSet.next()){
            songs = new Songs(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6));
        }
        return songs;
    }
}
