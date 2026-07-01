package com.cyberkidstudios.smartreps.ui.screens.workout;

import androidx.lifecycle.SavedStateHandle;
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
public final class ActiveWorkoutViewModel_Factory implements Factory<ActiveWorkoutViewModel> {
  private final Provider<WorkoutRepository> workoutRepositoryProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private ActiveWorkoutViewModel_Factory(Provider<WorkoutRepository> workoutRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.workoutRepositoryProvider = workoutRepositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public ActiveWorkoutViewModel get() {
    return newInstance(workoutRepositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static ActiveWorkoutViewModel_Factory create(
      Provider<WorkoutRepository> workoutRepositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new ActiveWorkoutViewModel_Factory(workoutRepositoryProvider, savedStateHandleProvider);
  }

  public static ActiveWorkoutViewModel newInstance(WorkoutRepository workoutRepository,
      SavedStateHandle savedStateHandle) {
    return new ActiveWorkoutViewModel(workoutRepository, savedStateHandle);
  }
}
