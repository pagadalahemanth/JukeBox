package com.niit.jukebox;

import com.niit.jukebox.DAO.PlaylistDAO;
import com.niit.jukebox.DAO.SongDAO;
import com.niit.jukebox.model.Songs;
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
        SongDAO songDAO = new SongDAO();
        try {
            //create an arraylist for songlist and playlist
            ArrayList<Songs> songsArrayList = null;
            Hashtable<String, Integer> playlists = null;
            //fetch songs and playlists
//        try {
            songsArrayList = songDAO.getAllSongs();
            playlists = playlistService.getAllPlaylists();
            //to display songs
            songService.displaySongs(songsArrayList);//displays arraylist of songs
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());;
//        } catch (Exception e) {
//            System.out.println(e.getMessage());;
//        }
            char ans;
            do {
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
//                            System.out.println("Enter the songId; ");
//                            int songId = sc.nextInt();
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
                                System.out.println(songs);
                                //Songs songs1 = new Songs(songId,songName,albumName,artistName,genre,duration);
//                            try {
                                boolean res = songService.addSong(songs, songsArrayList);//calling add method from song service class
                                if ((res==true)) {//if addSongs is not null refetching arraysonglist
                                    //songService.displaySongs(songsArrayList);
                                    songDAO.getAllSongs();
                                }
//                            } catch (Exception e) {
//                                System.out.println(e.getMessage());;
//                            }

                                break;
                            case 2:
                                System.out.println("Enter the song name: ");
                                sc.nextLine();
                                String songname = sc.nextLine();

                                //songService.getASongByName(songsArrayList,songname);
                                //try {
                                Songs songs1 = songService.getASongByName(songsArrayList, songname);
                                System.out.println(songs1);

                                break;
                            case 3:
                                System.out.println("Enter the Genre: ");
                                sc.nextLine();
                                String Genre = sc.nextLine();
                                //try {
                                ArrayList<Songs> songs2 = songService.getSongsByGenre(Genre, songsArrayList);//this will return an arraylist of songs
                                System.out.println(songs2);
//                            } catch (Exception e) {
//                                System.out.println(e.getMessage());;
//                            }
                                break;
                            case 4:
                                System.out.println("Enter artistName: ");
                                sc.nextLine();
                                String artistname = sc.next();

                                //try {
                                ArrayList<Songs> songs3 = songService.getSongsByArtistName(artistname, songsArrayList);
                                System.out.println(songs3);
//                            } catch (Exception e) {
//                                System.out.println(e.getMessage());;
//                            }
                                break;
                            case 5:
                                ;
                                System.out.println("Enter the albumName: ");
                                sc.nextLine();
                                String albumname = sc.nextLine();
                                //try {
                                ArrayList<Songs> songs4 = songService.getSongsByAlbumName(albumname, songsArrayList);
                                System.out.println(songs4);
//                            } catch (Exception e) {
//                                System.out.println(e.getMessage());;
//                            }
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
//                            System.out.println("Enter 1 to show all playlists");
//                            opt = sc.nextInt();
                                //try {
                                playlistService.getAllPlaylists();
//                            } catch (SQLException e) {
//                                System.out.println(e.getMessage());;
//                            }
                                //Set set1 = Collections.singleton(playlists.get(playlists)); //.singleton-Returns an immutable set containing only the specified object. The returned set is serializable
                                Set keys = playlists.keySet();//to fetch only keys from playlists
                                //Integer set1 = playlists.get(playlists);
                                Iterator value = keys.iterator();
                                while (value.hasNext()) {
                                    System.out.println(value.next());
                                }
                                break;
                            case 2:

                                System.out.println("Enter PlaylistName: ");
                                sc.nextLine();
                                String playlistname = sc.nextLine();
                                //try {
                                boolean res = playlistService.addPlaylist(playlistname, playlists);
                                Hashtable<String, Integer> allPlaylists = playlistService.getAllPlaylists();
                                System.out.println(allPlaylists);
                                System.out.println("playlist added successfully");
//                            } catch (SQLException e) {
//                                System.out.println(e.getMessage());
//                            }
                                break;
                            case 3:

                                System.out.println("Enter the song name: ");
                                sc.nextLine();
                                String songName = sc.nextLine();
                                System.out.println("Enter the playlistName: ");
                                //sc.nextLine();
                                String playlistName = sc.nextLine();

                                //try {
                                boolean result = playlistContentService.addSongsToPlaylistContent(songsArrayList, playlists, songName, playlistName);
                                System.out.println(result);
//                            } catch (Exception e) {
//                                System.out.println(e.getMessage());;
//                            }
                                if (playlistContentService != null) {
                                    System.out.println(songName + " song added to playlist");
                                }
                                break;
                            case 4:
                                System.out.println("Enter the  albumname: ");
                                sc.nextLine();
                                String albumName = sc.nextLine();
                                System.out.println("Enter the playlistName: ");
                                //sc.nextLine();
                                String playlistnames = sc.nextLine();

                                //try {
                                playlistContentService.addSongsByAlbumNameToPlaylistContent(songsArrayList, playlists, albumName, playlistnames);
//                            } catch (Exception e) {
//                                System.out.println(e.getMessage());;
//                            }
                                if (playlistContentService != null) {
                                    System.out.println(albumName + " album added to playlist");
                                }
                                break;
                            case 5:
                                sc.nextLine();
                                System.out.println("Enter the playlistName to display the content: ");
                                String playlistnam = sc.nextLine();
                                //try {
                                ArrayList<Songs> playlistsongs = playlistContentService.getSongsFromplaylistContent(playlistnam, playlists, songsArrayList);
                                //System.out.println(playlistsongs);
                                songService.displaySongs(playlistsongs);
//                            } catch (Exception e) {
//                                System.out.println(e.getMessage());;
//                            }
                                break;
                            case 6:
                                System.out.println("Returning to main");
                                break;
                            case 7:
                                System.exit(0);
                                break;
                        }
                        break;
                    case 3:
                        break;
                    case 4:
                        System.out.println("Exiting..........");
                        System.exit(0);
                        break;
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
