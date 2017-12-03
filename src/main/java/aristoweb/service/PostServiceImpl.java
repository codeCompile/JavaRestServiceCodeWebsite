package aristoweb.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import aristoweb.model.Post;

@Service("PostService")
@Transactional
public class PostServiceImpl implements PostService
{

	public Post findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Post findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}


}
