import repository.ODHRepository;

import java.util.List;

public interface MobilityRepository extends ODHRepository {
    String findBySnameEquals(String sname);
    String findBySoriginIn(List<String> sorigin);
}
