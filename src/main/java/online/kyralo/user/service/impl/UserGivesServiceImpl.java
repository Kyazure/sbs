package online.kyralo.user.service.impl;

import online.kyralo.user.dao.UserGivesMapper;
import online.kyralo.user.domain.GivesForComments;
import online.kyralo.user.domain.GivesForVideo;
import online.kyralo.user.domain.UserGives;
import online.kyralo.user.service.UserGivesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * \* Created with Intellij IDEA.
 * \* @author: yeqi
 * \* Date: 2019-12-30
 * \* Time: 16:40
 * \* Year: 2019
 * \
 */

@Service
public class UserGivesServiceImpl implements UserGivesService {

    @Autowired
    private UserGivesMapper mapper;

    public UserGivesServiceImpl(UserGivesMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<?> countGives(UserGives userGives) {
         if (userGives.getId() == null || userGives.getType() == null ){
             return ResponseEntity.badRequest().body("必要参数不能为空!");
         }else {
             int gives = mapper.countGives(userGives);
             return ResponseEntity.ok(gives);
         }
    }

    @Override
    public ResponseEntity<?> listsUserGives(String userId) {
        if (userId == null){
            return ResponseEntity.badRequest().body("必要参数不能为空!");
        }else {
            List<GivesForComments> comments = mapper.listsUserGivesForComments(userId);
            List<GivesForVideo> videos = mapper.listsUserGivesForVideo(userId);
            Map<String, Object> map = new HashMap<>(2);
            map.put("comments", comments);
            map.put("videos", videos);

            return ResponseEntity.ok(map);
        }
    }

    @Override
    public ResponseEntity<?> insert(UserGives userGives) {
        if (userGives.getUserId() == null || userGives.getType() == null || userGives.getTargetId() == null ){
            return ResponseEntity.badRequest().body("必要参数不能为空!");
        }else {
            if (mapper.insert(userGives) == 1){
                return ResponseEntity.status(HttpStatus.CREATED).build();
            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

    @Override
    public ResponseEntity<?> delete(UserGives userGives) {
        if (userGives.getTargetId() == null || userGives.getType() == null || userGives.getUserId() == null){
            return ResponseEntity.badRequest().build();
        }else {
            if (mapper.delete(userGives) >= 1){
                return ResponseEntity.noContent().build();
            }else {
                return ResponseEntity.notFound().build();
            }
        }
    }
}
