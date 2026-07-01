package com.cyberkidstudios.smartreps.wear;

import com.cyberkidstudios.smartreps.core.domain.repository.WorkoutRepository;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;

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
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<WorkoutRepository> workoutRepositoryProvider;

  private MainActivity_MembersInjector(Provider<WorkoutRepository> workoutRepositoryProvider) {
    this.workoutRepositoryProvider = workoutRepositoryProvider;
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectWorkoutRepository(instance, workoutRepositoryProvider.get());
  }

  public static MembersInjector<MainActivity> create(
      Provider<WorkoutRepository> workoutRepositoryProvider) {
    return new MainActivity_MembersInjector(workoutRepositoryProvider);
  }

  @InjectedFieldSignature("com.cyberkidstudios.smartreps.wear.MainActivity.workoutRepository")
  public static void injectWorkoutRepository(MainActivity instance,
      WorkoutRepository workoutRepository) {
    instance.workoutRepository = workoutRepository;
  }
}
