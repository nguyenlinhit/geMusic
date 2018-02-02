package vn.edu.tdmu.models;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonView;

import vn.edu.tdmu.enums.Country;

import java.util.HashSet;
import java.util.Set;

/**
 * @author NguyenLinh
 */

 @Entity
 @Table(name = "songs")
public final class Song extends BaseEntity{
    private static final long serialVersionUID = 1L;

    @JsonView(Views.Summary.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SONG_ID", unique = true, nullable = false)
    private Integer id;

    @JsonView(Views.Summary.class)
    @NotEmpty
    @Column(name = "name", nullable = false)
    private String name;

    @JsonView(Views.Summary.class)
    @Column(name = "url", nullable = true)
    private String url;

    @JsonView(Views.Summary.class)
    @Column(name = "total_views")
    private Integer totalView = 0;

    @JsonView(Views.Summary.class)
    @Column(name = "week_views")
    private Integer weekViews;

    @JsonView(Views.Summary.class)
    @Column(name = "country")
    private String country = Country.UNKNOWN.getCountry();

    @JsonView(Views.Summary.class)
    @Column(name = "image_url")
    private String imageUrl = "";

    @JsonView(Views.Summary.class)
    @Column(name = "description")
    private String description;

    @JsonView(Views.Summary.class)
    @Column(name = "show_on_home")
    private Boolean onHome;

    @JsonView(Views.Summary.class)
    @Column(name = "is_published")
    private Boolean isPublished = false;

    @JsonView(Views.ExtendedPublic.class)
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @JsonView(Views.ExtendedPublic.class)
    @ManyToOne
    @JoinColumn(name = "lyric_id")
    private Lyric lyric_id;

    @JsonView(Views.Summary.class)
    @Column(name = "type")
    private String type;

    @JsonView(Views.ExtendedPublic.class)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "song_artist_mappings", joinColumns = {@JoinColumn(name = "song_id")},
    inverseJoinColumns = {@JoinColumn(name = "artist_id")})
    private Set<Artist> artists = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "song", cascade = CascadeType.ALL)
    private Set<SongPlaylist> songPlaylists = new HashSet<>(0);

    public Song() {
    }

    public Song(String name, String url, Integer totalView, Integer weekViews, String country, String imageUrl, String description, Boolean onHome, Boolean isPublished, Genre genre, Lyric lyric_id, String type, Set<Artist> artists, Set<SongPlaylist> songPlaylists) {
        this.name = name;
        this.url = url;
        this.totalView = totalView;
        this.weekViews = weekViews;
        this.country = country;
        this.imageUrl = imageUrl;
        this.description = description;
        this.onHome = onHome;
        this.isPublished = isPublished;
        this.genre = genre;
        this.lyric_id = lyric_id;
        this.type = type;
        this.artists = artists;
        this.songPlaylists = songPlaylists;
    }

    public Integer getTotalView() {
        return totalView;
    }

    public void setTotalView(Integer totalView) {
        this.totalView = totalView;
    }

    public Integer getWeekViews() {
        return weekViews;
    }

    public void setWeekViews(Integer weekViews) {
        this.weekViews = weekViews;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Lyric getLyric_id() {
        return lyric_id;
    }

    public void setLyric_id(Lyric lyric_id) {
        this.lyric_id = lyric_id;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    public Set<SongPlaylist> getSongPlaylists() {
        return songPlaylists;
    }

    public void setSongPlaylists(Set<SongPlaylist> songPlaylists) {
        this.songPlaylists = songPlaylists;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getOnHome() {
        return onHome;
    }

    public void setOnHome(Boolean onHome) {
        this.onHome = onHome;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Artist addArtist(Artist artist) {
        if (this.artists.contains(artist)) {
            return null;
        }
        this.artists.add(artist);
        return artist;
    }

    public void update(String newName, String newUrl, Integer newTotalViews, Integer newWeekViews, String newCountry, String newDescription,
                       Boolean newOnHome, Boolean newIsPublished, Genre newGenre, String newType) {
        this.name = newName;
        this.url = newUrl;
        this.totalView = newTotalViews;
        this.weekViews = newWeekViews;
        this.country = newCountry;
        this.description = newDescription;
        this.onHome = newOnHome;
        this.isPublished = newIsPublished;
        this.genre = newGenre;
        this.type = newType;
    }

    public boolean removeArtist(Artist artist) {
        return this.artists.remove(artist);
    }

    public void incrementViews() {
        this.weekViews += 1;
        this.totalView += 1;
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

        Song other = (Song) obj;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

}