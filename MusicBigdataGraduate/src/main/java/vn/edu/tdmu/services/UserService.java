package vn.edu.tdmu.services;

import vn.edu.tdmu.models.Playlist;
import vn.edu.tdmu.models.User;

/**
 * Created by NguyenLinh on 2/1/2018.
 *
 */
public interface UserService extends BaseService<User, Integer>{
    public User findByUsername(String username);

    public User update(User updatedEntry);

    public User updateAvatar(Integer id, String newImageUrl);

    public User updatePassword(Integer id, String newPassword);

    public boolean isUsernameUnique(Integer id, String username);

    public boolean isEmailUnique(Integer id, String email);

    public boolean addPlaylistToUser(String username, Playlist playlist);
}
