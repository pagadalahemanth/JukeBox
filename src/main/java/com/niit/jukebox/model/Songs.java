package com.niit.jukebox.model;

public class Songs {
    private int songId;
    private String songName;
    private String albumName;
    private String artist;
    private String genre;
    private String duration;

    public Songs() {
    }

    public Songs(String songName, String albumName, String artist, String genre, String duration) {//To insert
        this.songName = songName;
        this.albumName = albumName;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
    }

    public Songs(int songId, String songName, String albumName, String artist, String genre, String duration) {//To select
        this.songId = songId;
        this.songName = songName;
        this.albumName = albumName;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
    }

    public int getSongId() {
        return songId;
    }//setter method for songId is not necessary because in jukebox db we have set to auto_increment and primary key

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
//To display in the form of tables
    @Override
    public String toString() {
        //System.out.println("===============================================================================");
//        System.out.format("%10s\t%30s\t%30s\t%30s\t%30s\t%20s\t,songId,songName,albumName,artist,genre,duration");
        return String.format("%10s\t%30s\t%30s\t%30s\t%30s\t%20s\t\n",songId,songName,albumName,artist,genre,duration);//to represent op in the form of rows and columns

    }

//    @Override
//    public String toString() {
//        return "Songs{" +
//                "songId=" + songId +
//                ", songName='" + songName + '\'' +
//                ", albumName='" + albumName + '\'' +
//                ", artist='" + artist + '\'' +
//                ", genre='" + genre + '\'' +
//                ", duration='" + duration + '\'' +
//                '}';
//    }
}
