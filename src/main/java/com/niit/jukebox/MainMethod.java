package com.niit.jukebox;

import com.niit.jukebox.model.Songs;
import com.niit.jukebox.service.SongService;

public class MainMethod {
    public static void main(String[] args) {
        SongService service = new SongService();
        Songs songs = new Songs("slowly", "slow", "alludu", "love", "7");
        Songs songs1 = new Songs();
        try {
            service.addSong(songs);
            service.displaySongs();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
