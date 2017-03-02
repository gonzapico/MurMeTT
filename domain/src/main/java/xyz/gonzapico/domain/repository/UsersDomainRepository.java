package xyz.gonzapico.domain.repository;

import java.util.List;
import rx.Observable;
import xyz.gonzapico.domain.model.RequestAPIModelDomain;
import xyz.gonzapico.domain.model.UserModelDomain;

/**
 * Created by gfernandez on 25/02/17.
 */

public interface UsersDomainRepository {

  Observable<List<UserModelDomain>> getUsers(RequestAPIModelDomain requestAPIModelDomain);
}
