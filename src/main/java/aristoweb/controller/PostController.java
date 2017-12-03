package aristoweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import aristoweb.dao.Employee;
import aristoweb.dao.EmployeeDAO;
import aristoweb.lib.BlogObject;
import aristoweb.model.Post;
import aristoweb.repository.PostRepository;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> postsList(Model model){
        model.addAttribute("posts", postRepository.findAll());
       // model.addAttribute("orders", orderRepository.findAll());
        //return "posts";
        List<Post> aPosts = postRepository.findAll();
    	if (aPosts.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Post>>(aPosts, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public Response getEmployee() {
        EmployeeDAO dao = new EmployeeDAO();
        List<Employee> employees = dao.getEmployees();
        //return Response.ok(employees, MediaType.APPLICATION_JSON_TYPE).build();

        return Response.ok().entity(new GenericEntity<List<Employee>>(employees) {}).build();
        //return "Count is : " + employees.size();
    }
    
    @RequestMapping(value="/createfixpost", method = RequestMethod.POST)
    @ResponseBody
    public String saveFixPost()
    //@RequestParam String firstName, @RequestParam String lastName, @RequestParam(value="productIds[]") Long[] productIds)
    {

        Post _post = new Post();
        _post.setArticleContent("this is article");
        _post.setArticleTitle("atricle");
        _post.setPostCommentCount(4);
        _post.setPostContent("ththththththththththththth");
        _post.setPostDate(new Date());
        _post.setPostHasArticle((byte)1);
        _post.setPostLikeCount(500);
        _post.setPostStatus("New");
        _post.setPostType("Info");
        
        postRepository.save(_post);
        return "New post fix created with id: " + _post.getPost_ID().toString();
    }
    
/*    @RequestMapping(value="/createpost", method = RequestMethod.POST)
    @ResponseBody
    public String savePost(
    @RequestParam String articleContent, @RequestParam String articleTitle,@RequestParam String postContent,@RequestParam String postType,
    @RequestParam Date postDate,@RequestParam String postStatus)
    {

        Post _post = new Post();
        _post.setArticleContent(articleContent);
        _post.setArticleTitle(articleTitle);
        _post.setPostCommentCount(4);
        _post.setPostContent(postContent);
        _post.setPostDate(postDate);
        _post.setPostHasArticle((byte)1);
        _post.setPostLikeCount(500);
        _post.setPostStatus(postStatus);
        _post.setPostType(postType);
        
        postRepository.save(_post);
        return "New post created with id: " + _post.getPost_ID().toString();
    }*/
    
    @RequestMapping(value="/createpost", method = RequestMethod.POST)
    @ResponseBody
    public String savePost( @RequestBody BlogObject blogObject )
    {
         Post _post = new Post();
        _post.setArticleContent(blogObject.articleContent);
        _post.setArticleTitle(blogObject.articleTitle);
        _post.setPostCommentCount(4);
        _post.setPostContent(blogObject.postContent);
        _post.setPostDate(blogObject.postDate);
        _post.setPostHasArticle((byte)1);
        _post.setPostLikeCount(500);
        _post.setPostStatus(blogObject.postStatus);
        _post.setPostType(blogObject.postType);
        
        postRepository.save(_post);
        return "New post created with id: " + _post.getPost_ID().toString();
    }
    
    @RequestMapping(value = "/removepost", method = RequestMethod.POST)
    @ResponseBody
    public String removePost(@RequestParam Long Id){
        postRepository.delete(Id);
        return Id.toString();
    }
}