package com.cyberkidstudios.smartreps.core.di;

import com.cyberkidstudios.smartreps.core.data.local.dao.ExerciseDao;
import com.cyberkidstudios.smartreps.core.data.local.dao.WorkoutDao;
import com.cyberkidstudios.smartreps.core.data.local.dao.WorkoutLogDao;
import com.cyberkidstudios.smartreps.core.domain.repository.WorkoutRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class RepositoryModule_ProvideWorkoutRepositoryFactory implements Factory<WorkoutRepository> {
  private final Provider<ExerciseDao> exerciseDaoProvider;

  private final Provider<WorkoutDao> workoutDaoProvider;

  private final Provider<WorkoutLogDao> workoutLogDaoProvider;

  private RepositoryModule_ProvideWorkoutRepositoryFactory(
      Provider<ExerciseDao> exerciseDaoProvider, Provider<WorkoutDao> workoutDaoProvider,
      Provider<WorkoutLogDao> workoutLogDaoProvider) {
    this.exerciseDaoProvider = exerciseDaoProvider;
    this.workoutDaoProvider = workoutDaoProvider;
    this.workoutLogDaoProvider = workoutLogDaoProvider;
  }

  @Override
  public WorkoutRepository get() {
    return provideWorkoutRepository(exerciseDaoProvider.get(), workoutDaoProvider.get(), workoutLogDaoProvider.get());
  }

  public static RepositoryModule_ProvideWorkoutRepositoryFactory create(
      Provider<ExerciseDao> exerciseDaoProvider, Provider<WorkoutDao> workoutDaoProvider,
      Provider<WorkoutLogDao> workoutLogDaoProvider) {
    return new RepositoryModule_ProvideWorkoutRepositoryFactory(exerciseDaoProvider, workoutDaoProvider, workoutLogDaoProvider);
  }

  public static WorkoutRepository provideWorkoutRepository(ExerciseDao exerciseDao,
      WorkoutDao workoutDao, WorkoutLogDao workoutLogDao) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideWorkoutRepository(exerciseDao, workoutDao, workoutLogDao));
  }
}
