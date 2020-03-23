package online.kyralo.user.service.impl;

import online.kyralo.user.dao.UserCollectionMapper;
import online.kyralo.user.domain.UserCollection;
import online.kyralo.user.service.UserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * \* Created with Intellij IDEA.
 * \* @author: yeqi
 * \* Date: 2019-12-25
 * \* Time: 20:53
 * \* Year: 2019
 * \
 */
@Service
public class UserCollectionServiceImpl implements UserCollectionService {

    @Autowired
    private UserCollectionMapper mapper;

    public UserCollectionServiceImpl(UserCollectionMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<?> listUserCollectionsById(String userId) {
        if ("".equals(userId)){
            return ResponseEntity.badRequest().build();
        }else {
            List<UserCollection> userCollections = mapper.listUserCollectionsById(userId);
            if (!userCollections.isEmpty()){
                return ResponseEntity.ok(userCollections);
            }else {
                return ResponseEntity.notFound().build();
            }
        }

    }

    @Override
    public ResponseEntity<?> insert(UserCollection userCollection) {
        if ("".equals(userCollection.getUserId()) || "".equals(userCollection.getVideoId())){
            return ResponseEntity.badRequest().build();
        }else {
            if(mapper.insert(userCollection) == 1){
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    @Override
    public ResponseEntity<?> delete(Integer id, String userId) {
        if (id != null || "".equals(userId)){
            return ResponseEntity.badRequest().build();
        }else {
            if (mapper.delete(id, userId) == 1){
                return ResponseEntity.noContent().build();
            }else {
                return ResponseEntity.notFound().build();
            }
        }
    }
}
