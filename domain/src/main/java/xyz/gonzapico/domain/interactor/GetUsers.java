package xyz.gonzapico.domain.interactor;

import javax.inject.Inject;
import rx.Observable;
import xyz.gonzapico.domain.repository.UsersRepository;
import xyz.gonzapico.domain.executor.PostExecutionThread;
import xyz.gonzapico.domain.executor.ThreadExecutor;

/**
 * Created by gfernandez on 25/02/17.
 */

public class GetUsers extends BaseUseCase {

  private final UsersRepository mRepository;

  @Inject public GetUsers(UsersRepository usersRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    this.mRepository = usersRepository;
  }

  @Override public Observable buildUseCaseObservable() {
    return this.mRepository.getUsers();
  }
}
