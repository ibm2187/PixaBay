package ibm2187.dev.pixabay.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ibm2187.dev.pixabay.model.interactors.DiscoveryInteractor
import ibm2187.dev.pixabay.model.interactors.SearchInteractor
import ibm2187.dev.pixabay.model.network.api.PixabayApi

@Module
@InstallIn(ViewModelComponent::class)
object InteractionModule {

    @ViewModelScoped
    @Provides
    fun getSearchInteraction(
        pixabayApi: PixabayApi
    ): SearchInteractor {
        return SearchInteractor(pixabayApi)
    }

    @ViewModelScoped
    @Provides
    fun getDiscoverInteraction(
        pixabayApi: PixabayApi
    ): DiscoveryInteractor {
        return DiscoveryInteractor(pixabayApi)
    }

}