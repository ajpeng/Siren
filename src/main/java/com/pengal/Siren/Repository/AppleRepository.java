package com.pengal.Siren.Repository;

import com.pengal.Siren.Model.ApplePodcast;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppleRepository extends CrudRepository<ApplePodcast, Long> {
    final static String ITUNES_URL = "https://itunes.apple.com/search?kind=podcast&";
}
