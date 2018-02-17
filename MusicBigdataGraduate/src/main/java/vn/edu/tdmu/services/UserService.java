package vn.edu.tdmu.services;

import vn.edu.tdmu.models.Playlist;
import vn.edu.tdmu.models.User;

/**
 * Created by NguyenLinh on 2/1/2018.
 *
 */
public interface UserService extends BaseService<User, Integer>{
    User findByUsername(String username);

    User update(User updatedEntry);

    User updateAvatar(Integer id, String newImageUrl);

    User updatePassword(Integer id, String newPassword);

    boolean isUsernameUnique(Integer id, String username);

    boolean isEmailUnique(Integer id, String email);

    boolean addPlaylistToUser(String username, Playlist playlist);
}
