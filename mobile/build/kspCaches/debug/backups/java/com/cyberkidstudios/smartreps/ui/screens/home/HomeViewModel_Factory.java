package com.cyberkidstudios.smartreps.ui.screens.home;

import com.cyberkidstudios.smartreps.core.domain.repository.WorkoutRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<WorkoutRepository> workoutRepositoryProvider;

  private HomeViewModel_Factory(Provider<WorkoutRepository> workoutRepositoryProvider) {
    this.workoutRepositoryProvider = workoutRepositoryProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(workoutRepositoryProvider.get());
  }

  public static HomeViewModel_Factory create(
      Provider<WorkoutRepository> workoutRepositoryProvider) {
    return new HomeViewModel_Factory(workoutRepositoryProvider);
  }

  public static HomeViewModel newInstance(WorkoutRepository workoutRepository) {
    return new HomeViewModel(workoutRepository);
  }
}
