package vn.edu.tdmu.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NguyenLinh on 1/26/2018.
 *
 */

@Entity
@Table(name = "genres")
public class Genre extends BaseEntity{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "genre")
    private List<Song> songs = new ArrayList<>();

    @OneToMany(mappedBy = "genre")
    private List<Playlist> playlists = new ArrayList<>();

    public void update(String newName, String newDescription){
        this.name =newName;
        this.description = newDescription;
    }

    public Genre() {
    }

    public Genre(String name, String description, List<Song> songs, List<Playlist> playlists) {
        this.name = name;
        this.description = description;
        this.songs = songs;
        this.playlists = playlists;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

        Genre other = (Genre) obj;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return null;
    }
}
