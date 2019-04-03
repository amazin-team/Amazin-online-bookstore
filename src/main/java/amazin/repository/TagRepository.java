package amazin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import amazin.model.Tag;

@RepositoryRestResource(collectionResourceRel = "tags", path = "tags.json")
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findById(String id);
}
