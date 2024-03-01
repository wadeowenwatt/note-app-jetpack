package wade.owen.watt.note_app.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import wade.owen.watt.note_app.data.repo_impl.AuthRepositoryImpl
import wade.owen.watt.note_app.data.repo_impl.FirestoreRepositoryImpl
import wade.owen.watt.note_app.domain.repository.AuthRepository
import wade.owen.watt.note_app.domain.repository.FirestoreRepository

@Module
@InstallIn(ViewModelComponent::class)
class FirebaseModule {
    @Provides
    fun provideAuthRepository(): AuthRepository {
        return AuthRepositoryImpl()
    }

    @Provides
    fun provideFirestoreRepository(): FirestoreRepository {
        return FirestoreRepositoryImpl()
    }
}