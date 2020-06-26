package net.softandroid.simplecats.ui.favourites

import net.softandroid.simplecats.base.favourites.FavouritesErrorHandlerFactory
import net.softandroid.simplecats.base.favourites.FavouritesViewModel
import net.softandroid.simplecats.base.favourites.FavsNavigator
import net.softandroid.simplecats.base.favourites.controllers.FavsListItemsController
import net.softandroid.teststproject.ui.main.viewproviders.FavsRvProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val favouritesActivityModule = module {
    scope(named<FavouritesActivity>()) {
        scoped<FavsNavigator> { params ->
            val activity: FavouritesActivity = params[0]
            FavouritesActivityNavigator(
                activity,
                getViewModel(activity)
            )
        }

        scoped { params ->
            val activity: FavouritesActivity = params[0]
            FavsListItemsController(
                activity.lifecycle,
                activity,
                getViewModel(activity),
                get { parametersOf(activity) },
                FavsRvProvider(activity)
            )
        }
    }

    viewModel {
        FavouritesViewModel(
            get(),
            get(),
            get(),
            FavouritesErrorHandlerFactory()
        )
    }
}