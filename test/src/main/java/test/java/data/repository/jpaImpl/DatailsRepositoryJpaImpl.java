package test.java.data.repository.jpaImpl;

import test.java.core.repo.RepositoryJPA;
import test.java.data.entities.Details;
import test.java.data.repository.interfaces.DetailsRepository;

public class DatailsRepositoryJpaImpl extends RepositoryJPA<Details> implements DetailsRepository {
    
    public DatailsRepositoryJpaImpl(Class<Details> type) {
        super(type);
    }
}
