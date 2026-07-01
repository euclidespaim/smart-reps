package com.cyberkidstudios.smartreps.core.di;

import android.content.Context;
import com.cyberkidstudios.smartreps.core.data.local.DatabaseSeeder;
import com.cyberkidstudios.smartreps.core.data.local.dao.ExerciseDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DatabaseModule_ProvideDatabaseSeederFactory implements Factory<DatabaseSeeder> {
  private final Provider<Context> contextProvider;

  private final Provider<ExerciseDao> exerciseDaoProvider;

  private DatabaseModule_ProvideDatabaseSeederFactory(Provider<Context> contextProvider,
      Provider<ExerciseDao> exerciseDaoProvider) {
    this.contextProvider = contextProvider;
    this.exerciseDaoProvider = exerciseDaoProvider;
  }

  @Override
  public DatabaseSeeder get() {
    return provideDatabaseSeeder(contextProvider.get(), exerciseDaoProvider.get());
  }

  public static DatabaseModule_ProvideDatabaseSeederFactory create(
      Provider<Context> contextProvider, Provider<ExerciseDao> exerciseDaoProvider) {
    return new DatabaseModule_ProvideDatabaseSeederFactory(contextProvider, exerciseDaoProvider);
  }

  public static DatabaseSeeder provideDatabaseSeeder(Context context, ExerciseDao exerciseDao) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideDatabaseSeeder(context, exerciseDao));
  }
}
