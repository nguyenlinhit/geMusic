package vn.edu.tdmu.models;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by nguye on 1/26/2018.
 *
 */

@Entity
@Table(name = "song_playlist_mappings")
@AssociationOverrides({@AssociationOverride(name = "song", joinColumns = @JoinColumn(name = "SONG_ID")),
@AssociationOverride(name = "playlist", joinColumns = @JoinColumn(name = "PLAYLIST_ID"))})
public class SongPlaylist implements Serializable{
    private static final long serialVersionUID = 1L;

    @JsonView(Views.Summary.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(Views.Summary.class)
    @ManyToOne
    private Song song;

    @JsonView(Views.Summary.class)
    @ManyToOne
    private Playlist playlist;

    @JsonView(Views.Summary.class)
    @Column(name = "order_index")
    private Integer order;

    public SongPlaylist() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public Integer getIndex() {
        return order;
    }

    public void setIndex(Integer order) {
        this.order = order;
    }

    public void updateOrder(Integer order){
        this.order = order;
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

        SongPlaylist other = (SongPlaylist) obj;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }
}
