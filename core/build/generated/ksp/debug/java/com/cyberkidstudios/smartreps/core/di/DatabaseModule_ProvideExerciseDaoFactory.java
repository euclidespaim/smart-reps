package com.cyberkidstudios.smartreps.core.di;

import com.cyberkidstudios.smartreps.core.data.local.AppDatabase;
import com.cyberkidstudios.smartreps.core.data.local.dao.ExerciseDao;
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
public final class DatabaseModule_ProvideExerciseDaoFactory implements Factory<ExerciseDao> {
  private final Provider<AppDatabase> dbProvider;

  private DatabaseModule_ProvideExerciseDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ExerciseDao get() {
    return provideExerciseDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideExerciseDaoFactory create(Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideExerciseDaoFactory(dbProvider);
  }

  public static ExerciseDao provideExerciseDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideExerciseDao(db));
  }
}
