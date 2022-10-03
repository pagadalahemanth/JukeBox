package com.niit.jukebox.main;

import com.niit.jukebox.DAO.PlaylistDAO;
import com.niit.jukebox.DAO.SongDAO;
import com.niit.jukebox.model.Songs;
import com.niit.jukebox.service.PlayerService;
import com.niit.jukebox.service.PlaylistContentService;
import com.niit.jukebox.service.PlaylistService;
import com.niit.jukebox.service.SongService;

import java.sql.SQLException;
import java.util.*;

//created main separately for abstraction
public class MainMethod {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //objects
        SongService songService = new SongService();
        PlaylistService playlistService = new PlaylistService();
        PlaylistContentService playlistContentService = new PlaylistContentService();
        PlayerService playerService = new PlayerService();
        SongDAO songDAO = new SongDAO();
        try {
            //create an arraylist for songlist and playlist
            ArrayList<Songs> songsArrayList;
            Hashtable<String, Integer> playlists;
            char ans;
            do {
                //fetch songs and playlists
                songsArrayList = songDAO.getAllSongs();
                playlists = playlistService.getAllPlaylists();
                songService.displaySongs(songsArrayList);//displays arraylist of songs
                //to display songs
                //fetching option from user
                int choice = 0;
                int option = 0;
                int opt = 0;
                System.out.println("*************************");
                System.out.println(" 1.Enter 1 for Song\n 2.Enter 2 for playlist\n 3.Enter 3 for Player\n 4.Enter 4 for exit");//choice
                System.out.println("*************************");
                System.out.println("Choose one of the following options");
                choice = sc.nextInt();
                switch (choice) {
                    case 1:

                        System.out.println("*************************");
                        System.out.println(" 1.Enter 1 to add a song\n 2.Enter 2 to search song by name\n 3.Enter 3 to search song by genre\n 4.Enter 4 to search song by artistName\n 5.Enter 5 to search song by albumName\n 6.Enter 6 to return to main\n 7.Enter 7 to exit");
                        System.out.println("*************************");
                        option = sc.nextInt();
                        switch (option) {
                            case 1:
                                System.out.println("Enter the songname: ");
                                sc.nextLine();
                                String songName = sc.nextLine();
//                            sc.nextLine();
                                System.out.println("Enter albumName: ");
                                String albumName = sc.nextLine();
                                System.out.println("Enter artistName: ");
                                String artistName = sc.nextLine();
                                System.out.println("Enter genre: ");
                                String genre = sc.nextLine();
                                System.out.println("Enter duration: ");
                                String duration = sc.nextLine();
                                //sc.nextLine();
                                Songs songs = new Songs(songName, albumName, artistName, genre, duration);//song obj
                                //Songs songs1 = new Songs(songId,songName,albumName,artistName,genre,duration);
                                boolean res = songService.addSong(songs, songsArrayList);//calling add method from song service class
                                if ((res == true)) {//if addSongs is not null refetching arraysonglist
                                    System.out.println("Song added");
                                    songsArrayList = songDAO.getAllSongs();
                                    songService.displaySongs(songsArrayList);
                                }
                                break;
                            case 2:
                                System.out.println("Enter the song name: ");
                                sc.nextLine();
                                String songname = sc.nextLine();
                                //songService.getASongByName(songsArrayList,songname);
                                Songs songs1 = songService.getASongByName(songsArrayList, songname);
                                if (songs1 != null) {
                                    System.out.println(songs1);
                                } else
                                    System.out.println("Song not found");
                                break;
                            case 3:
                                System.out.println("Enter the Genre: ");
                                sc.nextLine();
                                String Genre = sc.nextLine();
                                ArrayList<Songs> songs2 = songService.getSongsByGenre(Genre, songsArrayList);//this will return an arraylist of songs
                                if (songs2 != null) {
                                    System.out.println(songs2);
                                    //System.out.println("songs found by genre= " + Genre);
                                } else
                                    System.out.println(Genre + " Genre not found");
                                break;
                            case 4:
                                System.out.println("Enter artistName: ");
                                sc.nextLine();
                                String artistname = sc.next();
                                ArrayList<Songs> songs3 = songService.getSongsByArtistName(artistname, songsArrayList);
                                if (songs3 != null) {
                                    System.out.println(songs3);
                                } else
                                    System.out.println(artistname + " artist not found");
                                break;
                            case 5:
                                ;
                                System.out.println("Enter the albumName: ");
                                sc.nextLine();
                                String albumname = sc.nextLine();
                                ArrayList<Songs> songs4 = songService.getSongsByAlbumName(albumname, songsArrayList);
                                if (songs4 != null) {
                                    System.out.println(songs4);
                                } else
                                    System.out.println(albumname + " album not found");
                                break;
                            case 6:
                                System.out.println("Returning to main- press 'y' to return to main menu and 'n' to to exit");
                                break;
                            case 7:
                                System.exit(0);
                                break;

                        }
                        break;
                    case 2:
                        System.out.println("*************************");
                        System.out.println(" 1.Enter 1 to show all playlists\n 2.Enter 2 to create playlists\n 3.Enter 3 to add song to playlist\n 4.Enter 4 to add album to playlist\n 5.Enter 5 to display content\n 6. Enter 6 to return to main menu\n 7.Enter 7 to exit");
                        System.out.println("*************************");
                        //System.out.println("Enter 2 for playlists");
                        opt = sc.nextInt();
                        switch (opt) {
                            case 1:
                                playlistService.getAllPlaylists();
                                //Set set1 = Collections.singleton(playlists.get(playlists)); //.singleton-Returns an immutable set containing only the specified object. The returned set is serializable
                                Set keys = playlists.keySet();//to fetch only keys from playlists
                                //Integer set1 = playlists.get(playlists);
                                Iterator value = keys.iterator();
                                System.out.println("=============PLAYLISTS=============");
                                while (value.hasNext()) {
                                    System.out.println(value.next());
                                }
                                System.out.println("===================================");
                                break;
                            case 2:

                                System.out.println("Enter PlaylistName: ");
                                sc.nextLine();
                                String playlistname = sc.nextLine();
//                              //playlistService.addPlaylist(playlistname, playlists);
                                //Hashtable<String, Integer> allPlaylists = playlistService.getAllPlaylists();
                                if (playlistService.addPlaylist(playlistname, playlists) == true) {
                                    //System.out.println(playlists);
                                    playlists = playlistService.getAllPlaylists();
                                    System.out.println(playlists);
                                }
                                //playlistname="\u001B[0m";
                                System.out.println(playlistname + "playlist added successfully");
                                break;
                            case 3:

                                System.out.println("Enter the song name: ");
                                sc.nextLine();
                                String songName = sc.nextLine();
                                System.out.println("Enter the playlistName: ");
                                //sc.nextLine();
                                String playlistName = sc.nextLine();
                                //playlistContentService.addSongsToPlaylistContent(songsArrayList, playlists, songName, playlistName);
                                //System.out.println(result);
                                if (playlistContentService.addSongsToPlaylistContent(songsArrayList, playlists, songName, playlistName) == true) {
                                    System.out.println(songName.toUpperCase() + " song added to playlist " + playlistName.toUpperCase());
                                } else
                                    System.out.println("song is not added to playlist");
                                break;
                            case 4:
                                System.out.println("Enter the  albumname: ");
                                sc.nextLine();
                                String albumName = sc.nextLine();
                                System.out.println("Enter the playlistName: ");
                                //sc.nextLine();
                                String playlistnames = sc.nextLine();
                                if (playlistContentService.addSongsByAlbumNameToPlaylistContent(songsArrayList, playlists, albumName, playlistnames) == true) {
                                    System.out.println(albumName.toUpperCase() + " album added to playlist " + playlistnames);
                                } else
                                    System.out.println("album is not added to playlist");
                                break;
                            case 5:
                                sc.nextLine();
                                System.out.println("Enter the playlistName to display the content: ");
                                String playlistnam = sc.nextLine();
                                //try {
                                ArrayList<Songs> playlistsongs = playlistContentService.getSongsFromplaylistContent(playlistnam, playlists, songsArrayList);
                                //System.out.println(playlistsongs);
                                if (playlistsongs != null) {
                                    songService.displaySongs(playlistsongs);
                                } else System.out.println("songs not present");
//                            } catch (Exception e) {
//                                System.out.println(e.getMessage());;
//                            }
                                break;
                            case 6:
                                System.out.println("Returning to main" + "\u001B[0m");
                                break;
                            case 7:
                                System.exit(0);
                                break;
                        }
                        break;
                    case 3:

                            int songId = 0;
                            System.out.println("Enter playlistName to play");
                            sc.nextLine();
                            String playlistName = sc.nextLine();
                            ArrayList<Songs> songsToPlay = playlistContentService.getSongsFromplaylistContent(playlistName, playlists, songsArrayList);
                            int c = 0;
                            System.out.println("Enter songId");
                            songId = sc.nextInt();
                            for (Songs songs : songsToPlay) {
                                playerService.playSong(songId);//plays song

                                while (true) {
                                    System.out.println("1. pause");
                                    System.out.println("2. resume");
                                    System.out.println("3. restart");
                                    System.out.println("4. stop");
                                    System.out.println("5. Jump to specific time");
                                    c = sc.nextInt();
                                    playerService.gotoChoice(c);
                                    if (c == 4)
                                        //playerService.stop();
                                        break;
                                }
                                break;
                            }
                        break;
//                            while (true) {
//                                do {
//                                playerService.gotoChoice(c);
//                                System.out.println("1.pause");
//                                System.out.println("2.resume");
//                                System.out.println("3.restart");
//                                System.out.println("4.stop");
//                                System.out.println("5.jump to specific time");
//                                c = sc.nextInt();
//                                if (c == 1) {
//                                    playerService.pause();
//                                }
//                                else if (c == 2) {
//                                    playerService.resume();
//                                } else if (c == 3) {
//                                    playerService.restart();
//                                } else if (c == 4) {
//                                    playerService.stop();
//                                } else if (c == 5) {
//                                    playerService.jump(10);
//                                }else
//                                    System.out.println("Do you want to choose options again");
//                                    ans=sc.next().charAt(0);
//                            }while (ans=='y');
//                        break;
//                        }

                    case 4:
                        System.out.println("Exiting..........");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("default");
                }
                System.out.println("Do you want to continue?: ");
                ans = sc.next().charAt(0);
            } while (ans == 'y');
            System.out.println("Ok");
            sc.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
