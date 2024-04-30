package org.geniuus.practice.Repository;

import org.geniuus.practice.domain.Post;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
    List<Post> findAll(Specification<Post> spec);

    // 내 블로그이거나, 다른 사람 블로그면 visibility가 true 인것만 검색 (전체 공개)
    static Specification<Post> findVisiblePostsById(Long blogId) {
        return ((root, query, builder) ->
                builder.or(
                    builder.equal(root.get("visibility"), true),
                    builder.equal(root.get("blog").get("id"), blogId))
        );
    }
}
