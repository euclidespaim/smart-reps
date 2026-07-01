package com.cyberkidstudios.smartreps.core.di;

import com.cyberkidstudios.smartreps.core.data.local.AppDatabase;
import com.cyberkidstudios.smartreps.core.data.local.dao.WorkoutDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideWorkoutDaoFactory implements Factory<WorkoutDao> {
  private final Provider<AppDatabase> dbProvider;

  private DatabaseModule_ProvideWorkoutDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public WorkoutDao get() {
    return provideWorkoutDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideWorkoutDaoFactory create(Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideWorkoutDaoFactory(dbProvider);
  }

  public static WorkoutDao provideWorkoutDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideWorkoutDao(db));
  }
}
