package vn.edu.tdmu.enums;

/**
 * @author Created by NguyenLinh on 01/29/2018
 */

public enum PlaylistType{
    OFFICIAL("Official"), USER("User"),COLLECTION("COllection"),TOP("Top"),HOT("Hot");

    String playlistType;

    PlaylistType(String playlistType){
        this.playlistType = playlistType;
    }

    public String getPlaylistType(){
        return this.playlistType;
    }
}