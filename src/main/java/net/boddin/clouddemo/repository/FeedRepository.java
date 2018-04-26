package net.boddin.clouddemo.repository;

import net.boddin.clouddemo.entity.Feed;
import net.boddin.clouddemo.entity.SmallFeed;
import net.boddin.clouddemo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface FeedRepository extends CrudRepository<Feed, Integer> {
    @Query("select c from Feed c join c.users us where us.id = ?1")
    Collection<SmallFeed> findAllByUsersContains(@Param("userId") int userId);
}
