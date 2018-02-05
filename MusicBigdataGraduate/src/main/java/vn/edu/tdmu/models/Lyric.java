package vn.edu.tdmu.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguye on 1/26/2018.
 *
 */

@Entity
@Table(name = "lyrics")
public class Lyric extends BaseEntity{
    private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lyric_id", nullable = false, unique = true)
    private Integer id;

    @NotEmpty
    @Column(name = "title", nullable = false)
    private String title;

    @NotEmpty
    @Column(name = "body_txt", nullable = false)
    private String body;

    @OneToMany(mappedBy = "lyric_id")
    private List<Song> songs = new ArrayList<>();

    public Lyric() {
    }

    public Lyric(String title, String body, List<Song> songs) {
        this.title = title;
        this.body = body;
        this.songs = songs;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void update(String newTitle, String newBody){
        this.title = newTitle;
        this.body = newBody;
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

        Lyric other = (Lyric) obj;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }
}
