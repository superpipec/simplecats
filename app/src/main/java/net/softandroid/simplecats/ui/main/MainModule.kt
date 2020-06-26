package net.softandroid.simplecats.ui.main

import net.softandroid.simplecats.base.main.MainErrorHandlerFactory
import net.softandroid.simplecats.base.main.MainNavigator
import net.softandroid.simplecats.base.main.MainViewModel
import net.softandroid.simplecats.base.main.controllers.MainListItemsController
import net.softandroid.teststproject.ui.main.viewproviders.MainRvProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainActivityModule = module {
    scope(named<MainActivity>()) {
        scoped<MainNavigator> { params ->
            val activity: MainActivity = params[0]
            MainActivityNavigator(
                activity,
                getViewModel(activity)
            )
        }

        scoped { params ->
            val activity: MainActivity = params[0]
            MainListItemsController(
                activity.lifecycle,
                getViewModel(activity),
                get { parametersOf(activity) },
                MainRvProvider(activity)
            )
        }
    }

    viewModel {
        MainViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
            MainErrorHandlerFactory()
        )
    }
}