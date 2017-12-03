package aristoweb.service;

import aristoweb.model.Post;

public interface PostService {
	
	Post findById(Long id);

	Post findByName(String name);

}
