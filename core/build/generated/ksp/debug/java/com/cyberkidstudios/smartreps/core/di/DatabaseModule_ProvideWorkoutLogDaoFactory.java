package com.cyberkidstudios.smartreps.core.di;

import com.cyberkidstudios.smartreps.core.data.local.AppDatabase;
import com.cyberkidstudios.smartreps.core.data.local.dao.WorkoutLogDao;
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
public final class DatabaseModule_ProvideWorkoutLogDaoFactory implements Factory<WorkoutLogDao> {
  private final Provider<AppDatabase> dbProvider;

  private DatabaseModule_ProvideWorkoutLogDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public WorkoutLogDao get() {
    return provideWorkoutLogDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideWorkoutLogDaoFactory create(
      Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideWorkoutLogDaoFactory(dbProvider);
  }

  public static WorkoutLogDao provideWorkoutLogDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideWorkoutLogDao(db));
  }
}
