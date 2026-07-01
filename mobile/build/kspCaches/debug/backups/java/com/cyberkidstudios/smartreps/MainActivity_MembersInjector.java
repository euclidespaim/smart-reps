package com.cyberkidstudios.smartreps;

import com.cyberkidstudios.smartreps.core.data.local.DatabaseSeeder;
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
  private final Provider<DatabaseSeeder> databaseSeederProvider;

  private MainActivity_MembersInjector(Provider<DatabaseSeeder> databaseSeederProvider) {
    this.databaseSeederProvider = databaseSeederProvider;
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectDatabaseSeeder(instance, databaseSeederProvider.get());
  }

  public static MembersInjector<MainActivity> create(
      Provider<DatabaseSeeder> databaseSeederProvider) {
    return new MainActivity_MembersInjector(databaseSeederProvider);
  }

  @InjectedFieldSignature("com.cyberkidstudios.smartreps.MainActivity.databaseSeeder")
  public static void injectDatabaseSeeder(MainActivity instance, DatabaseSeeder databaseSeeder) {
    instance.databaseSeeder = databaseSeeder;
  }
}
