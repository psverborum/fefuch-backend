package com.verborum.fefuchbackend.Repository;

import org.springframework.data.repository.CrudRepository;
import com.verborum.fefuchbackend.Entity.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

}
