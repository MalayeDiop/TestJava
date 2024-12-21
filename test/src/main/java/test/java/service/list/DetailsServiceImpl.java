package test.java.service.list;

import java.util.List;

import test.java.data.entities.Details;
import test.java.data.repository.interfaces.DetailsRepository;
import test.java.service.interfaces.DetailsService;

public class DetailsServiceImpl implements DetailsService {
    DetailsRepository detailsRepository;

    public DetailsServiceImpl(DetailsRepository detailsRepository) {
		this.detailsRepository = detailsRepository;
	}

    @Override
    public boolean save(Details object) {
        return detailsRepository.insert(object);
    }

    @Override
    public List<Details> show() {
        return detailsRepository.selectAll();
    }

    @Override
    public boolean update(Details object) {
        return detailsRepository.update(object);
    }

    @Override
    public void effacer(Details object) {
        detailsRepository.remove(object);
    }

    @Override
    public Details getById(int id) {
        return detailsRepository.selectById(id);
    }
    
}
