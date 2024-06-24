package org.company.ticketonline2.dao.place;

import org.company.ticketonline2.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository  extends JpaRepository<Place,Long> {
}
