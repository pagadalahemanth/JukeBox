package com.niit.jukebox.service;

import com.niit.jukebox.model.Songs;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PlayerService {
    AudioInputStream audioInputStream;
    Clip clip;
    Long currentFrame;
    String status;
    int id;
    //String filepath;

    // Method to play the audio
//    public  void play(){
//        clip.start();
//        status = "play";
//    }
    public void playSong(int id) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        //filepath="src/main/resources/"+id+".wav";
        audioInputStream = AudioSystem.getAudioInputStream(new File("src/main/resources/"+id+".wav"));
        clip = AudioSystem.getClip();//LineUnavailableException//UnsupportedAudioFileException,
        clip.open(audioInputStream);
        //this will make my song available to play
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
        status = "playing";
        System.out.println(status);
    }
    // Method to pause the audio
    public void pause(){
        if(status.equals("paused")){
            System.out.println("audio is already paused");
            return;
        }else
        this.currentFrame = this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }
    // Method to resume the audio
    public void resume() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if(status.equals("playing")){
            System.out.println("Audio is already" + "being played");
            return;
        }
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        //this.playSong(id);
    }
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(new File("src/main/resources/"+id+".wav"));
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    // Method to restart the audio
    public void restart() throws IOException, LineUnavailableException,
            UnsupportedAudioFileException
    {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        //this.playSong(id);
    }
    // Method to stop the audio
    public void stop() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }
    public void jump(long c) throws UnsupportedAudioFileException, IOException,
            LineUnavailableException
    {
        if (c > 0 && c < clip.getMicrosecondLength())
        {
            clip.stop();
            clip.close();
            resetAudioStream();
            this.currentFrame = c;
            this.clip.setMicrosecondPosition(c);
            //playSong(id);
        }
        else
            System.out.println("provide between given 0 and 259622312");
    }
    public void gotoChoice(int c)
            throws IOException, LineUnavailableException, UnsupportedAudioFileException
    {
        switch (c)
        {
            case 1:
                pause();
                break;
            case 2:
                resume();
                break;
            case 3:
                restart();
                break;
            case 4:
                stop();
                break;
            case 5:
                System.out.println("Enter time (" + 0 +
                        ", " + clip.getMicrosecondLength() + ")");
                Scanner sc = new Scanner(System.in);
                long c1 = sc.nextLong();
                jump(c1);
                break;
        }
    }
}
