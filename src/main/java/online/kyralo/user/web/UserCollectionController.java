package online.kyralo.user.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.kyralo.common.util.JwtUtil;
import online.kyralo.user.domain.UserCollection;
import online.kyralo.user.service.UserCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * \* Created with Intellij IDEA.
 * \* @author: yeqi
 * \* Date: 2019-12-25
 * \* Time: 20:57
 * \* Year: 2019
 * \
 */

@RestController
@RequestMapping("/api/v2/user_collection")
@Api("用户收藏接口")
public class UserCollectionController {

    @Autowired
    private UserCollectionService service;

    public UserCollectionController(UserCollectionService service) {
        this.service = service;
    }

    @GetMapping
    @Secured("ROLE_USER")
    @ApiOperation(value = "查询所有用户收藏", response = UserCollection.class)
    public ResponseEntity<?> getAllByUserId(HttpServletRequest request){
        String userId = JwtUtil.claimGet(request).getAudience();
        return service.listUserCollectionsById(userId);
    }

    @PostMapping
    @Secured("ROLE_USER")
    @ApiOperation(value = "新增用户收藏", response = UserCollection.class)
    public ResponseEntity<?> insert(@RequestBody UserCollection uerCollection, HttpServletRequest request){
        String userId = JwtUtil.claimGet(request).getAudience();
        uerCollection.setUserId(userId);
        return service.insert(uerCollection);
    }

    @DeleteMapping
    @Secured("ROLE_USER")
    @ApiOperation(value = "删除用户收藏", response = UserCollection.class)
    public ResponseEntity<?> delete(@RequestParam("id") Integer id, HttpServletRequest request){
        String userId = JwtUtil.claimGet(request).getAudience();
        return service.delete(id, userId);
    }
}
