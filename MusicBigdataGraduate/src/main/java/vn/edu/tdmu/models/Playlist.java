package vn.edu.tdmu.models;

import com.fasterxml.jackson.annotation.JsonView;

import vn.edu.tdmu.enums.Country;

import org.hibernate.validator.constraints.NotEmpty;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nguye on 1/26/2018.
 *
 */
@Entity
@Table(name = "playlists")
public class Playlist extends BaseEntity{
    private static final long serialVersionUID = 1L;

    @JsonView(Views.Summary.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAYLIST_ID", unique = true, nullable = false)
    private Integer id;

    @JsonView(Views.Summary.class)
    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    @JsonView(Views.Summary.class)
    @Column(name = "total_views")
    private Integer totalViews = 0;

    @JsonView(Views.Summary.class)
    @Column(name = "week_views")
    private Integer weekViews = 0;

    @JsonView(Views.Summary.class)
    @Column(name = "country")
    private String country = Country.UNKNOWN.getCountry();

    @JsonView(Views.Summary.class)
    @Column(name = "image_url")
    private String imageUrl;

    @JsonView(Views.Summary.class)
    @Column(name = "show_on_home")
    private Boolean onHome;

    @JsonView(Views.Summary.class)
    @ManyToOne
    @JoinColumn(name = "week_id")
    private Week week;

    @JsonView(Views.Summary.class)
    @Column(name = "slide_image_url")
    private String slideImageUrl;

    @JsonView(Views.Summary.class)
    @Column(name = "slide_actived")
    private Boolean slideActived;

    @JsonView(Views.Summary.class)
    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @JsonView(Views.Summary.class)
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @JsonView(Views.Summary.class)
    @Column(name = "type")
    private String type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "playlist")
    private Set<SongPlaylist> songPlaylists = new HashSet<>(0);

    @ManyToMany(mappedBy = "playlists")
    private Set<User> users = new HashSet<>();

    public Playlist() {
    }

    public Playlist(String name, Integer totalViews, Integer weekViews, String country, String imageUrl, Boolean onHome, Week week, String slideImageUrl, Boolean slideActived, Artist artist, Genre genre, String type, Set<SongPlaylist> songPlaylists, Set<User> users) {
        this.name = name;
        this.totalViews = totalViews;
        this.weekViews = weekViews;
        this.country = country;
        this.imageUrl = imageUrl;
        this.onHome = onHome;
        this.week = week;
        this.slideImageUrl = slideImageUrl;
        this.slideActived = slideActived;
        this.artist = artist;
        this.genre = genre;
        this.type = type;
        this.songPlaylists = songPlaylists;
        this.users = users;
    }

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public Set<SongPlaylist> getSongPlaylists() {
        return songPlaylists;
    }

    public void setSongPlaylists(Set<SongPlaylist> songPlaylists) {
        this.songPlaylists = songPlaylists;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(Integer totalViews) {
        this.totalViews = totalViews;
    }

    public Integer getWeekViews() {
        return weekViews;
    }

    public void setWeekViews(Integer weekViews) {
        this.weekViews = weekViews;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getOnHome() {
        return onHome;
    }

    public void setOnHome(Boolean onHome) {
        this.onHome = onHome;
    }

    public String getSlideImageUrl() {
        return slideImageUrl;
    }

    public void setSlideImageUrl(String slideImageUrl) {
        this.slideImageUrl = slideImageUrl;
    }

    public Boolean getSlideActived() {
        return slideActived;
    }

    public void setSlideActived(Boolean slideActived) {
        this.slideActived = slideActived;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void update(String newName, Integer newTotalViews, Integer newWeekViews, String newCountry, Artist newArtist, Genre newGenre,
                       String newType, Boolean newOnHome, Week newWeek, Boolean newSlideActived){
        this.name = newName;
        this.totalViews = newTotalViews;
        this.weekViews = newWeekViews;
        this.country = newCountry;
        this.artist = newArtist;
        this.genre = newGenre;
        this.type = newType;
        this.onHome = newOnHome;
        this.week = newWeek;
        this.slideActived = newSlideActived;
    }

    public void addSongPlaylist(SongPlaylist songPlaylists){
        this.songPlaylists.add(songPlaylists);
    }

    public void incrementViews(){
        this.weekViews += 1;
        this.totalViews += 1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Genre)){
            return false;
        }

        Playlist other = (Playlist) obj;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }


}
