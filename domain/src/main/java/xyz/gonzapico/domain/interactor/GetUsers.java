package xyz.gonzapico.domain.interactor;

import javax.inject.Inject;
import rx.Observable;
import xyz.gonzapico.domain.executor.PostExecutionThread;
import xyz.gonzapico.domain.executor.ThreadExecutor;
import xyz.gonzapico.domain.model.RequestAPIModelDomain;
import xyz.gonzapico.domain.repository.UsersDomainRepository;

/**
 * Created by gfernandez on 25/02/17.
 */

public class GetUsers extends BaseUseCase {

  private final UsersDomainRepository mRepository;
  private RequestAPIModelDomain requestParams;

  @Inject public GetUsers(UsersDomainRepository usersDomainRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.mRepository = usersDomainRepository;
  }

  public void setParameters(RequestAPIModelDomain requestParameters) {
    this.requestParams = requestParameters;
  }

  @Override public Observable buildUseCaseObservable() {
    return this.mRepository.getUsers(this.requestParams);
  }
}
